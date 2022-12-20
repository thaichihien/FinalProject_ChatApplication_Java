package database;

public class DatabaseConfig {
    public static String databaseName = "finalproject_chatapplication";
    public static String username = System.getenv("POSTGRES_USERNAME");
    public static String password = System.getenv("POSTGRES_PASSWORD");
}
