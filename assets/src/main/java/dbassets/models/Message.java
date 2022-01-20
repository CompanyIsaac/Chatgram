package dbassets.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;

public class Message implements Serializable {

    private final IntegerProperty id = new SimpleIntegerProperty(this, "id", -2);
    private final IntegerProperty userId = new SimpleIntegerProperty(this, "userId", -2);
    private final IntegerProperty chatId = new SimpleIntegerProperty(this, "chatId", -2);
    private final StringProperty text = new SimpleStringProperty(this, "text", "");
    private final StringProperty userName = new SimpleStringProperty(this, "userName", "");

    public Message() {
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + getId() +
                ", userId=" + getUserId() +
                ", chatId=" + getChatId() +
                ", text=" + getText() +
                '}';
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public int getUserId() {
        return userId.get();
    }

    public IntegerProperty userIDProperty() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId.set(userId);
    }

    public int getChatId() {
        return chatId.get();
    }

    public IntegerProperty chatIdProperty() {
        return chatId;
    }

    public void setChatId(int roomId) {
        this.chatId.set(roomId);
    }

    public String getText() {
        return text.get();
    }

    public StringProperty textProperty() {
        return text;
    }

    public void setText(String content) {
        this.text.set(content);
    }

    public String getUserName() { return userName.get(); }

    public StringProperty userNameProperty() { return userName; }

    public void setUserName(String userName) { this.userName.set(userName); }
}