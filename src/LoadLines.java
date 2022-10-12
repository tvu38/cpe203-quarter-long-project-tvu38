import java.util.*;

public class LoadLines {

    private static final int PROPERTY_KEY = 0;

    public static final String SAPLING_KEY = "sapling";

    private static final String BGND_KEY = "background";

    private static final String OBSTACLE_KEY = "obstacle";

    private static final String DUDE_KEY = "dude";

    private static final String HOUSE_KEY = "house";

    private static final String FAIRY_KEY = "fairy";

    public static final String TREE_KEY = "tree";

    public static final String BEE_KEY = "bee";

    private static final String BEEHIVE_KEY = "beehive";


    public static void load(
            Scanner in, WorldModel world, ImageStore imageStore)
    {
        int lineNumber = 0;
        while (in.hasNextLine()) {
            try {
                if (!processLine(in.nextLine(), world, imageStore)) {
                    System.err.println(String.format("invalid entry on line %d",
                            lineNumber));
                }
            }
            catch (NumberFormatException e) {
                System.err.println(
                        String.format("invalid entry on line %d", lineNumber));
            }
            catch (IllegalArgumentException e) {
                System.err.println(
                        String.format("issue on line %d: %s", lineNumber,
                                e.getMessage()));
            }
            lineNumber++;
        }
    }

    private static boolean processLine(
            String line, WorldModel world, ImageStore imageStore)
    {
        String[] properties = line.split("\\s");
        if (properties.length > 0) {
            switch (properties[PROPERTY_KEY]) {
                case BGND_KEY:
                    return Parsing.parseBackground(properties, world, imageStore);
                case DUDE_KEY:
                    return Parsing.parseDude(properties, world, imageStore);
                case OBSTACLE_KEY:
                    return Parsing.parseObstacle(properties, world, imageStore);
                case FAIRY_KEY:
                    return Parsing.parseFairy(properties, world, imageStore);
                case HOUSE_KEY:
                    return Parsing.parseHouse(properties, world, imageStore);
                case TREE_KEY:
                    return Parsing.parseTree(properties, world, imageStore);
                case SAPLING_KEY:
                    return Parsing.parseSapling(properties, world, imageStore);
            }
        }

        return false;
    }

}
