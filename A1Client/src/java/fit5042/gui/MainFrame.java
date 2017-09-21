/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.gui;

import fit5042.duopan.BankSystem;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author duopan
 */
public class MainFrame extends javax.swing.JFrame
{
    DefaultTableModel tableModel;
    // search from all
    private TableRowSorter<TableModel> rowSorter;
  
    
    
    /**
     * Creates new form MainFrame
     */
    public MainFrame()
    {
        initComponents();
        setClose();
        initView();
    }
       
    private void setClose()
    {
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                int result = JOptionPane.showConfirmDialog(null, "Log out?", "Warning", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION)
                {
                    logout();
                }
            }
        });
    }
    
    private void initView()
    {
        jLabelWel.setText(jLabelWel.getText() + " " + BankSystem.getInstance().getLoginUser().getFirstName() 
                + " " + BankSystem.getInstance().getLoginUser().getLastName());
        
        tableModel = (DefaultTableModel) jTableAll.getModel();
        RowSorter<TableModel> sorter = new TableRowSorter<>(jTableAll.getModel());  
        jTableAll.setRowSorter(sorter);
        
        tableModel.setRowCount(0);
        BankSystem.getInstance().showSelfTransaction(tableModel);
        
        rowSorter = new TableRowSorter<>(jTableAll.getModel());
        jTableAll.setRowSorter(rowSorter);
        
        // Search From All Columns Dynamicly
        jtfFilter.getDocument().addDocumentListener(new DocumentListener()
        {        
            @Override
            public void insertUpdate(DocumentEvent e)
            {
                String text = jtfFilter.getText();
                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) 
            {
                String text = jtfFilter.getText();

                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) 
            {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

        });

    }
    
    private void logout()
    {
        LoginFrame loginFrame = new LoginFrame();
        loginFrame.setVisible(true);
        this.dispose();
        BankSystem.getInstance().logout();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jLabelWel = new javax.swing.JLabel();
        jLabelAllTrans = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableAll = new javax.swing.JTable();
        jButtonMyInfo = new javax.swing.JButton();
        jtfFilter = new javax.swing.JTextField();
        jTextFieldSearchId = new javax.swing.JTextField();
        jTextFieldSearchName = new javax.swing.JTextField();
        jTextFieldSearchType = new javax.swing.JTextField();
        jTextFieldSearchAmount = new javax.swing.JTextField();
        jTextFieldSearchDes = new javax.swing.JTextField();
        jButtonMulSearch = new javax.swing.JButton();
        jButtonClear = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabelWel.setText("Welcome Public User:");

        jLabelAllTrans.setText("All Transactions        Search");

        jTableAll.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {

            },
            new String []
            {
                "No", "Name", "Type", "Description", "Amount"
            }
        )
        {
            Class[] types = new Class []
            {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean []
            {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex)
            {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex)
            {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableAll);

        jButtonMyInfo.setText("My Information");
        jButtonMyInfo.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButtonMyInfoActionPerformed(evt);
            }
        });

        jTextFieldSearchId.setToolTipText("Search ID");

        jTextFieldSearchName.setToolTipText("Search Name");

        jTextFieldSearchType.setToolTipText("Search Type");

        jTextFieldSearchAmount.setToolTipText("Search Amount");

        jTextFieldSearchDes.setToolTipText("Search Description");

        jButtonMulSearch.setText("Multiple Search");
        jButtonMulSearch.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButtonMulSearchActionPerformed(evt);
            }
        });

        jButtonClear.setText("Clear");
        jButtonClear.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButtonClearActionPerformed(evt);
            }
        });

        jButton1.setText("Create");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTextFieldSearchId, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldSearchName, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldSearchType, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextFieldSearchDes, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTextFieldSearchAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(165, 165, 165)
                .addComponent(jButtonMulSearch)
                .addGap(35, 35, 35)
                .addComponent(jButtonClear, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelWel, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelAllTrans, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jtfFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(108, 108, 108)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonMyInfo, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelWel)
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(jLabelAllTrans))
                            .addComponent(jtfFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonMyInfo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)))
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldSearchId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldSearchName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldSearchType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldSearchAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldSearchDes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonMulSearch)
                    .addComponent(jButtonClear)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonMyInfoActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonMyInfoActionPerformed
    {//GEN-HEADEREND:event_jButtonMyInfoActionPerformed
        MyInfo myInfo = new MyInfo();
        myInfo.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButtonMyInfoActionPerformed

    private void jButtonClearActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonClearActionPerformed
    {//GEN-HEADEREND:event_jButtonClearActionPerformed
        jTextFieldSearchAmount.setText("");
        jTextFieldSearchDes.setText("");
        jTextFieldSearchId.setText("");
        jTextFieldSearchName.setText("");
        jTextFieldSearchType.setText("");
        tableModel.setRowCount(0);
        BankSystem.getInstance().showSelfTransaction(tableModel);
    }//GEN-LAST:event_jButtonClearActionPerformed

    private void jButtonMulSearchActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonMulSearchActionPerformed
    {//GEN-HEADEREND:event_jButtonMulSearchActionPerformed
        ArrayList<String> array = new ArrayList<>();
        array.add(jTextFieldSearchId.getText());
        array.add(jTextFieldSearchName.getText());
        array.add(jTextFieldSearchType.getText());
        array.add(jTextFieldSearchDes.getText());
        array.add(jTextFieldSearchAmount.getText());
        tableModel.setRowCount(0);
        BankSystem.getInstance().showMulSearchSelfTransaction(tableModel,array);
    }//GEN-LAST:event_jButtonMulSearchActionPerformed


    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonClear;
    private javax.swing.JButton jButtonMulSearch;
    private javax.swing.JButton jButtonMyInfo;
    private javax.swing.JLabel jLabelAllTrans;
    private javax.swing.JLabel jLabelWel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableAll;
    private javax.swing.JTextField jTextFieldSearchAmount;
    private javax.swing.JTextField jTextFieldSearchDes;
    private javax.swing.JTextField jTextFieldSearchId;
    private javax.swing.JTextField jTextFieldSearchName;
    private javax.swing.JTextField jTextFieldSearchType;
    private javax.swing.JTextField jtfFilter;
    // End of variables declaration//GEN-END:variables
}
