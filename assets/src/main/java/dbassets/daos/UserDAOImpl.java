package dbassets.daos;

import dbassets.models.Chat;
import dbassets.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    private static final String DB_NAME = "jdbc:sqlite:D:/Asztal/alkfejl/Chatgram/projekt/assets/cgdatabase.db";
    private static final String SELECT_ALL_USER = "SELECT * FROM user;";
    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS user ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'name' TEXT,'pw' TEXT,'email' TEXT, 'admin' INTEGER, 'interested' TEXT);";
    private static final String INSERT_USER = "INSERT INTO user ('name', 'pw', 'email', 'interested') VALUES (?,?,?,?);";
    private static final String CHECK_USER = "SELECT 'id' FROM user WHERE email=? AND pw=?;";
    private static final String GET_ID_BY_EMAIL = "SELECT * FROM user WHERE email = ?;";
    private static final String IS_ADMIN = "SELECT * FROM user WHERE id = ?;";
    private static final String GET_USER_BY_ID = "SELECT * FROM user WHERE id = ?;";
    private static final String GET_USER_BY_NAME = "SELECT * FROM user WHERE name LIKE ?;";
    private static final String GET_USER_BY_INTEREST = "SELECT * FROM user WHERE interested LIKE ?;";
    private static final String DELETE = "DELETE FROM user WHERE id = ?;";


    public UserDAOImpl() throws ClassNotFoundException {
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
    public List<User> getAll() {
        List<User> result = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DB_NAME);
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(SELECT_ALL_USER)

        ) {
            while(rs.next()){
                User u = new User();
                u.setId(rs.getInt(1));
                u.setName(rs.getString(2));
                u.setPw(rs.getString(3));
                u.setEmail(rs.getString(4));
                u.setInterested(rs.getString(6));

                result.add(u);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return result;
    }

    @Override
    public boolean addUser(User user) {

        try(Connection conn = DriverManager.getConnection(DB_NAME);
            PreparedStatement pst = conn.prepareStatement(INSERT_USER)
        ) {
            pst.setString(1, user.getName());
            pst.setString(2, user.getPw());
            pst.setString(3, user.getEmail());
            pst.setString(4, user.getInterested());

            int result = pst.executeUpdate();
            return result == 1;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean checkUser(User user) {
        try(Connection conn = DriverManager.getConnection(DB_NAME);
            PreparedStatement pst = conn.prepareStatement(CHECK_USER)
        ) {
            pst.setString(1, user.getEmail());
            pst.setString(2, user.getPw());
            ResultSet rs = pst.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public int getIdByEmail(User user) {

        try(Connection conn = DriverManager.getConnection(DB_NAME);
            PreparedStatement pst = conn.prepareStatement(GET_ID_BY_EMAIL)
        ) {
            pst.setString(1, user.getEmail());
            ResultSet rs = pst.executeQuery();
            if(rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public User getUserById(int Id) {
        User user = new User();

        try (Connection conn = DriverManager.getConnection(DB_NAME);
             PreparedStatement pst = conn.prepareStatement(GET_USER_BY_ID);
        ) {
            pst.setInt(1, Id);
            ResultSet rs = pst.executeQuery();

            user.setId(rs.getInt(1));
            user.setName(rs.getString(2));
            user.setPw(rs.getString(3));
            user.setEmail(rs.getString(4));
            user.setInterested((rs.getString(6)));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> getUserByName(String name) {
        List<User> result = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DB_NAME);
             PreparedStatement pst = conn.prepareStatement(GET_USER_BY_NAME);
        ) {
            pst.setString(1, "%"+name+"%");
            ResultSet rs = pst.executeQuery();

            while(rs.next()){
                User r = new User();
                r.setId(rs.getInt(1));
                r.setName(rs.getString(2));
                r.setPw(rs.getString(3));
                r.setEmail(rs.getString(4));
                r.setInterested((rs.getString(6)));

                result.add(r);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    @Override
    public List<User> getUserByInterest(String interest) {
        List<User> result = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DB_NAME);
             PreparedStatement pst = conn.prepareStatement(GET_USER_BY_INTEREST);
        ) {
            pst.setString(1, "%"+interest+"%");
            ResultSet rs = pst.executeQuery();

            while(rs.next()){
                User r = new User();
                r.setId(rs.getInt(1));
                r.setName(rs.getString(2));
                r.setPw(rs.getString(3));
                r.setEmail(rs.getString(4));
                r.setInterested((rs.getString(6)));

                result.add(r);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }


    @Override
    public boolean isAdmin(User user) {
        try(Connection conn = DriverManager.getConnection(DB_NAME);
            PreparedStatement pst = conn.prepareStatement(IS_ADMIN)
        ) {
            pst.setInt(1, user.getId());
            ResultSet rs = pst.executeQuery();
            if(rs.next()) {
                return rs.getInt(5)==1;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void delete(User user) {
        try(Connection conn = DriverManager.getConnection(DB_NAME);
            PreparedStatement pst = conn.prepareStatement(DELETE)
        ) {
            pst.setInt(1, user.getId());
            pst.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
