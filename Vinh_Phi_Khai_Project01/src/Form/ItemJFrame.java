/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form;

import DAO.CategoryDAO;
import DAO.ItemDAO;
import Library.Convert;
import Library.MyError;
import Library.OptionPane;
import Model.Category;
import Model.Item;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author ➻❥ ๖Kɦaї Ꮭε ๖➻❥
 */
public class ItemJFrame extends javax.swing.JFrame {

    DefaultComboBoxModel modelCategory;
    DefaultComboBoxModel modelCategoryFilter;
    DefaultTableModel modelTable;
    CategoryDAO cateDAO = new CategoryDAO();
    ItemDAO itemDAO = new ItemDAO();
    List<Item> list;

    String[] btnDeleteText = {"Xóa", "Ngừng bán", "Bán trở lại"};

    public ItemJFrame() {
        initComponents();
        modelCategory = (DefaultComboBoxModel) cboCategory.getModel();
        modelCategoryFilter = (DefaultComboBoxModel) cboIdCategoryFilter.getModel();
        this.modelTable = (DefaultTableModel) tblTable.getModel();
        txtIdItem.setText("0");
        txtPrice.setText("0");
        btnDelete.setEnabled(false);
    }

    private void loadTable(List<Item> listParam) {
        list = listParam;
        modelTable.setRowCount(0);
        for (Item item : list) {
            modelTable.addRow(new Object[]{
                modelTable.getRowCount() + 1,
                item.getItemName(),
                item.getUnit(),
                Convert.toMoney(item.getPrice()),
                getCategoryByID(item.getIdCategory()),
                item.isSell() ? "Đang bán" : "Đã ngừng bán"
            });
        }
    }

    public void fillCategory() {
        List<Category> listCategory = cateDAO.getAll();

        modelCategory.removeAllElements();

        modelCategoryFilter.removeAllElements();
        modelCategoryFilter.addElement("Tất cả");
        modelCategoryFilter.addElement("Đã ngừng bán");
        modelCategoryFilter.addElement("Đang bán");

        for (Category category : listCategory) {
            modelCategory.addElement(category);
            modelCategoryFilter.addElement(category);
        }
    }

    private Item finItemBy(String itemName) {
        List<Item> lists = itemDAO.findModel(itemName);
        return lists.isEmpty() ? null : lists.get(0);
    }

    private boolean checkDuticateItem(String itemName) {
        return finItemBy(itemName) != null;
    }

    private boolean checkItemName() {
        return MyError.isEmpty(txtItemName)
                ? OptionPane.error(txtItemName, "Không được để trống tên mặt hàng !")
                : true;
    }

    private boolean checkPrice() {
        try {
            Long price = Long.parseLong(txtPrice.getText());
            if (price <= 0) {
                return OptionPane.error(txtPrice, "Giá tiền Phải lớn hơn 0 !");
            }
            return true;
        } catch (NumberFormatException ex) {
            return OptionPane.error(txtPrice, "Giá tiền phải là số !");
        }
    }

    private Category getCategoryToCbo() {
        try {
            return (Category) cboCategory.getSelectedItem();
        } catch (NullPointerException ex) {
            throw new Error(ex);
        }
    }

    private boolean checkCBO() {
        try {
            getCategoryToCbo();
            return true;
        } catch (Error ex) {
            return OptionPane.error(cboCategory, "Vui lòng chọn loại mặt hàng hoặc thêm mới !");
        }
    }

    private boolean checkOther() {
        if (MyError.isEmpty(txtUnit)) {
            return OptionPane.error(txtUnit, "Không được để trống đơn vị tính !");
        }
        if (MyError.isEmpty(txtPrice)) {
            return OptionPane.error(txtPrice, "Không được để trống giá tiền !");
        }
        return true;
    }

    private boolean checkAll() {
        return checkItemName() && checkOther() && checkPrice() && checkCBO();
    }

    private boolean update(Item item) {
        return itemDAO.update(item);
    }

    private void add() {
        if (checkAll()) {
            Item item = getItem(true);
            if (checkDuticateItem(txtItemName.getText())) {
                if (OptionPane.confirm(this, "Mặt hàng đã tồn tại ! "
                        + "Bạn có muốn sửa mặt hàng không ?")) {
                    update(item);
                }
            } else {
                itemDAO.insert(item);
            }
            reload();
        }
    }

    private void edit() {
        if (checkAll() && OptionPane.confirm(this, "Bạn có muốn sửa mặt hàng này ?")) {
            update(getItem(true));
            reload();
        }
    }

