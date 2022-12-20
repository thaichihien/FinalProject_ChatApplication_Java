
package adminchatapp;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class MenuGroupManager extends MenuAdminLayout{

    JTable tableGroup;
    JComboBox<String> sortFilter;
    JButton viewDetailGroupButton;

    //TODO 1: nạp dữ liệu từ database vào tableLoginHistory, dựa vào sortFilter
    // để lọc 
    public void filltableGroup(){
        
    }

    
    
    public MenuGroupManager(JFrame parentFrame) {
        super(parentFrame);
        tableGroup = new JTable();
        sortFilter = new JComboBox<>();
        viewDetailGroupButton = new JButton();
        
        JLabel jLabel_danhsachnhom = new JLabel();
        JLabel jLabel_sapxeptheo = new JLabel();
        
        

        tableGroup.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Tên nhóm", "Admin", "Số lượng", "Ngày tạo"
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
