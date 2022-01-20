package chatgram;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * JavaFX App
 */
public class App extends Application {

    private static int loggedInUser;
    private static int currentChat;
    private static Scene scene;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("login"));
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Chatgram");
        stage.setHeight(450);
        stage.setWidth(620);
    }

    public static int getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(int loggedInUser) {
        App.loggedInUser = loggedInUser;
    }

    public static int getCurrentChat() {
        return currentChat;
    }

    public static void setCurrentChat(int currentRoom) {
        App.currentChat = currentRoom;
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
}