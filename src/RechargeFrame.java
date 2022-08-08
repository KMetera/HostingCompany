import javax.swing.*;

public class RechargeFrame extends JFrame {
    private JButton acceptButton;
    private JPanel rechargePane;
    private JSpinner rechargeValueSpinner;

    public RechargeFrame(Subscriber subscriber, Service service) {
        setContentPane(rechargePane);
        setTitle("Recharge funds");
        setSize(600, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.rechargeValueSpinner.setValue((service.getPrice()>subscriber.getFunds()?(Math.ceil(service.getPrice()-subscriber.getFunds())):0));

        acceptButton.addActionListener(e -> {
            String value = rechargeValueSpinner.getValue()+"";
            if (!isCorrectNumber(value)) {
                JOptionPane.showMessageDialog(this,
                        "The number must be greater than zero!",
                        "Warning!",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                acceptRechargeValue(Double.parseDouble(value), subscriber, service);
            }
        });

        setVisible(true);
    }

    private boolean isCorrectNumber(String input) {
        if (input == null) {
            return false;
        }
        double number;
        try {
            number = Double.parseDouble(input);
        } catch (NumberFormatException numberFormatException) {
            return false;
        }

        return !(number <= 0);
    }

    private void acceptRechargeValue(double value, Subscriber subscriber, Service service) {
        subscriber.addFunds(value);
        JFrame frame = new RechargeSumUpFrame(subscriber, service);
        this.dispose();
    }
}
