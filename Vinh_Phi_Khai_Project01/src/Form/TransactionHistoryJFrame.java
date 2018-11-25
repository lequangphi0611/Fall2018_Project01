/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form;

import DAO.BillDAO;
import DAO.ImportItemDAO;
import DAO.ItemDAO;
import Library.Convert;
import Model.Bill;
import Model.ImportItem;
import Model.Item;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Quang Phi
 */
public class TransactionHistoryJFrame extends javax.swing.JFrame {

    List<Bill> list;
    DefaultTableModel model;
    BillDAO billDAO = new BillDAO();
    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
    DefaultComboBoxModel modelComboBoxFilter;
    ItemDAO itemDAO = new ItemDAO();
    ImportItemDAO importDAO = new ImportItemDAO();
    DefaultTableModel modelTableImport;

    final String[] FILLTER = {
        "Tất cả",
        "Theo mặt hàng",
        "Theo ngày"
    };

    public TransactionHistoryJFrame() {
        initComponents();
        setLocationRelativeTo(null);
        model = (DefaultTableModel) tbTable.getModel();
        initSetDate();
        modelComboBoxFilter = (DefaultComboBoxModel) cboFiltersDetail.getModel();
        modelTableImport = (DefaultTableModel) tbTableImportHistory.getModel();
        load();
        addElementCBOInit();
    }

    private void initSetDate() {
        Date min = billDAO.minDate();
        Date max = billDAO.maxDate();
        dateChooseMin.setDateFormat(format);
        dateChooseMax.setDateFormat(format);
        Calendar calMin = Calendar.getInstance();
        calMin.setTime(min);
        dateChooseMin.setMinDate(calMin);
        dateChooseMax.setMinDate(calMin);
        Calendar calMax = Calendar.getInstance();
        calMax.setTime(Convert.addDate(max, 1));
        dateChooseMin.setMaxDate(calMax);
        dateChooseMax.setMaxDate(calMax);
        dateChooseMin.setSelectedDate(calMin);
        Calendar calSetMax = Calendar.getInstance();
        calSetMax.setTime(max);
        dateChooseMax.setSelectedDate(calSetMax);
    }

    private Date getMinDate() {
        Calendar cal = dateChooseMin.getSelectedDate();
        return cal.getTime();
    }

    private Date getMaxDate() {
        Calendar cal = dateChooseMax.getSelectedDate();
        return cal.getTime();
    }

    private List<Bill> getListForDate() {
        return billDAO.getListForDate(getMinDate(), getMaxDate());
    }

    private void load() {
        model.setRowCount(0);
        for (Bill bill : list) {
            model.addRow(new Object[]{
                bill.getFullTime(),
                bill.getIdBill(),
                bill.getIdEmployees(),
                bill.getTableNumber(),
                Convert.toMoney(bill.getSumPrice()),
                Convert.toMoney(bill.getSale()),
                Convert.toMoney(bill.getTotal())
            });
        }
    }

    private void showDetail() {
        int i = tbTable.getSelectedRow();
        if (i >= 0) {
            new BillDetailJDialog(this, true, "Thông Tin Hóa Đơn", list.get(i)).setVisible(true);
        }
    }

    //--------------------------------------------------------------------//
    //--------------------------------------------------------------------//
    //                            Lịch sử Nhập hàng                             
    //--------------------------------------------------------------------//
    //--------------------------------------------------------------------//
    private void addElementCBOInit() {
        cboFilter.removeAllItems();
        for (String str : FILLTER) {
            cboFilter.addItem(str);
        }
    }

    private void addItemInCBO() {
        List<Item> list = itemDAO.getAll();
        for (Item item : list) {
            modelComboBoxFilter.addElement(item);
        }
    }

    private String getStringCBOFilter() {
        return (String) cboFilter.getSelectedItem();
    }

    private void addElementCBOfilter(String filter) {
        modelComboBoxFilter.removeAllElements();
        if (filter.equals(FILLTER[1])) {
            addItemInCBO();
        } else if (filter.equals(FILLTER[2])) {
            List<Date> list = importDAO.getAllDate();
            for (Date date : list) {
                modelComboBoxFilter.addElement(Convert.formatDate(date, "dd/MM/yyyy"));
            }
        }else{
            loadImportHistory(importDAO.getListForItem(null));
        }
    }
    
