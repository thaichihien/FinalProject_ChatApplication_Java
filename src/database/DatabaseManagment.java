package database;

import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Properties;

import datastructure.GroupChat;
import datastructure.LoginHistory;
import datastructure.UserAccount;

public class DatabaseManagment {
    
    private static volatile DatabaseManagment instance;
    private Connection conn;

    public Connection getConnection(){
        return conn;
    }

    public DatabaseManagment(){
        try {
            String databaseName = DatabaseConfig.databaseName;
            //Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/" + databaseName;
            Properties props = new Properties();
            props.setProperty("user", DatabaseConfig.username);
            props.setProperty("password", DatabaseConfig.password);
            props.setProperty("ssl", "false");
            conn = DriverManager.getConnection(url, props);
            System.out.println("connect successfully");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static DatabaseManagment getInstance(){
        if(instance == null){
            synchronized (DatabaseManagment.class){
                if(instance == null){
                    instance = new DatabaseManagment();
                }
            }
        }
        return instance;
    }


    // Sử dụng các hàm bên dưới để lấy dữ liệu:------------------------------

    
    /**Thêm một tài khoản vào database
     * @param account
     */
    public void addNewAccount(UserAccount account){
        if(account.isEmpty()){
            System.out.println("account information is empty");
            return;
        }
        String INSERT_QUERY = "INSERT INTO USER_ACCOUNT(USERNAME,PASSWORD,FULLNAME,ADDRESS,DATE_OF_BIRTH,GENDER,EMAIL,ONLINE)"
         + "VALUES(?,?,?,?,?,?,?,?)";
        try (PreparedStatement statement = conn.prepareStatement(INSERT_QUERY);) {
            statement.setString(1, account.getUsername());

            //TODO encrypt password
            statement.setString(2, account.getPassword());
            statement.setString(3, account.getFullname());
            statement.setString(4, account.getAddress());
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            statement.setDate(5, new java.sql.Date(formatter.parse(account.getBirthDay()).getTime()));
            if(account.isMale()) statement.setString(6, "Male");
            else  statement.setString(6, "Female");
            statement.setString(7, account.getEmail());
            statement.setBoolean(8, false);
   
            statement.execute();
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**Lấy danh sách bạn bè của một account với ID
     * @param ID
     * @return  ArrayList<UserAccount>
     */
    public ArrayList<UserAccount> getFriendArrayList(int ID){
        String SELECT_QUERY = "SELECT UA.ID,UA.USERNAME,UA.FULLNAME,UA.ONLINE FROM USER_ACCOUNT UA INNER JOIN USER_FRIEND UF ON UA.ID = UF.FRIEND_ID WHERE UF.ID = ?";
        ResultSet data = null;
        try (PreparedStatement statment = conn.prepareStatement(SELECT_QUERY,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);){
            
            statment.setInt(1, ID);
            data = statment.executeQuery();
            
            if(!data.next()){
                return null;
            }
            else{
                ArrayList<UserAccount> friendList = new ArrayList<>();
                
                do {                    
                    UserAccount account = new UserAccount();
                    account.setID(data.getInt("ID"));
                    account.setUsername(data.getString("USERNAME"));
                    account.setFullname(data.getString("FULLNAME"));
                    account.setOnline(data.getBoolean("ONLINE"));
                    friendList.add(account);
                    
                } while (data.next());
                return friendList;
            }
            
            
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            if(data != null){
                try {
                    data.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return null;
    }

    
    /**
     * Lấy thông tin chi tiết của một account với ID
     * @return UserAccount
     */
    public UserAccount getDetailAccount(int ID){
        String SELECT_QUERY = "SELECT * FROM USER_ACCOUNT WHERE ID = ?";
        ResultSet data = null;
        try (PreparedStatement statment = conn.prepareStatement(SELECT_QUERY,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);){
            
            statment.setInt(1, ID);
            data = statment.executeQuery();
            
            if(!data.next()){
                return null;
            }
            else{
                UserAccount account = new UserAccount();
                account.setID(data.getInt("ID"));
                account.setUsername(data.getString("USERNAME"));
                account.setFullname(data.getString("FULLNAME"));
                account.setOnline(data.getBoolean("ONLINE"));
                return account;
            }
            
            
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            if(data != null){
                try {
                    data.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return null;
    }

    /** Kiểm tra xem tài khoản có tồn tại trong database
     * @param ID
     * @return true nếu có và false nếu không
     */
    public boolean checkAccount(int ID){
        String SELECT_QUERY = "SELECT ID FROM USER_ACCOUNT WHERE ID = ?";
        ResultSet data = null;
        try (PreparedStatement statment = conn.prepareStatement(SELECT_QUERY,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);){
            
            statment.setInt(1, ID);
            data = statment.executeQuery();
            
            if(!data.next()){
                return false;
            }
            else{
               return true;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            if(data != null){
                try {
                    data.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return false;
    }

    /** Tìm danh sách account với username bắt đầu bằng name
     * @param name
     * @return ArrayList
     */
    public ArrayList<UserAccount> searchAccounts(String name){
        String SELECT_QUERY = "SELECT * FROM USER_ACCOUNT WHERE USERNAME LIKE '?%'";
        ResultSet data = null;
        try (PreparedStatement statment = conn.prepareStatement(SELECT_QUERY,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);){
            
            statment.setString(1, name);
            data = statment.executeQuery();
            
            if(!data.next()){
                return null;
            }
            else{
                ArrayList<UserAccount> accountList = new ArrayList<>();
                
                do {                    
                    UserAccount account = new UserAccount();
                    account.setID(data.getInt("ID"));
                    account.setUsername(data.getString("USERNAME"));
                    account.setFullname(data.getString("FULLNAME"));
                    account.setOnline(data.getBoolean("ONLINE"));
                    accountList.add(account);
                    
                } while (data.next());
                return accountList;
            }
            
            
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            if(data != null){
                try {
                    data.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return null;
    }

    /** Tìm các tài khoản có username bắt đầu bằng name nằm trong danh sách bạn bè của tài khoản với ID
     * @param ID
     * @param name
     * @return ArrayList
     */
    public ArrayList<UserAccount> searchFriendList(int ID,String name){
        String SELECT_QUERY = "SELECT UA.ID,UA.USERNAME,UA.FULLNAME,UA.ONLINE FROM USER_ACCOUNT UA INNER JOIN USER_FRIEND UF ON UA.ID = UF.FRIEND_ID WHERE UF.ID = ? AND UA.USERNAME LIKE '?%'";
        ResultSet data = null;
        try (PreparedStatement statment = conn.prepareStatement(SELECT_QUERY,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);){
            statment.setInt(1, ID);
            statment.setString(2, name);
            data = statment.executeQuery();
            
            if(!data.next()){
                return null;
            }
            else{
                ArrayList<UserAccount> accountList = new ArrayList<>();
                
                do {                    
                    UserAccount account = new UserAccount();
                    account.setID(data.getInt("ID"));
                    account.setUsername(data.getString("USERNAME"));
                    account.setFullname(data.getString("FULLNAME"));
                    account.setOnline(data.getBoolean("ONLINE"));
                    accountList.add(account);
                    
                } while (data.next());
                return accountList;
            }
            
            
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            if(data != null){
                try {
                    data.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return null;
    }

    /** Tìm các tài khoản có username bắt đầu bằng name KHÔNG nằm trong danh sách bạn bè của tài khoản với ID
     * @param ID
     * @param name
     * @return
     */
    public ArrayList<UserAccount> searchAccountsNotFriend(int ID,String name){
        String SELECT_QUERY = "SELECT UA.ID,UA.USERNAME,UA.FULLNAME,UA.ONLINE FROM USER_ACCOUNT UA INNER JOIN USER_FRIEND UF ON UA.ID = UF.FRIEND_ID WHERE NOT UF.ID = ? AND UA.USERNAME LIKE '?%'";
        ResultSet data = null;
        try (PreparedStatement statment = conn.prepareStatement(SELECT_QUERY,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);){
            statment.setInt(1, ID);
            statment.setString(2, name);
            data = statment.executeQuery();
            
            if(!data.next()){
                return null;
            }
            else{
                ArrayList<UserAccount> accountList = new ArrayList<>();
                
                do {                    
                    UserAccount account = new UserAccount();
                    account.setID(data.getInt("ID"));
                    account.setUsername(data.getString("USERNAME"));
                    account.setFullname(data.getString("FULLNAME"));
                    account.setOnline(data.getBoolean("ONLINE"));
                    accountList.add(account);
                    
                } while (data.next());
                return accountList;
            }
            
            
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            if(data != null){
                try {
                    data.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return null;
    }



    /** danh sách các ID của nhóm chat có tài khoản với ID tham gia
     * @param ID
     * @return
     */
    public ArrayList<Integer> searchGroupIDFromUser(int ID){
        String SELECT_QUERY = "SELECT GROUPCHAT_ID FROM GROUPCHAT_MEMBER WHERE MEMBER_ID = ?";
        ResultSet data = null;
        try (PreparedStatement statment = conn.prepareStatement(SELECT_QUERY,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);){
            statment.setInt(1, ID);
            data = statment.executeQuery();
            
            if(!data.next()){
                return null;
            }
            else{
                ArrayList<Integer> allGroupID = new ArrayList<>();
                
                do {                    
                    int groupID = data.getInt("GROUPCHAT_ID");
                    allGroupID.add(groupID);
                    
                } while (data.next());
                return allGroupID;
            }
            
            
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            if(data != null){
                try {
                    data.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return null;
    }

    /** Thêm một nhóm chat vào database
     * @param group
     */
    public void addNewGroup(GroupChat group){
        if(group.isEmpty()){
            System.out.println("group is empty");
            return;
        }
        int groupID = addToGroupTable(group);
        group.setID(groupID);
        addMemberToGroup(group);
    }

    private int addToGroupTable(GroupChat group){
        String INSERT_QUERY = "INSERT INTO GROUPCHAT(GROUPNAME,CREATED_AT,ONLINE)"
         + "VALUES(?,?,?)";
        try (PreparedStatement statement = conn.prepareStatement(INSERT_QUERY);) {
            statement.setString(1, group.getGroupname());
            Date date = new Date();
            //ZonedDateTime  myDateObj = ZonedDateTime.now( ZoneId.of("Asia/Ho_Chi_Minh")); 
            Timestamp createdAt =new Timestamp(date.getTime());
            statement.setTimestamp(2, createdAt);
            statement.setBoolean(3, false);

            statement.execute();
            ResultSet rs = statement.getGeneratedKeys();
            if(rs.next()){
                int groupID = rs.getInt("ID");
                return groupID;
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
        return -1;
    }

    private void addMemberToGroup(GroupChat group){
        String INSERT_QUERY = "INSERT INTO GROUPCHAT_MEMBER(GROUPNAME_ID,MEMBER_ID,POSITION)"
         + "VALUES(?,?,?)";
        try (PreparedStatement statement = conn.prepareStatement(INSERT_QUERY);) {
            
            ArrayList<UserAccount> admins = group.getAdmins();
            for(UserAccount admin : admins){
                statement.setInt(1, group.getID());
                statement.setInt(2, admin.getID());
                statement.setString(3, "admin");
                statement.addBatch();
            }
            ArrayList<UserAccount> members = group.getMembers();
            for(UserAccount member : members){
                statement.setInt(1, group.getID());
                statement.setInt(2, member.getID());
                statement.setString(3, "member");
                statement.addBatch();
            }
            statement.executeBatch();
            
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }

    /** Lấy dữ liệu tất cả lịch sử đăng nhập
     * @return
     */
    public ArrayList<LoginHistory> getAllLoginHistory(){
        String SELECT_QUERY = "SELECT * FROM LOGIN_HISTORY";
        ResultSet data = null;
        try (PreparedStatement statment = conn.prepareStatement(SELECT_QUERY,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);){
            
            //statment.setString(1, name);
            data = statment.executeQuery();
            
            if(!data.next()){
                return null;
            }
            else{
                ArrayList<LoginHistory> loginList = new ArrayList<>();
                
                do {                    
                    LoginHistory login = new LoginHistory();
                    login.setID(data.getInt("ID"));
                    login.setUserID(data.getInt("USER_ID"));
                    Timestamp date = data.getTimestamp("LOGIN_TIME");
                    String formattedDate = new SimpleDateFormat("yyyyMMdd").format(date);
                    login.setLoginTime(formattedDate);
                    loginList.add(login);
                    
                } while (data.next());
                return loginList;
            }
            
            
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            if(data != null){
                try {
                    data.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return null;
    }

    /** thêm dữ liệu lịch sử đăng nhập của tài khoản với ID ngay tại lúc gọi hàm này
     * @param ID
     */
    public void addToLoginHistory(int ID){
        String INSERT_QUERY = "INSERT INTO LOGIN_HISTORY(USER_ID,LOGIN_TIME)"
         + "VALUES(?,?)";
        try (PreparedStatement statement = conn.prepareStatement(INSERT_QUERY);) {
            statement.setInt(1, ID);
            Date date = new Date();
            //ZonedDateTime  myDateObj = ZonedDateTime.now( ZoneId.of("Asia/Ho_Chi_Minh")); 
            Timestamp loginTime =new Timestamp(date.getTime());
            statement.setTimestamp(2, loginTime);

            statement.execute();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    

    /** Lấy tất cả tài khoản có trong database
     * @return ArrayList
     */
    public ArrayList<UserAccount> getAllAccounts(){
        String SELECT_QUERY = "SELECT * FROM USER_ACCOUNT";
        ResultSet data = null;
        try (PreparedStatement statment = conn.prepareStatement(SELECT_QUERY,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);){
            
            //statment.setString(1, name);
            data = statment.executeQuery();
            
            if(!data.next()){
                return null;
            }
            else{
                ArrayList<UserAccount> accountList = new ArrayList<>();
                
                do {                    
                    UserAccount account = new UserAccount();
                    account.setID(data.getInt("ID"));
                    account.setUsername(data.getString("USERNAME"));
                    account.setFullname(data.getString("FULLNAME"));
                    account.setOnline(data.getBoolean("ONLINE"));
                    accountList.add(account);
                    
                } while (data.next());
                return accountList;
            }
            
            
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            if(data != null){
                try {
                    data.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return null;

    }

    /** lấy tất cả các nhóm chat có trong database
     * @return
     */
    public ArrayList<GroupChat> getAllGroupChat(){
        String SELECT_QUERY = "SELECT * FROM GROUPCHAT";
        ResultSet data = null;
        try (PreparedStatement statment = conn.prepareStatement(SELECT_QUERY,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);){
            
            //statment.setString(1, name);
            data = statment.executeQuery();
            
            if(!data.next()){
                return null;
            }
            else{
                ArrayList<GroupChat> groupList = new ArrayList<>();
                
                do {                    
                    GroupChat group = new GroupChat();
                    group.setID(data.getInt("ID"));
                    group.setGroupname("GROUP_NAME");
                    Timestamp date = data.getTimestamp("CREATED_AT");
                    String formattedDate = new SimpleDateFormat("yyyyMMdd").format(date);
                    group.setCreatedAt(formattedDate);
                    group.setOnline(data.getBoolean("ONLINE"));
                    groupList.add(group);
                    
                } while (data.next());
                return groupList;
            }
            
            
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            if(data != null){
                try {
                    data.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return null;
    }

    public boolean checkAccount(String username, String password){
        String SELECT_QUERY = "SELECT ID FROM USER_ACCOUNT WHERE USERNAME = '?' AND PASSWORD = '?'";
        ResultSet data = null;
        try (PreparedStatement statment = conn.prepareStatement(SELECT_QUERY,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);){
            
            statment.setString(1, username);
            statment.setString(2, password);
            data = statment.executeQuery();
            
            if(!data.next()){
                return false;
            }
            else{
               return true;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            if(data != null){
                try {
                    data.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return false;
    }
}
