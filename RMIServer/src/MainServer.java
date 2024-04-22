import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class MainServer {
    public static void main(String[] args) throws Exception {
        System.out.println("Server is running...");
        String url = "rmi://localhost:9001/chat";
        try {
            ChatImplmentation chat = new ChatImplmentation();
            LocateRegistry.createRegistry(9001);
            Naming.rebind(url, chat);
            System.out.println("Server RUNNING...");

        } catch (Exception e) {
            System.out.println("Server is not connected: " + e);
        }
    }
}
