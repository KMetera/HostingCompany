import javax.swing.*;
import java.text.DecimalFormat;

public class PaySumUpFrame extends JFrame {
    private JLabel serviceLabel;
    private JLabel fundsLabel;
    private JPanel paySumUpPanel;
    private JButton exitButton;

    public PaySumUpFrame(Subscriber subscriber, Service service) {
        setContentPane(paySumUpPanel);
        setTitle("Pay for a service");
        setSize(600, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setServiceLabel(service);
        setFundsLabel(subscriber.getFunds());

        exitButton.addActionListener(e -> System.exit(0));


        setVisible(true);
    }

    private void setServiceLabel(Service service) {
        this.serviceLabel.setText(service.toString());
    }

    private void setFundsLabel(double funds) {
        DecimalFormat df = new DecimalFormat("0.00");
        this.fundsLabel.setText(df.format(funds) + " zł");
    }
}