    private Date getDateToCBO(){
        String date = (String) cboFiltersDetail.getSelectedItem();
        return Convert.parseDate(date, "dd/MM/yyyy");
    }

    private void loadImportHistory(List<ImportItem> list) {
        modelTableImport.setRowCount(0);
        for (ImportItem imports : list) {
            modelTableImport.addRow(new Object[]{
                imports.getFullTime(),
                itemDAO.findModel(imports.getIdItem()).get(0).getItemName(),
                imports.getIdEmployees(),
                imports.getQuantityReceived()
            });
        }
    }


    private void actionCBOFilter() {
        List<ImportItem> list = new ArrayList<>();
        try {
            Item item = (Item) cboFiltersDetail.getSelectedItem();
            list = importDAO.getListForItem(item.getIdItem());
        } catch (NullPointerException ex) {
            
        }catch(ClassCastException ex){
            list = importDAO.selectByDate(getDateToCBO());
        }
        loadImportHistory(list);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbTable = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        dateChooseMin = new datechooser.beans.DateChooserCombo();
        dateChooseMax = new datechooser.beans.DateChooserCombo();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbTableImportHistory = new javax.swing.JTable();
        cboFilter = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        cboFiltersDetail = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        jLabel2.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Lịch sử");

        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jPanel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        tbTable.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tbTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Ngày giao dich", "Mã giao dịch", "Mã Nhân viên", "Số bàn", "Tổng phụ", "Giảm trừ", "Tổng giá"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        header = tbTable.getTableHeader();
        header.setFont(new java.awt.Font("Times New Roman",0,20));
        tbTable.setRowHeight(26);
        tbTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbTableMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbTableMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tbTable);
        if (tbTable.getColumnModel().getColumnCount() > 0) {
            tbTable.getColumnModel().getColumn(0).setMinWidth(200);
            tbTable.getColumnModel().getColumn(0).setMaxWidth(210);
            tbTable.getColumnModel().getColumn(3).setMinWidth(80);
            tbTable.getColumnModel().getColumn(3).setMaxWidth(90);
        }

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel3.setText("Từ ngày :");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel4.setText("Đến ngày :");

        dateChooseMin.setCurrentView(new datechooser.view.appearance.AppearancesList("Swing",
            new datechooser.view.appearance.ViewAppearance("custom",
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(0, 0, 255),
                    true,
                    true,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 255),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(128, 128, 128),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.LabelPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.LabelPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(255, 0, 0),
                    false,
                    false,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                (datechooser.view.BackRenderer)null,
                false,
                true)));
    dateChooseMin.setCalendarPreferredSize(new java.awt.Dimension(350, 230));
    dateChooseMin.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 20));
    dateChooseMin.addSelectionChangedListener(new datechooser.events.SelectionChangedListener() {
        public void onSelectionChange(datechooser.events.SelectionChangedEvent evt) {
            dateChooseMinOnSelectionChange(evt);
        }
    });

    dateChooseMax.setCurrentView(new datechooser.view.appearance.AppearancesList("Swing",
        new datechooser.view.appearance.ViewAppearance("custom",
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                new java.awt.Color(0, 0, 0),
                new java.awt.Color(0, 0, 255),
                false,
                true,
                new datechooser.view.appearance.swing.ButtonPainter()),
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                new java.awt.Color(0, 0, 0),
                new java.awt.Color(0, 0, 255),
                true,
                true,
                new datechooser.view.appearance.swing.ButtonPainter()),
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                new java.awt.Color(0, 0, 255),
                new java.awt.Color(0, 0, 255),
                false,
                true,
                new datechooser.view.appearance.swing.ButtonPainter()),
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                new java.awt.Color(128, 128, 128),
                new java.awt.Color(0, 0, 255),
                false,
                true,
                new datechooser.view.appearance.swing.LabelPainter()),
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                new java.awt.Color(0, 0, 0),
                new java.awt.Color(0, 0, 255),
                false,
                true,
                new datechooser.view.appearance.swing.LabelPainter()),
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                new java.awt.Color(0, 0, 0),
                new java.awt.Color(255, 0, 0),
                false,
                false,
                new datechooser.view.appearance.swing.ButtonPainter()),
            (datechooser.view.BackRenderer)null,
            false,
            true)));
