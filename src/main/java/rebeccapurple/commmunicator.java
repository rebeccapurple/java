package rebeccapurple;

public class commmunicator {

    public interface Task<PACKET> extends rebeccapurple.Task<PACKET> {

    }

    public interface Request<PACKET> extends Task<PACKET>, rebeccapurple.Request<PACKET, PACKET> {

    }

    public interface Channel<T> {

    }

    public interface Base<PACKET> {
        void close();
        void cancel(Task<PACKET> task);
    }

    public interface Client<PACKET> extends Base<PACKET> {
        <TASK extends Task<PACKET>> TASK send(TASK task);
        <REQUEST extends Request<PACKET>> REQUEST send(REQUEST task);

        void connect();
        void connect(Listener<Client<PACKET>> connect);
        void disconnect();
        void disconnect(Listener<Client<PACKET>> disconnect);
    }

    public interface Server<PACKET> extends Base<PACKET> {
        void listen();
        void listen(Listener<Server<PACKET>> listen);
    }
}
