import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) {
        // Чтение файла
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
        // Шифрование
        try {
            KeyGenerator keygenerator = KeyGenerator.getInstance("DES");
            SecretKey myDesKey = keygenerator.generateKey();
            key = myDesKey.getEncoded().toString();
            Cipher desCipher;
            desCipher = Cipher.getInstance("DES");
            byte[] text = fileContent.toString().getBytes("UTF8");

            desCipher.init(Cipher.ENCRYPT_MODE, myDesKey);
            byte[] textEncrypted = desCipher.doFinal(text);
            System.out.print("Зашифрованный текст: ");
            String s = new String(textEncrypted);
            System.out.println(s);
            str = s;
            desCipher.init(Cipher.DECRYPT_MODE, myDesKey);
            byte[] textDecrypted = desCipher.doFinal(textEncrypted);
            System.out.print("Исходный текст: ");
            s = new String(textDecrypted);
            System.out.println(s);
        } catch (Exception e) {
            System.out.println("Ошибка");
        }
        System.out.println("Ключ шифрования: " + key);
        // Запись зашифрованного текста в файл
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("encrypted.txt");
            writer.print(str);
        } catch (IOException e) {
            System.err.println("Ошибка записи в файл: " + e.getMessage());
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
        //Запись ключа в файл
        writer = null;
        try {
            writer = new PrintWriter("key.txt");
            writer.print(key);
        } catch (IOException e) {
            System.err.println("Ошибка записи в файл: " + e.getMessage());
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}