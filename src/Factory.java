import processing.core.PImage;

import java.util.List;

public class Factory {

    private static final int SAPLING_HEALTH_LIMIT = 5;
    private static final int SAPLING_ACTION_ANIMATION_PERIOD = 1000; // have to be in sync since grows and gains health at same time

    public static Entity createHouse(
            String id, Point position, List<PImage> images)
    {
        return new House(id, position, images);
    }

    public static Entity createBeehive(
            String id, Point position, List<PImage> images)
    {
        return new Beehive(id, position, images);
    }

    public static Entity createStump(
            String id,
            Point position,
            List<PImage> images)
    {
        return new Stump(id, position, images
                );
    }

    public static Entity createObstacle(
            String id, Point position, int animationPeriod, List<PImage> images)
    {
        return new Obstacle(id, position, images, animationPeriod);
    }

    public static EntityAction createTree(
            String id,
            Point position,
            int actionPeriod,
            int animationPeriod,
            int health,
            List<PImage> images)
    {
        return new Tree(id, position, images,
                actionPeriod, animationPeriod, health);
    }

    // health starts at 0 and builds up until ready to convert to Tree
    public static EntityAction createSapling(
            String id,
            Point position,
            List<PImage> images)
    {
        return new Sapling(id, position, images,
                SAPLING_ACTION_ANIMATION_PERIOD, SAPLING_ACTION_ANIMATION_PERIOD, 0, SAPLING_HEALTH_LIMIT);
    }

    public static Entity createFairy(
            String id,
            Point position,
            int actionPeriod,
            int animationPeriod,
            List<PImage> images)
    {
        return new Fairy(id, position, images,
                actionPeriod, animationPeriod);
    }

    public static EntityAction createBee(
            String id,
            Point position,
            int actionPeriod,
            int animationPeriod,
            List<PImage> images, List<PImage> deadimages)
    {
        return new Bee(id, position, images,
                actionPeriod, animationPeriod, deadimages);
    }

    public static EntityAction createHoneyTree(String id, Point position, List<PImage> images, int animationPeriod)
    {
        return new HoneyTree(id, position, images, animationPeriod);
    }

    // need resource count, though it always starts at 0
    public static EntityAction createDudeNotFull(
            String id,
            Point position,
            int actionPeriod,
            int animationPeriod,
            int resourceLimit,
            List<PImage> images)
    {
        return new DudeNotFull(id, position, images, resourceLimit, 0,
                actionPeriod, animationPeriod);
    }

    public static EntityAction createHoneyDude(
            String id,
            Point position,
            int actionPeriod,
            int animationPeriod,
            List<PImage> images)
    {
        return new HoneyDude(id, position, images,
                actionPeriod, animationPeriod);
    }

    // don't technically need resource count ... full
    public static EntityAction createDudeFull(
            String id,
            Point position,
            int actionPeriod,
            int animationPeriod,
            int resourceLimit,
            List<PImage> images) {
        return new DudeFull(id, position, images, resourceLimit,
                actionPeriod, animationPeriod);
    }

}
