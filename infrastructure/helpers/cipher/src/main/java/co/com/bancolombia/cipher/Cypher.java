package co.com.bancolombia.cipher;

import co.com.bancolombia.model.shared.cipher.gateway.CipherRepository;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;
import java.util.Base64;


@Component
public class Cypher implements CipherRepository {
    private static final String SECRET_KEY = "SecretKey";
    private static final String SALT = "SALT";

    public String encrypt(String textToEncrypt) {
        try {
            byte[] a = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            IvParameterSpec Iv_Specifications = new IvParameterSpec(a);

            SecretKeyFactory Secret_Key_Factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec Key_Spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALT.getBytes(), 65536, 256);
            SecretKey Temp_Secret_Key = Secret_Key_Factory.generateSecret(Key_Spec);
            SecretKeySpec Secret_Key = new SecretKeySpec(Temp_Secret_Key.getEncoded(), "AES");

            Cipher AES_Cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            AES_Cipher.init(Cipher.ENCRYPT_MODE, Secret_Key, Iv_Specifications);
            return Base64.getEncoder().encodeToString(
                    AES_Cipher.doFinal(textToEncrypt.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }

    public String decrypt(String textToBeDecrypt) {
        try {
            byte[] a = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            IvParameterSpec IV_Specifications = new IvParameterSpec(a);

            SecretKeyFactory Secret_Key_Factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec Key_Spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALT.getBytes(), 65536, 256);
            SecretKey Temp_Secret_Key = Secret_Key_Factory.generateSecret(Key_Spec);
            SecretKeySpec Secret_Key = new SecretKeySpec(Temp_Secret_Key.getEncoded(), "AES");

            Cipher AES_Cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            AES_Cipher.init(Cipher.DECRYPT_MODE, Secret_Key, IV_Specifications);
            return new String(AES_Cipher.doFinal(Base64.getDecoder().decode(textToBeDecrypt)));
        } catch (Exception e) {
            System.out.println("Error while decrypting: " + e);
        }
        return null;
    }
}
