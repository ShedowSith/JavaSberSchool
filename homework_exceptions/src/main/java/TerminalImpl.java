import api.MessagesApi;
import exceptions.*;

public class TerminalImpl {
    private final TerminalServer server;
    private final PinValidator pinValidator;
    MessagesApi messages;
    public TerminalImpl(MessagesApi messages) {
        this.server = new TerminalServer();
        this.pinValidator = new PinValidator();
        this.messages = messages;
    }
    public boolean entry(String pass) throws PasswordException {
        if(pass.length()!=4) throw new PasswordException("Пин-код должен содержать 4 цифры");
        try {
            return pinValidator.checkPassword(pass);
        } catch (AccountIsLockedException e) {
            messages.showMessages(e.getMessage());
        } catch (PasswordException e1){
            messages.showMessages(e1.getMessage());
        } catch (InvalidPasswordException e2){
            messages.showMessages(e2.getMessage());
        }
        return false;
    }
    public void printScore(){
        messages.showMessages("Средств на счете: "+ server.checkScore());
    }
    public void putMoney(int money){
        try {
            server.putMoney(money);
        } catch (TerminalServerException e) {
            messages.showMessages(e.getMessage());
        } catch (MultiplicityException e1) {
            messages.showMessages(e1.getMessage());
        }
    }
    public void getMoney(int money){
        try {
            boolean status = server.withdrawMoney(money);
            if(status) messages.showMessages("Вывод средств...");
        } catch (InsufficientFundsException e) {
            messages.showMessages(e.getMessage());
        }catch (MultiplicityException e1) {
            messages.showMessages(e1.getMessage());
        }
    }
}
