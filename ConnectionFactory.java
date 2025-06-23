
package biblioteca;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public Connection conectaBD(){
        
        Connection conn = null;
        
        try{
           // testando a conexao com bd
           String url = "jbdc:mysql://localhost:3306/db_biblioteca?useSSL=false";
           String  user = "root";
           String password = "";
           
           conn = DriverManager.getConnection(url, user, password);
           
           System.out.println("deu certo");
       
       }catch(SQLException e){   
               System.out.println("deu ruim: "+e.getMessage());         
        }   
       return conn;
    }
}
