import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Crypt {
    public static void main(String[] args) {
        //Чтение файла
        String fileName = "text.txt";
        StringBuilder fileContent = new StringBuilder();
        String str = "";
        String key = "";
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
            key = Base64.getEncoder().encodeToString(myDesKey.getEncoded());
            Cipher desCipher = Cipher.getInstance("DES");

            byte[] text = fileContent.toString().getBytes("UTF8");

            desCipher.init(Cipher.ENCRYPT_MODE, myDesKey);
            byte[] textEncrypted = desCipher.doFinal(text);
            System.out.print("Зашифрованный файл: ");
            System.out.println(Base64.getEncoder().encodeToString(textEncrypted));
            str = Base64.getEncoder().encodeToString(textEncrypted);

            byte[] encryptedBytes = Base64.getDecoder().decode(str);
            desCipher.init(Cipher.DECRYPT_MODE, myDesKey);
            byte[] textDecrypted = desCipher.doFinal(encryptedBytes);
            System.out.print("Расшифрованный файл: ");
            String decryptedString = new String(textDecrypted, "UTF8");
            System.out.println(decryptedString);

        } catch (Exception e) {
            System.err.println("Ошибка шифрования: " + e.getMessage());
        }

        System.out.println("Ключ расшифровки: " + key);

        //Запись расшифровки в файл
        try (PrintWriter writer = new PrintWriter("encrypted.txt")) {
            writer.print(str);
        } catch (IOException e) {
            System.err.println("Ошибка записи в файл: " + e.getMessage());
        }

        //Запись ключа в файл
        try (PrintWriter writer = new PrintWriter("key.txt")) {
            writer.print(key);
        } catch (IOException e) {
            System.err.println("Ошибка записи в файл: " + e.getMessage());
        }
    }
}