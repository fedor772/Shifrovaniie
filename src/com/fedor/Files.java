package com.fedor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Files {
  public static String readfile(String filename) {
    StringBuilder fileContent = new StringBuilder();

    try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
      String line;
      while ((line = br.readLine()) != null) {
        fileContent.append(line).append("\n");
      }
    } catch (IOException e) {
      System.err.println("Ошибка чтения файла: " + e.getMessage());
    }
    return fileContent.toString();
  }
  public static void writefile(String filename, String text) {
        try (PrintWriter writer = new PrintWriter(filename)) {
            writer.print(text);
        } catch (IOException e) {
            System.err.println("Ошибка записи в файл: " + e.getMessage());
        }
  }
}