package utils;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Utils {
    public static void clearTable(JTable table){
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        while (model.getRowCount() > 0) {                
            model.removeRow(0);
        }
    }
}
