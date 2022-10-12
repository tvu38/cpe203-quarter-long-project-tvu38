import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

/**
 * An action that can be taken by an entity
 */
public final class Activity implements Action {
    private final EntityActivity entity;
    private final WorldModel world;
    private final ImageStore imageStore;

    public Activity(
            EntityActivity entity,
            WorldModel world,
            ImageStore imageStore) {
        this.entity = entity;
        this.world = world;
        this.imageStore = imageStore;
    }

    public void executeAction(EventScheduler scheduler) {
        entity.executeActivity(world, imageStore, scheduler);
    }
}
