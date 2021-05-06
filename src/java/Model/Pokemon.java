/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author jcardenas
 */
public class Pokemon {

    private int id;
    private String name;
    private String img;
    private String url;
    private int weight;
    private Habitat habitat;
    private boolean is_baby;
    private boolean is_legendary;
    private boolean is_mythical;
    private Sprites sprites;
    private Shape shape;
    private Color color;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Habitat getHabitat() {
        return habitat;
    }

    public void setHabitat(Habitat habitat) {
        this.habitat = habitat;
    }

    public Sprites getSprites() {
        return sprites;
    }

    public void setSprites(Sprites sprites) {
        this.sprites = sprites;
    }

    public boolean isIs_baby() {
        return is_baby;
    }

    public void setIs_baby(boolean is_baby) {
        this.is_baby = is_baby;
    }

    public boolean isIs_legendary() {
        return is_legendary;
    }

    public void setIs_legendary(boolean is_legendary) {
        this.is_legendary = is_legendary;
    }

    public boolean isIs_mythical() {
        return is_mythical;
    }

    public void setIs_mythical(boolean is_mythical) {
        this.is_mythical = is_mythical;
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
