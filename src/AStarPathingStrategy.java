import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Collections;

class AStarPathingStrategy
        implements PathingStrategy
{

    private int distanceSquared(Point p1, Point p2) { //tooken from Point class
        int deltaX = p1.x - p2.x;
        int deltaY = p1.y - p2.y;

        return deltaX * deltaX + deltaY * deltaY;
    }


    public List<Point> computePath(Point start, Point end,
                                   Predicate<Point> canPassThrough,
                                   BiPredicate<Point, Point> withinReach,
                                   Function<Point, Stream<Point>> potentialNeighbors)
    {

        List<Point> path = new LinkedList<Point>();

        PriorityQueue<Node> openList = new PriorityQueue();
        HashMap<Point, Node> opList = new HashMap<Point, Node>();
        HashMap<Point,Node> closedList = new HashMap<Point,Node>();
        List<Point> neighbors = new ArrayList<>();

        Node n = new Node(start,null,0,0,0);
        openList.add(n); //adds start node as current node
        opList.put(start, n);
        Node current = openList.poll();
        opList.remove(current.getLocation(), current);

        while (!withinReach.test(current.getLocation(), end) && current != null) {
            List<Point> fakeneighbors = potentialNeighbors.apply(current.getLocation()).collect(Collectors.toList());
            if (!fakeneighbors.isEmpty())
            {
                for (Point neighbor: fakeneighbors) //finds out which neighbors are valid
                {
                    if (canPassThrough.test(neighbor) && !closedList.containsKey(neighbor))
                    {
                        neighbors.add(neighbor);
                    }

                }
            }
            for (Point neighbor : neighbors) {
                int gvalue = current.getG() + 1; //calculates gvalue
                if (opList.containsKey(neighbor)) { //changes gvalue of neighbor if neighbor is in open list already
                    if (gvalue > opList.get(neighbor).getG()) {
                        opList.get(neighbor).setG(gvalue);
                    }
                }
                else {
                    int hvalue = distanceSquared(neighbor, end); //calculates hvalue, fvalue, and adds neighbor to open list
                    int fvalue = gvalue + hvalue;
                    Node pri = current;
                    Node ne = new Node(neighbor, pri, fvalue, gvalue, hvalue);
                    opList.put(ne.getLocation(), ne);
                    openList.add(ne);
                }
            }

            closedList.put(current.getLocation(), current); //puts current node in closed list and removes from open list
            if (!opList.isEmpty()) {
                current = openList.poll();
                opList.remove(current.getLocation());
            }
            else {
                return path; //returns path is open list empty
            }
            neighbors.clear(); //clears neighbors for reset
        }

        ArrayList<Point> reversed = new ArrayList<Point>(); //reverses path by taking prior nodes and forming list
        while (current.getPrior() != null){
            reversed.add(current.getLocation());
            current = closedList.get(current.getPrior().getLocation());
        }

        Collections.reverse(reversed);
        path = reversed;
        return path;
    }
}
