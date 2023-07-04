//import processing.core.PImage;
//
//import java.util.List;
//
//public class Quake implements AnimatableEntity {
//    private static final int QUAKE_ANIMATION_REPEAT_COUNT = 10;
//    private final String id;
//    private final List<PImage> images;
//    private final int actionPeriod;
//    private final int animationPeriod;
//    private Point position;
//    private int imageIndex;
//
//    public Quake(String id, Point position, List<PImage> images, int actionPeriod, int animationPeriod) {
//        this.id = id;
//        this.position = position;
//        this.images = images;
//        this.imageIndex = 0;
//        this.actionPeriod = actionPeriod;
//        this.animationPeriod = animationPeriod;
//    }
//
//
//    public int getAnimationPeriod() {
//        return animationPeriod;
//    }
//
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
//    }
//
//    public PImage getCurrentImage() {
//        return images.get(imageIndex);
//    }
//
//    public EntityKind getKind() {
//        return EntityKind.QUAKE;
//    }
//
//    public int getActionPeriod() {
//        return actionPeriod;
//    }
//
//    public void scheduleActions(EventScheduler scheduler, WorldModel world, ImageStore imageStore) {
//        scheduler.scheduleEvent(this, createActivityAction(world, imageStore), this.actionPeriod);
//        scheduler.scheduleEvent(this, createAnimationAction(QUAKE_ANIMATION_REPEAT_COUNT), this.getAnimationPeriod());
//    }
//
//    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
//        scheduler.unscheduleAllEvents(this);
//        world.removeEntity(this);
//    }
//
//    public Animation createAnimationAction(int repeatCount) {
//        //todo
//        return new Animation(this, repeatCount);
//
//    }
//
//    public ActionInterface createActivityAction(WorldModel world, ImageStore imageStore) {
//        //todo
//        return new Activity(this, world, imageStore, actionPeriod);
//    }
//
//}

import processing.core.PImage;

import java.util.List;

public class Quake extends Animatable {
    private static final int QUAKE_ANIMATION_REPEAT_COUNT = 10;

    public Quake(String id, Point position, List<PImage> images, int actionPeriod, int animationPeriod) {
        super(EntityKind.QUAKE, id, position, images, actionPeriod, animationPeriod);
    }

    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
        scheduler.unscheduleAllEvents(this);
        world.removeEntity(this);
    }


    public void scheduleActions(EventScheduler scheduler, WorldModel world, ImageStore imageStore) {
        scheduler.scheduleEvent(this, new Activity(this,world,imageStore,actionPeriod), this.actionPeriod);
        scheduler.scheduleEvent(this, new Animation(this,QUAKE_ANIMATION_REPEAT_COUNT), this.getAnimationPeriod());
    }
}