    private void deleteOrSetSellItem() {
        String text = btnDelete.getText();
        String message = "";
        Item item = getItem(false);
        int quantityRemain = item.getQuantityRemain();
        if (quantityRemain > 0) {
            message = "Mặt hàng này trong kho vẫn còn  " + quantityRemain + " !\n";
        }
        if (text.equals(btnDeleteText[0])) {
            message += "Bạn có muốn xóa mặt hàng này ?";
            if (OptionPane.confirm(this, message)) {
                itemDAO.delete(item.getIdItem());
            }
        } else {
            boolean isSell = text.equals(btnDeleteText[2]);
            item = getItem(isSell);
            if (isSell) {
                itemDAO.setSell(item);
            } else {
                message += "Bạn có thực sự muốn ngừng kinh doanh mặt hàng này ?";
                if (OptionPane.confirm(this, message)) {
                    itemDAO.setSell(item);
                }
            }
        }
        reload();
    }

    private Category getCategoryByID(String id) {
        List<Category> lists = cateDAO.findModel(id);
        if (lists.isEmpty()) {
            return new Category();
        }
        return lists.get(0);
    }

    private Item getItem(boolean isSell) {
        return new Item(
                Integer.parseInt(txtIdItem.getText()),
                txtItemName.getText(),
                txtUnit.getText(),
                Long.parseLong(txtPrice.getText()),
                getCategoryToCbo().getIdCategory(),
                isSell
        );
    }

    private void loadForm(Item item) {
        txtIdItem.setText(item.getIdItem() + "");
        txtItemName.setText(item.getItemName());
        txtUnit.setText(item.getUnit());
        txtPrice.setText(item.getPrice() + "");
        Category category = getCategoryByID(item.getIdCategory());
        cboCategory.setSelectedIndex(getIndexComboboxToCategory(category.getCategoryName()));
        String text = btnDeleteText[0];
        if (!item.isSell()) {
            text = btnDeleteText[2];
        } else if (itemDAO.checkItemInBill(item.getIdItem())) {
            text = btnDeleteText[1];
        }
        btnDelete.setText(text);
    }

    private int getIndexComboboxToCategory(String categoryName) {
        for (int i = 0; i < cboCategory.getItemCount(); i++) {
            String combobox = ((Category) cboCategory.getItemAt(i)).toString();
            if (combobox.equals(categoryName)) {
                return i;
            }
        }
        return -1;
    }

    private void reset() {
        txtIdItem.setText("0");
        txtItemName.setText(null);
        txtPrice.setText("0");
        txtUnit.setText("");
    }

    private void reload() {
        reset();
        loadData();
        btnDelete.setEnabled(false);
        btnDelete.setText(null);
    }

    private List<Item> getListToStringcbo() throws ClassCastException {
        String cboText = (String) cboIdCategoryFilter.getSelectedItem();
        switch (cboText) {
            case "Tất cả":
                return itemDAO.getAll();
            case "Đang bán":
                return itemDAO.getItemIsSell();
            default:
                return itemDAO.getItemStopSell();
        }
    }

    private void loadData() {
        try {
            list = getListToStringcbo();
        } catch (ClassCastException ex) {
            Category cate = (Category) cboIdCategoryFilter.getSelectedItem();
            list = itemDAO.getItemByCategory(cate.getCategoryName());
        } catch (NullPointerException ex) {
        }
        loadTable(list);
    }

