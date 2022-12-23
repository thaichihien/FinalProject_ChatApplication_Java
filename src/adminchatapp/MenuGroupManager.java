
package adminchatapp;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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

    
    
    public MenuGroupManager(JFrame parentFrame) {
        super(parentFrame);
        initComponents();

        sortFilter.addItemListener(new ItemListener(){

            @Override
            public void itemStateChanged(ItemEvent e) {
               filltableGroup();
                
            }
            
        });

        filltableGroup();
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
