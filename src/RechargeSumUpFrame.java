import javax.swing.*;
import java.text.DecimalFormat;

public class RechargeSumUpFrame extends JFrame {
    private JButton continueButton;
    private JLabel fundsLabel;
    private JPanel rechargeSumUpPanel;

    RechargeSumUpFrame(Subscriber subscriber, Service service) {
        setContentPane(rechargeSumUpPanel);
        setTitle("Pay for a service");
        setSize(600, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setFundsLabel(subscriber.getFunds());

        continueButton.addActionListener(e -> payingWindow(subscriber, service));

        setVisible(true);
    }

    private void payingWindow(Subscriber subscriber, Service service) {
        JFrame frame = new PayAndFundsFrame(subscriber, service);
        this.dispose();
    }


    private void setFundsLabel(double funds) {
        DecimalFormat df = new DecimalFormat("0.00");
        this.fundsLabel.setText(df.format(funds) + " zł");
    }


}
