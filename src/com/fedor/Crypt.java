package com.fedor;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import java.util.Base64;
import javax.crypto.KeyGenerator;
import java.io.BufferedReader;
import java.io.IOException;
import com.fedor.Files;

public class Crypt {
    public static void main(String[] args) {
        launch();
    }

    public static void launch() {
        Files files = new Files();
        String text = files.readfile("text.txt");
        String key = files.readfile("key.txt");
        SecretKey nkey = files.toSecretKey(key);
        String[] textEncrypted = crypt(text, nkey);
        files.writefile("encrypted.txt", textEncrypted[0]);
        files.writefile("key.txt", textEncrypted[1]);
    }

    public static String[] crypt(String fileContent, SecretKey myDesKey) {
        try {
            KeyGenerator keygenerator = KeyGenerator.getInstance("DES");
            String key = Base64.getEncoder().encodeToString(myDesKey.getEncoded());
            Cipher desCipher = Cipher.getInstance("DES");

            byte[] text = fileContent.getBytes("UTF8");

            desCipher.init(Cipher.ENCRYPT_MODE, myDesKey);
            byte[] textEncrypted = desCipher.doFinal(text);
            System.out.print("Зашифрованный файл: ");
            System.out.println(Base64.getEncoder().encodeToString(textEncrypted));
            String str = Base64.getEncoder().encodeToString(textEncrypted);

            byte[] encryptedBytes = Base64.getDecoder().decode(str);
            desCipher.init(Cipher.DECRYPT_MODE, myDesKey);
            byte[] textDecrypted = desCipher.doFinal(encryptedBytes);
            System.out.print("Расшифрованный файл: ");
            String decryptedString = new String(textDecrypted, "UTF8");
            System.out.println(decryptedString);
            System.out.println("Ключ расшифровки: " + key);
            String[] arr = { Base64.getEncoder().encodeToString(textEncrypted), key };
            return arr;
        } catch (Exception e) {
            System.err.println("Ошибка шифрования: " + e.getMessage());
            String[] arr = { "error", "error" };
            return arr;
        }
    }
}