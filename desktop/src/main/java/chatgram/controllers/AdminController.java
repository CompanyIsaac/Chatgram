package chatgram.controllers;

import chatgram.App;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import dbassets.daos.*;
import dbassets.models.Message;
import dbassets.models.Chat;
import dbassets.models.User;

import java.io.IOException;
import java.util.ArrayList;

public class AdminController {

    public UserDAO userdao = new UserDAOImpl();
    public User user = new User();
    public ChatDAO chatdao = new ChatDAOImpl();
    public TableView<Chat> chattable;
    public TableColumn<Chat, String> chatNameCol;
    public TableColumn<Chat, Void> deleteChatCol;
    public TableView<User> usertable;
    public TableColumn<User, String> nameCol;
    public TableColumn<User, String> emailCol;
    public TableColumn<User, Void> deleteUserCol;

    public AdminController() throws ClassNotFoundException {
    }


    @FXML
    public void initialize(){
        chattable.setVisible(false);
        usertable.setVisible(true);


        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        usertable.getColumns().add(nameCol);
        usertable.getColumns().add(emailCol);

        ArrayList<User> listuser;
        ObservableList<User> users = FXCollections.observableArrayList();
        listuser = (ArrayList<User>) userdao.getAll();
        users.addAll(listuser);

        usertable.setItems(users);

        deleteUserCol.setCellFactory(param -> new TableCell<>() {
            private final Button deleteuser = new Button("Delete");

            {
                deleteuser.setOnAction(event -> {
                    User user = getTableView().getItems().get(getIndex());
                    userdao.delete(user);
                    ArrayList<User> listuser;
                    ObservableList<User> users = FXCollections.observableArrayList();
                    listuser = (ArrayList<User>) userdao.getAll();
                    users.addAll(listuser);
                    usertable.setItems(users);
                });
            }
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(deleteuser);
                }
            }
        });
        usertable.getColumns().add(deleteUserCol);



        chatNameCol.setCellValueFactory(new PropertyValueFactory<>("chatName"));
        chattable.getColumns().add(chatNameCol);

        ArrayList<Chat> listchat;
        ObservableList<Chat> chats = FXCollections.observableArrayList();
        listchat = (ArrayList<Chat>) chatdao.getAll();
        chats.addAll(listchat);

        chattable.setItems(chats);

        deleteChatCol.setCellFactory(param -> new TableCell<>() {
            private final Button deletechat = new Button("Delete");

            {
                deletechat.setOnAction(event -> {
                    Chat chat = getTableView().getItems().get(getIndex());
                    chatdao.delete(chat);
                    ArrayList<Chat> listchat;
                    ObservableList<Chat> chats = FXCollections.observableArrayList();
                    listchat = (ArrayList<Chat>) chatdao.getAll();
                    chats.addAll(listchat);
                    chattable.setItems(chats);
                });
            }
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(deletechat);
                }
            }
        });
        chattable.getColumns().add(deleteChatCol);

    }

    @FXML
    public void users() {
        chattable.setVisible(false);
        usertable.setVisible(true);


    }

    @FXML
    public void chats() throws IOException {
        chattable.setVisible(true);
        usertable.setVisible(false);

    }

    @FXML
    public void messages() throws IOException {
        chattable.setVisible(false);
        usertable.setVisible(false);

    }

    @FXML
    public void logout() throws IOException {
        App.setLoggedInUser(-5);
        App.setRoot("login");
    }
}
