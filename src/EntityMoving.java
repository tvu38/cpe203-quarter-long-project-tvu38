import processing.core.PImage;

import java.util.List;

public abstract class EntityMoving extends EntityActivity{

    public EntityMoving(String id, Point position, List<PImage> images, int actionPeriod, int animationPeriod)
    {
        super(id, position, images, actionPeriod, animationPeriod);
    }

    abstract Point nextPosition(WorldModel world, Point destPos);
}
