import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Database {
    String URL = "jdbc:postgresql://localhost:5432/sign";



    String user = "postgres";

    String password = "1";

    Connection connection;

    {
        try {
            connection = DriverManager.getConnection(URL, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    Set<Users> users = new HashSet<>();
    public void signUp(Users users){
        String query = "insert into users(firstname,lastname,username,password) " +
                "values(?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,users.getFirstname());
            preparedStatement.setString(2,users.getLastname());
            preparedStatement.setString(3,users.getUsername());
            preparedStatement.setString(4,users.getPassword());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isUsername(String username) {
        String query = "select * from users where username like '"+username+"'";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return false;
            }

        } catch (SQLException e) {

        }
        return true;
    }

    public Users signIn(String username, String password) {
        Users users = new Users();
        String query = "select * from users where username=? and password=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                users.setId(resultSet.getInt("id"));
                users.setFirstname(resultSet.getString("firstname"));
                users.setLastname(resultSet.getString("lastname"));
                users.setUsername(resultSet.getString("username"));
                users.setPassword(resultSet.getString("password"));
                users.setAdmin(resultSet.getBoolean("isAdmin"));
                return users;
            }
            return null;
        } catch (SQLException e) {
            return null;
        }

    }

    public boolean deleteUsername(String username, String password) {

        try {
            Statement statement = connection.createStatement();
            String query = "delete from users where username='"+username+"' and password='"+password+"'";
            int execute = statement.executeUpdate(query);
            if(execute==0){
                return false;
            }else {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



    }

    public int updateUser(Users users) {
        String query = "update users set firstname=?, lastname=?, username=?, password=? where username=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,users.getFirstname());
            preparedStatement.setString(2,users.getLastname());
            preparedStatement.setString(3,users.getUsername());
            preparedStatement.setString(4,users.getPassword());
            preparedStatement.setString(5,users.getUsername());
            int i = preparedStatement.executeUpdate();
            return i;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Users> listUser() {
        Statement statement = null;
        try {
            List<Users> list = new ArrayList<>();
            statement = connection.createStatement();
            String query = "select * from users";
            ResultSet resultSet = statement.executeQuery(query);
            int i=0;
            while(resultSet.next()){
                Users user1 = new Users();
                user1.setId(resultSet.getInt("id"));
                user1.setFirstname(resultSet.getString("firstname"));
                user1.setLastname(resultSet.getString("lastname"));
                user1.setUsername(resultSet.getString("username"));
                user1.setPassword(resultSet.getString("password"));
                user1.setAdmin(resultSet.getBoolean("isAdmin"));
                user1.setActive(resultSet.getBoolean("isActive"));
                list.add(user1);
            }
            return list;
        } catch (SQLException e) {

        }
        return null;
    }
}
