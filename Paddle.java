import java.awt.*;

public class Paddle {
    private int x, y;
    private int width = 100;
    private int height = 20;
    private int speed = 10;

    public Paddle(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void moveLeft() {
        x = Math.max(0, x - speed);
    }

    public void moveRight(int panelWidth) {
        x = Math.min(panelWidth - width, x + speed);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(x, y, width, height);
    }

    public int getX() {
        return x;
    }
}