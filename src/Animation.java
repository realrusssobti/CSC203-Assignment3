
public class Animation extends Action {
    public Animation(Animatable entity, int repeatCount) {
        super(entity, repeatCount);
    }

    public void executeAction(EventScheduler scheduler) {
        this.entity.nextImage();

        if (this.repeatCount != 1) {
            scheduler.scheduleEvent(this.entity,
                    new Animation((Animatable) this.entity,
                            Math.max(this.repeatCount - 1, 0)),
                    ((Animatable) this.entity).getAnimationPeriod());
        }
    }
}