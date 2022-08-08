import javax.swing.*;
import java.awt.event.ItemEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BookServiceFrame extends JFrame {
    private JRadioButton websiteRadioButton;
    private JRadioButton serverFTPRadioButton;
    private JButton nextBtn;
    private JPanel bookServicePanel;
    private JPanel chooseServicePanel;
    private JTextField domainTextField;
    private JLabel domainText;
    private JLabel plText;
    private Status status;

    private enum Status {WEBSITE, FTP}

    public BookServiceFrame(Subscriber subscriber) {
        setContentPane(bookServicePanel);
        setTitle("Book a service");
        setSize(600, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(this.websiteRadioButton);
        buttonGroup.add(this.serverFTPRadioButton);

        this.status = Status.WEBSITE;
        this.websiteRadioButton.setSelected(true);

        this.websiteRadioButton.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                //Enable writing
                this.status = Status.WEBSITE;
                domainText.setEnabled(true);
                domainTextField.setEnabled(true);
                plText.setEnabled(true);
                clearTextField();
            } else if (e.getStateChange() == ItemEvent.DESELECTED) {
                //Disable writing
                this.status = Status.FTP;
                domainText.setEnabled(false);
                domainTextField.setEnabled(false);
                plText.setEnabled(false);
                clearTextField();
            }
        });

        nextBtn.addActionListener(e -> {
            if (status == Status.WEBSITE) {
                if(!checkIfDomainIsValid(getDomainFromTextField())){
                    JOptionPane.showMessageDialog(this,
                            "The domain must be valid!",
                            "Warning!",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    showAvailabilityWindow(subscriber, getDomainFromTextField());
                }
            } else if (status == Status.FTP) {
                showAvailabilityWindow(subscriber);
            }
        });


        setVisible(true);
    }

    private void showAvailabilityWindow(Subscriber subscriber) {
        JFrame frame = new ServiceAvailableFrame(subscriber);
        this.dispose();
    }


    private void showAvailabilityWindow(Subscriber subscriber, String domain) {
        if (this.status == Status.WEBSITE) {
            if (checkIfWebsiteAlreadyExists(domain)) {
                JFrame frame = new ServiceUnavailableFrame(this);
                this.dispose();
            } else {
                JFrame frame = new ServiceAvailableFrame(subscriber, domain);
                this.dispose();
            }
        }
    }


    private void clearTextField() {
        this.domainTextField.setText("");
    }

    private String getDomainFromTextField() {
        return this.domainTextField.getText();
    }

    private boolean checkIfWebsiteAlreadyExists(String domain) {
        return PostgreSQLConnect.checkIfWebsiteExists(domain);
    }

    private boolean checkIfDomainIsValid(String domain) {
        Pattern pattern = Pattern.compile("^(?!-)[A-Za-z0-9-]{1,63}(?<!-)$");
        Matcher matcher = pattern.matcher(domain);

        return matcher.matches();
    }
}
