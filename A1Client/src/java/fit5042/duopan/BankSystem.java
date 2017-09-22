/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.duopan;

import fit5042.duopan.repository.TransactionRepository;
import fit5042.duopan.repository.entities.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.swing.SwingUtilities;
import fit5042.gui.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author duopan
 */
public class BankSystem
{
    @EJB
    private static TransactionRepository transactionRepository;

    // all users' transaction
    private List<BankTransaction> bankTransactionList;
    // all users' info
    private List<User> userList;
    // transfer type
    private List<TransactionType> types;
    // login user information
    private User loginUser;
    private int nextTransactionNo;
    private int nextUserId;
    
    
    private static BankSystem instance;
    
    private BankSystem()
    {
        bankTransactionList = new ArrayList<>();
        userList = new ArrayList<>();
        types = new ArrayList<>();
        loginUser = new User();
        reload();
    }
    
    public static BankSystem getInstance()
    {
        if (instance == null)
        {
            instance = new BankSystem();
        }
        return instance;
    }
    
    private void reload()
    {
        try
        {
            bankTransactionList = transactionRepository.getAllBankTransactions();
            userList = transactionRepository.getAllUsers();
            types = transactionRepository.getAllTransactionTypes();
            nextTransactionNo = transactionRepository.nextAvailableTransactionId();
            nextUserId = transactionRepository.nextAvailableUserId();
        } catch (Exception ex)
        {
            Logger.getLogger(BankSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    
    
    
    // one transaction to handle
 //   private BankTransaction currBankTransaction;
    
    // one user to handle
 //   private User currUser;
    // handle user's transaction list
  //  private List<BankTransaction> currBankTransactionList;
    
    
//    private String transferToUser;
//    private List<String> transferToUserNames;
//    private static int nextUserId;
//    private static int nextTransactionNo;
//    
//    // login email blank
//    private String loginEmail;
//    // login password
//    private String loginPsw;
//    
//    // transfer type
//    private List<TransactionType> types;
//    private List<String> typeNames;
//    private String newType; 

    public List<BankTransaction> getBankTransactionList()
    {
        return bankTransactionList;
    }

    public void setBankTransactionList(List<BankTransaction> bankTransactionList)
    {
        this.bankTransactionList = bankTransactionList;
    }

    public List<User> getUserList()
    {
        return userList;
    }

    public void setUserList(List<User> userList)
    {
        this.userList = userList;
    }

    public List<TransactionType> getTypes()
    {
        return types;
    }

    public void setTypes(List<TransactionType> types)
    {
        this.types = types;
    }

    public User getLoginUser()
    {
        return loginUser;
    }

    public void setLoginUser(User loginUser)
    {
        this.loginUser = loginUser;
    }

    public int getNextTransactionNo()
    {
        return nextTransactionNo;
    }

    public void setNextTransactionNo(int nextTransactionNo)
    {
        this.nextTransactionNo = nextTransactionNo;
    }

    public int getNextUserId()
    {
        return nextUserId;
    }

    public void setNextUserId(int nextUserId)
    {
        this.nextUserId = nextUserId;
    }
    
    
    
    
    
    public void login()
    {
        LoginFrame loginFrame = new LoginFrame();
        loginFrame.setVisible(true);
    }
    
    public void logout()
    {
        loginUser = null;
    }

    // show who log in the system
    public void showSelfTransaction(DefaultTableModel tableModel)
    {   
        try
        {
            Set<BankTransaction> temp = transactionRepository.searchTransactionsByUser(loginUser);
            for (BankTransaction transaction : temp)
            {
                tableModel.addRow(new Object[]{transaction.getTransactionNo(),
                    transaction.getTransactionName(),transaction.getTransactionType(),
                    transaction.getTransactionDes(),transaction.getTransactionAmount()}); 
            }
        } catch (Exception ex)
        {
            Logger.getLogger(BankSystem.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
    public void showMulSearchSelfTransaction(DefaultTableModel tableModel, ArrayList<String> array)
    {   
        try
        {
            Set<BankTransaction> temp = transactionRepository.searchTransactionsByUser(loginUser); 
            ArrayList<BankTransaction> bts = new ArrayList<>();
            for (BankTransaction bankTransaction : temp)
            {
                bts.add(bankTransaction);
            }
            for(int i = 0; i < array.size(); ++ i)
            {
                if (!array.get(i).equals(""))
                {
                    int len = bts.size();
                    switch (i)
                    {
                        case 0:
                            for (int j = 0; j < len; j++)
                            {
                                if(!Integer.toString(bts.get(j).getTransactionNo()).contains(array.get(i)))
                                {
                                    bts.remove(j);
                                    --len;
                                    --j;
                                }
                            }
                            break;
                        case 1:
                            for (int j = 0; j < len; j++)
                            {
                                if(!bts.get(j).getTransactionName().contains(array.get(i)))
                                {
                                    bts.remove(j);
                                    --len;
                                    --j;
                                }
                            }
                            break;
                        case 2:
                            for (int j = 0; j < len; j++)
                            {
                                if(!bts.get(j).getTransactionType().contains(array.get(i)))
                                {
                                    bts.remove(j);
                                    --len;
                                    --j;
                                }
                            }
                            break;
                        case 3:
                            for (int j = 0; j < len; j++)
                            {
                                if(!bts.get(j).getTransactionDes().contains(array.get(i)))
                                {
                                    bts.remove(j);
                                    --len;
                                    --j;
                                }
                            }
                            break;
                        case 4:
                            for (int j = 0; j < len; j++)
                            {
                                if(!Float.toString(bts.get(j).getTransactionAmount()).contains(array.get(i)))
                                {
                                    bts.remove(j);
                                    --len;
                                    --j;
                                }
                            }
                            break;    
                        default:
                            break;
                    }
                }
            }
        
            for (BankTransaction transaction : bts)
            {
                tableModel.addRow(new Object[]{transaction.getTransactionNo(),
                    transaction.getTransactionName(),transaction.getTransactionType(),
                    transaction.getTransactionDes(),transaction.getTransactionAmount()}); 
            }
        } catch (Exception ex)
        {
            Logger.getLogger(BankSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void updateInfo()
    {
        try
        {
            transactionRepository.editUser(loginUser);
            reload();
        } catch (Exception ex)
        {
            Logger.getLogger(BankSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateInfo(User u)
    {
        try
        {
            transactionRepository.editUser(u);// do not reload here
        } catch (Exception ex)
        {
            Logger.getLogger(BankSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addTransaction(BankTransaction bt)
    {
        try
        {
            transactionRepository.addTransaction(bt); 
        } catch (Exception ex)
        {
            Logger.getLogger(BankSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        BankSystem bankSystem = BankSystem.getInstance();
        bankSystem.login();   
    }
    
}
