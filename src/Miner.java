import processing.core.PImage;

import java.util.List;
import java.util.Optional;

public abstract class Miner extends Movable{
    public static final String MINER_KEY = "miner";
    public static final int MINER_NUM_PROPERTIES = 7;
    public static final int MINER_ID = 1;
    public static final int MINER_COL = 2;
    public static final int MINER_ROW = 3;
    public static final int MINER_LIMIT = 4;
    public static final int MINER_ACTION_PERIOD = 5;
    public static final int MINER_ANIMATION_PERIOD = 6;
    protected int resourceLimit;
        protected int resourceCount;

        public Miner(EntityKind kind, String id, Point position, List<PImage> images, int resourceLimit, int resourceCount, int actionPeriod, int animationPeriod) {
            super(kind, id, position, images, actionPeriod, animationPeriod);
            this.resourceLimit = resourceLimit;
            this.resourceCount = resourceCount;
        }

    public static boolean parseMiner(String[] properties, WorldModel world,
                                     ImageStore imageStore) {
        if (properties.length == MINER_NUM_PROPERTIES) {
            Point pt = new Point(Integer.parseInt(properties[MINER_COL]),
                    Integer.parseInt(properties[MINER_ROW]));
            Entity entity = new NotFullMiner(properties[MINER_ID],
                    Integer.parseInt(properties[MINER_LIMIT]),
                    pt,
                    Integer.parseInt(properties[MINER_ACTION_PERIOD]),
                    Integer.parseInt(properties[MINER_ANIMATION_PERIOD]),
                    imageStore.getImageList(MINER_KEY));
            world.tryAddEntity(entity);
        }

        return properties.length == MINER_NUM_PROPERTIES;
    }


    public boolean move(WorldModel world, Entity target, EventScheduler scheduler) {
        if (Point.adjacent(this.position, target.getPosition())) {
            if (target.getKind() == EntityKind.BLACKSMITH) {
                return true;
            }
            else {
                this.resourceCount += 1;
                world.removeEntity(target);
                scheduler.unscheduleAllEvents(target);

                return true;
            }
        } else {
            Point nextPos = nextPosition(world, target.getPosition());

            if (!this.position.equals(nextPos)) {
                Optional<Entity> occupant = world.getOccupant(nextPos);
                if (occupant.isPresent()) {
                    scheduler.unscheduleAllEvents(occupant.get());
                }

                world.moveEntity(this, nextPos);
            }
            return false;
        }
    }
    public Point nextPosition(WorldModel world,
                              Point destPos) {
        int horiz = Integer.signum(destPos.x - this.position.x);
        Point newPos = new Point(this.position.x + horiz,
                this.position.y);

        Optional<Entity> occupant = world.getOccupant(newPos);

        if (horiz == 0 ||
                (occupant.isPresent() && !(occupant.get().getKind() == EntityKind.ORE))) {
            int vert = Integer.signum(destPos.y - this.position.y);
            newPos = new Point(this.position.x, this.position.y + vert);
            occupant = world.getOccupant(newPos);

            if (vert == 0 ||
                    (occupant.isPresent() && !(occupant.get().getKind() == EntityKind.ORE))) {
                newPos = this.position;
            }
        }

        return newPos;
    }

    public boolean transform(WorldModel world, EventScheduler scheduler, ImageStore imageStore) {
        if (this.getKind() == EntityKind.MINER_NOT_FULL) {
            // switch to full miner
            if (resourceCount >= resourceLimit) {
                Miner miner = new FullMiner(id, position, images, resourceLimit, resourceCount, actionPeriod, animationPeriod);

                world.removeEntity(this);
                scheduler.unscheduleAllEvents(this);

                world.addEntity(miner);
                miner.scheduleActions(scheduler, world, imageStore);

                return true;
            }
            return false;
        }
        else {
            // switch to not full miner

                Miner miner = new NotFullMiner(id, resourceLimit, position, actionPeriod, animationPeriod, images);

                world.removeEntity(this);
                scheduler.unscheduleAllEvents(this);

                world.addEntity(miner);
                miner.scheduleActions(scheduler, world, imageStore);

                return true;

        }
    }
    public void scheduleActions(EventScheduler scheduler, WorldModel world, ImageStore imageStore) {
        {
            scheduler.scheduleEvent(this,
                    new Activity(this, world, imageStore, actionPeriod),
                    this.getAnimationPeriod());

            scheduler.scheduleEvent(this, new Animation(this, 0),
                    this.getAnimationPeriod());
        }


    }




}
