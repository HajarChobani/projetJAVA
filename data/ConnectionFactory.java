package data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class ConnectionFactory {
    static Connection getConnection() throws SQLException {
        Connection myConnection = null;
        String user = "c##hajar_chobani";  
        String password = "123";  
        String url = "jdbc:oracle:thin:@localhost:1521:ORCL";  

        myConnection = DriverManager.getConnection(url, user, password);
        return myConnection;
    }
}
