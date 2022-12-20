package database;

public class DatabaseConfig {
    public static String databaseName = "finalproject_chatapplication";
    public static String username = System.getenv("POSTGRES_USERNAME"); // thay bằng chuỗi tài khoản postgres nếu k dùng biến môi trường, mặc định là "postgres"
    public static String password = System.getenv("POSTGRES_PASSWORD"); //thay bằng chuỗi mật khẩu postgres nếu k dùng biến môi trường
}
