import java.awt.*; // library for creating graphical interfaces
import java.awt.event.*; // library for handling user interactions with a graphical interface
import javax.swing.*; // (extension for awt) library for connecting JFrame, JPanel, JButton, JLabel

// The BallApp class inherits JFrame, which allows it to create the main application window.
public class BallApp extends JFrame {
    private BallCanvas canvas;

    // The constructor specifies window parameters, such as size and closing behavior.
    // The constructor creates a BallCanvas object, which is added to the center of the window. 
    // BallCanvas is responsible for drawing and updating the balls.
    // In the constructor, the “Add Red Ball” and “Add Blue Ball” buttons are created and added to the panel at the bottom of the window.
    public BallApp() {
        canvas = new BallCanvas();
        add(canvas, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton redBallButton = new JButton("Add Red Ball");
        JButton blueBallButton = new JButton("Add Blue Ball");
        
        redBallButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Ball redBall = new Ball(canvas, Color.RED);
                redBall.setPriority(Thread.MAX_PRIORITY);
                canvas.addBall(redBall);
            }
        });

        blueBallButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Ball blueBall = new Ball(canvas, Color.BLUE);
                blueBall.setPriority(Thread.MIN_PRIORITY);
                canvas.addBall(blueBall);
            }
        });

        buttonPanel.add(redBallButton);
        buttonPanel.add(blueBallButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // main uses SwingUtilities.invokeLater to start the application in the Swing event dispatch thread, which ensures that the GUI works correctly.
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new BallApp();
            }
        });
    }
}



