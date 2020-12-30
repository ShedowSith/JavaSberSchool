import api.MessagesApi;

public class MyMessages implements MessagesApi {
    @Override
    public void showMessages(String message) {
        System.out.println(message);
    }
}
