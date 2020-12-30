import exceptions.AccountIsLockedException;
import exceptions.InvalidPasswordException;
import exceptions.PasswordException;

import java.util.regex.Pattern;


public class PinValidator {
    private final String password = "0000";
    private int attempts;
    private boolean locking;
    private int countTime;
    public PinValidator() {
        attempts = 3;
        locking = false;
        countTime = 10;
    }
    public boolean isLocking(){
        return locking;
    }
    public boolean checkPassword(String pin) throws AccountIsLockedException, PasswordException, InvalidPasswordException {
        Pattern pattern = Pattern.compile("\\d+");
        if(!pattern.matcher(pin).matches()) {
            //выдать исключение о вводе не цифрового пароля
            throw new PasswordException("Предупреждение, введен не цифровой символ.");
        }
        if(locking) throw new AccountIsLockedException("Осталось до снятия блокировки " + countTime + " c.");
        if(pin.equals(password)) {
            return true;
        }else {
            attempts--;
            if(attempts == 0)locking();
            throw new InvalidPasswordException("Предупреждение, введен неверный пин-код.");
        }
    }
    private void locking(){
        locking = true;
        new Thread(() -> {
            while (countTime>0){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countTime--;
            }
            locking = false;
            countTime = 10;
            attempts = 3;
        }).start();

    }
}