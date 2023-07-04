//import processing.core.PImage;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.Random;
//
//public class Vein implements ExecutableEntity {
//    public static final String ORE_KEY = "ore";
//    private static final String ORE_ID_PREFIX = "ore -- ";
//    private static final int ORE_CORRUPT_MIN = 20000;
//    private static final int ORE_CORRUPT_MAX = 30000;
//    private final String id;
//    private final List<PImage> images;
//    private final int actionPeriod;
//    Random rand = new Random();
//    private Point position;
//    private int imageIndex;
//
//
//    public Vein(String id, Point position, List<PImage> images, int actionPeriod) {
//        this.id = id;
//        this.position = position;
//        this.images = images;
//        this.imageIndex = 0;
//        this.actionPeriod = actionPeriod;
//    }
//
//    public Point getPosition() {
//        return this.position;
//    }
//
//    public void setPosition(Point position) {
//        this.position = position;
//    }
//
//    public List<PImage> getImages() {
//        return this.images;
//    }
//
//    public int getImageIndex() {
//        return this.imageIndex;
//    }
//
//    public void nextImage() {
//        this.imageIndex = (this.imageIndex + 1) % this.images.size();
//    }
//
//    public PImage getCurrentImage() {
//        return this.images.get(this.imageIndex);
//    }
//
//    public int getActionPeriod() {
//        return this.actionPeriod;
//    }
//
//    public void executeActivity(WorldModel world,
//                                ImageStore imageStore, EventScheduler scheduler) {
//        Optional<Point> openPt = world.findOpenAround(this.position);
//
//        if (openPt.isPresent()) {
//            ExecutableEntity ore = new Ore(ORE_ID_PREFIX + this.id,
//                    openPt.get(), imageStore.getImageList(ORE_KEY), ORE_CORRUPT_MIN +
//                    rand.nextInt(ORE_CORRUPT_MAX - ORE_CORRUPT_MIN)
//            );
//            world.addEntity(ore);
//            ore.scheduleActions(scheduler, world, imageStore);
//        }
//
//        scheduler.scheduleEvent(this,
//                createActivityAction(world, imageStore),
//                this.actionPeriod);
//    }
//
//    @Override
//    public void scheduleActions(EventScheduler scheduler, WorldModel world, ImageStore imageStore) {
//        scheduler.scheduleEvent(this,
//                createActivityAction(world, imageStore),
//                this.actionPeriod);
//    }
//
//    @Override
//    public EntityKind getKind() {
//        return EntityKind.VEIN;
//    }
//
//    public Activity createActivityAction(WorldModel world,
//                                         ImageStore imageStore) {
//        return new Activity(this, world, imageStore, 0);
//    }
//}

import processing.core.PImage;

import java.util.List;
import java.util.Optional;
import java.util.Random;

public class Vein extends Executable {
    public static final String ORE_KEY = "ore";
    public static final String VEIN_KEY = "vein";
    public static final int VEIN_NUM_PROPERTIES = 5;
    public static final int VEIN_ID = 1;
    public static final int VEIN_COL = 2;
    public static final int VEIN_ROW = 3;
    public static final int VEIN_ACTION_PERIOD = 4;
    private static final String ORE_ID_PREFIX = "ore -- ";
    private static final int ORE_CORRUPT_MIN = 20000;
    private static final int ORE_CORRUPT_MAX = 30000;
    private static Random rand = new Random();

    public Vein(String id, Point position, List<PImage> images, int actionPeriod) {
        super(EntityKind.VEIN,id, position, images, actionPeriod);
    }

    public static boolean parseVein(String[] properties, WorldModel world,
                                    ImageStore imageStore) {
        if (properties.length == VEIN_NUM_PROPERTIES) {
            Point pt = new Point(Integer.parseInt(properties[VEIN_COL]),
                    Integer.parseInt(properties[VEIN_ROW]));
            Entity entity = new Vein(properties[VEIN_ID],
                    pt,
                    imageStore.getImageList(VEIN_KEY),
                    Integer.parseInt(properties[VEIN_ACTION_PERIOD]));
            world.tryAddEntity(entity);
        }

        return properties.length == VEIN_NUM_PROPERTIES;
    }

    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
                Optional<Point> openPt = world.findOpenAround(this.position);

        if (openPt.isPresent()) {
            Executable ore = new Ore(ORE_ID_PREFIX + this.id,
                    openPt.get(), imageStore.getImageList(ORE_KEY), ORE_CORRUPT_MIN +
                    rand.nextInt(ORE_CORRUPT_MAX - ORE_CORRUPT_MIN)
            );
            world.addEntity(ore);
            ore.scheduleActions(scheduler, world, imageStore);
        }

        scheduler.scheduleEvent(this,
                new Activity(this, world, imageStore, 0),
                actionPeriod);

    }
}