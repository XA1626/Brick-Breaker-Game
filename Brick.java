import java.awt.*;

public class Brick {
    private int x, y;
    private int width = 60;
    private int height = 25;
    private boolean destroyed = false;
    private Color color;

    public Brick(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public void draw(Graphics g) {
        if (!destroyed) {
            g.setColor(color);
            g.fillRect(x, y, width, height);
            g.setColor(Color.BLACK);
            g.drawRect(x, y, width, height);
        }
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }
}