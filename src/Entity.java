import processing.core.PImage;
import java.util.List;

public abstract class Entity{

        private final String id;
        private Point position;
        private List<PImage> images;
        private int imageIndex;

        public Entity(String id, Point position, List<PImage> images)
        {
                this.id = id;
                this.position = position;
                this.images = images;
                this.imageIndex = 0;
        }

        String getId()
        {
                return id;
        }

        Point getPosition()
        {
                return position;
        }

        List<PImage> getImages() {return images; }

        void setImages(List<PImage> images) { this.images = images;}

        void setPosition(Point position)
        {
                this.position = position;
        }

        void nextImage()
        {
                this.imageIndex = (this.imageIndex + 1) % this.images.size();
        }

        PImage getCurrentImage()
        {
                return images.get((this).imageIndex);
        }
    }
