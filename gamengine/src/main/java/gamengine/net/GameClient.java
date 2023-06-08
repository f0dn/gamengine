package gamengine.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * A class that is used to run a client
 */
public abstract class GameClient extends Thread {
    
    private InetAddress ipAddress;
    private DatagramSocket socket;

    /**
     * Used for instantiating a {@link GameClient}
     * 
     * @param ipAddress the ipAddress of the server
     */
    public GameClient(String ipAddress) {
        try {
            this.socket = new DatagramSocket();
            this.ipAddress = InetAddress.getByName(ipAddress);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * This function is called for every {@code packet} sent from the server
     *
     * @param packetID the ID corresponding to the packet {@code data}
     * @param data the data sent from the client
     */
    public abstract void clientRun(String packetID, String data);

    public void run() {
        while (true) {
            byte[] data = new byte[1024];
            DatagramPacket packet = new DatagramPacket(data, data.length);
            try {
                socket.receive(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String message = new String(packet.getData()).trim();
            String id = message.substring(0, 2);
            this.clientRun(id, message.substring(2));
        }
    }

    /**
     * A function that sends data to the server
     * <p>Usually called at the end of {@link GameClient#clientRun(packetID, data)}</p>
     *
     * @param packet the {@link Packet} to send to the server
     */
    public void sendData(Packet packet) {
        byte[] data = (packet.getId() + packet.getData()).getBytes();
        DatagramPacket datagramPacket = new DatagramPacket(data, data.length, this.ipAddress, 1331);
        try {
            socket.send(datagramPacket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
