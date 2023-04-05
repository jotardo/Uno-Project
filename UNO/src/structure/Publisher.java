package structure;

public interface Publisher {
	public void addSubscriber(Subscriber s);
	public void removeSubscriber(Subscriber s);
	public void notifySubscribers();
}
