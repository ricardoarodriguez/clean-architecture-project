package co.com.bancolombia.cipher;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CypherTest {

    private static final String SECRET_KEY = "SecretKey";
    private static final String SALT = "SALT";

    @Test
    void testEncrypt() {
        // Given
        Cypher encryption = new Cypher();
        String textToEncrypt = "Test123";

        // When
        String encryptedText = encryption.encrypt(textToEncrypt);

        // Then
        assertNotNull(encryptedText);
        assertNotEquals(textToEncrypt, encryptedText);
    }

    @Test
    void testDecrypt() {
        // Given
        Cypher encryption = new Cypher();
        String originalText = "Test123";
        String encryptedText = encryption.encrypt(originalText);

        // When
        String decryptedText = encryption.decrypt(encryptedText);

        // Then
        assertNotNull(decryptedText);
        assertEquals(originalText, decryptedText);
    }

    @Test
    void testDecryptWithInvalidText() {
        // Given
        Cypher encryption = new Cypher();
        String invalidEncryptedText = "InvalidText";

        // When
        String decryptedText = encryption.decrypt(invalidEncryptedText);

        // Then
        assertNull(decryptedText);
    }
}
