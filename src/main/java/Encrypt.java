import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Encrypt {
    public static void main(String[] args) {
        String fileName = "encrypted.txt";
        StringBuilder fileContent = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                fileContent.append(line).append("\n");
            }
        } catch (IOException e) {
            System.err.println("Ошибка чтения файла: " + e.getMessage());
        }

        try {
            KeyGenerator keygenerator = KeyGenerator.getInstance("DES");
            SecretKey myDesKey = keygenerator.generateKey();

            Cipher desCipher = Cipher.getInstance("DES");
            byte[] encryptedText = fileContent.toString().getBytes("UTF8");

            desCipher.init(Cipher.DECRYPT_MODE, myDesKey);
            byte[] decryptedText = desCipher.doFinal(encryptedText);

            String decryptedString = new String(decryptedText);

            System.out.println("Расшифрованный текст: " + decryptedString);
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}