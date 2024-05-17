package com.fedor;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.BufferedReader;
import com.fedor.Files;

public class Encrypt {
    public static void main(String[] args) {
        launch();
    }
    public static void launch() {
        Files files = new Files();
        String text = files.readfile("encrypted.txt");
        System.out.println(encrypt(text));
    }
    public static String encrypt (String file) {
        try {
            KeyGenerator keygenerator = KeyGenerator.getInstance("DES");
            SecretKey myDesKey = keygenerator.generateKey();

            Cipher desCipher = Cipher.getInstance("DES");
            byte[] encryptedText = file.getBytes("UTF8");

            desCipher.init(Cipher.DECRYPT_MODE, myDesKey);
            byte[] decryptedText = desCipher.doFinal(encryptedText);

            String decryptedString = new String(decryptedText);
            return decryptedString;
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}