import javax.swing.*;
import java.text.DecimalFormat;

public class PayAndFundsFrame extends JFrame {
    private JLabel servicePriceLabel;
    private JLabel subscriberFundsLabel;
    private JButton payOrRechargeButton;
    private JPanel payAndFundsPanel;
    private JLabel needToRechargeLabel;
    private final String PAY = "Pay";
    private final String RECHARGE = "Recharge";


    public PayAndFundsFrame(Subscriber subscriber, Service service) {
        setContentPane(payAndFundsPanel);
        setTitle("Pay for a service");
        setSize(600, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        double priceValue = service.getPrice();
        double fundsValue = subscriber.getFunds();

        setServicePriceLabel(priceValue);
        setSubscriberFundsLabel(fundsValue);

        boolean canPay = fundsValue >= priceValue;

        String buttonLabel = (canPay ? PAY : RECHARGE);
        this.payOrRechargeButton.setText(buttonLabel);

        this.needToRechargeLabel.setVisible(!canPay);

        payOrRechargeButton.addActionListener(e -> {
            if (payOrRechargeButton.getText().equals(PAY)) {
                payForService(subscriber, service);
            } else if (payOrRechargeButton.getText().equals(RECHARGE)) {
                rechargeFunds(subscriber, service);
            }
        });

        setVisible(true);
    }

    private void setServicePriceLabel(double servicePrice) {
        DecimalFormat df = new DecimalFormat("0.00");
        this.servicePriceLabel.setText(df.format(servicePrice) + " zł");
    }

    private void setSubscriberFundsLabel(double subscriberFunds) {
        DecimalFormat df = new DecimalFormat("0.00");
        this.subscriberFundsLabel.setText(df.format(subscriberFunds) + " zł");
    }


    private void payForService(Subscriber subscriber, Service service) {
        subscriber.payForService(service);
        JFrame frame = new PaySumUpFrame(subscriber, service);
        this.dispose();
    }


    private void rechargeFunds(Subscriber subscriber, Service service) {
        JFrame frame = new RechargeFrame(subscriber, service);
        this.dispose();
    }

}
