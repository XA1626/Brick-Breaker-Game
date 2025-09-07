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
        Graphics2D g2d = (Graphics2D) g.create();
        // Draw shadow
        g2d.setColor(new Color(0, 0, 0, 80));
        g2d.fillRoundRect(x + 5, y + 5, width, height, 16, 16);
        // Draw paddle with gradient
        GradientPaint gp = new GradientPaint(x, y, new Color(80, 180, 255), x, y + height, new Color(0, 80, 180));
        g2d.setPaint(gp);
        g2d.fillRoundRect(x, y, width, height, 16, 16);
        g2d.dispose();
    }

    public int getX() {
        return x;
    }
}