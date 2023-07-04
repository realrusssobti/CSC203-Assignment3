import processing.core.PImage;

import java.util.List;

abstract class Entity  {
    protected EntityKind kind;
    protected String id;
    protected Point position;
    protected List<PImage> images;
    protected int imageIndex;

    public Entity(EntityKind kind, String id, Point position,
                  List<PImage> images)
    {
        this.kind = kind;
        this.id = id;
        this.position = position;
        this.images = images;
        this.imageIndex = 0;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public List<PImage> getImages() {
        return images;
    }

    public int getImageIndex() {
        return imageIndex;
    }

    public void nextImage() {
        imageIndex = (imageIndex + 1) % images.size();
    }

    public PImage getCurrentImage() {
        return (this).getImages().get((this).getImageIndex());
    }

    public EntityKind getKind() {
        return kind;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

