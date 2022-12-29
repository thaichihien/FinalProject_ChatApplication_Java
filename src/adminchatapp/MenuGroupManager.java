
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
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import database.DatabaseManagment;
import datastructure.GroupChat;
import uichatcomponent.DetailGroupForm;
import utils.Utils;


public class MenuGroupManager extends MenuAdminLayout{

    JTable tableGroup;
    JComboBox<String> sortFilter;
    JButton viewDetailGroupButton;

    // TODO 1: xem chi tiết của một nhóm
   

    private void viewDetailGroupButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                      
        GroupChat groupChatSelected = new GroupChat();
        
        int row = tableGroup.getSelectedRow();
        if(row < 0){    // Cảnh báo chưa chọn dòng nào trong bảng
             JOptionPane.showMessageDialog(null, "Please select an group", "Not selected", JOptionPane.WARNING_MESSAGE);
             return;
        }

        String id = tableGroup.getModel().getValueAt(row, 0).toString();
        String groupname = tableGroup.getModel().getValueAt(row, 1).toString();

        groupChatSelected.setID(Integer.parseInt(id));
        groupChatSelected.setGroupname(groupname);


        DetailGroupForm detailGroupForm = new DetailGroupForm(groupChatSelected);
        detailGroupForm.setVisible(true);
        //this.setEnabled(false);
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
    
   
    
}
