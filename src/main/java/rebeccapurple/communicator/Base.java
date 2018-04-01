package rebeccapurple.communicator;

public interface Base<PACKET> {
    void close();
    void cancel(Task<PACKET> task);
}
