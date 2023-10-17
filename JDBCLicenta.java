import java.sql.*;

public class JDBCLicenta {
    public static void main(String[] args){
        String url = "jdbc:mysql://localhost:3306/jdbclicenta";
        String username = "root";
        String password = "";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, username, password);

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select * from user");

            while(resultSet.next()){
                User user1= new User(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),  resultSet.getString(4), resultSet.getInt(5));
                System.out.println(user1.toString());
            }

            connection.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
}
