import processing.core.PImage;

import java.util.List;

public abstract class EntityActivity extends EntityAction{

    private int actionPeriod;

    public EntityActivity(String id, Point position, List<PImage> images, int actionPeriod, int animationPeriod)
    {
        super(id, position, images, animationPeriod);
        this.actionPeriod = actionPeriod;
    }

    public Action createActivityAction(WorldModel world, ImageStore imageStore)
    {
        return new Activity(this, world, imageStore);
    }

    int getActionPeriod()
    {
        return actionPeriod;
    }

    abstract void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler);

}
