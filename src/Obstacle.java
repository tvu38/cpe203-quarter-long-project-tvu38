import processing.core.PImage;

import java.util.*;

public class Obstacle extends EntityAction{

    public Obstacle(
            String id,
            Point position,
            List<PImage> images,
            int animationPeriod)
    {

        super(id, position, images, animationPeriod);
    }


    protected void _schedulerHelper(EventScheduler scheduler, WorldModel world, ImageStore imageStore)
    {

    }
}
