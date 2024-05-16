import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Режимы работы: 0 - шифрование, 1 - расшифровка");
    System.out.print("Выберите режим работы: ");
    int mode = sc.nextInt();
    switch (mode) {
      case 0:
        System.out.println("Загрузка...");
        comm("clear");
        comm("java -classpath .:target/dependency/* Crypt");
        break;
      case 1:
        System.out.println("Загрузка...");
        comm("clear");
        comm("java -classpath .:target/dependency/* Encrypt");
        break;
    }
  }

  public static void comm(String prc) {
    try {
      Process process = Runtime.getRuntime().exec(prc);
      BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
      String line;
      while ((line = reader.readLine()) != null) {
        System.out.println(line);
      }
      process.waitFor();
      reader.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}