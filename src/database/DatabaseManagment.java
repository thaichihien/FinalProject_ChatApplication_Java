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
import datastructure.UserAccount;

public class DatabaseManagment {
    
    private static volatile DatabaseManagment instance;
    private Connection conn;

    private DatabaseManagment(){
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
    

}
