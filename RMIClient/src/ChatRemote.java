import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ChatRemote extends Remote {
    public ArrayList<Message> getAllMsgs() throws RemoteException;
    public void addMessage(Message ch) throws RemoteException;
}
