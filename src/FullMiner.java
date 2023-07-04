import processing.core.PImage;

import java.util.List;
import java.util.Optional;

//public class FullMiner implements MovableEntity {
//    private final String id;
//    private final List<PImage> images;
//    private final int resourceLimit;
//    private final int resourceCount;
//    private final int actionPeriod;
//    private final int animationPeriod;
//    private Point position;
//    private int imageIndex;
//
//    public FullMiner(String id, Point position, List<PImage> images, int resourceLimit, int resourceCount, int actionPeriod, int animationPeriod) {
//        this.id = id;
//        this.position = position;
//        this.images = images;
//        this.imageIndex = 0;
//        this.resourceLimit = resourceLimit;
//        this.resourceCount = resourceCount;
//        this.actionPeriod = actionPeriod;
//        this.animationPeriod = animationPeriod;
//    }
//
//    public int getActionPeriod() {
//        return 0;
//    }
//
//    public void scheduleActions(EventScheduler scheduler, WorldModel world, ImageStore imageStore) {
//        {
//            scheduler.scheduleEvent(this,
//                    new Activity(this, world, imageStore, actionPeriod),
//                    this.getAnimationPeriod());
//
//            scheduler.scheduleEvent(this, Animation.createAnimationAction(this, 0),
//                    this.getAnimationPeriod());
//        }
//
//
//    }
//
//    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
//        Optional<EntityInterface> fullTarget = world.findNearest(this.position, EntityKind.BLACKSMITH);
//
//        if (fullTarget.isPresent() &&
//                move(world, fullTarget.get(), scheduler)) {
//            this.transformFull(world, scheduler, imageStore);
//        } else {
//            scheduler.scheduleEvent(this,
//                    new Activity(this, world, imageStore, 0),
//                    this.actionPeriod);
//        }
//    }
//
//
//    public void transformFull(WorldModel world,
//                              EventScheduler scheduler, ImageStore imageStore) {
//        ExecutableEntity miner = new NotFullMiner(id, resourceLimit, position, actionPeriod, animationPeriod, images);
//
//        world.removeEntity(this);
//        scheduler.unscheduleAllEvents(this);
//
//        world.addEntity(miner);
//        miner.scheduleActions(scheduler, world, imageStore);
//    }
//
//    public boolean move(WorldModel world, EntityInterface target, EventScheduler scheduler) {
//        {
//            if (Point.adjacent(this.position, target.getPosition())) {
//                return true;
//            } else {
//                Point nextPos = nextPosition(world, target.getPosition());
//
//                if (!this.position.equals(nextPos)) {
//                    Optional<EntityInterface> occupant = world.getOccupant(nextPos);
//                    if (occupant.isPresent()) {
//                        scheduler.unscheduleAllEvents(occupant.get());
//                    }
//
//                    world.moveEntity(this, nextPos);
//                }
//                return false;
//            }
//        }
//    }
//
//    public Point nextPosition(WorldModel world,
//                              Point destPos) {
//        int horiz = Integer.signum(destPos.x - this.position.x);
//        Point newPos = new Point(this.position.x + horiz,
//                this.position.y);
//
//        if (horiz == 0 || world.isOccupied(newPos)) {
//            int vert = Integer.signum(destPos.y - this.position.y);
//            newPos = new Point(this.position.x,
//                    this.position.y + vert);
//
//            if (vert == 0 || world.isOccupied(newPos)) {
//                newPos = this.position;
//            }
//        }
//
//        return newPos;
//    }
//
//    public int getAnimationPeriod() {
//        return animationPeriod;
//    }
//
//    public Animation createAnimationAction(int repeatCount) {
//        return new Animation(this, repeatCount);
//    }
//
//    public Point getPosition() {
//        return position;
//    }
//
//    public void setPosition(Point position) {
//        this.position = position;
//
//    }
//
//    public List<PImage> getImages() {
//        return images;
//    }
//
//    public int getImageIndex() {
//        return imageIndex;
//    }
//
//    public void nextImage() {
//        imageIndex = (imageIndex + 1) % images.size();
//
//    }
//
//    public PImage getCurrentImage() {
//        return images.get(imageIndex);
//    }
//
//    public EntityKind getKind() {
//        return EntityKind.MINER_FULL;
//    }


//}
public class FullMiner extends Miner{
    public FullMiner(String id, Point position, List<PImage> images, int resourceLimit, int resourceCount, int actionPeriod, int animationPeriod) {
        super(EntityKind.MINER_FULL,id, position, images, resourceLimit, resourceCount, actionPeriod, animationPeriod);
    }

    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
        Optional<Entity> fullTarget = world.findNearest(this.position, EntityKind.BLACKSMITH);

        if (fullTarget.isPresent() &&
                move(world, fullTarget.get(), scheduler)) {
            this.transform(world, scheduler, imageStore);
        } else {
            scheduler.scheduleEvent(this,
                    new Activity(this, world, imageStore, 0),
                    this.actionPeriod);
        }
    }

//    public void transformFull(WorldModel world,
//                              EventScheduler scheduler, ImageStore imageStore) {
//        ExecutableEntity miner = new NotFullMiner(id, resourceLimit, position, actionPeriod, animationPeriod, images);
//
//        world.removeEntity(this);
//        scheduler.unscheduleAllEvents(this);
//
//        world.addEntity(miner);
//        miner.scheduleActions(scheduler, world, imageStore);
//    }
//


}

