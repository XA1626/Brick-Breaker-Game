import java.awt.*;

public class Ball {
    private int x, y;
    private int diameter = 20;
    private int dx = 4;
    private int dy = -4;
    private boolean inGame = true;

    public Ball(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move() {
        x += dx;
        y += dy;
    }

    public void checkCollisions(int panelWidth, int panelHeight, Paddle paddle) {
        // Wall collisions
        if (x <= 0 || x >= panelWidth - diameter)
            dx = -dx;
        if (y <= 0)
            dy = -dy;

        // Game over
        if (y >= panelHeight - diameter)
            inGame = false;

        // Paddle collision
        if (getBounds().intersects(paddle.getBounds())) {
            dy = -Math.abs(dy); // Always bounce up
            dx = (x < paddle.getX() + paddle.getBounds().width / 2) ? -Math.abs(dx) : Math.abs(dx);
        }
    }

    public boolean brickCollision(Brick brick) {
        if (!brick.isDestroyed() && getBounds().intersects(brick.getBounds())) {
            brick.setDestroyed(true);
            dy = -dy;
            return true;
        }
        return false;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, diameter, diameter);
    }

    public void draw(Graphics g) {
        g.setColor(Color.MAGENTA);
        g.fillOval(x, y, diameter, diameter);
    }

    public boolean isInGame() {
        return inGame;
    }
}
