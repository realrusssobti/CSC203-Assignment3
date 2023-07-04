import com.sun.org.apache.xpath.internal.operations.Or;
import processing.core.PImage;

import java.util.List;
import java.util.Random;

public class Ore extends Executable {
    public static final String BLOB_KEY = "blob";
    public static final String BLOB_ID_SUFFIX = " -- blob";
    public static final int BLOB_PERIOD_SCALE = 4;
    public static final int BLOB_ANIMATION_MIN = 50;
    public static final int BLOB_ANIMATION_MAX = 150;
    public static final String ORE_KEY = "ore";
    public static final int ORE_NUM_PROPERTIES = 5;
    public static final int ORE_ID = 1;
    public static final int ORE_COL = 2;
    public static final int ORE_ROW = 3;
    public static final int ORE_ACTION_PERIOD = 4;
    // rand
    private static Random rand = new Random();

    public Ore(String id, Point position, List<PImage> images, int actionPeriod) {
        super(EntityKind.ORE, id, position, images, actionPeriod);

    }

    public static boolean parseOre(String[] properties, WorldModel world,
                                   ImageStore imageStore) {
        if (properties.length == ORE_NUM_PROPERTIES) {
            Point pt = new Point(Integer.parseInt(properties[ORE_COL]),
                    Integer.parseInt(properties[ORE_ROW]));
            Entity entity = new Ore(properties[ORE_ID],
                    pt, imageStore.getImageList(ORE_KEY), Integer.parseInt(properties[ORE_ACTION_PERIOD]));
            world.tryAddEntity(entity);
        }

        return properties.length == ORE_NUM_PROPERTIES;
    }

    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
        Point pos = this.getPosition();  // store current position before removing

        world.removeEntity(this);
        scheduler.unscheduleAllEvents(this);

        OreBlob ore = new OreBlob(this.id + BLOB_ID_SUFFIX,
                pos, imageStore.getImageList(BLOB_KEY), this.actionPeriod / BLOB_PERIOD_SCALE,
                BLOB_ANIMATION_MIN +
                        rand.nextInt(BLOB_ANIMATION_MAX - BLOB_ANIMATION_MIN)
        );

        world.addEntity(ore);
        ore.scheduleActions(scheduler, world, imageStore);
    }
}