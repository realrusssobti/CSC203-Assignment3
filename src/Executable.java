import processing.core.PImage;

import java.util.List;

public abstract class Executable extends Entity {
    protected int actionPeriod;

    public Executable(EntityKind kind, String id, Point position, List<PImage> images, int actionPeriod) {
        super(kind, id, position, images);
        this.actionPeriod = actionPeriod;
    }

    public int getActionPeriod() {
        return actionPeriod;
    }

    public void scheduleActions(EventScheduler scheduler, WorldModel world, ImageStore imageStore) {
        scheduler.scheduleEvent(this,
                new Activity(this, world, imageStore, 0),
                this.getActionPeriod());
    }

    public abstract void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler);
}
