package course_4;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.nio.charset.StandardCharsets;

/**
 * @author peter
 * date: 2019-10-22 16:51
 **/
@Data
@AllArgsConstructor
public class Login {
    private String username;
    private String password;

    public static void main(String[] args) throws JsonProcessingException {
        Login login = new Login("小明", "xxx");
        ObjectMapper mapper = new ObjectMapper();

        byte[] bytes = mapper.writeValueAsBytes(login);
        for (byte aByte : bytes) {
            System.out.print(aByte);
        }
        System.out.println();
        String asString = mapper.writeValueAsString(login);
        System.out.println(asString);
        byte[] bytes1 = asString.getBytes(StandardCharsets.UTF_8);
        StringBuilder re = new StringBuilder();
        for (byte aByte : bytes1) {
            re.append(",").append(Byte.toUnsignedInt(aByte));
        }

        byte[] by= new byte[]{123, 34, 117, 115, 101, 114, 110, 97, 109, 101, 34, 58, 34, (byte) 229, (byte)176, (byte)143, (byte)230, (byte)152, (byte)142, 34, 44, 34, 112, 97, 115, 115, 119, 111, 114, 100, 34, 58, 34, 120, 120, 120, 34, 125};

        System.out.println(re.toString());
        System.out.println(new String(by, StandardCharsets.UTF_8));

        Integer ii= 12;
        System.out.println(Integer.toHexString(ii));
    }

}
