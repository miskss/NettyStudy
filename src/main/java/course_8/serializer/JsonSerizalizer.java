package course_8.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import course_8.enums.SerializedTypeEnum;

import java.io.IOException;

/**
 * @author peter
 * date: 2019-10-29 15:50
 **/
public class JsonSerizalizer implements Serializer {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Byte getSerializerAlgorithm() {
        return SerializedTypeEnum.JSON.getCode();
    }

    @Override
    public byte[] serialize(Object object) {
        try {
            return objectMapper.writeValueAsBytes(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        try {
           return objectMapper.readValue(bytes,clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