    private void openWare() {
        int index = tblTable.getSelectedRow();
        if (index >= 0) {
            this.dispose();
            new WareJFrame(list.get(index)).setVisible(true);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtItemName = new javax.swing.JTextField();
        txtPrice = new javax.swing.JTextField();
        cboCategory = new javax.swing.JComboBox();
        btnInsert = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnCategoryDialog = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTable = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        lblFind = new javax.swing.JLabel();
        cboIdCategoryFilter = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        txtIdItem = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtUnit = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 255, 255), 2));

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("MẶT HÀNG");

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Mặt hàng :");

        jLabel4.setBackground(new java.awt.Color(0, 0, 0));
        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Giá tiền :");

        jLabel5.setBackground(new java.awt.Color(0, 0, 0));
        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Loại hàng :");

        txtItemName.setBackground(new java.awt.Color(249, 249, 249));
        txtItemName.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txtItemName.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtItemNameCaretUpdate(evt);
            }
        });
        txtItemName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtItemNameActionPerformed(evt);
            }
        });

        txtPrice.setBackground(new java.awt.Color(249, 249, 249));
        txtPrice.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        cboCategory.setBackground(new java.awt.Color(249, 249, 249));
        cboCategory.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        btnInsert.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        btnInsert.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/if_insert-object_23421.png"))); // NOI18N
        btnInsert.setText("Thêm");
        btnInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertActionPerformed(evt);
            }
        });

        btnUpdate.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/if_compose_1055085.png"))); // NOI18N
        btnUpdate.setText("Cập nhật");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/delete.png"))); // NOI18N
        btnDelete.setText("Xóa");
        btnDelete.setPreferredSize(new java.awt.Dimension(120, 41));
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnClear.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        btnClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/new.png"))); // NOI18N
        btnClear.setText("Làm mới");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnCategoryDialog.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Create.png"))); // NOI18N
        btnCategoryDialog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCategoryDialogActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255)));

        tblTable.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tblTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No.", "Tên Mặt Hàng", "Đơn vị tính", "Giá", "Loại Mặt Hàng", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        header = tblTable.getTableHeader();
        header.setFont(new java.awt.Font("Times New Roman",0,20));
        tblTable.setRowHeight(26);
        tblTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblTable);
        if (tblTable.getColumnModel().getColumnCount() > 0) {
            tblTable.getColumnModel().getColumn(0).setMinWidth(30);
            tblTable.getColumnModel().getColumn(0).setMaxWidth(40);
            tblTable.getColumnModel().getColumn(2).setMinWidth(140);
            tblTable.getColumnModel().getColumn(2).setMaxWidth(250);
            tblTable.getColumnModel().getColumn(3).setMinWidth(120);
            tblTable.getColumnModel().getColumn(3).setMaxWidth(200);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 778, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblFind.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        lblFind.setForeground(new java.awt.Color(51, 51, 51));
        lblFind.setText("Tìm kiếm:");
        jPanel3.add(lblFind, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, -1));

        cboIdCategoryFilter.setBackground(new java.awt.Color(249, 249, 249));
        cboIdCategoryFilter.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        cboIdCategoryFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboIdCategoryFilterActionPerformed(evt);
            }
        });
        jPanel3.add(cboIdCategoryFilter, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 20, 468, -1));

        jLabel6.setBackground(new java.awt.Color(0, 0, 0));
        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Mã mặt hàng:");

        txtIdItem.setEditable(false);
        txtIdItem.setBackground(new java.awt.Color(249, 249, 249));
        txtIdItem.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txtIdItem.setEnabled(false);
        txtIdItem.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtIdItemCaretUpdate(evt);
            }
        });
        txtIdItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdItemActionPerformed(evt);
            }
        });

        jLabel7.setBackground(new java.awt.Color(0, 0, 0));
        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("  Đơn vị  :");

        txtUnit.setBackground(new java.awt.Color(249, 249, 249));
        txtUnit.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/iconfinder_fork5_62576.png"))); // NOI18N
        jButton1.setText("Nhập Kho");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(45, 45, 45))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(216, 216, 216)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnClear, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnUpdate, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(54, 54, 54)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(87, 87, 87))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(16, 16, 16)
                                        .addComponent(jLabel6))
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtUnit, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtItemName, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtIdItem, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(cboCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnCategoryDialog)))
                                .addGap(18, 18, 18)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(46, 46, 46))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel3, jLabel4, jLabel5, jLabel6});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnClear, btnDelete, btnInsert, btnUpdate});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtIdItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtItemName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtUnit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cboCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5))
                            .addComponent(btnCategoryDialog, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnClear)
                            .addComponent(btnInsert))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnUpdate)))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnClear, btnDelete, btnInsert, btnUpdate});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1383, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtItemNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtItemNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtItemNameActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        reload();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnCategoryDialogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCategoryDialogActionPerformed
        new CategoryJDialog(this, true).setVisible(true);
    }//GEN-LAST:event_btnCategoryDialogActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

    }//GEN-LAST:event_formWindowOpened

    private void btnInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertActionPerformed
        add();
    }//GEN-LAST:event_btnInsertActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        edit();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void txtItemNameCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtItemNameCaretUpdate
        Item item = finItemBy(txtItemName.getText());
        if (item != null) {
            txtIdItem.setText(item.getIdItem() + "");
        }
    }//GEN-LAST:event_txtItemNameCaretUpdate

    private void tblTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTableMouseClicked
        btnDelete.setEnabled(true);
        loadForm(list.get(tblTable.getSelectedRow()));
    }//GEN-LAST:event_tblTableMouseClicked

    private void txtIdItemCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtIdItemCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdItemCaretUpdate

    private void txtIdItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdItemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdItemActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        deleteOrSetSellItem();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void cboIdCategoryFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboIdCategoryFilterActionPerformed
        loadData();
    }//GEN-LAST:event_cboIdCategoryFilterActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        fillCategory();
    }//GEN-LAST:event_formWindowActivated

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        openWare();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(ItemJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ItemJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ItemJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ItemJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ItemJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCategoryDialog;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnInsert;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox cboCategory;
    private javax.swing.JComboBox cboIdCategoryFilter;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblFind;
    private javax.swing.JTable tblTable;
    private JTableHeader header;
    private javax.swing.JTextField txtIdItem;
    private javax.swing.JTextField txtItemName;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtUnit;
    // End of variables declaration//GEN-END:variables
}
