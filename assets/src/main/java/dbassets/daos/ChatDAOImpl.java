package dbassets.daos;

import dbassets.models.Chat;
import dbassets.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChatDAOImpl implements ChatDAO {

    private static final String DB_NAME = "jdbc:sqlite:D:/Asztal/alkfejl/Chatgram/projekt/assets/cgdatabase.db";
    private static final String SELECT_ALL_CHAT = "SELECT * FROM chat;";
    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS chat ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'chatAdminId'INTEGER, 'chatName' TEXT, 'rules' TEXT, 'category' TEXT );";
    private static final String INSERT_CHAT = "INSERT INTO chat ('chatName', 'chatAdminId', 'rules', 'category') VALUES (?, ?, ?, ?);";
    private static final String CHECK_CHAT_NAME = "SELECT * FROM chat WHERE chatName=?;";
    private static final String GET_CHAT_BY_ID = "SELECT * FROM chat WHERE id = ?;";
    private static final String GET_CHAT_BY_NAME = "SELECT * FROM chat WHERE chatName LIKE ?;";
    private static final String GET_CHAT_BY_CATEGORY = "SELECT * FROM chat WHERE category LIKE ?;";
    private static final String DELETE = "DELETE FROM chat WHERE id = ?;";

    public ChatDAOImpl() throws ClassNotFoundException {
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
    public List<Chat> getAll() {
        List<Chat> result = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DB_NAME);
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(SELECT_ALL_CHAT)

        ) {
            while(rs.next()){
                Chat r = new Chat();
                r.setId(rs.getInt(1));
                r.setChatAdminId(rs.getInt(2));
                r.setChatName(rs.getString(3));
                r.setRules(rs.getString(4));
                r.setCategory(rs.getString(5));

                result.add(r);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return result;
    }

    @Override
    public boolean addChat(Chat chat) {

        try(Connection conn = DriverManager.getConnection(DB_NAME);
            PreparedStatement pst = conn.prepareStatement(INSERT_CHAT)
        ) {
            pst.setString(1, chat.getChatName());
            pst.setInt(2, chat.getChatAdminId());
            pst.setString(3, chat.getRules());
            pst.setString(4, chat.getCategory());

            int result = pst.executeUpdate();
            return result == 1;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public int getChatAdminId(Chat chat) {
        try(Connection conn = DriverManager.getConnection(DB_NAME);
            PreparedStatement pst = conn.prepareStatement(GET_CHAT_BY_ID)
        ) {
            pst.setInt(1, chat.getId());
            ResultSet rs = pst.executeQuery();

            if(rs.next()) {
                return rs.getInt(2);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public List<Chat> getChatByName(String chatName) {
        List<Chat> result = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DB_NAME);
             PreparedStatement pst = conn.prepareStatement(GET_CHAT_BY_NAME);
        ) {
            pst.setString(1, "%"+chatName+"%");
            ResultSet rs = pst.executeQuery();

            while(rs.next()){
                Chat r = new Chat();
                r.setId(rs.getInt(1));
                r.setChatAdminId(rs.getInt(2));
                r.setChatName(rs.getString(3));
                r.setRules(rs.getString(4));
                r.setCategory(rs.getString(5));

                result.add(r);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Chat> getChatByCategory(String category) {
        List<Chat> res = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DB_NAME);
             PreparedStatement pst = conn.prepareStatement(GET_CHAT_BY_CATEGORY);
        ) {
            pst.setString(1, "%"+category+"%");
            ResultSet rs = pst.executeQuery();

            while(rs.next()){
                Chat r = new Chat();
                r.setId(rs.getInt(1));
                r.setChatAdminId(rs.getInt(2));
                r.setChatName(rs.getString(3));
                r.setRules(rs.getString(4));
                r.setCategory(rs.getString(5));

                res.add(r);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return res;
    }


    @Override
    public void delete(Chat chat) {
        try(Connection conn = DriverManager.getConnection(DB_NAME);
            PreparedStatement pst = conn.prepareStatement(DELETE)
        ) {
            pst.setInt(1, chat.getId());
            pst.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean checkChatName(Chat chat) {

        try(Connection conn = DriverManager.getConnection(DB_NAME);
            PreparedStatement pst = conn.prepareStatement(CHECK_CHAT_NAME)
        ) {
            pst.setString(1, chat.getChatName());
            ResultSet rs = pst.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}