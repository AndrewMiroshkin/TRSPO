import java.awt.*; // library for creating graphical interfaces
import java.awt.geom.*; // library for handling user interactions with a graphical interface
import java.util.Random; // (extension for awt) library for connecting JFrame, JPanel, JButton, JLabel

// The Ball class performs the following functions: creating, displaying, and moving balls on the screen.
class Ball implements Runnable {
    private static final int XSIZE = 20;
    private static final int YSIZE = 20;
    private int x = 0;
    private int y = 0;
    private int dx = 4;
    private int dy = 4;
    private Component canvas;
    private Thread thread;
    private Color color;


    // The constructor takes 2 arguments color and canvas on which the ball will be displayed. 
    // Also It randomly initializes the initial coordinates of the ball.
    public Ball(Component c, Color color) {
        this.canvas = c;
        this.color = color;

        if (Math.random() < 0.5) {
            x = new Random().nextInt(this.canvas.getWidth());
            y = 0;
        } else {
            x = 0;
            y = new Random().nextInt(this.canvas.getHeight());
        }

        thread = new Thread(this);
        thread.start();
    }

    // Allows to set the priority of the thread in which the ball is executed.
    public void setPriority(int priority) {
        thread.setPriority(priority);
    }

    // Uses a Graphics2D object to draw an ellipse with the given coordinates and size.
    public void draw(Graphics2D g2) {
        g2.setColor(color);
        g2.fill(new Ellipse2D.Double(x, y, XSIZE, YSIZE));
    }

    // Updates the ball's coordinates to the dx and dy values.
    // It also checks for collisions with the canvas component's boundaries and changes the direction of movement if a collision occurs.
    public void move() {
        x += dx;
        y += dy;
        if (x < 0) {
            x = 0;
            dx = -dx;
        }
        if (x + XSIZE >= this.canvas.getWidth()) {
            x = this.canvas.getWidth() - XSIZE;
            dx = -dx;
        }
        if (y < 0) {
            y = 0;
            dy = -dy;
        }
        if (y + YSIZE >= this.canvas.getHeight()) {
            y = this.canvas.getHeight() - YSIZE;
            dy = -dy;
        }
        this.canvas.repaint();
    }

    // Contains an infinite loop that calls the move() method and pauses with Thread.sleep(10).
    //It's an implementation of the Runnable interface function, which allows to run Ball in a separate thread.
    @Override
    public void run() {
        try {
            while (true) {
                move();
                Thread.sleep(10);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}