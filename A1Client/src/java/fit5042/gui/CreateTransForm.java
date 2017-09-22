/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.gui;

import fit5042.duopan.BankSystem;
import fit5042.duopan.repository.entities.BankTransaction;
import fit5042.duopan.repository.entities.TransactionType;
import fit5042.duopan.repository.entities.User;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author duopan
 */
public class CreateTransForm extends javax.swing.JFrame
{
    DefaultComboBoxModel<String> typesModel; 
    DefaultComboBoxModel<String> namesModel; 
    
    /**
     * Creates new form CreateTransForm
     */
    public CreateTransForm()
    {
        ArrayList<String> array = new ArrayList<>();              
        for (TransactionType type : BankSystem.getInstance().getTypes())
        {
            array.add(type.getTypeName());
        }
        typesModel = new DefaultComboBoxModel(array.toArray());
        
        ArrayList<String> arrayNames = new ArrayList<>();  
        for (User u : BankSystem.getInstance().getUserList())
        {
            if (u.getUserId() != BankSystem.getInstance().getLoginUser().getUserId())
            {
                arrayNames.add(u.getFirstName() + " " + u.getLastName());
            }
        }       
        namesModel = new DefaultComboBoxModel(arrayNames.toArray());
        
        initComponents();
        setClose();
        jTextFieldBalance.setText(Float.toString(BankSystem.getInstance().getLoginUser().getBalance()));
        jLabelTo.setVisible(false);
        jComboBoxTo.setVisible(false);
    }
    
