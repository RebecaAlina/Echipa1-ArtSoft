import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception{
        client client1 = new client("username1","key1");
        System.out.println("Semnatura primului client: "+hmacDigest(client1.getLogin(),client1.getKey(),"HmacMD5"));
        client client2 = new client("username2","key2");
        server s1 = new server(client1.getLogin(),client1.getKey());
        if(s1.check())
            System.out.println("The client is conected");
        else System.out.println("The client is not conected");
        s1.setLogin(client2.getLogin());
        s1.setKey(client2.getKey());
        if(s1.check())
            System.out.println("The client is conected");
        else System.out.println("The client is not conected");
    }

    public static String hmacDigest(String msg, String keyString, String algo) {
        String digest = null;
        try {
            SecretKeySpec key = new SecretKeySpec((keyString).getBytes(StandardCharsets.UTF_8), algo);
            Mac mac = Mac.getInstance(algo);
            mac.init(key);

            byte[] bytes = mac.doFinal(msg.getBytes(StandardCharsets.US_ASCII));

            StringBuilder hash = new StringBuilder();
            for (byte aByte : bytes) {
                String hex = Integer.toHexString(0xFF & aByte);
                if (hex.length() == 1) {
                    hash.append('0');
                }
                hash.append(hex);
            }
            digest = hash.toString();
        } catch (NoSuchAlgorithmException | InvalidKeyException ignored) {
        }
        return digest;
    }
}
