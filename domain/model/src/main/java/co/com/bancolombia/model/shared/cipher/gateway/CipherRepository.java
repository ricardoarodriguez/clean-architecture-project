package co.com.bancolombia.model.shared.cipher.gateway;

public interface CipherRepository {
    String encrypt(String textToEncrypt);
    String decrypt(String textToDecrypt);
}
