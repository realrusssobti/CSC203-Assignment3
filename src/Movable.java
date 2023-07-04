import processing.core.PImage;

import java.util.List;
import java.util.Optional;

public abstract class Movable extends Animatable{
    public Movable(EntityKind kind, String id, Point position, List<PImage> images, int actionPeriod, int animationPeriod) {
        super(kind, id, position, images, actionPeriod, animationPeriod);
    }

    public abstract boolean move(WorldModel world, Entity target, EventScheduler scheduler);

    public abstract Point nextPosition(WorldModel world, Point destPos) ;
}
