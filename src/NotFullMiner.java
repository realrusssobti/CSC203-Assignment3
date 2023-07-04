
import processing.core.PImage;

import java.util.List;
import java.util.Optional;

public class NotFullMiner extends Miner {

        public NotFullMiner(String id, int resourceLimit, Point position, int actionPeriod, int animationPeriod, List<PImage> images) {
            super(EntityKind.MINER_NOT_FULL,id, position, images, resourceLimit, 0, actionPeriod, animationPeriod);
        }


        public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {

            Optional<Entity> notFullTarget = world.findNearest(this.position, EntityKind.ORE);

            if (!notFullTarget.isPresent() ||
                    !move(world, notFullTarget.get(), scheduler) ||
                    !transform(world, scheduler, imageStore)) {
                scheduler.scheduleEvent(this,
                        new Activity(this, world, imageStore, 0),
                        this.actionPeriod);
            }
        }


}