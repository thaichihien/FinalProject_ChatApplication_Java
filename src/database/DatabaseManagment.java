package database;

import java.sql.Timestamp;
import java.security.Identity;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import java.util.Locale;
import java.util.Properties;

import datastructure.FriendRequest;
import datastructure.GroupChat;
import datastructure.LoginHistory;
import datastructure.Message;
import datastructure.UserAccount;

public class DatabaseManagment {
    
    private static volatile DatabaseManagment instance;
    private Connection conn;

    public Connection getConnection(){
        return conn;
    }

    private DatabaseManagment(){
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





    // Gọi database bằng cách dùng hàm getInstance()
    // Ex: DatabaseManagement database = DatabaseManagment.getInstance()
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


    // ! WARINING : KHÔNG CHỈNH SỬA FILE NÀY

    // Sử dụng các hàm bên dưới để lấy dữ liệu:------------------------------

    
    /**Thêm một tài khoản vào database
     * @param account
     */
    public int addNewAccount(UserAccount account){
        if(account.isEmpty()){
            System.out.println("account information is empty");
            return -1;
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
            statement.setString(6, account.getGender());
            statement.setString(7, account.getEmail());
            statement.setBoolean(8, false);
   
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            int id = rs.getInt("ID");
            return id;
            
        } catch (Exception e) {
            System.out.println(e);
        }

        return -1;
    }

    /**đăng ký một tài khoản vào database không cần đầy đủ các trường
     * @param account
     */
    public int registerNewAccount(UserAccount account){
        if(account.isEmpty()){
            System.out.println("account information is empty");
            return -1;
        }
        String INSERT_QUERY = "INSERT INTO USER_ACCOUNT(USERNAME,PASSWORD,EMAIL,ONLINE)"
         + "VALUES(?,?,?,?)";

        try (PreparedStatement statement = conn.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);) {
            statement.setString(1, account.getUsername());

            //TODO encrypt password
            statement.setString(2, account.getPassword());
            statement.setString(3, account.getEmail());
            statement.setBoolean(4, true);
   
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            int id = rs.getInt("ID");
            return id;
            
        } catch (Exception e) {
            System.out.println(e);
        }

        return -1;
    }


