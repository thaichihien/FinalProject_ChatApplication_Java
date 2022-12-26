
package adminchatapp;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.security.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import database.DatabaseManagment;
import datastructure.GroupChat;
import utils.Utils;


public class MenuGroupManager extends MenuAdminLayout{

    JTable tableGroup;
    JComboBox<String> sortFilter;
    JButton viewDetailGroupButton;


    //TODO 2: cải tiến lần này kiểm tra sortFilter có 3 giá trị:
    //- Mặc định: nạp tất cả bình thường
    // - Tên nhóm: sort theo tên nhóm tăng dần 
    //- Thời gian tạo: sort thêm thời gian lâu nhất
    // Sử dụng database.getAllGroupChat(String sort,String by):
    // + sort: chỉ chấp nhận giá trị "GROUP_NAME","CREATED_AT"
    //+ by: chỉ chấp nhận giá trị "ASC","DESC"

    // Phần Nhật làm start

    // Sửa inner join -> left outer join
    public ArrayList<GroupChat> getAllGroupChat(){
        String SELECT_QUERY = "SELECT GC.ID,GC.GROUP_NAME,COUNT(MB.MEMBER_ID) AS SOLUONG,GC.CREATED_AT,GC.ONLINE FROM GROUPCHAT GC LEFT OUTER JOIN GROUPCHAT_MEMBER MB ON GC.ID = MB.GROUPCHAT_ID GROUP BY GC.ID";
        ResultSet data = null;
        ArrayList<GroupChat> groupList = new ArrayList<>();
        Connection conn = DatabaseManagment.getInstance().getConnection();
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
                    java.sql.Timestamp date = data.getTimestamp("CREATED_AT");
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
        String SELECT_QUERY = "SELECT GC.ID,GC.GROUP_NAME,COUNT(MB.MEMBER_ID) AS SOLUONG,GC.CREATED_AT,GC.ONLINE FROM GROUPCHAT GC LEFT OUTER JOIN GROUPCHAT_MEMBER MB ON GC.ID = MB.GROUPCHAT_ID GROUP BY GC.ID ORDER BY " + sort + " " + by;
        ResultSet data = null;
        ArrayList<GroupChat> groupList = new ArrayList<>();
        Connection conn = DatabaseManagment.getInstance().getConnection();
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
                    java.sql.Timestamp date = data.getTimestamp("CREATED_AT");
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

    public void filltableGroup(){
        Utils.clearTable(tableGroup);

        DatabaseManagment database = DatabaseManagment.getInstance();
        ArrayList<GroupChat> allGroupChat = database.getAllGroupChat();
        // tableFindFriend is JTable
        DefaultTableModel tableModel = (DefaultTableModel) tableGroup.getModel();
        for(GroupChat group : allGroupChat){
            String ID = String.valueOf(group.getID());
            String Groupname = group.getGroupname();
            String number = String.valueOf(group.getNumberOfMember());
            String CreatedAt = group.getCreatedAt();
            String Online = String.valueOf(group.getOnline());

            String row[] = {ID,Groupname,number,CreatedAt,Online};
            tableModel.addRow(row);
        }
    }

    // overide with SORT and By, sử dung hàm tự tạo vì lỗi INNER JOIN
    public void filltableGroup(String sort, String by) {
        Utils.clearTable(tableGroup);

        DatabaseManagment database = DatabaseManagment.getInstance();
        ArrayList<GroupChat> allGroupChat = database.getAllGroupChat(sort, by);
        // tableFindFriend is JTable
        DefaultTableModel tableModel = (DefaultTableModel) tableGroup.getModel();
        for(GroupChat group : allGroupChat){
            String ID = String.valueOf(group.getID());
            String Groupname = group.getGroupname();
            String number = String.valueOf(group.getNumberOfMember());
            String CreatedAt = group.getCreatedAt();
            String Online = String.valueOf(group.getOnline());

            String row[] = {ID,Groupname,number,CreatedAt,Online};
            tableModel.addRow(row);
        }
    }

    //handle combobox
    public void handleSortCb() {

        ActionListener cbActionListener = new ActionListener() {//add actionlistner to listen for change
            @Override
            public void actionPerformed(ActionEvent e) {

                String s = (String) sortFilter.getSelectedItem();//get the selected item

                switch (s) {//check for a match
                    case "Mặc định":
                        filltableGroup();
                        break;
                    case "Tên nhóm":
                        filltableGroup("GROUP_NAME", "ASC");
                        break;
                    case "Thời gian tạo":
                        filltableGroup("CREATED_AT",  "DESC");
                        break;
                    default:
                        System.out.println("No match selected!");
                        break;
                }
            }
        };

        sortFilter.addActionListener(cbActionListener);
    }
    
    // Phần Nhật làm end
    public MenuGroupManager(JFrame parentFrame) {
        super(parentFrame);
        initComponents();

        // sortFilter.addItemListener(new ItemListener(){

        //     @Override
        //     public void itemStateChanged(ItemEvent e) {
        //        filltableGroup();
                
        //     }
            
        // });

        filltableGroup();
        handleSortCb();
    }

    private void initComponents(){
        tableGroup = new JTable();
        sortFilter = new JComboBox<>();
        viewDetailGroupButton = new JButton();
        
        JLabel jLabel_danhsachnhom = new JLabel();
        JLabel jLabel_sapxeptheo = new JLabel();
        
        

        tableGroup.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                
            },
            new String [] {
                "Group ID","Tên nhóm", "Số lượng", "Ngày tạo", "Online"
            }
        ));
        JScrollPane jScrollPane_tableGroup = new JScrollPane();
        jScrollPane_tableGroup.setViewportView(tableGroup);

        this.add(jScrollPane_tableGroup);
        jScrollPane_tableGroup.setBounds(40, 90, 1180, 600);

        jLabel_danhsachnhom.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel_danhsachnhom.setForeground(new java.awt.Color(0, 0, 0));
        jLabel_danhsachnhom.setText("Danh sách nhóm");
        this.add(jLabel_danhsachnhom);
        jLabel_danhsachnhom.setBounds(40, 20, 370, 50);

        jLabel_sapxeptheo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel_sapxeptheo.setForeground(new java.awt.Color(0, 0, 0));
        jLabel_sapxeptheo.setText("Sắp xếp theo :");
        this.add(jLabel_sapxeptheo);
        jLabel_sapxeptheo.setBounds(530, 30, 120, 40);

        sortFilter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mặc định", "Tên nhóm", "Thời gian tạo" }));
        this.add(sortFilter);
        sortFilter.setBounds(660, 30, 190, 40);

        viewDetailGroupButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        viewDetailGroupButton.setText("Xem chi tiết");
        viewDetailGroupButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewDetailGroupButtonActionPerformed(evt);
            }
        });
        this.add(viewDetailGroupButton);
        viewDetailGroupButton.setBounds(1040, 30, 180, 40);
    }
    
    private void viewDetailGroupButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                      
//       DetailGroupForm detailGroupForm = new DetailGroupForm(this);
//       
//       detailGroupForm.setVisible(true);
//       this.setEnabled(false);
    }    
    
}
