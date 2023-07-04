//public class Activity implements ActionInterface {
//    // refactor Action with ActionInterface
//
//    private final ExecutableEntity entity;
//    private final WorldModel world;
//    private final ImageStore imageStore;
//    private final int repeatCount;
//
//    public Activity(ExecutableEntity entity, WorldModel world, ImageStore imageStore, int repeatCount) {
//        this.entity = entity;
//        this.world = world;
//        this.imageStore = imageStore;
//        this.repeatCount = repeatCount;
//    }
//
//    public void executeAction(EventScheduler scheduler) {
//        this.entity.executeActivity(this.world, this.imageStore, scheduler);
//    }
//
//
//}

public class Activity extends Action{
    WorldModel world;
    ImageStore imageStore;
    public Activity(Executable entity, WorldModel world, ImageStore imageStore, int repeatCount) {
        super(entity, repeatCount);
        this.world = world;
        this.imageStore = imageStore;

    }

    public void executeAction(EventScheduler scheduler) {
        this.entity.executeActivity(this.world, this.imageStore, scheduler);
    }
}