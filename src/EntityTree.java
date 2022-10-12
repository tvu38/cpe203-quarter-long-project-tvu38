import processing.core.PImage;

import java.util.List;

public abstract class EntityTree extends EntityActivity{

    private int health;

    public EntityTree(String id, Point position, List<PImage> images, int animationPeriod, int actionPeriod, int health)
    {
        super(id, position, images, animationPeriod, actionPeriod);
        this.health = health;
    }

    int getHealth()
    {
        return health;
    }
    void setHealth(int health)
    {
        this.health = health;
    }
}