dateChooseMax.setCalendarPreferredSize(new java.awt.Dimension(350, 230));
dateChooseMax.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 20));
dateChooseMax.addSelectionChangedListener(new datechooser.events.SelectionChangedListener() {
    public void onSelectionChange(datechooser.events.SelectionChangedEvent evt) {
        dateChooseMaxOnSelectionChange(evt);
    }
    });

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1254, Short.MAX_VALUE))
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addGap(68, 68, 68)
            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(dateChooseMin, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(76, 76, 76)
            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(dateChooseMax, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 0, Short.MAX_VALUE))
    );
    jPanel1Layout.setVerticalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addGap(40, 40, 40)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(dateChooseMax, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(dateChooseMin, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
    );

    dateChooseMin.getAccessibleContext().setAccessibleName("");

    jTabbedPane1.addTab("Lịch sử giao dịch", jPanel1);

    jPanel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

    header = tbTableImportHistory.getTableHeader();
    header.setFont(new java.awt.Font("Times New Roman",0,20));
    tbTableImportHistory.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {
            {null, null, null, null},
            {null, null, null, null},
            {null, null, null, null},
            {null, null, null, null}
        },
        new String [] {
            "Ngày nhập kho", "Mặt hàng", "Mã nhân viên", "Số lượng"
        }
    ));
    jScrollPane2.setViewportView(tbTableImportHistory);

    cboFilter.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
    cboFilter.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            cboFilterActionPerformed(evt);
        }
    });

    jLabel5.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
    jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/iconfinder_filter_data_45534.png"))); // NOI18N
    jLabel5.setText("Lọc :");

    cboFiltersDetail.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
    cboFiltersDetail.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            cboFiltersDetailActionPerformed(evt);
        }
    });

    jLabel6.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
    jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/iconfinder_filter_data_44818.png"))); // NOI18N
    jLabel6.setText("Lọc chi tiết:");

    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
    jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jScrollPane2)
        .addGroup(jPanel2Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jLabel5)
            .addGap(8, 8, 8)
            .addComponent(cboFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(140, 140, 140)
            .addComponent(jLabel6)
            .addGap(63, 63, 63)
            .addComponent(cboFiltersDetail, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(137, Short.MAX_VALUE))
    );
    jPanel2Layout.setVerticalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(34, 34, 34)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cboFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(37, 37, 37)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cboFiltersDetail, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGap(18, 18, 18)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
    );

    jTabbedPane1.addTab("Lịch sử nhập hàng vào kho", jPanel2);

    jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/iconfinder_history_45350 (1).png"))); // NOI18N

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(506, 506, 506))
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(0, 0, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel7)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 563, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap())
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void dateChooseMaxOnSelectionChange(datechooser.events.SelectionChangedEvent evt) {//GEN-FIRST:event_dateChooseMaxOnSelectionChange
        list = getListForDate();
        load();
    }//GEN-LAST:event_dateChooseMaxOnSelectionChange

    private void dateChooseMinOnSelectionChange(datechooser.events.SelectionChangedEvent evt) {//GEN-FIRST:event_dateChooseMinOnSelectionChange
        list = getListForDate();
        load();
    }//GEN-LAST:event_dateChooseMinOnSelectionChange

    private void tbTableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTableMousePressed
        if (evt.getClickCount() == 2) {
            showDetail();
        }
    }//GEN-LAST:event_tbTableMousePressed

    private void tbTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTableMouseClicked

    }//GEN-LAST:event_tbTableMouseClicked

    private void cboFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboFilterActionPerformed
        addElementCBOfilter(getStringCBOFilter());
    }//GEN-LAST:event_cboFilterActionPerformed

    private void cboFiltersDetailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboFiltersDetailActionPerformed
        actionCBOFilter();
    }//GEN-LAST:event_cboFiltersDetailActionPerformed

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
            java.util.logging.Logger.getLogger(TransactionHistoryJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TransactionHistoryJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TransactionHistoryJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TransactionHistoryJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TransactionHistoryJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cboFilter;
    private javax.swing.JComboBox<String> cboFiltersDetail;
    private datechooser.beans.DateChooserCombo dateChooseMax;
    private datechooser.beans.DateChooserCombo dateChooseMin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tbTable;
    private JTableHeader header;
    private javax.swing.JTable tbTableImportHistory;
    // End of variables declaration//GEN-END:variables
}
