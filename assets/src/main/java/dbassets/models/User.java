package dbassets.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;

public class User implements Serializable {

    private final IntegerProperty id = new SimpleIntegerProperty(this, "id", -2);
    private final StringProperty name = new SimpleStringProperty(this,"name","");
    private final StringProperty email = new SimpleStringProperty(this,"email","");
    private final StringProperty pw = new SimpleStringProperty(this,"pw","");
    private final StringProperty interested = new SimpleStringProperty(this,"interested","");

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + getId() +
                ", name=" + getName() +
                ", email=" + getEmail() +
                ", pw=" + getPw() +
                ", interested=" + getInterested() +
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

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getPw() {
        return pw.get();
    }

    public StringProperty pwProperty() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw.set(pw);
    }

    public String getInterested() {
        return interested.get();
    }

    public StringProperty interestedProperty() {
        return interested;
    }

    public void setInterested(String interested) {
        this.interested.set(interested);
    }

}
