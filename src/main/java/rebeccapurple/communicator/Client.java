package rebeccapurple.communicator;

import rebeccapurple.Listener;

public interface Client<PACKET> extends Base<PACKET> {
    <TASK extends Task<PACKET>> TASK send(TASK task);
    <REQUEST extends Request<PACKET>> REQUEST send(REQUEST task);

    void connect();
    void connect(Listener<Client<PACKET>> connect);
    void disconnect();
    void disconnect(Listener<Client<PACKET>> disconnect);
}
