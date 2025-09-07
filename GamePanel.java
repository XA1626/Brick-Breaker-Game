import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
    private Timer timer;
    private Paddle paddle;
    private Ball ball;
    private Brick[][] bricks;
    private boolean gameActive = true;
    private int score = 0;
    private final int ROWS = 4;
    private final int COLS = 10;

    public GamePanel() {
        setPreferredSize(new Dimension(600, 600));
        setBackground(Color.WHITE);
        addKeyListener(this);
        setFocusable(true);

        paddle = new Paddle(250, 550);
        ball = new Ball(300, 300);
        createBricks();

        timer = new Timer(16, this); // ≈60 FPS
        timer.start();
    }

    private void createBricks() {
        bricks = new Brick[ROWS][COLS];
        Color[] colors = { Color.PINK, Color.CYAN, Color.MAGENTA, Color.YELLOW };

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                bricks[i][j] = new Brick(j * 60, i * 30 + 50, colors[i]);
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw gradient background
        Graphics2D g2d = (Graphics2D) g.create();
        GradientPaint gp = new GradientPaint(0, 0, new Color(30, 30, 60), 0, getHeight(), new Color(80, 180, 220));
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, getWidth(), getHeight());
        g2d.dispose();

        // Draw game objects
        paddle.draw(g);
        ball.draw(g);
        for (Brick[] row : bricks) {
            for (Brick brick : row) {
                brick.draw(g);
            }
        }

        // Draw score with modern font and shadow
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setFont(new Font("Segoe UI", Font.BOLD, 24));
        g2.setColor(new Color(0, 0, 0, 120));
        g2.drawString("Score: " + score, 22, 42);
        g2.setColor(Color.WHITE);
        g2.drawString("Score: " + score, 20, 40);
        g2.dispose();

        // Game over messages
        if (!gameActive) {
            g.setFont(new Font("Segoe UI", Font.BOLD, 40));
            if (ball.isInGame()) {
                g.setColor(Color.GREEN);
                g.drawString("YOU WIN!", 200, 300);
            } else {
                g.setColor(Color.RED);
                g.drawString("GAME OVER", 180, 300);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameActive) {
            ball.move();
            ball.checkCollisions(getWidth(), getHeight(), paddle);

            // Check brick collisions and animate fade
            for (Brick[] row : bricks) {
                for (Brick brick : row) {
                    if (!brick.isDestroyed() && ball.brickCollision(brick)) {
                        score += 10;
                    }
                    if (brick.isFading()) {
                        brick.updateFade();
                    }
                }
            }

            // Check win condition
            if (score >= ROWS * COLS * 10) {
                gameActive = false;
            }
        } else {
            // Continue fading bricks after game ends
            for (Brick[] row : bricks) {
                for (Brick brick : row) {
                    if (brick.isFading()) {
                        brick.updateFade();
                    }
                }
            }
        }
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT)
            paddle.moveLeft();
        if (key == KeyEvent.VK_RIGHT)
            paddle.moveRight(getWidth());
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
