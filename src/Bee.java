import processing.core.PImage;
import java.util.function.Predicate;
import java.util.function.BiPredicate;
import java.util.Random;

import java.util.*;

public class Bee extends EntityMoving{

    private List<PImage> deadimages;


    public Bee(
            String id,
            Point position,
            List<PImage> images,
            int actionPeriod,
            int animationPeriod, List<PImage> deadimages)
    {
        super(id, position, images, actionPeriod, animationPeriod);
        this.deadimages = deadimages;
    }

    public Point nextPosition(WorldModel world, Point destPos)
    {
            //PathingStrategy strat = new SingleStepPathingStrategy();
            PathingStrategy strat = new AStarPathingStrategy();
            Predicate<Point> canPassThrough = p1 -> {
                return (world.withinBounds(p1) && !world.isOccupied(p1));
            };
            BiPredicate<Point, Point> withinReach = (p1, p2) ->
            {
                return p1.adjacent(p2);
            };
            List<Point> path = strat.computePath(getPosition(), destPos, canPassThrough, withinReach,
                    PathingStrategy.CARDINAL_NEIGHBORS);
            if (path.isEmpty()) {
                return getPosition();
            }
            return path.get(0);
        }


    protected void _schedulerHelper(
            EventScheduler scheduler,
            WorldModel world,
            ImageStore imageStore)
    {
            scheduler.scheduleEvent(this,
                    createActivityAction(world, imageStore),
                    this.getActionPeriod());
    }

    public void executeActivity(
            WorldModel world,
            ImageStore imageStore,
            EventScheduler scheduler)
    {
        Optional<Entity> beeTarget;
        Random rd = new Random();
        int target = rd.nextInt(2);
        if (target == 0)
        {
            beeTarget = world.findNearest(this.getPosition(), new ArrayList<>(Arrays.asList(DudeNotFull.class, DudeFull.class)));
        }
        else{
            beeTarget =
                    world.findNearest(this.getPosition(), new ArrayList<>(Arrays.asList(Tree.class)));
        }

            if (beeTarget.isPresent() && target == 0) {
                Point tgtPos = beeTarget.get().getPosition();

                if (this.moveTo(world, beeTarget.get(), scheduler)) {
                    EntityAction honeydude = Factory.createHoneyDude("honeydude_" + this.getId(), tgtPos,
                            787, 100,imageStore.getImageList(Parsing.HONEYDUDE_KEY));
                    world.addEntity(honeydude);
                    honeydude.scheduleActions(scheduler, world, imageStore);
                    world.setBackground(tgtPos, new Background("honey_" + this.getId(), imageStore.getImageList("honey")));
                }
            }
            if (beeTarget.isPresent() && target == 1) {
                Point tgtPos = beeTarget.get().getPosition();

                if (this.moveTo(world, beeTarget.get(), scheduler)) {
                    EntityAction honeytree = Factory.createHoneyTree("honeytree_" + this.getId(), tgtPos,
                            imageStore.getImageList(Parsing.HONEYTREE_KEY), 70);
                    world.addEntity(honeytree);
                    honeytree.scheduleActions(scheduler, world, imageStore);
                    world.setBackground(tgtPos, new Background("honey_" + this.getId(), imageStore.getImageList("honey")));
                }
            }

            scheduler.scheduleEvent(this,
                    this.createActivityAction(world, imageStore),
                    this.getActionPeriod());

    }

    public boolean moveTo(
            WorldModel world,
            Entity target,
            EventScheduler scheduler) {
            if (this.getPosition().adjacent(target.getPosition())) {
                world.removeEntity(target);
                scheduler.unscheduleAllEvents(target);

                Entity dead = new DeadBee(getId(), getPosition(), deadimages);

                world.removeEntity(this);
                scheduler.unscheduleAllEvents(this);

                world.addEntity(dead);
                return true;

            } else {
                Point nextPos = this.nextPosition(world, target.getPosition());

                if (!this.getPosition().equals(nextPos)) {
                    Optional<Entity> occupant = world.getOccupant(nextPos);
                    if (occupant.isPresent()) {
                        scheduler.unscheduleAllEvents(occupant.get());
                    }

                    world.moveEntity(this, nextPos);
                }
                return false;
            }
    }

}
