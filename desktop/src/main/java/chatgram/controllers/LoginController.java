package chatgram.controllers;

import chatgram.App;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import dbassets.daos.UserDAO;
import dbassets.daos.UserDAOImpl;
import dbassets.models.User;

import java.io.IOException;



public class LoginController {

    public TextField email;
    public PasswordField pw;
    public Label errormessage;
    public Button login;
    public User user = new User();
    public UserDAO userdao = new UserDAOImpl();

    public LoginController() throws ClassNotFoundException {
    }

    @FXML
    public void initialize(){
        email.textProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.matches("\\S+@\\S+\\.\\S+")){
                errormessage.setText("");
            }
            else{
                errormessage.setText("Invalid email");
            }
        });

        email.textProperty().bindBidirectional(user.emailProperty());
        pw.textProperty().bindBidirectional(user.pwProperty());

        login.disableProperty().bind(email.textProperty().isEmpty().or(pw.textProperty().isEmpty()).or(errormessage.textProperty().isNotEmpty()));

    }

    @FXML
    public void switchToRegistration() throws IOException {
        App.setRoot("registration");
    }
    @FXML
    public void login() throws IOException {
        if(userdao.checkUser(user)){
            user.setId(userdao.getIdByEmail(user));
            App.setLoggedInUser(user.getId());

            if(userdao.isAdmin(user)){
                App.setRoot("admin");
            }else{
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setTitle("Alert");
                a.setTitle("Alert");
                a.setHeaderText("You have no permission!");
                a.showAndWait();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alert");
            alert.setHeaderText("Alert");
            alert.setContentText("Incorrect email or password!");
            alert.showAndWait();
        }
    }
}
