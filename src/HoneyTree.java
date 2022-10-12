import processing.core.PImage;

import java.util.*;

public class HoneyTree extends EntityAction{

    public HoneyTree(
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
