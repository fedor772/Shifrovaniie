import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import com.fedor.Crypt;
import com.fedor.Encrypt;
import com.fedor.Generate;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    Crypt crypt = new Crypt();
    Encrypt encrypt = new Encrypt();
    Generate generate = new Generate();
    System.out.println("Режимы работы: 0 - шифрование, 1 - расшифровка, 2 - генерация ключа");
    System.out.print("Выберите режим работы: ");
    int mode = sc.nextInt();
    System.out.println("Загрузка...");
        System.out.print("\033[H\033[2J");
    switch (mode) {
      case 0:
        crypt.launch();
        break;
      case 1:
        encrypt.launch();
        break;
      case 2:
        generate.launch();
        break;
      default:
        System.out.println("Неверный режим работы");
        break;
    }
  }
}