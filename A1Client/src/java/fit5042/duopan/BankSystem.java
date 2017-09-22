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
import fit5042.gui.*;
import java.util.ArrayList;
import java.util.Set;
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
    // operated user by bank worker
    private User currUser;
    // handle user's transaction list
    private List<BankTransaction> currBankTransactionList;
    
    private int nextTransactionNo;
    private int nextUserId;
    
    
    private static BankSystem instance;
    
    private BankSystem()
    {
        bankTransactionList = new ArrayList<>();
        userList = new ArrayList<>();
        types = new ArrayList<>();
        loginUser = new User();
        currBankTransactionList = new ArrayList<>();
        currUser = new User();
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
    
    public void reload()
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

    public List<BankTransaction> getCurrBankTransactionList()
    {
        return currBankTransactionList;
    }

    public void setCurrBankTransactionList(List<BankTransaction> currBankTransactionList)
    {
        this.currBankTransactionList = currBankTransactionList;
    }

    public User getCurrUser()
    {
        return currUser;
    }

    public void setCurrUser(User currUser)
    {
        this.currUser = currUser;
    }
    public void setCurrUserAndTrans(int id)
    {
        try
        {
            this.currUser = transactionRepository.searchUserById(id);
            currBankTransactionList.clear();
            for (BankTransaction bankTransaction : transactionRepository.searchTransactionsByUser(currUser))
            {
                currBankTransactionList.add(bankTransaction);
            }
        } catch (Exception ex)
        {
            Logger.getLogger(BankSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
    
    public void login()
    {
        LoginFrame loginFrame = new LoginFrame();
        loginFrame.setVisible(true);
    }
    
    public void logout()
    {
        loginUser = null;
        currUser = null;
        currBankTransactionList.clear();
    }

    // show who log in the system, public user main page
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
    
    public void showCurrTransaction(DefaultTableModel tableModel)
    {   
        try
        {
            for (BankTransaction transaction : currBankTransactionList)
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
    
    public void showAllTransaction(DefaultTableModel tableModel)
    {   
        try
        {
            for (BankTransaction transaction : bankTransactionList)
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
    
    // bank worker main page
    public void showAllUsersInfo(DefaultTableModel tableModel)
    {   
        try
        {
            for (User u : userList)
            {
                tableModel.addRow(new Object[]{u.getUserId(),u.getLastName(),u.getFirstName(),
                    u.getEmail(),u.getPassword(),u.getType(),u.getAddress(),u.getPhone(),u.getBalance()});         
            }
        } catch (Exception ex)
        {
            Logger.getLogger(BankSystem.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
    public void showMulSearchSelfTransaction(DefaultTableModel tableModel, ArrayList<String> array, int flag)
    {   
        try
        {
            ArrayList<BankTransaction> bts = new ArrayList<>();
            if (flag == 0)
            {
                Set<BankTransaction> temp = transactionRepository.searchTransactionsByUser(loginUser);             
                for (BankTransaction bankTransaction : temp)
                {
                    bts.add(bankTransaction);
                }
            }
            else  if (flag == 1)
            {            
                for (BankTransaction bankTransaction : currBankTransactionList)
                {
                    bts.add(bankTransaction);
                }
            }
            else
            {
                for (BankTransaction bankTransaction : bankTransactionList)
                {
                    bts.add(bankTransaction);
                }
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
    
    public void showMulSearchUser(DefaultTableModel tableModel, ArrayList<String> array)
    {   
        try
        {
            ArrayList<User> users = new ArrayList<>();                      
            for (User u : userList)
            {
                users.add(u);
            }
            for(int i = 0; i < array.size(); ++ i)
            {
                if (!array.get(i).equals(""))
                {
                    int len = users.size();
                    switch (i)
                    {
                        case 0:
                            for (int j = 0; j < len; j++)
                            {
                                if(!Integer.toString(users.get(j).getUserId()).contains(array.get(i)))
                                {
                                    users.remove(j);
                                    --len;
                                    --j;
                                }
                            }
                            break;
                        case 1:
                            for (int j = 0; j < len; j++)
                            {
                                if(!users.get(j).getLastName().contains(array.get(i)))
                                {
                                    users.remove(j);
                                    --len;
                                    --j;
                                }
                            }
                            break;
                        case 2:
                            for (int j = 0; j < len; j++)
                            {
                                if(!users.get(j).getFirstName().contains(array.get(i)))
                                {
                                    users.remove(j);
                                    --len;
                                    --j;
                                }
                            }
                            break;
                        case 3:
                            for (int j = 0; j < len; j++)
                            {
                                if(!users.get(j).getEmail().contains(array.get(i)))
                                {
                                    users.remove(j);
                                    --len;
                                    --j;
                                }
                            }
                            break;
                        case 4:
                            for (int j = 0; j < len; j++)
                            {
                                if(!users.get(j).getPassword().contains(array.get(i)))
                                {
                                    users.remove(j);
                                    --len;
                                    --j;
                                }
                            }
                            break;
                        case 5:
                            for (int j = 0; j < len; j++)
                            {
                                if(!users.get(j).getType().contains(array.get(i)))
                                {
                                    users.remove(j);
                                    --len;
                                    --j;
                                }
                            }
                            break;
                        case 6:
                            for (int j = 0; j < len; j++)
                            {
                                if(!users.get(j).getAddress().contains(array.get(i)))
                                {
                                    users.remove(j);
                                    --len;
                                    --j;
                                }
                            }
                            break;
                        case 7:
                            for (int j = 0; j < len; j++)
                            {
                                if(!users.get(j).getPhone().contains(array.get(i)))
                                {
                                    users.remove(j);
                                    --len;
                                    --j;
                                }
                            }
                            break;
                        case 8:
                            for (int j = 0; j < len; j++)
                            {
                                if(!Float.toString(users.get(j).getBalance()).contains(array.get(i)))
                                {
                                    users.remove(j);
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
        
            for (User u : users)
            {
                tableModel.addRow(new Object[]{u.getUserId(),u.getLastName(),u.getFirstName(),
                    u.getEmail(),u.getPassword(),u.getType(),u.getAddress(),u.getPhone(),u.getBalance()}); 
            }
        } catch (Exception ex)
        {
            Logger.getLogger(BankSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void updateInfo(int flag)
    {
        try
        {
            if (flag == 0)
            {
                transactionRepository.editUser(loginUser);
            }
            else
            {
                transactionRepository.editUser(currUser);
            }
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
    
    public void addUser(User u)
    {
        try
        {
            transactionRepository.addUser(u); 
            userList.add(u);
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
    
    public void addTransactionType(TransactionType tt)
    {
        try
        {
            transactionRepository.addTransactionType(tt); 
            types.add(tt);
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
