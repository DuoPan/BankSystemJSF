/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.duopan.repository.mbeans;

import fit5042.duopan.repository.TransactionRepository;
import fit5042.duopan.repository.entities.BankTransaction;
import fit5042.duopan.repository.entities.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author duopan
 */
@ManagedBean(name = "bankTransactionManagedBean")
@SessionScoped
public class BankTransactionManagedBean implements Serializable
{
    @EJB
    TransactionRepository transactionRepository;
    
    // all users' transaction
    private List<BankTransaction> bankTransactionList;
    // one transaction to handle
    private BankTransaction currBankTransaction;
    // all users' info
    private List<User> userList;
    // one user to handle
    private User currUser;
    // handle user's transaction list
    private List<BankTransaction> currBankTransactionList;
    // login user information
    private User loginUser;

    
    public BankTransactionManagedBean()
    {
        // variables must be init
        bankTransactionList = new ArrayList<>();      
        currBankTransaction = new BankTransaction();       
        userList = new ArrayList<>();      
        currUser = new User();
        currBankTransactionList = new ArrayList<>();
        loginUser = new User();
    }
    
    @PostConstruct
    public void init()
    {
        try
        {
            bankTransactionList = transactionRepository.getAllBankTransactions();
            userList = transactionRepository.getAllUsers();
            loginUser = userList.get(0);
        } catch (Exception ex)
        {
            Logger.getLogger(BankTransactionManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


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

    public User getCurrUser()
    {
        return currUser;
    }

    public void setCurrUser(User currUser)
    {
        this.currUser = currUser;
    }

    public BankTransaction getCurrBankTransaction()
    {
        return currBankTransaction;
    }

    public void setCurrBankTransaction(BankTransaction currBankTransaction)
    {
        this.currBankTransaction = currBankTransaction;
    }

    public User getLoginUser()
    {
        return loginUser;
    }

    public void setLoginUser(User loginUser)
    {
        this.loginUser = loginUser;
    }

    public List<BankTransaction> getCurrBankTransactionList()
    {
        return currBankTransactionList;
    }

    public void setCurrBankTransactionList(List<BankTransaction> currBankTransactionList)
    {
        this.currBankTransactionList = currBankTransactionList;
    }

   

    
    
    // transaction CRUD   
    // do not allow to modify transaction records
//    public void updateBTL()
//    {
//        try
//        {
//            transactionRepository.editTransaction(currBankTransaction);
//            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");           
//        } catch (Exception ex)
//        {
//            Logger.getLogger(BankTransactionManagedBean.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    
    public void addBT()
    {
        float oldBalance = loginUser.getBalance();
        try 
        {
            if (currBankTransaction.getTransactionAmount() <= 0)
            {
                    FacesContext.getCurrentInstance().addMessage("1", new FacesMessage(
                        FacesMessage.SEVERITY_ERROR, "Money amount must larger than 0.", null));
                    return;
            }
            if (currBankTransaction.getTransactionType().equals("Deposit"))
            {
                loginUser.setBalance(loginUser.getBalance() + currBankTransaction.getTransactionAmount());
            }
            else if (currBankTransaction.getTransactionType().equals("Withdraw"))
            {
                if (currBankTransaction.getTransactionAmount() > loginUser.getBalance())
                {
                    FacesContext.getCurrentInstance().addMessage("1", new FacesMessage(
                        FacesMessage.SEVERITY_ERROR, "Balance is not enough.", null));
                    return;
                }
                loginUser.setBalance(loginUser.getBalance() - currBankTransaction.getTransactionAmount());
            }
            else if (currBankTransaction.getTransactionType().equals("Transfer"))
            {
                if (currBankTransaction.getTransactionAmount() > loginUser.getBalance())
                {
                    FacesContext.getCurrentInstance().addMessage("1", new FacesMessage(
                        FacesMessage.SEVERITY_ERROR, "Balance is not enough.", null));
                    return;
                }
                loginUser.setBalance(loginUser.getBalance() - currBankTransaction.getTransactionAmount());
            }
            else
            {
                
            }
            currBankTransaction.setUser(loginUser);
            transactionRepository.addTransaction(currBankTransaction);                    
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage("1", new FacesMessage(
                FacesMessage.SEVERITY_ERROR, "Transaction ID is already in use.", null));
            loginUser.setBalance(oldBalance);
            Logger.getLogger(BankTransactionManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            return;
        } 
        try
        { 
            // update balance
            transactionRepository.editUser(loginUser);
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml"); 
        } catch (Exception ex)
        {
            Logger.getLogger(BankTransactionManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // do not allow to delete transaction records
//    public void deleteBT(int id)
//    {
//        try
//        {
//            transactionRepository.removeTransaction(id);
//            bankTransactionList = transactionRepository.getAllBankTransactions();
//        } catch (Exception ex)
//        {
//            Logger.getLogger(BankTransactionManagedBean.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    
    
    
    // user CRUD
    
    public void currUserInfoSave()
    {
        try
        {
            transactionRepository.editUser(currUser);             
            FacesContext.getCurrentInstance().getExternalContext().redirect("wIndex.xhtml");           
        } catch (Exception ex)
        {
            
            Logger.getLogger(BankTransactionManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void loginUserInfoSave()
    {
        String type = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("editfrom");
        try
        {
            transactionRepository.editUser(loginUser); 
            if(type.equals("worker"))
            {
                FacesContext.getCurrentInstance().getExternalContext().redirect("wIndex.xhtml"); 
            }   
            else{
                FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml"); 
            }                      
        } catch (Exception ex)
        {
            
            Logger.getLogger(BankTransactionManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void editSelectedUser(ActionEvent event)
    {
        currUser = (User) event.getComponent().getAttributes().get("currSeletedU");
    }
    
    public void deleteUser(int id)
    {
        try
        {
            transactionRepository.removeUser(id);
            userList = transactionRepository.getAllUsers();
        } catch (Exception ex)
        {
            Logger.getLogger(BankTransactionManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addUser()
    {
        try {
            transactionRepository.addUser(currUser);
            FacesContext.getCurrentInstance().getExternalContext().redirect("wIndex.xhtml");                 
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage("1", new FacesMessage(
                FacesMessage.SEVERITY_ERROR, "User ID is already in use.", null));
            Logger.getLogger(BankTransactionManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    // others
    
    public void currentBT(ActionEvent event)
    {
        currBankTransaction = (BankTransaction) event.getComponent().getAttributes().get("currBT");
    }
        


    // call by bank worker, get a user's transactions
    public void showOneUserTransactions(ActionEvent event)
    {
        int id = (int) event.getComponent().getAttributes().get("uid");
        try
        {
            FacesContext ctx = FacesContext.getCurrentInstance();
            NavigationHandler nh = ctx.getApplication().getNavigationHandler();
            nh.handleNavigation(ctx, null, "wOneUserTransactions.xhtml");

            currUser = transactionRepository.searchUserById(id);
            Set<BankTransaction> temp = transactionRepository.searchTransactionsByUser(currUser);
            currBankTransactionList.clear();
            for (BankTransaction next : temp)
            {
                currBankTransactionList.add(next);
            }
        } catch (Exception ex)
        {
            Logger.getLogger(BankTransactionManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

 
}

