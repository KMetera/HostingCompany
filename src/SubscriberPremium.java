public class SubscriberPremium extends Subscriber{

    public SubscriberPremium(int id, String username, double funds){
        super(id, username, funds);
    }

    public SubscriberPremium(Subscriber prevSubscriber) {
        super(prevSubscriber.id, prevSubscriber.username, prevSubscriber.funds);
    }

    private void sendReport(StringBuilder report){}
}
