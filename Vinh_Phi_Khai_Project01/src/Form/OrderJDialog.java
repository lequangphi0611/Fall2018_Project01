/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form;

import DAO.BillDAO;
import DAO.BillDetailDAO;
import DAO.CategoryDAO;
import DAO.ItemDAO;
import Data.UserData;
import Library.Convert;
import Model.Bill;
import Model.BillDetail;
import Model.Item;
import Model.ItemOrder;
import Model.Table;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Quang Phi
 */
public class OrderJDialog extends javax.swing.JDialog {

    Table tableMain;
    List<Item> listAllItem;
    ItemDAO itemDO = new ItemDAO();
    CategoryDAO cateDO = new CategoryDAO();
    BillDAO billDAO = new BillDAO();
    Bill billMain = new Bill();
    BillDetailDAO detailDAO = new BillDetailDAO();
    final String IDBILL;

    public OrderJDialog(java.awt.Frame parent, boolean modal, Table table) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        tableMain = table;
        modelInforBill = (DefaultTableModel) tbInforBill.getModel();
        modelAllItem = (DefaultTableModel) tbAllItem.getModel();
        loadToTableInforBill();
        loadAllItem();
        txtSumPrice.setText(tableMain.sumPrice() + "");
        lblTable.setText("Bàn số "+table.getTableNum());
        IDBILL = billDAO.getIDBill();
    }

    private void loadAllItem() {
        modelAllItem.setRowCount(0);
        listAllItem = itemDO.getAll();
        for (Item item : listAllItem) {
            modelAllItem.addRow(new Object[]{
                item.getItemName(),
                Convert.toMoney(item.getPrice()),
                item.getUnit(),
                cateDO.findModel(item.getIdCategory()).get(0)
            });
        }
    }

    private void loadToTableInforBill() {
        modelInforBill.setRowCount(0);
        for (ItemOrder item : tableMain.getItemOrder()) {
            modelInforBill.addRow(new Object[]{
                modelInforBill.getRowCount() + 1,
                item.getItemName(),
                item.getUnit(),
                Convert.toMoney(item.getPrice()),
                item.getQuantity(),
                Convert.toMoney(item.sumPrice())
            });
        }
    }

    private Bill getBill() {
        return new Bill(
                IDBILL,
                UserData.getUserInfor().getIdEmployees(),
                Convert.getNow(),
                Convert.getNow(),
                tableMain.getTableNum(),
                tableMain.sumPrice(),
                getSale()
        );
    }

    private long getSale() {
        try {
            return Long.parseLong(txtSale.getText());
        } catch (NumberFormatException ex) {
            //Do nothing
        }
        return 0;
    }

    private int getPercent() {
        try {
            return Integer.parseInt(txtPercent.getText());
        } catch (NumberFormatException ex) {
            //Do nothing
        }
        return 0;
    }

    private int getQuantity() {
        return (Integer) spnQuantity.getValue();
    }

    private void addItem() {
        int i = tbAllItem.getSelectedRow();
        tableMain.push(new ItemOrder(listAllItem.get(i), getQuantity()));
        loadToTableInforBill();
        txtSumPrice.setText(tableMain.sumPrice() + "");

    }

    private void giveBackItem() {
        int index = tbInforBill.getSelectedRow();
        if (index >= 0) {
            tableMain.giveBackItem(tableMain.getItemOrder().get(index), getQuantity());
            loadToTableInforBill();
            if (!tableMain.isEmpty()) {
                tbInforBill.setRowSelectionInterval(index, index);
            }
            txtSumPrice.setText(tableMain.sumPrice() + "");

        }
    }

    private void loadForm(Item item, int quantity) {
        txtItemName.setText(item.getItemName());
        txtPrice.setText(item.getPrice() + "");
        spnQuantity.setValue(quantity);
    }

    private void action() {
        Bill bill = getBill();
        if (billDAO.insert(bill)) {
            for (ItemOrder item : tableMain.getItemOrder()) {
                detailDAO.insert(new BillDetail(
                        bill.getIdBill(),
                        item.getIdItem(),
                        item.getQuantity(),
                        item.getPrice()
                ));
            }
        }
        tableMain.clear();
        loadToTableInforBill();
        resetPayForm();
        clearForm();
        new BillDetailJDialog(null, true, bill).setVisible(true);
    }

    private void clearForm(){
        loadForm(new Item(), 1);
    }
    
    private void resetPayForm(){
        txtSumPrice.setText("0");
        txtPercent.setText("0");
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbAllItem = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbInforBill = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtPercent = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtSumPrice = new javax.swing.JTextField();
        txtSale = new javax.swing.JTextField();
        txtTotal = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtItemName = new javax.swing.JTextField();
        txtPrice = new javax.swing.JTextField();
        btnAddItem = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        spnQuantity = new javax.swing.JSpinner();
        lblTable = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("DANH SÁCH THỰC ĐƠN");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        tbAllItem.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tbAllItem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên mặt hàng", "Đơn giá", "ĐVT", "Loại mặt hàng"
            }
        ));
        tbAllItem.setRowHeight(26);
        header = tbAllItem.getTableHeader();
        header.setFont(new java.awt.Font("Times New Roman",0,20));
        tbAllItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbAllItemMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbAllItem);
        if (tbAllItem.getColumnModel().getColumnCount() > 0) {
            tbAllItem.getColumnModel().getColumn(0).setMinWidth(150);
            tbAllItem.getColumnModel().getColumn(0).setMaxWidth(150);
            tbAllItem.getColumnModel().getColumn(1).setMinWidth(100);
            tbAllItem.getColumnModel().getColumn(1).setMaxWidth(100);
            tbAllItem.getColumnModel().getColumn(2).setMinWidth(100);
            tbAllItem.getColumnModel().getColumn(2).setMaxWidth(100);
            tbAllItem.getColumnModel().getColumn(3).setMinWidth(180);
            tbAllItem.getColumnModel().getColumn(3).setMaxWidth(180);
        }

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 510, 311));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("THÔNG TIN HÓA ĐƠN");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 20, -1, -1));

        tbInforBill.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tbInforBill.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mặt hàng", "ĐVT", "Đơn giá", "Số lượng", "Tổng"
            }
        ));
        headers = tbInforBill.getTableHeader();
        headers.setFont(new java.awt.Font("Times New Roman",0,20));
        tbInforBill.setRowHeight(26);
        tbInforBill.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbInforBillMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbInforBill);
        if (tbInforBill.getColumnModel().getColumnCount() > 0) {
            tbInforBill.getColumnModel().getColumn(0).setMinWidth(50);
            tbInforBill.getColumnModel().getColumn(0).setMaxWidth(50);
            tbInforBill.getColumnModel().getColumn(1).setMinWidth(200);
            tbInforBill.getColumnModel().getColumn(1).setMaxWidth(270);
        }

        jPanel2.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 60, 640, 310));

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/if_Money_206469.png"))); // NOI18N
        jButton3.setText("Thanh toán");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 520, -1, 50));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel8.setText("Giảm giá:");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 440, -1, -1));

        txtPercent.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtPercent.setText("0");
        txtPercent.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtPercentCaretUpdate(evt);
            }
        });
        jPanel2.add(txtPercent, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 440, 40, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel9.setText("%");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 440, 20, 30));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel10.setText("Tổng:");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 390, -1, -1));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel11.setText("Giảm trừ:");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 490, -1, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel12.setText("Phải thu:");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 540, -1, -1));

        txtSumPrice.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtSumPrice.setText("0");
        txtSumPrice.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtSumPriceCaretUpdate(evt);
            }
        });
        jPanel2.add(txtSumPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 390, 130, -1));

        txtSale.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtSale.setText("0");
        txtSale.setEnabled(false);
        txtSale.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtSaleCaretUpdate(evt);
            }
        });
        txtSale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSaleActionPerformed(evt);
            }
        });
        jPanel2.add(txtSale, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 490, 130, -1));

        txtTotal.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtTotal.setText("0");
        jPanel2.add(txtTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 540, 130, -1));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel13.setText("Tên mặt hàng:");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 390, -1, -1));

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel14.setText("Đơn giá:");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 440, -1, -1));

        txtItemName.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jPanel2.add(txtItemName, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 390, 180, -1));

        txtPrice.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jPanel2.add(txtPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 440, 110, -1));

        btnAddItem.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        btnAddItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Create.png"))); // NOI18N
        btnAddItem.setText("Gọi");
        btnAddItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddItemActionPerformed(evt);
            }
        });
        jPanel2.add(btnAddItem, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 530, 90, 40));

        jButton5.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/if_minus_remove_green_61638.png"))); // NOI18N
        jButton5.setText("Trả");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 530, 90, 40));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel5.setText("Số lượng:");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 490, -1, -1));

        spnQuantity.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        spnQuantity.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
        jPanel2.add(spnQuantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 490, 60, -1));

        lblTable.setFont(new java.awt.Font("Tahoma", 1, 34)); // NOI18N
        lblTable.setForeground(new java.awt.Color(0, 153, 0));
        lblTable.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTable.setText("Bàn số");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1220, Short.MAX_VALUE)
                    .addComponent(lblTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTable)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 586, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddItemActionPerformed
        addItem();
    }//GEN-LAST:event_btnAddItemActionPerformed

    private void txtSaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSaleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSaleActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        giveBackItem();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void tbAllItemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbAllItemMouseClicked
        loadForm(listAllItem.get(tbAllItem.getSelectedRow()), 1);
    }//GEN-LAST:event_tbAllItemMouseClicked

    private void tbInforBillMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbInforBillMouseClicked
        int index = tbInforBill.getSelectedRow();
        ItemOrder item = tableMain.getItemOrder().get(index);
        loadForm(item, item.getQuantity());
    }//GEN-LAST:event_tbInforBillMouseClicked

    private void txtPercentCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtPercentCaretUpdate
        long percentSale = tableMain.sumPrice() * getPercent() / 100;
        txtSale.setText(percentSale+"");
    }//GEN-LAST:event_txtPercentCaretUpdate

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        action();

    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtSumPriceCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtSumPriceCaretUpdate
        txtTotal.setText(getBill().getTotal()+"");
        int percent = Integer.parseInt(txtPercent.getText());
        txtPercent.setText(percent+"");
    }//GEN-LAST:event_txtSumPriceCaretUpdate

    private void txtSaleCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtSaleCaretUpdate
        txtTotal.setText(getBill().getTotal()+"");
    }//GEN-LAST:event_txtSaleCaretUpdate

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(OrderJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OrderJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OrderJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OrderJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                OrderJDialog dialog = new OrderJDialog(new javax.swing.JFrame(), true, new Table(0));
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    private DefaultTableModel modelInforBill;
    private DefaultTableModel modelAllItem;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddItem;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblTable;
    private javax.swing.JSpinner spnQuantity;
    private javax.swing.JTable tbAllItem;
    private JTableHeader header;
    private javax.swing.JTable tbInforBill;
    private JTableHeader headers;
    private javax.swing.JTextField txtItemName;
    private javax.swing.JTextField txtPercent;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtSale;
    private javax.swing.JTextField txtSumPrice;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
