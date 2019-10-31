package course_7.course_6_stick_package.serializer;

/**
 * @author peter
 * date: 2019-10-29 15:46
 **/
public interface Serializer {

    /**
     * 序列化
     *
     * @return Byte
     */
    Byte getSerializerAlgorithm();


    byte[] serialize(Object object);


    <T> T deserialize(Class<T> clazz, byte[] bytes);

}