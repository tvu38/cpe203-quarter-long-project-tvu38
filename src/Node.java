import java.util.Objects;

public class Node implements Comparable<Node>{

    private Point location;
    private Node prior;
    private int f;
    private int g;
    private int h;

    public Node(Point location, Node prior, int f, int g, int h)
    {
        this.location = location;
        this.prior = prior;
        this.f = f;
        this.g = g;
        this.h = h;
    }

    public Point getLocation()
    {
        return location;
    }

    public int getG()
    {
        return g;
    }

    public void setG(int g)
    {
        this.g = g;
    }

    public int getF()
    {
        return f;
    }

    public Node getPrior()
    {
        return prior;
    }

    public boolean equals(Object other) {
        return other instanceof Point && ((Node)other).location == this.location
                && ((Node)other).prior == this.prior && ((Node)other).f == this.f
                && ((Node)other).g == this.g && ((Node)other).h == this.h;
    }

    public int hashCode() {
        return Objects.hash(location, prior, f, g, h);
    }

    public int compareTo(Node o)
    {
        if (this.f > o.f)
        {
            return 1;
        }
        else if (this.f < o.f)
        {
            return -1;
        }
        return 0;
    }

}
