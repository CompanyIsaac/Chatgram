package dbassets.daos;

import dbassets.models.Message;
import dbassets.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageDAOImpl implements MessageDAO {

    private static final String DB_NAME = "jdbc:sqlite:D:/Asztal/alkfejl/Chatgram/projekt/assets/cgdatabase.db";
    private static final String SELECT_ALL_MESSAGE = "SELECT * FROM message;";
    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS message ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'userId' INTEGER,'chatId' INTEGER,'text' TEXT, 'userName' TEXT);";
    private static final String INSERT_MESSAGE = "INSERT INTO message ('userId', 'chatId', 'text','userName') VALUES (?,?,?,?);";
    private static final String GET_MESSAGE_BY_CHAT = "SELECT * FROM message WHERE chatId = ?;";
    private static final String DELETE = "DELETE FROM message WHERE id = ?;";

    public MessageDAOImpl() throws ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        constructTables();
    }
    private void constructTables() {
        try(Connection conn = DriverManager.getConnection(DB_NAME);
            Statement st = conn.createStatement()
        ){
            st.execute(CREATE_TABLE);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Message> getAll() {
        List<Message> result = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DB_NAME);
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(SELECT_ALL_MESSAGE)

        ) {
            while(rs.next()){
                Message m = new Message();
                m.setId(rs.getInt(1));
                m.setUserId(rs.getInt(2));
                m.setChatId(rs.getInt(3));
                m.setText(rs.getString(4));
                m.setUserName(rs.getString(5));

                result.add(m);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return result;
    }

    @Override
    public boolean addMessage(Message message) {

        try(Connection conn = DriverManager.getConnection(DB_NAME);
            PreparedStatement pst = conn.prepareStatement(INSERT_MESSAGE)
        ) {
            pst.setInt(1, message.getUserId());
            pst.setInt(2, message.getChatId());
            pst.setString(3, message.getText());
            pst.setString(4, message.getUserName());

            int result = pst.executeUpdate();
            return result == 1;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public List<Message> getMessageByChat(int chatId) {
        List<Message> result = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DB_NAME);
             PreparedStatement pst = conn.prepareStatement(GET_MESSAGE_BY_CHAT);
        ) {
            pst.setInt(1, chatId);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                Message m = new Message();
                m.setUserId(rs.getInt(2));
                m.setText(rs.getString(4));
                m.setUserName(rs.getString(5));


                result.add(m);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return result;
    }
    @Override
    public void delete(Message message) {
        try(Connection conn = DriverManager.getConnection(DB_NAME);
            PreparedStatement pst = conn.prepareStatement(DELETE)
        ) {
            pst.setInt(1, message.getId());
            pst.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}