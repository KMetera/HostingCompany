import java.util.List;

public abstract class Subscriber {
    protected int id;
    protected String username;
    protected double funds;

    public Subscriber(int id, String username, double funds) {
        this.id = id;
        this.username = username;
        this.funds = funds;
    }

    protected void payForService(Service service) {
        PostgreSQLConnect.addNewPaidService(this, service);
    }

    protected void bookService() {
        PostgreSQLConnect.addNewService(this, new ServerFtp());
    }

    protected void bookService(String domain) {
        PostgreSQLConnect.addNewService(this, new Website(domain));
    }

    protected void addFunds(double value) {
        PostgreSQLConnect.giveMoneyToSubscriber(this, value);
    }

    public int getId() {
        return this.id;
    }

    public String getUsername() {
        return this.username;
    }

    public double getFunds() {
        this.funds = PostgreSQLConnect.getSubscriberFunds(this.getId());
        return this.funds;
    }

    public List<Service> getBookedServices() {
        return PostgreSQLConnect.getBookedServices(getId());
    }
}
