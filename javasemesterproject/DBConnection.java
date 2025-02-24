package javasemesterproject;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
        
public class DBConnection {
    public Connection c;
    public Statement s;
    public DBConnection(){
        try{
            //Register JDBC Driver with Class's Static method
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/elearningsystem","root","dhanna");
            s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        }
        catch(ClassNotFoundException | SQLException e){
            System.err.println(e);
        }
    }
    public void Close(){
        try {
            c.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
