import javax.swing.*;
import java.util.List;

public class PayFrame extends JFrame {
    private JList<Service> bookedServicesList;
    private JButton proceedBtn;
    private JPanel payPanel;
    private JScrollPane jscrollpane;
    private JLabel servicesLabel;
    private JLabel noBookedServicesLabel;
    private List<Service> bookedList;

    private final String CHOOSE = "Choose";
    private final String BOOK = "Book a service";

    public PayFrame(Subscriber subscriber) {
        setContentPane(payPanel);
        setTitle("Pay for a service");
        setSize(600, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.bookedList = PostgreSQLConnect.getBookedServices(subscriber.getId());


        DefaultListModel<Service> model = new DefaultListModel<>();
        model.addAll(subscriber.getBookedServices());

        String buttonLabel;
        if (model.isEmpty()) {
            this.noBookedServicesLabel.setVisible(true);
            this.bookedServicesList.setVisible(false);
            buttonLabel = BOOK;
        } else {
            this.noBookedServicesLabel.setVisible(false);
            this.bookedServicesList.setVisible(true);
            buttonLabel = CHOOSE;
        }

        this.proceedBtn.setText(buttonLabel);

        this.bookedServicesList.setModel(model);
        proceedBtn.addActionListener(e -> {
            if (bookedServicesList.isSelectionEmpty() && proceedBtn.getText().equals(CHOOSE)) {
                JOptionPane.showMessageDialog(this,
                        "You have to select one service!",
                        "Warning!",
                        JOptionPane.WARNING_MESSAGE);
            } else if (proceedBtn.getText().equals(CHOOSE)) {
                payForWebsite(subscriber, bookedServicesList.getSelectedValue());
            } else {
                bookService(subscriber);
            }
        });

        setVisible(true);
    }


    private void payForWebsite(Subscriber subscriber, Service service) {
        JFrame frame = new PayAndFundsFrame(subscriber, service);
        this.dispose();
    }

    private void bookService(Subscriber subscriber) {
        JFrame frame = new BookServiceFrame(subscriber);
        this.dispose();

    }
}
