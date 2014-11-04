package voldemort.store.venice;

import voldemort.store.routed.Pipeline;

/**
 * Class which stores the components of VeniceMessage, and is the format specified in the Kafka Serializer
 */
public class VeniceMessage {

    // TODO: eliminate magic numbers when finished debugging
    public static final byte DEFAULT_MAGIC_BYTE = 22;
    public static final byte DEFAULT_SCHEMA_VERSION = 17;

    private byte magicByte;
    private byte schemaVersion;

    private OperationType operationType;
    private byte[] payload;

    // TODO: find best data type for timestamp
    private Object timestamp;

    // A message without a payload (used for deletes)
    public VeniceMessage(OperationType type) {
        this.operationType = (type == OperationType.PUT) ? OperationType.ERROR : type;
        this.magicByte = DEFAULT_MAGIC_BYTE;
        this.schemaVersion = DEFAULT_SCHEMA_VERSION;
        this.timestamp = null;
        this.payload = new byte[0];
    }

    public VeniceMessage(OperationType type, byte[] payload) {
        this.magicByte = DEFAULT_MAGIC_BYTE;
        this.schemaVersion = DEFAULT_SCHEMA_VERSION;
        this.operationType = type;
        this.payload = payload;
        this.timestamp = null;
    }

    public byte getMagicByte() {
        return magicByte;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public byte getSchemaVersion() {
        return schemaVersion;
    }

    public byte[] getPayload() {
        return payload;
    }

    public String toString() {
        return operationType.toString() + " " + payload.toString();
    }

    public Object getTimestamp() {
        return timestamp;
    }

}
