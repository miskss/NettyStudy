package course_4.enums;

import course_4.serializer.JsonSerizalizer;
import course_4.serializer.Serializer;

/**
 * @author peter
 * date: 2019-10-29 15:50
 **/
public enum SerializedTypeEnum {
    JSON((byte) 1, new JsonSerizalizer());

    private byte code;

    private Serializer serializer;

    SerializedTypeEnum(byte code, Serializer serializer) {
        this.code = code;
        this.serializer = serializer;
    }

    public byte getCode() {
        return code;
    }

    public Serializer getSerializer() {
        return serializer;
    }

    public static SerializedTypeEnum fromCode(byte code) {

        for (SerializedTypeEnum value : SerializedTypeEnum.values()) {
            if (value.code == code) return value;
        }

        return null;
    }
}
