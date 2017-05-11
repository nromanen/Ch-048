package utils.databaseutil;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
/**
 * Created by Evgen on 07.04.2017.
 */
public class UserDAO {

    public static List<String> getUserFromDatabaseByEmail(String email) {
        List<String> result = new LinkedList<>();
        try {
            Statement statement = DatabaseOperations.connectToDatabase().createStatement();
            String sql = "SELECT * FROM users  INNER JOIN userdetail ON users.userdetails_id = userdetail.id WHERE email=?";
            statement.getConnection().setAutoCommit(false);
            PreparedStatement preparedStatement = statement.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, email);
            ResultSet resultSet = statement.executeQuery(preparedStatement.toString());
            if (resultSet.next()) {
                result.add(resultSet.getString("id"));
                result.add(resultSet.getString("email"));
                result.add(resultSet.getString("firstname"));
                result.add(resultSet.getString("lastname"));
                result.add(getRoleById(resultSet.getString("id")));
                return result;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<String> getWindowDataFromDatabase(String email, String webElement){
        if (webElement.equals("userInfoWindow")) {
            List<String> list = getUserFromDatabaseByEmail(email);
            List<String> result = new LinkedList<>();
            result.add(list.get(0));
            result.add(list.get(1));
            result.add(String.valueOf(getStatusByEmail(email)));
            return result;
        }
        return null;
    }


    private static String getRoleById(String id) {
        try {
            Statement statement = DatabaseOperations.connectToDatabase().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM role_users WHERE role_users.users_id=" + id );
            String roleId =  "";
            if (resultSet.next()) roleId = resultSet.getString("role_id");
            resultSet = statement.executeQuery("SELECT role.type FROM role WHERE role.id =" + roleId);
            if (resultSet.next()) return resultSet.getString("type");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }



    public static List<String> getEnableUsersEmails() {
        try {
            Statement statement = DatabaseOperations.connectToDatabase().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT users.email FROM users WHERE users.enabled = TRUE ");
            List<String> result = new LinkedList<>();
            while (resultSet.next()) {
                result.add(resultSet.getString("email"));
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void deleteAllEvents(){
        try {
            Statement statement = DatabaseOperations.connectToDatabase().createStatement();
            statement.executeUpdate("TRUNCATE  events");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteAllFeedbacks(){
        try {
            Statement statement = DatabaseOperations.connectToDatabase().createStatement();
            statement.executeUpdate("TRUNCATE  feedbacks");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean getStatusByEmail(String email) {
        List<String> list = getEnableUsersEmails();
        if (list.contains(email)) return true;
        return false;
    }
}
