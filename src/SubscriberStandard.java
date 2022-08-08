public class SubscriberStandard extends Subscriber {
    public SubscriberStandard(int id, String username, double funds) {
        super(id, username, funds);
    }

    public SubscriberStandard(Subscriber prevSubscriber) {
        super(prevSubscriber.id, prevSubscriber.username, prevSubscriber.funds);
    }
}
