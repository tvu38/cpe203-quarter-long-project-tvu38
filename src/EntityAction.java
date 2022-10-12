import processing.core.PImage;

import java.util.List;

public abstract class EntityAction extends Entity{

    private final int animationPeriod;

    public EntityAction(String id, Point position, List<PImage> images, int animationPeriod)
    {
        super(id, position, images);
        this.animationPeriod = animationPeriod;
    }

    void scheduleActions(EventScheduler scheduler, WorldModel world, ImageStore imageStore)
    {
        _schedulerHelper(scheduler, world, imageStore);
        scheduler.scheduleEvent(this,
                createAnimationAction(0),
                this.getAnimationPeriod());
    }

    int getAnimationPeriod()
    {
        return this.animationPeriod;
    }

    Action createAnimationAction(int repeatCount)
    {
        return new Animation(this,
                repeatCount);
    }

    protected abstract void _schedulerHelper(EventScheduler scheduler, WorldModel world, ImageStore imageStore);

}
