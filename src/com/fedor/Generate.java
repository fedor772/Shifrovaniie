package com.fedor;

import com.fedor.Files;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;
	
public class Generate {
	public static void main(String[] args) {
		launch();
	}
  public static void launch() {
		try {
			Files files = new Files();
			KeyGenerator keyGen = KeyGenerator.getInstance("DES");
			SecretKey secretKey = keyGen.generateKey();
			String encodedKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());
			files.writefile("key.txt", encodedKey);
			System.out.println(encodedKey);
		} catch (Exception e) {
			System.err.println("Ошибка генерации ключа: " + e.getMessage());
		}
	}
}