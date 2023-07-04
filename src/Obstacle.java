import processing.core.PImage;

import java.util.List;

public class Obstacle extends Entity {
    public static final String OBSTACLE_KEY = "obstacle";
    public static final int OBSTACLE_NUM_PROPERTIES = 4;
    public static final int OBSTACLE_ID = 1;
    public static final int OBSTACLE_COL = 2;
    public static final int OBSTACLE_ROW = 3;

    public Obstacle(String id, Point position, List<PImage> images) {
        super(EntityKind.OBSTACLE, id, position, images);
    }

    public static boolean parseObstacle(String[] properties, WorldModel world,
                                        ImageStore imageStore) {
        if (properties.length == OBSTACLE_NUM_PROPERTIES) {
            Point pt = new Point(
                    Integer.parseInt(properties[OBSTACLE_COL]),
                    Integer.parseInt(properties[OBSTACLE_ROW]));
            Obstacle entity = createObstacle(properties[OBSTACLE_ID],
                    pt, imageStore.getImageList(OBSTACLE_KEY));
            world.tryAddEntity(entity);
        }

        return properties.length == OBSTACLE_NUM_PROPERTIES;
    }

    public static Obstacle createObstacle(String id, Point position,
                                          List<PImage> images) {
        return new Obstacle(id, position, images);
    }
}