package dbassets.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;

public class Chat implements Serializable {

    private final IntegerProperty id = new SimpleIntegerProperty(this, "id", -2);
    private final IntegerProperty chatAdminId = new SimpleIntegerProperty(this, "chatAdminId", -2);
    private final StringProperty chatName = new SimpleStringProperty(this, "chatName", "");
    private final StringProperty rules = new SimpleStringProperty(this, "rules", "");



    private final StringProperty category = new SimpleStringProperty(this, "category", "");


    public Chat() {}

    @Override
    public String toString() {
        return "Chat{" +
                "id=" + getId() +
                ", chatName=" + getChatName() +
                ", rules=" + getRules() +
                ", category=" + getCategory() +
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

    public int getChatAdminId() {
        return chatAdminId.get();
    }

    public IntegerProperty chatAdminIdProperty() {
        return chatAdminId;
    }

    public void setChatAdminId(int roomAdminId) {
        this.chatAdminId.set(roomAdminId);
    }

    public String getChatName() {
        return chatName.get();
    }

    public StringProperty chatNameProperty() {
        return chatName;
    }

    public void setChatName(String chatName) {
        this.chatName.set(chatName);
    }

    public String getRules() {
        return rules.get();
    }

    public StringProperty rulesProperty() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules.set(rules);
    }

    public String getCategory() {
        return category.get();
    }

    public StringProperty categoryProperty() {
        return category;
    }

    public void setCategory(String category) {
        this.category.set(category);
    }
}
