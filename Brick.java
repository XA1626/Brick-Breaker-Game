import java.awt.*;

public class Brick {
    private int x, y;
    private int width = 60;
    private int height = 25;
    private boolean destroyed = false;
    private Color color;
    private boolean fading = false;
    private float alpha = 1.0f; // For fade-out animation

    public Brick(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public void draw(Graphics g) {
        if (!destroyed || fading) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
            g2d.setColor(color);
            g2d.fillRect(x, y, width, height);
            g2d.setColor(Color.BLACK);
            g2d.drawRect(x, y, width, height);
            g2d.dispose();
        }
    }

    public boolean isDestroyed() {
        return destroyed && !fading;
    }

    public void setDestroyed(boolean destroyed) {
        if (destroyed && !this.destroyed) {
            fading = true;
        }
        this.destroyed = destroyed;
    }

    public boolean isFading() {
        return fading;
    }

    public void updateFade() {
        if (fading) {
            alpha -= 0.08f;
            if (alpha <= 0f) {
                alpha = 0f;
                fading = false;
            }
        }
    }
}