    /**Lấy danh sách bạn bè của một account với ID
     * @param ID
     * @return  ArrayList<UserAccount>
     */
    public ArrayList<UserAccount> getFriendArrayList(int ID){
        String SELECT_QUERY = "SELECT UA.ID,UA.USERNAME,UA.FULLNAME,UA.ONLINE FROM USER_ACCOUNT UA INNER JOIN USER_FRIEND UF ON UA.ID = UF.FRIEND_ID WHERE UF.ID = ?";
        ResultSet data = null;
        ArrayList<UserAccount> friendList = new ArrayList<>();
        try (PreparedStatement statment = conn.prepareStatement(SELECT_QUERY,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);){
            
            statment.setInt(1, ID);
            data = statment.executeQuery();
            
            if(!data.next()){
                return friendList;
            }
            else{
               
                
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
        return friendList;
    }

    public ArrayList<UserAccount> getFriendArrayListByOnline(int ID){
        String SELECT_QUERY = "SELECT UA.ID,UA.USERNAME,UA.FULLNAME,UA.ONLINE FROM USER_ACCOUNT UA INNER JOIN USER_FRIEND UF ON UA.ID = UF.FRIEND_ID WHERE UF.ID = ? ORDER BY UA.ONLINE DESC";
        ResultSet data = null;
        ArrayList<UserAccount> friendList = new ArrayList<>();
        try (PreparedStatement statment = conn.prepareStatement(SELECT_QUERY,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);){
            
            statment.setInt(1, ID);
            data = statment.executeQuery();
            
            if(!data.next()){
                return friendList;
            }
            else{
                
                
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
        return friendList;
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


    /**
     * Lấy thông tin chi tiết của một account với username và password
     * @return UserAccount
     */
    public UserAccount getDetailAccount(String username, String password){
        String SELECT_QUERY = "SELECT * FROM USER_ACCOUNT WHERE USERNAME = ? AND PASSWORD = ?";
        ResultSet data = null;
        try (PreparedStatement statment = conn.prepareStatement(SELECT_QUERY,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);){

            statment.setString(1, username);
            statment.setString(2, password);
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

    public boolean checkAccount(String username, String password){
        String SELECT_QUERY = "SELECT ID FROM USER_ACCOUNT WHERE USERNAME = ? AND PASSWORD = ?";
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

    /** Tìm danh sách account với username bắt đầu bằng name
     * @param name
     * @return ArrayList
     */
    public ArrayList<UserAccount> searchAccounts(String name){
        String SELECT_QUERY = "SELECT * FROM USER_ACCOUNT WHERE USERNAME LIKE '?%'";
        ResultSet data = null;
        ArrayList<UserAccount> accountList = new ArrayList<>();
        try (PreparedStatement statment = conn.prepareStatement(SELECT_QUERY,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);){
            
            statment.setString(1, name);
            data = statment.executeQuery();
            
            if(!data.next()){
                return accountList;
            }
            else{
                
                
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
        return accountList;
    }

    /** Tìm các tài khoản có username bắt đầu bằng name nằm trong danh sách bạn bè của tài khoản với ID
     * @param ID
     * @param name
     * @return ArrayList
     */
    public ArrayList<UserAccount> searchFriendList(int ID,String name){
        String SELECT_QUERY = "SELECT UA.ID,UA.USERNAME,UA.FULLNAME,UA.ONLINE FROM USER_ACCOUNT UA INNER JOIN USER_FRIEND UF ON UA.ID = UF.FRIEND_ID WHERE UF.ID = ? AND (UA.USERNAME LIKE ? OR UA.FULLNAME LIKE ?)";
        ResultSet data = null;
        ArrayList<UserAccount> accountList = new ArrayList<>();
        try (PreparedStatement statment = conn.prepareStatement(SELECT_QUERY,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);){
            statment.setInt(1, ID);
            statment.setString(2, "%" + name + "%");
            statment.setString(3, "%" + name + "%");
            data = statment.executeQuery();
            
            if(!data.next()){
                return accountList;
            }
            else{
                
                
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
        return accountList;
    }

    /** Tìm các tài khoản có username bắt đầu bằng name KHÔNG nằm trong danh sách bạn bè của tài khoản với ID
     * @param ID
     * @param name
     * @return
     */
    public ArrayList<UserAccount> searchAccountsNotFriend(int ID,String name){
        String SELECT_QUERY = "SELECT UA.ID,UA.USERNAME,UA.FULLNAME,UA.ONLINE FROM USER_ACCOUNT UA WHERE UA.ID NOT IN(SELECT FRIEND_ID FROM USER_FRIEND WHERE ID = ?) AND  (UA.USERNAME LIKE ? OR UA.FULLNAME LIKE ?) AND NOT UA.ID = ?";
        ResultSet data = null;
        ArrayList<UserAccount> accountList = new ArrayList<>();
        try (PreparedStatement statment = conn.prepareStatement(SELECT_QUERY,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);){
            statment.setInt(1, ID);
            statment.setString(2, "%" + name + "%");
            statment.setString(3, "%" + name + "%");
            statment.setInt(4, ID);
            data = statment.executeQuery();
            
            if(!data.next()){
                return accountList;
            }
            else{
               
                
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
        return accountList;
    }



    /** danh sách các ID của nhóm chat có tài khoản với ID tham gia
     * @param ID
     * @return
     */
    public ArrayList<Integer> searchGroupIDFromUser(int ID){
        String SELECT_QUERY = "SELECT GROUPCHAT_ID FROM GROUPCHAT_MEMBER WHERE MEMBER_ID = ?";
        ResultSet data = null;
        ArrayList<Integer> allGroupID = new ArrayList<>();
        try (PreparedStatement statment = conn.prepareStatement(SELECT_QUERY,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);){
            statment.setInt(1, ID);
            data = statment.executeQuery();
            
            if(!data.next()){
                return allGroupID;
            }
            else{
                
                
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
        return allGroupID;
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
        String INSERT_QUERY = "INSERT INTO GROUPCHAT(GROUP_NAME,CREATED_AT,ONLINE)"
         + "VALUES(?,?,?)";
        try (PreparedStatement statement = conn.prepareStatement(INSERT_QUERY,PreparedStatement.RETURN_GENERATED_KEYS);) {
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
        String INSERT_QUERY = "INSERT INTO GROUPCHAT_MEMBER(GROUPCHAT_ID,MEMBER_ID,POSITION)"
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
        String SELECT_QUERY = "SELECT LH.*,UA.USERNAME FROM LOGIN_HISTORY LH INNER JOIN USER_ACCOUNT UA ON LH.USER_ID = UA.ID";
        ResultSet data = null;
        ArrayList<LoginHistory> loginList = new ArrayList<>();
        Connection conn = DatabaseManagment.getInstance().getConnection();
        try (PreparedStatement statment = conn.prepareStatement(SELECT_QUERY,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);){
            
            //statment.setString(1, name);
            data = statment.executeQuery();
            
            if(!data.next()){
                return loginList;
            }
            else{
                do {                    
                    LoginHistory login = new LoginHistory();
                    login.setID(data.getInt("LOGIN_ID"));
                    login.setUserID(data.getInt("USER_ID"));
                    login.setUserName(data.getString("username"));
                    java.sql.Timestamp date = data.getTimestamp("LOGIN_TIME");
                    String formattedDate = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(date);
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
        return loginList;
    }

    public ArrayList<LoginHistory> getAllLoginHistory(String sort,String by){
        String SELECT_QUERY = "SELECT GC.ID,GC.GROUP_NAME,COUNT(MB.MEMBER_ID) AS SOLUONG,GC.CREATED_AT,GC.ONLINE FROM GROUPCHAT GC LEFT OUTER JOIN GROUPCHAT_MEMBER MB ON GC.ID = MB.GROUPCHAT_ID GROUP BY GC.ID ORDER BY " + sort + " " + by;
        ResultSet data = null;
        ArrayList<LoginHistory> loginList = new ArrayList<>();
        try (PreparedStatement statment = conn.prepareStatement(SELECT_QUERY,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);){
            
            //statment.setString(1, name);
            data = statment.executeQuery();
            
            if(!data.next()){
                return loginList;
            }
            else{
                
                
                do {                    
                    LoginHistory login = new LoginHistory();
                    login.setID(data.getInt("LOGIN_ID"));
                    login.setUserID(data.getInt("USER_ID"));
                    login.setUserName(data.getString("username"));
                    Timestamp date = data.getTimestamp("LOGIN_TIME");
                    String formattedDate = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(date);
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
        return loginList;
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
        ArrayList<UserAccount> accountList = new ArrayList<>();
        try (PreparedStatement statment = conn.prepareStatement(SELECT_QUERY,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);){
            
            //statment.setString(1, name);
            data = statment.executeQuery();
            
            if(!data.next()){
                return accountList;
            }
            else{
                
                
                do {                    
                    UserAccount account = new UserAccount();
                    account.setID(data.getInt("ID"));
                    account.setUsername(data.getString("USERNAME"));
                    account.setFullname(data.getString("FULLNAME"));
                    account.setAddress(data.getString("address"));
                    Date birthDay = data.getDate("date_of_birth");
                    account.setBirthDay(birthDay.toString());
                    account.setGender(data.getString("Gender"));
                    account.setEmail(data.getString("email"));
                    Timestamp createdAt = data.getTimestamp("created_at");
                    String formattedDate = new SimpleDateFormat("HH:mm dd-MM-yyyy").format(createdAt);
                    account.setCreatedAt(formattedDate);
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
        return accountList;

    }

    // ! FIX COLUMN VARIABLE
    public ArrayList<UserAccount> getAllAccounts(String name,String sort,String by){
        
        String SELECT_QUERY = "SELECT * FROM USER_ACCOUNT WHERE (USERNAME LIKE ? OR FULLNAME LIKE ?)  ORDER BY " + sort + " " + by;
        if(sort == null && name == null){
            SELECT_QUERY = "SELECT * FROM USER_ACCOUNT";
        }
        else if(sort != null && name == null){
            SELECT_QUERY = "SELECT * FROM USER_ACCOUNT ORDER BY " + sort + " " + by;
        }
        else if(name != null && sort == null){
            SELECT_QUERY = "SELECT * FROM USER_ACCOUNT WHERE (USERNAME LIKE ? OR FULLNAME LIKE ?)";
        }


        ResultSet data = null;
        ArrayList<UserAccount> accountList = new ArrayList<>();
        try (PreparedStatement statment = conn.prepareStatement(SELECT_QUERY,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);){
            
            if(name != null){
                statment.setString(1, "%" + name + "%");
                statment.setString(2, "%" + name + "%");
            }
            


            data = statment.executeQuery();
            
            if(!data.next()){
                return accountList;
            }
            else{
                
                
                do {                    
                    UserAccount account = new UserAccount();
                    account.setID(data.getInt("ID"));
                    account.setUsername(data.getString("USERNAME"));
                    account.setFullname(data.getString("FULLNAME"));
                    account.setAddress(data.getString("address"));
                    Date birthDay = data.getDate("date_of_birth");
                    account.setBirthDay(birthDay.toString());
                    account.setGender(data.getString("Gender"));
                    account.setEmail(data.getString("email"));
                    Timestamp createdAt = data.getTimestamp("created_at");
                    String formattedDate = new SimpleDateFormat("HH:mm dd-MM-yyyy").format(createdAt);
                    account.setCreatedAt(formattedDate);
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
        return accountList;

    }


    /** lấy tất cả các nhóm chat có trong database
     * @return
     */
    public ArrayList<GroupChat> getAllGroupChat(){
        String SELECT_QUERY = "SELECT GC.ID,GC.GROUP_NAME,COUNT(MB.MEMBER_ID) AS SOLUONG,GC.CREATED_AT,GC.ONLINE FROM GROUPCHAT GC LEFT OUTER JOIN GROUPCHAT_MEMBER MB ON GC.ID = MB.GROUPCHAT_ID GROUP BY GC.ID";
        ResultSet data = null;
        ArrayList<GroupChat> groupList = new ArrayList<>();
        try (PreparedStatement statment = conn.prepareStatement(SELECT_QUERY,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);){
            
            //statment.setString(1, name);
            data = statment.executeQuery();
            
            if(!data.next()){
                return groupList;
            }
            else{
                
                do {                    
                    GroupChat group = new GroupChat();
                    group.setID(data.getInt("ID"));
                    group.setGroupname(data.getString("GROUP_NAME"));
                    group.setNumberOfMember(data.getInt("soluong"));
                    Timestamp date = data.getTimestamp("CREATED_AT");
                    String formattedDate = new SimpleDateFormat("HH:mm dd-MM-yyyy").format(date);
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
        return groupList;
    }

    public ArrayList<GroupChat> getAllGroupChat(String sort,String by){
        String SELECT_QUERY = "SELECT GC.ID,GC.GROUP_NAME,COUNT(MB.MEMBER_ID) AS SOLUONG,GC.CREATED_AT,GC.ONLINE FROM GROUPCHAT GC INNER JOIN GROUPCHAT_MEMBER MB ON GC.ID = MB.GROUPCHAT_ID GROUP BY GC.ID ORDER BY " + sort + " " + by;
        ResultSet data = null;
        ArrayList<GroupChat> groupList = new ArrayList<>();
        try (PreparedStatement statment = conn.prepareStatement(SELECT_QUERY,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);){
            
            //statment.setString(1, name);
            data = statment.executeQuery();
            
            if(!data.next()){
                return groupList;
            }
            else{
                
                do {                    
                    GroupChat group = new GroupChat();
                    group.setID(data.getInt("ID"));
                    group.setGroupname(data.getString("GROUP_NAME"));
                    group.setNumberOfMember(data.getInt("soluong"));
                    Timestamp date = data.getTimestamp("CREATED_AT");
                    String formattedDate = new SimpleDateFormat("HH:mm dd-MM-yyyy").format(date);
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
        return groupList;
    }
    

    public ArrayList<GroupChat> getAllGroupChatOnline(int ID){
        String SELECT_QUERY = "SELECT GC.* FROM GROUPCHAT_MEMBER GM LEFT OUTER JOIN GROUPCHAT GC ON GM.GROUPCHAT_ID = GC.ID WHERE GM.MEMBER_ID = ? ORDER BY GC.ONLINE DESC";
        ResultSet data = null;
        ArrayList<GroupChat> groupList = new ArrayList<>();
        try (PreparedStatement statment = conn.prepareStatement(SELECT_QUERY,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);){
            
            statment.setInt(1, ID);
            data = statment.executeQuery();
            
            if(!data.next()){
                return groupList;
            }
            else{
                
                do {                    
                    GroupChat group = new GroupChat();
                    group.setID(data.getInt("ID"));
                    group.setGroupname(data.getString("GROUP_NAME"));
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
        return groupList;
    }



    public ArrayList<Message> getAllMessageFromUser(int ID){
        String SELECT_QUERY = "SELECT MU.ID,MU.CHATBOX_ID,UA.USERNAME,MU.TIME_SEND,MU.CONTENT,MU.VISIBLE_ONLY FROM MESSAGE_USER MU INNER JOIN USER_ACCOUNT UA ON UA.ID = MU.FROM_USER WHERE CHATBOX_ID LIKE ? ORDER BY MU.TIME_SEND DESC";
        ResultSet data = null;
        ArrayList<Message> messageList = new ArrayList<>();
        try (PreparedStatement statment = conn.prepareStatement(SELECT_QUERY,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);){
            
            String id = String.valueOf(ID);
            statment.setString(1, id + "-%");
            data = statment.executeQuery();
            
            if(!data.next()){
                return messageList;
            }
            else{
               
                
                do {                    
                    Message message = new Message();
                    message.setChatboxID(data.getString("chatbox_id"));
                    Timestamp date = data.getTimestamp("time_send");
                    String formattedDate = new SimpleDateFormat("HH:mm dd-MM-yyyy").format(date);
                    message.setDateSend(formattedDate);
                    message.setUserName(data.getString("username"));
                    message.setContent(data.getString("content"));
                    int id_visibleOnly = data.getInt("visible_only");
                    if(data.wasNull()){
                        message.setVisible_only(message.NOT_HIDE);
                    }
                    else{
                        message.setVisible_only(id_visibleOnly);
                    }
                   
                    messageList.add(message);
                    
                } while (data.next());
                return messageList;
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
        return messageList;
    }

    public ArrayList<FriendRequest> getAllFriendRequestRaw(int ID){
        String SELECT_QUERY = "SELECT FR.*,UA.USERNAME FROM FRIEND_REQUEST FR LEFT OUTER JOIN USER_ACCOUNT UA ON FR.FROM_ID = UA.ID WHERE TO_ID = ?";
        ResultSet data = null;
        ArrayList<FriendRequest> requestList = new ArrayList<>();
        try (PreparedStatement statment = conn.prepareStatement(SELECT_QUERY,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);){
            
           statment.setInt(1, ID);
            data = statment.executeQuery();
            
            if(!data.next()){
                return requestList;
            }
            else{
                do {                    
                    FriendRequest request = new FriendRequest();
                    request.setFromName(data.getString("USERNAME"));
                    request.setStatus(data.getString("STATUS"));
                    request.setTryTime(data.getInt("TRY"));
                   
                    requestList.add(request);                
                } while (data.next());
                return requestList;
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
        return requestList;
    }

    public ArrayList<UserAccount> getAllFriendRequest(int ID){
        String SELECT_QUERY = "SELECT UA.ID,UA.USERNAME,UA.FULLNAME,UA.EMAIL,UA.ONLINE FROM FRIEND_REQUEST FR LEFT OUTER JOIN USER_ACCOUNT UA ON FR.FROM_ID = UA.ID WHERE TO_ID = 1 AND FR.STATUS = 'WAIT'";
        ResultSet data = null;
        ArrayList<UserAccount> requestList = new ArrayList<>();
        try (PreparedStatement statment = conn.prepareStatement(SELECT_QUERY,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);){
            
            statment.setInt(1, ID);
            data = statment.executeQuery();
            
            if(!data.next()){
                return requestList;
            }
            else{
                do {                    
                    UserAccount account = new UserAccount();
                    account.setID(data.getInt("ID"));
                    account.setUsername(data.getString("USERNAME"));
                    account.setFullname(data.getString("FULLNAME"));
                    account.setOnline(data.getBoolean("ONLINE"));
                   
                    requestList.add(account);                
                } while (data.next());
                return requestList;
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
        return requestList;
    }

    public ArrayList<UserAccount> getAllFriendRequest(int ID,String name){
        String SELECT_QUERY = "SELECT UA.ID,UA.USERNAME,UA.FULLNAME,UA.EMAIL,UA.ONLINE FROM FRIEND_REQUEST FR LEFT OUTER JOIN USER_ACCOUNT UA ON FR.FROM_ID = UA.ID WHERE TO_ID = ? AND FR.STATUS = 'WAIT' AND (UA.USERNAME LIKE ? OR UA.FULLNAME LIKE ?)";
        ResultSet data = null;
        ArrayList<UserAccount> requestList = new ArrayList<>();
        try (PreparedStatement statment = conn.prepareStatement(SELECT_QUERY,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);){
            
           statment.setInt(1, ID);
           statment.setString(2, name);
           statment.setString(3, name);
            data = statment.executeQuery();
            
            if(!data.next()){
                return requestList;
            }
            else{
                do {                    
                    UserAccount account = new UserAccount();
                    account.setID(data.getInt("ID"));
                    account.setUsername(data.getString("USERNAME"));
                    account.setFullname(data.getString("FULLNAME"));
                    account.setOnline(data.getBoolean("ONLINE"));
                   
                    requestList.add(account);                
                } while (data.next());
                return requestList;
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
        return requestList;
    }

    public void addFriendToUser(int ID,int FriendID){
        String INSERT_QUERY = "INSERT INTO USER_FRIEND(ID,FRIEND_ID) "
        + "VALUES(?,?)";
       try (PreparedStatement statement = conn.prepareStatement(INSERT_QUERY);) {
           
            statement.setInt(1, ID);
            statement.setInt(2, FriendID);

            statement.addBatch();
            
            statement.setInt(1, FriendID);
            statement.setInt(2, ID);

            statement.addBatch();
           
            statement.executeBatch();
           
       } catch (Exception e) {
           System.out.println(e);
       }
       
    }

    public void unfriendUsers(int ID, int friendID){
        String DELETE_QUERY = "DELETE FROM USER_FRIEND WHERE ID = ? AND FRIEND_ID = ?";
        try (PreparedStatement statement = conn.prepareStatement(DELETE_QUERY);) {
           
            statement.setInt(1, ID);
            statement.setInt(2, friendID);
            statement.addBatch();
            
            statement.setInt(1, friendID);
            statement.setInt(2, ID);
            statement.addBatch();
           
            statement.executeBatch();
           
       } catch (Exception e) {
           System.out.println(e);
       }
    }

    public ArrayList<UserAccount> getAllGroupMembers(int groupID){
        String SELECT_QUERY = "SELECT UA.ID,UA.USERNAME,UA.FULLNAME,UA.ONLINE,GM.POSITION FROM GROUPCHAT_MEMBER GM LEFT OUTER JOIN USER_ACCOUNT UA ON UA.ID = GM.MEMBER_ID WHERE GM.GROUPCHAT_ID = ?";
        ResultSet data = null;
        ArrayList<UserAccount> memberList = new ArrayList<>();
        try (PreparedStatement statment = conn.prepareStatement(SELECT_QUERY,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);){
           statment.setInt(1, groupID);
            data = statment.executeQuery();
            
            if(!data.next()){
                return memberList;
            }
            else{
                do {                    
                    UserAccount account = new UserAccount();
                    account.setID(data.getInt("ID"));
                    account.setUsername(data.getString("USERNAME"));
                    account.setFullname(data.getString("FULLNAME"));
                    account.setOnline(data.getBoolean("ONLINE"));
                    account.setPosition(data.getString("POSITION"));
                   
                    memberList.add(account);                
                } while (data.next());
                return memberList;
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
        return memberList;


    }


    public void assignAdminToUser(int ID,int groupID){
        String UPDATE_QUERY = "UPDATE GROUPCHAT_MEMBER SET POSITION = 'admin' WHERE GROUPCHAT_ID = ? AND MEMBER_ID = ?";
        try (PreparedStatement statement = conn.prepareStatement(UPDATE_QUERY);) {
           
            statement.setInt(1, groupID);
            statement.setInt(2, ID);
            statement.executeUpdate();
           
       } catch (Exception e) {
           System.out.println(e);
       }
    }

    public void assignMemberToUser(int ID,int groupID){
        String UPDATE_QUERY = "UPDATE GROUPCHAT_MEMBER SET POSITION = 'member' WHERE GROUPCHAT_ID = ? AND MEMBER_ID = ?";
        try (PreparedStatement statement = conn.prepareStatement(UPDATE_QUERY);) {
           
            statement.setInt(1, groupID);
            statement.setInt(2, ID);
            statement.executeUpdate();
           
       } catch (Exception e) {
           System.out.println(e);
       }
    }

    public void setNewGroupName(String name,int groupID){
        String UPDATE_QUERY = "UPDATE GROUPCHAT SET GROUP_NAME = ? WHERE ID = ?";
        try (PreparedStatement statement = conn.prepareStatement(UPDATE_QUERY);) {
           
            statement.setString(1, name);
            statement.setInt(2, groupID);
            statement.executeUpdate();
           
       } catch (Exception e) {
           System.out.println(e);
       }
    }



}
