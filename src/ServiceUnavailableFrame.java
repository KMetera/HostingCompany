import javax.swing.*;

public class ServiceUnavailableFrame extends JFrame{
    private JButton returnButton;
    private JPanel serviceUnavailablePanel;

    public ServiceUnavailableFrame(JFrame prevFrame) {
        setContentPane(serviceUnavailablePanel);
        setTitle("Book a service");
        setSize(600, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        returnButton.addActionListener(e -> returnToBooking(prevFrame));
    }

    private void returnToBooking(JFrame prevFrame) {
        prevFrame.setVisible(true);
        this.dispose();
    }
}
