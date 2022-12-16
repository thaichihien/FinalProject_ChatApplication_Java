package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseManagment {
    
    private static volatile DatabaseManagment instance;
    private static Connection conn;

    private DatabaseManagment(){

    }

    public static DatabaseManagment getInstance(){
        if(instance == null){
            synchronized (DatabaseManagment.class){
                if(instance == null){
                    instance = new DatabaseManagment();
                    try {
                        String databaseName = "";
                        //Class.forName("org.postgresql.Driver");
                        String url = "jdbc:postgresql://localhost:5432/" + databaseName;
                        Properties props = new Properties();
                        props.setProperty("user", System.getenv("POSTGRES_USERNAME"));
                        props.setProperty("password", System.getenv("POSTGRES_PASSWORD"));
                        props.setProperty("ssl", "false");
                        conn = DriverManager.getConnection(url, props);
                        System.out.println("connect successfully");
                        
                         
            
                    } catch (SQLException e) {
                        System.out.println(e);
                    }
                }
            }
        }

        return instance;
        
    }
}
