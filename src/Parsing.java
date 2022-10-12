import java.util.Optional;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class Parsing {

    public static final String SAPLING_KEY = "sapling";
    private static final int SAPLING_HEALTH_LIMIT = 5;
    private static final int SAPLING_ACTION_ANIMATION_PERIOD = 1000; // have to be in sync since grows and gains health at same time
    private static final int SAPLING_NUM_PROPERTIES = 4;
    private static final int SAPLING_ID = 1;
    private static final int SAPLING_COL = 2;
    private static final int SAPLING_ROW = 3;
    private static final int SAPLING_HEALTH = 4;

    private static final int BGND_NUM_PROPERTIES = 4;
    private static final int BGND_ID = 1;
    private static final int BGND_COL = 2;
    private static final int BGND_ROW = 3;

    private static final String OBSTACLE_KEY = "obstacle";
    private static final int OBSTACLE_NUM_PROPERTIES = 5;
    private static final int OBSTACLE_ID = 1;
    private static final int OBSTACLE_COL = 2;
    private static final int OBSTACLE_ROW = 3;
    private static final int OBSTACLE_ANIMATION_PERIOD = 4;

    public static final String DUDE_KEY = "dude";
    private static final int DUDE_NUM_PROPERTIES = 7;
    private static final int DUDE_ID = 1;
    private static final int DUDE_COL = 2;
    private static final int DUDE_ROW = 3;
    private static final int DUDE_LIMIT = 4;
    private static final int DUDE_ACTION_PERIOD = 5;
    private static final int DUDE_ANIMATION_PERIOD = 6;

    private static final String HOUSE_KEY = "house";
    private static final int HOUSE_NUM_PROPERTIES = 4;
    private static final int HOUSE_ID = 1;
    private static final int HOUSE_COL = 2;
    private static final int HOUSE_ROW = 3;

    private static final String FAIRY_KEY = "fairy";
    private static final int FAIRY_NUM_PROPERTIES = 6;
    private static final int FAIRY_ID = 1;
    private static final int FAIRY_COL = 2;
    private static final int FAIRY_ROW = 3;
    private static final int FAIRY_ANIMATION_PERIOD = 4;
    private static final int FAIRY_ACTION_PERIOD = 5;

    public static final String TREE_KEY = "tree";
    private static final int TREE_NUM_PROPERTIES = 7;
    private static final int TREE_ID = 1;
    private static final int TREE_COL = 2;
    private static final int TREE_ROW = 3;
    private static final int TREE_ANIMATION_PERIOD = 4;
    private static final int TREE_ACTION_PERIOD = 5;
    private static final int TREE_HEALTH = 6;

    private static final String BEEHIVE_KEY = "beehive";

    private static final String BEE_KEY = "bee";

    public static final String DEADBEE_KEY = "deadbee";

    public static final String HONEYTREE_KEY = "honeytree";

    public static final String HONEYDUDE_KEY = "honeydude";

    public static boolean parseBackground(
            String[] properties, WorldModel world, ImageStore imageStore) {
        if (properties.length == BGND_NUM_PROPERTIES) {
            Point pt = new Point(Integer.parseInt(properties[BGND_COL]),
                    Integer.parseInt(properties[BGND_ROW]));
            String id = properties[BGND_ID];
            world.setBackground(pt,
                    new Background(id, imageStore.getImageList(id)));
        }

        return properties.length == BGND_NUM_PROPERTIES;
    }

    public static boolean parseSapling(
            String[] properties, WorldModel world, ImageStore imageStore) {
        if (properties.length == SAPLING_NUM_PROPERTIES) {
            Point pt = new Point(Integer.parseInt(properties[SAPLING_COL]),
                    Integer.parseInt(properties[SAPLING_ROW]));
            String id = properties[SAPLING_ID];
            int health = Integer.parseInt(properties[SAPLING_HEALTH]);
            Entity entity = new Sapling(id, pt, imageStore.getImageList(SAPLING_KEY),
                    SAPLING_ACTION_ANIMATION_PERIOD, SAPLING_ACTION_ANIMATION_PERIOD, health, SAPLING_HEALTH_LIMIT);
            world.tryAddEntity(entity);
        }

        return properties.length == SAPLING_NUM_PROPERTIES;
    }

    public static boolean parseDude(
            String[] properties, WorldModel world, ImageStore imageStore) {
        if (properties.length == DUDE_NUM_PROPERTIES) {
            Point pt = new Point(Integer.parseInt(properties[DUDE_COL]),
                    Integer.parseInt(properties[DUDE_ROW]));
            Entity entity = Factory.createDudeNotFull(properties[DUDE_ID],
                    pt,
                    Integer.parseInt(properties[DUDE_ACTION_PERIOD]),
                    Integer.parseInt(properties[DUDE_ANIMATION_PERIOD]),
                    Integer.parseInt(properties[DUDE_LIMIT]),
                    imageStore.getImageList(DUDE_KEY));
            world.tryAddEntity(entity);
        }

        return properties.length == DUDE_NUM_PROPERTIES;
    }

    public static boolean parseFairy(
            String[] properties, WorldModel world, ImageStore imageStore) {
        if (properties.length == FAIRY_NUM_PROPERTIES) {
            Point pt = new Point(Integer.parseInt(properties[FAIRY_COL]),
                    Integer.parseInt(properties[FAIRY_ROW]));
            Entity entity = Factory.createFairy(properties[FAIRY_ID],
                    pt,
                    Integer.parseInt(properties[FAIRY_ACTION_PERIOD]),
                    Integer.parseInt(properties[FAIRY_ANIMATION_PERIOD]),
                    imageStore.getImageList(FAIRY_KEY));
            world.tryAddEntity(entity);
        }

        return properties.length == FAIRY_NUM_PROPERTIES;
    }

    public static boolean parseTree(
            String[] properties, WorldModel world, ImageStore imageStore) {
        if (properties.length == TREE_NUM_PROPERTIES) {
            Point pt = new Point(Integer.parseInt(properties[TREE_COL]),
                    Integer.parseInt(properties[TREE_ROW]));
            Entity entity = Factory.createTree(properties[TREE_ID],
                    pt,
                    Integer.parseInt(properties[TREE_ACTION_PERIOD]),
                    Integer.parseInt(properties[TREE_ANIMATION_PERIOD]),
                    Integer.parseInt(properties[TREE_HEALTH]),
                    imageStore.getImageList(TREE_KEY));
            world.tryAddEntity(entity);
        }

        return properties.length == TREE_NUM_PROPERTIES;
    }

    public static boolean parseObstacle(
            String[] properties, WorldModel world, ImageStore imageStore) {
        if (properties.length == OBSTACLE_NUM_PROPERTIES) {
            Point pt = new Point(Integer.parseInt(properties[OBSTACLE_COL]),
                    Integer.parseInt(properties[OBSTACLE_ROW]));
            Entity entity = Factory.createObstacle(properties[OBSTACLE_ID], pt,
                    Integer.parseInt(properties[OBSTACLE_ANIMATION_PERIOD]),
                    imageStore.getImageList(
                            OBSTACLE_KEY));
            world.tryAddEntity(entity);
        }

        return properties.length == OBSTACLE_NUM_PROPERTIES;
    }


    public static boolean parseHouse(
            String[] properties, WorldModel world, ImageStore imageStore) {
        if (properties.length == HOUSE_NUM_PROPERTIES) {
            Point pt = new Point(Integer.parseInt(properties[HOUSE_COL]),
                    Integer.parseInt(properties[HOUSE_ROW]));
            Entity entity = Factory.createHouse(properties[HOUSE_ID], pt,
                    imageStore.getImageList(
                            HOUSE_KEY));
            world.tryAddEntity(entity);
        }

        return properties.length == HOUSE_NUM_PROPERTIES;
    }

    public static void parseBeehive(
            int id, int x, int y, WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
        Point pt = new Point(x, y);
        String i = "beehive_" + String.valueOf(id);
        String i2 = "bee_" + String.valueOf(id);
        Entity entity = Factory.createBeehive(i, pt,
                imageStore.getImageList(
                        BEEHIVE_KEY));
        world.setBackground(pt, new Background(i, imageStore.getImageList("honey")));
        Point pt2 = new Point(x + 1, y);
        Point pt3 = new Point(x - 1, y);
        Point pt4 = new Point(x - 1, y + 1);
        Point pt5 = new Point(x - 1, y - 1);
        Point pt6 = new Point(x, y - 1);
        Point pt7 = new Point(x, y + 1);
        Point pt8 = new Point(x + 1, y + 1);
        Point pt9 = new Point(x + 1, y - 1);
        List<Point> points = new ArrayList<>();
        points.add(pt2);
        points.add(pt3);
        points.add(pt4);
        points.add(pt5);
        points.add(pt6);
        points.add(pt7);
        points.add(pt8);
        points.add(pt9);
        world.setBackground(pt2, new Background(i, imageStore.getImageList("honey")));
        world.setBackground(pt3, new Background(i, imageStore.getImageList("honey")));
        world.setBackground(pt4, new Background(i, imageStore.getImageList("honey")));
        world.setBackground(pt5, new Background(i, imageStore.getImageList("honey")));
        world.setBackground(pt6, new Background(i, imageStore.getImageList("honey")));
        world.setBackground(pt7, new Background(i, imageStore.getImageList("honey")));
        world.setBackground(pt8, new Background(i, imageStore.getImageList("honey")));
        world.setBackground(pt9, new Background(i, imageStore.getImageList("honey")));
        points.removeIf(p -> !world.withinBounds(p));
        for (Point p: points)
        {
            if (world.getOccupancyCell(p) != null)
            {
                if ((world.getOccupancyCell(p).getClass().equals(DudeNotFull.class) || world.getOccupancyCell(p).getClass().equals(DudeFull.class)))
                {
                    EntityAction honeydude = Factory.createHoneyDude("honeydude_" + world.getOccupancyCell(p).getId(), p,
                            787, 100,imageStore.getImageList(Parsing.HONEYDUDE_KEY));
                    world.removeEntity(world.getOccupancyCell(p));
                    scheduler.unscheduleAllEvents(world.getOccupancyCell(p));
                    world.addEntity(honeydude);
                    honeydude.scheduleActions(scheduler, world, imageStore);
                }
            }
        }
        points.removeIf(p -> !(world.getOccupant(p).equals(Optional.empty())));
        world.tryAddEntity(entity);
        if (points.size() > 1) {
            Random rd = new Random();
            Point ptfinal = points.get(rd.nextInt(points.size() - 1));
            EntityAction entity2 = Factory.createBee(i2,
                    ptfinal,
                    300,
                    51,
                    imageStore.getImageList(BEE_KEY), imageStore.getImageList(DEADBEE_KEY));
            world.tryAddEntity(entity2);
            entity2.scheduleActions(scheduler, world, imageStore);
        }
        else{
            if (points.size() == 1)
            {
                Point ptfinal = points.get(0);
                EntityAction entity2 = Factory.createBee(i2,
                        ptfinal,
                        300,
                        51,
                        imageStore.getImageList(BEE_KEY), imageStore.getImageList(DEADBEE_KEY));
                world.tryAddEntity(entity2);
                entity2.scheduleActions(scheduler, world, imageStore);
            }
        }
    }
}
