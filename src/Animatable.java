import processing.core.PImage;

import java.util.List;

public abstract class Animatable extends Executable {
    protected int animationPeriod;

    public Animatable(EntityKind kind, String id, Point position, List<PImage> images, int actionPeriod, int animationPeriod) {
        super(kind, id, position, images, actionPeriod);
        this.animationPeriod = animationPeriod;
    }

    public int getAnimationPeriod() {
        return animationPeriod;
    }

    public Animation createAnimationAction(int repeatCount) {
        return new Animation(this, repeatCount);
    }

    public abstract void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler);

}