    private void setClose()
    {
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                backToMainPage();
            }
        });
    }

    private void backToMainPage()
    {
        MainFrame mainFrame = new MainFrame();
        mainFrame.setVisible(true);
        this.dispose();
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldName = new javax.swing.JTextField();
        jComboBoxType = new javax.swing.JComboBox<>();
        jTextFieldDes = new javax.swing.JTextField();
        jTextFieldAmount = new javax.swing.JTextField();
        jTextFieldBalance = new javax.swing.JTextField();
        jLabelTo = new javax.swing.JLabel();
        jComboBoxTo = new javax.swing.JComboBox<>();
        jButtonCancel = new javax.swing.JButton();
        jButtonSave = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Create Transaction");

        jLabel1.setText("Name");

        jLabel2.setText("Type");

        jLabel3.setText("Description ");

        jLabel4.setText("Amount");

        jLabel5.setText("Balance");

        jComboBoxType.setModel(typesModel);
        jComboBoxType.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jComboBoxTypeActionPerformed(evt);
            }
        });

        jTextFieldBalance.setEditable(false);

        jLabelTo.setText("To");

        jComboBoxTo.setModel(namesModel);

        jButtonCancel.setText("Cancel");
        jButtonCancel.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButtonCancelActionPerformed(evt);
            }
        });

        jButtonSave.setText("Save");
        jButtonSave.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButtonSaveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabelTo))
                                .addGap(25, 25, 25)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextFieldAmount)
                                    .addComponent(jTextFieldName)
                                    .addComponent(jComboBoxType, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextFieldDes)
                                    .addComponent(jTextFieldBalance)
                                    .addComponent(jComboBoxTo, 0, 143, Short.MAX_VALUE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jButtonCancel)
                        .addGap(39, 39, 39)
                        .addComponent(jButtonSave)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBoxType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldDes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextFieldAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextFieldBalance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTo)
                    .addComponent(jComboBoxTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 42, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSave)
                    .addComponent(jButtonCancel)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonCancelActionPerformed
    {//GEN-HEADEREND:event_jButtonCancelActionPerformed
        backToMainPage();
    }//GEN-LAST:event_jButtonCancelActionPerformed

    private void jComboBoxTypeActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jComboBoxTypeActionPerformed
    {//GEN-HEADEREND:event_jComboBoxTypeActionPerformed
        if (jComboBoxType.getSelectedItem().toString().equals("Transfer"))         
        {
            jLabelTo.setVisible(true);
            jComboBoxTo.setVisible(true);          
        }
        else
        {
            jLabelTo.setVisible(false);
            jComboBoxTo.setVisible(false); 
        }
    }//GEN-LAST:event_jComboBoxTypeActionPerformed

    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonSaveActionPerformed
    {//GEN-HEADEREND:event_jButtonSaveActionPerformed
        float oldbalance = BankSystem.getInstance().getLoginUser().getBalance();
        float amount = 0;
        if (jTextFieldName.getText().equals("") || 
            jTextFieldAmount.getText().equals("") ||
            jTextFieldDes.getText().equals("") )
        {
            JOptionPane.showMessageDialog(null, "Please enter all blanks", "Waring", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        try
        {
            amount = Float.parseFloat(jTextFieldAmount.getText());
        } catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "Wrong Money Format!", "Waring", JOptionPane.INFORMATION_MESSAGE);
            return;
        }       
        if (amount <= 0)
        {
            JOptionPane.showMessageDialog(null, "Money amount must larger than 0.", "Waring", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if (jComboBoxType.getSelectedItem().toString().equals("Deposit"))  
        {
            BankSystem.getInstance().getLoginUser().setBalance(amount + oldbalance);
        }
        else if (jComboBoxType.getSelectedItem().toString().equals("Withdraw")) 
        {
            if (amount > oldbalance)
            {
                JOptionPane.showMessageDialog(null, "Balance is not enough.", "Waring", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            BankSystem.getInstance().getLoginUser().setBalance(oldbalance - amount);
        }
        else if (jComboBoxType.getSelectedItem().toString().equals("Transfer")) 
        {
            if (amount > oldbalance)
            {
                JOptionPane.showMessageDialog(null, "Balance is not enough.", "Waring", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            BankSystem.getInstance().getLoginUser().setBalance(oldbalance - amount);
            String[] names = jComboBoxTo.getSelectedItem().toString().split(" ");
            for (User u : BankSystem.getInstance().getUserList())
            {
                if (u.getFirstName().equals(names[0]) && u.getLastName().equals(names[1]))
                {
                    float exbalance = u.getBalance();
                    u.setBalance(exbalance + amount);
                    BankSystem.getInstance().updateInfo(u);
                    BankTransaction bt = new BankTransaction(BankSystem.getInstance().getNextTransactionNo(),
                            jTextFieldName.getText(),"Transfer To",jTextFieldDes.getText(),u,amount);
                    BankSystem.getInstance().addTransaction(bt);
                    BankSystem.getInstance().setNextTransactionNo(BankSystem.getInstance().getNextTransactionNo()+1);
                    break;
                }
            }
        }
        else
        {
        }
        BankTransaction bt = new BankTransaction(BankSystem.getInstance().getNextTransactionNo(),
                jTextFieldName.getText(), jComboBoxType.getSelectedItem().toString(), jTextFieldDes.getText(),
                BankSystem.getInstance().getLoginUser(), amount);
        BankSystem.getInstance().addTransaction(bt);
        BankSystem.getInstance().setNextTransactionNo(BankSystem.getInstance().getNextTransactionNo() + 1);
        BankSystem.getInstance().updateInfo();      
        
        JOptionPane.showMessageDialog(null, "Create Transaction Successfully!", "Waring", JOptionPane.INFORMATION_MESSAGE);
        backToMainPage();
    }//GEN-LAST:event_jButtonSaveActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JComboBox<String> jComboBoxTo;
    private javax.swing.JComboBox<String> jComboBoxType;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabelTo;
    private javax.swing.JTextField jTextFieldAmount;
    private javax.swing.JTextField jTextFieldBalance;
    private javax.swing.JTextField jTextFieldDes;
    private javax.swing.JTextField jTextFieldName;
    // End of variables declaration//GEN-END:variables
}
