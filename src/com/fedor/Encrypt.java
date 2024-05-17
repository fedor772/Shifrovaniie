package com.fedor;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.BufferedReader;
import java.util.Base64;
import javax.crypto.spec.SecretKeySpec;
import com.fedor.Files;

public class Encrypt {
    public static void main(String[] args) {
        launch();
    }

    public static void launch() {
        Files files = new Files();
        String text = files.readfile("encrypted.txt");
        String encodedkey = files.readfile("key.txt");
        SecretKey key = files.toSecretKey(encodedkey);
        System.out.println(encrypt(text, key));
    }

    public static String encrypt(String file, SecretKey key) {
        try {
            Cipher desCipher = Cipher.getInstance("DES");
            byte[] encryptedText = file.getBytes("UTF8");

            desCipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] decryptedText = desCipher.doFinal(encryptedText);

            String decryptedString = new String(decryptedText);
            return decryptedString;
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}