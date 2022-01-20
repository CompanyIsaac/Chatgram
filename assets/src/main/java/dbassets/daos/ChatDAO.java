package dbassets.daos;

import dbassets.models.Chat;
import dbassets.models.User;

import java.util.List;

public interface ChatDAO {

    List<Chat> getAll();

    boolean checkChatName(Chat chat);
    boolean addChat(Chat chat);
    int getChatAdminId(Chat Chat);
    List<Chat> getChatByName(String name);
    List<Chat> getChatByCategory(String categ);
    void delete(Chat Chat);

}