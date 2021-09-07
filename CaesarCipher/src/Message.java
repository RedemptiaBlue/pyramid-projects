import java.io.Serializable;

public class Message implements Serializable {
    String message;

    Message(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
