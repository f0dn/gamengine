package gamengine.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * A class that is used to run a game server
 */
public abstract class GameServer extends Thread {
    
    private DatagramSocket socket;

    /**
     * Used for instantiating a {@link GameServer}
     */
    public GameServer() {
        try {
            this.socket = new DatagramSocket(1331);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    /**
     * This function is called for every {@link Packet} sent from the client
     *
     * @param packetID the ID corresponding to the packet {@code data}
     * @param data the data sent from the client
     * @param ipAddress the IP Address of the packet
     * @param port the port of the client
     */
    public abstract void serverRun(String packetID, String data, InetAddress ipAddress, int port);

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
            this.serverRun(id, message.substring(2), packet.getAddress(), packet.getPort());
        }
    }

    /**
     * A function that sends data to the client
     * <p>Usually called at the end of {@link GameServer#serverRun(packetID, data, ipAddress, port)}</p>
     *
     * @param packet the {@link Packet} to send to the client
     * @param ipAddress the IP adress to which to send the data
     * @param port the port to which to send the data
     */
    public void sendData(Packet packet, InetAddress ipAddress, int port) {
        byte[] data = (packet.getId() + packet.getData()).getBytes();
        DatagramPacket datagramPacket = new DatagramPacket(data, data.length, ipAddress, port);
        try {
            socket.send(datagramPacket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}