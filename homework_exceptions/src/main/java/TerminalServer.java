import exceptions.InsufficientFundsException;
import exceptions.MultiplicityException;
import exceptions.TerminalServerException;

public class TerminalServer {
    private int score;

    public TerminalServer() {
        this.score = 0;
    }
    public int checkScore(){
        return score;
    }
    public boolean withdrawMoney(int money) throws InsufficientFundsException, MultiplicityException {
        isMultiplicity100(money);
        if(score-money<0) throw new InsufficientFundsException("Недостаточно средств на счету.");
        score-=money;
        return true;
    }
    public boolean putMoney(int money) throws TerminalServerException, MultiplicityException {
        isMultiplicity100(money);
        try {
            score+=money;
        }catch (Exception e){
            throw new TerminalServerException("Что то пошло не так "+e);
        }

        return true;
    }
    private void isMultiplicity100(int money) throws MultiplicityException {
        if(money%100 != 0) throw new MultiplicityException("Введите сумму кратную 100.");
    }
}
