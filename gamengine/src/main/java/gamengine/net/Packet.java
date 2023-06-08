package gamengine.net;

/**
 * A class that is used to make the string data which {@link GameClient} and {@link GameServer} send to each other more manageable
 */
public abstract class Packet {

    private String packetId;

    private String data;

    /**
     * Used for instantiating a {@link Packet}
     *
     * @param id a string that represents the type of packet
     * @param data a string of data specific to the packet type
     */
    public Packet(String id, String data) {
        this.packetId = id;
        this.data = data;
        this.init(data);
    }

    /**
     * This is called when the {@link Packet} is filled with {@code data}
     *
     * @param data a string of data specific to the packet type
     */
    public abstract void init(String data);

    /**
     * Used to get the data that was initially stored in the packet
     * 
     * @return the packet's data
     */
    public String getData() {
        return this.data;
    }

    /**
     * Used to get the type of packet
     * 
     * @return the packet's id
     */
    public String getId() {
        return this.packetId;
    }
}