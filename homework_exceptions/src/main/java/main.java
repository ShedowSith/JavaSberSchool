import exceptions.PasswordException;
import java.io.IOException;
import java.util.Scanner;


public class main {
    public static void main(String[] args) throws IOException {
        MyMessages messages = new MyMessages();
        TerminalImpl terminal = new TerminalImpl(messages);
        Scanner scanner = new Scanner(System.in);
        boolean result = false;
        String password = "";
        messages.showMessages("Для выхода введите 'q'.");
        do {
            messages.showMessages("Введите пин-код.");
            password = scanner.next();
            if (password.equals("q"))System.exit(0);
            try {
                result = terminal.entry(password);
            } catch (PasswordException e) {
                messages.showMessages(e.getMessage());
            }

        }while (!result);
        int o = 0;
        do {
            messages.showMessages("1 - Проверить состояние счета \n2 - Положить деньги\n3 - Снять деньги\nq - Выход");
            if(scanner.hasNextInt()){
                o = scanner.nextInt();
                switch (o){
                    case 1: terminal.printScore();
                        break;
                    case 2:
                        messages.showMessages("Введите сумму.");
                        if(scanner.hasNextInt()){
                            int m = scanner.nextInt();
                            terminal.putMoney(m);
                        }else {
                            scanner.next();
                            messages.showMessages("Введена неверная сумма.");
                        }
                        break;
                    case 3:
                        messages.showMessages("Введите сумму.");
                        if(scanner.hasNextInt()){
                            int m2 = scanner.nextInt();
                            terminal.getMoney(m2);
                        }else {
                            scanner.next();
                            messages.showMessages("Введена неверная сумма.");
                        }
                        break;
                }
            } else {
                String str = scanner.next();
                if (str.equals("q")) System.exit(0);
            }
        }while (true);
    }
}
