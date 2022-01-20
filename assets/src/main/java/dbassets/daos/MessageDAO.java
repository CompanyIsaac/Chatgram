package dbassets.daos;

import dbassets.models.Message;

import java.util.List;

public interface MessageDAO {

    List<Message> getAll();
    List<Message> getMessageByChat(int chatId);

    boolean addMessage(Message message);
    void delete(Message message);

}
