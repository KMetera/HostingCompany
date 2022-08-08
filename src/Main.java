import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Subscriber subscriber = PostgreSQLConnect.getSubscriber(2);
        JFrame frame = new PayFrame(subscriber);
    }
}
