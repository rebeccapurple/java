package rebeccapurple.communicator;

import rebeccapurple.Listener;

public interface Server<PACKET> extends Base<PACKET> {
    void listen();
    void listen(Listener<Server<PACKET>> listen);
}
