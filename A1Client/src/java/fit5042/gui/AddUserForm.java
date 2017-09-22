/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.gui;

import fit5042.duopan.BankSystem;
import fit5042.duopan.repository.entities.User;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author duopan
 */
public class AddUserForm extends javax.swing.JFrame
{

    DefaultComboBoxModel<String> typesModel; 
    
    /**
     * Creates new form MyInfo
     */
    public AddUserForm()
    {
        String[] typeStrings = new String[] {"Public","Bank Worker"};
        typesModel = new DefaultComboBoxModel(typeStrings);
        
        initComponents();
        setClose();
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
        AdminFrame adFrame = new AdminFrame();
        adFrame.setVisible(true);        
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

        jLabel2 = new javax.swing.JLabel();
        jTextFieldFN = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldLN = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldEmail = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldPsw = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jComboBoxType = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jTextFieldPhone = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTextFieldAddress = new javax.swing.JTextField();
        jButtonCancel = new javax.swing.JButton();
        jButtonSave = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Create New User");
        getContentPane().setLayout(new java.awt.GridLayout(0, 2));

        jLabel2.setText("First Name");
        getContentPane().add(jLabel2);
        getContentPane().add(jTextFieldFN);

        jLabel3.setText("Last Name");
        getContentPane().add(jLabel3);
        getContentPane().add(jTextFieldLN);

        jLabel5.setText("Email");
        getContentPane().add(jLabel5);
        getContentPane().add(jTextFieldEmail);

        jLabel6.setText("Password");
        getContentPane().add(jLabel6);
        getContentPane().add(jTextFieldPsw);

        jLabel7.setText("Type");
        getContentPane().add(jLabel7);

        jComboBoxType.setModel(typesModel);
        jComboBoxType.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jComboBoxTypeActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBoxType);

        jLabel9.setText("Phone Number");
        getContentPane().add(jLabel9);
        getContentPane().add(jTextFieldPhone);

        jLabel10.setText("Address");
        getContentPane().add(jLabel10);
        getContentPane().add(jTextFieldAddress);

        jButtonCancel.setText("Cancel");
        jButtonCancel.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButtonCancelActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonCancel);

        jButtonSave.setText("Save");
        jButtonSave.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButtonSaveActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonSave);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBoxTypeActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jComboBoxTypeActionPerformed
    {//GEN-HEADEREND:event_jComboBoxTypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxTypeActionPerformed

    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonCancelActionPerformed
    {//GEN-HEADEREND:event_jButtonCancelActionPerformed
        backToMainPage();
    }//GEN-LAST:event_jButtonCancelActionPerformed

    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonSaveActionPerformed
    {//GEN-HEADEREND:event_jButtonSaveActionPerformed
        if (!jTextFieldFN.getText().matches("[a-zA-Z]+") || !jTextFieldLN.getText().matches("[a-zA-Z]+"))
        {
            JOptionPane.showMessageDialog(null, "Name Must Be Letters!", "Waring", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if (!jTextFieldEmail.getText().matches("[\\w\\.-]*[a-zA-Z0-9_]@[\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]") )
        {
            JOptionPane.showMessageDialog(null, "Wrong Email Format!", "Waring", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if (!jTextFieldPhone.getText().matches("^(9\\d{7})|(0\\d{9})$"))
        {
            JOptionPane.showMessageDialog(null, "Wrong Phone Number Format!", "Waring", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        User u = new User(BankSystem.getInstance().getNextUserId(),
                jTextFieldLN.getText(), jTextFieldFN.getText(), 
                jTextFieldEmail.getText(), jTextFieldPsw.getText(), 
                jComboBoxType.getSelectedItem().toString(), jTextFieldAddress.getText(),
                jTextFieldPhone.getText(),0);
        u.setDel("false");
        BankSystem.getInstance().setNextUserId(BankSystem.getInstance().getNextUserId() + 1);      
        
        BankSystem.getInstance().addUser(u);
        
        JOptionPane.showMessageDialog(null, "Create New User Successfully!", "Waring", JOptionPane.INFORMATION_MESSAGE);
            
        backToMainPage();
    }//GEN-LAST:event_jButtonSaveActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JComboBox<String> jComboBoxType;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField jTextFieldAddress;
    private javax.swing.JTextField jTextFieldEmail;
    private javax.swing.JTextField jTextFieldFN;
    private javax.swing.JTextField jTextFieldLN;
    private javax.swing.JTextField jTextFieldPhone;
    private javax.swing.JTextField jTextFieldPsw;
    // End of variables declaration//GEN-END:variables
}
