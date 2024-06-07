import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
    
    public static Connection connectionDB(){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url =  "jdbc:sqlserver://BURKILEO\\SQLEXPRESS;" +"databaseName=login;integratedSecurity=true;" +"encrypt=true;trustServerCertificate=true";
            Connection connection = DriverManager.getConnection(url);
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}