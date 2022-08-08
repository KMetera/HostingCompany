import javax.swing.*;

public class ServiceAvailableFrame extends JFrame {


    private JPanel serviceAvailablePanel;
    private JButton acceptBtn;
    private JButton editButton;

    public ServiceAvailableFrame(Subscriber subscriber) {
        generateWindow(subscriber);
        this.acceptBtn.addActionListener(e -> acceptBooking(subscriber));
    }

    public ServiceAvailableFrame(Subscriber subscriber, String domain) {
        generateWindow(subscriber);
        this.acceptBtn.addActionListener(e -> acceptBooking(subscriber, domain));
    }

    private void generateWindow(Subscriber subscriber) {
        setContentPane(serviceAvailablePanel);
        setTitle("Book a service");
        setSize(600, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        editButton.addActionListener(e -> editBooking(subscriber));

        setVisible(true);

    }

    private void acceptBooking(Subscriber subscriber) {
        subscriber.bookService();
        JFrame frame = new PayFrame(subscriber);
        this.dispose();
    }

    private void acceptBooking(Subscriber subscriber, String domain) {
        subscriber.bookService(domain);
        JFrame frame = new PayFrame(subscriber);
        this.dispose();
    }

    private void editBooking(Subscriber subscriber) {
        JFrame frame = new BookServiceFrame(subscriber);
        this.dispose();
    }
}
