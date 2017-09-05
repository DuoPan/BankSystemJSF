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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
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
    
    private List<BankTransaction> bankTransactionList;
    private BankTransaction currBankTransaction;
    private List<User> userList;
    private User currUser;

    
    public BankTransactionManagedBean()
    {
        // variables must be init
        bankTransactionList = new ArrayList<>();
        currBankTransaction = new BankTransaction();
        userList = new ArrayList<>();
        currUser = new User();
    }
    
    @PostConstruct
    public void init()
    {
        try
        {
            bankTransactionList = transactionRepository.getAllBankTransactions();
            userList = transactionRepository.getAllUsers();
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

    // transaction CRUD   
    
    public void updateBTL()
    {
        try
        {
            transactionRepository.editTransaction(currBankTransaction);
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");           
        } catch (Exception ex)
        {
            Logger.getLogger(BankTransactionManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addBT()
    {
        try {
            transactionRepository.addTransaction(currBankTransaction);
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");                 
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage("1", new FacesMessage(
                FacesMessage.SEVERITY_ERROR, "Transaction ID is already in use.", null));
            Logger.getLogger(BankTransactionManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public void deleteBT(int id)
    {
        try
        {
            transactionRepository.removeTransaction(id);
            bankTransactionList = transactionRepository.getAllBankTransactions();
        } catch (Exception ex)
        {
            Logger.getLogger(BankTransactionManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    // user CRUD
    
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
            FacesContext.getCurrentInstance().getExternalContext().redirect("viewAllUsers.xhtml");                 
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
        
        
    // called by button my information
    public void currentUser(ActionEvent event)
    {
        try
        {
            currUser = transactionRepository.searchUserById(1);
        } catch (Exception ex)
        {
            Logger.getLogger(BankTransactionManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void currUserInfoSave()
    {
        String type = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("editfrom");
        try
        {
            transactionRepository.editUser(currUser);             
            if(type.equals("other"))
            {
                FacesContext.getCurrentInstance().getExternalContext().redirect("viewAllUsers.xhtml"); 
            }   
            else{
                FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml"); 
            }
        } catch (Exception ex)
        {
            
            Logger.getLogger(BankTransactionManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   

}

