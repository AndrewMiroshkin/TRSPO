import java.awt.*; // library for creating graphical interfaces
import java.util.ArrayList; // library for handling user interactions with a graphical interface
import java.util.List; // (extension for awt) library for connecting JFrame, JPanel, JButton, JLabel
import javax.swing.*; // library for working with geometric primitives

// BallCanvas contains a list of Ball objects that are added using the addBall(Ball ball) method.
// This allows to manage all the balls that will be displayed on the panel.
class BallCanvas extends JPanel {
    private List<Ball> balls = new ArrayList<>();

    public void addBall(Ball ball) {
        balls.add(ball);
    }

    // Responsible for drawing all Ball objects on the panel.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for (Ball ball : balls) {
            ball.draw(g2);
        }
    }
}