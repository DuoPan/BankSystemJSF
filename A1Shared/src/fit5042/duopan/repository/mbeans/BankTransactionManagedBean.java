/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.duopan.repository.mbeans;

import fit5042.duopan.repository.TransactionRepository;
import fit5042.duopan.repository.entities.BankTransaction;
import fit5042.duopan.repository.entities.TransactionType;
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
    private static User loginUser;
    
    private String transferToUser;
    private List<String> transferToUserNames;
    private static int nextUserId;
    private static int nextTransactionNo;
    
    // login email blank
    private String loginEmail;
    // login password
    private String loginPsw;
    
    // transfer type
    private List<TransactionType> types;
    private List<String> typeNames;
    private String newType;
    
    public BankTransactionManagedBean()
    {
        // variables must be init
        bankTransactionList = new ArrayList<>();      
        currBankTransaction = new BankTransaction();       
        userList = new ArrayList<>();      
        currUser = new User();
        currBankTransactionList = new ArrayList<>();
        //loginUser = new User();
        loginEmail = "";
        loginPsw = "";
        transferToUser = "";
        transferToUserNames = new ArrayList<>();
        types = new ArrayList<>();
        typeNames = new ArrayList<>();
        newType = "";
    }
    
    @PostConstruct
    public void init()
    {
        try
        {
            bankTransactionList = transactionRepository.getAllBankTransactions();
            userList = transactionRepository.getAllUsers();
            types = transactionRepository.getAllTransactionTypes();
            for(TransactionType tt : types)
            {
                typeNames.add(tt.getTypeName());
            }
            //loginUser = userList.get(0);
            if (loginUser != null)
            {
                Set<BankTransaction> temp = transactionRepository.searchTransactionsByUser(loginUser);
                currBankTransactionList.clear();
                for (BankTransaction next : temp)
                {
                    currBankTransactionList.add(next);
                }
                for (User u : userList)
                {
                    if (u.getUserId() != loginUser.getUserId())
                    {
                        transferToUserNames.add(u.getFirstName() + " " + u.getLastName());
                    }
                }
            }
            else // first time in system
            {
                nextTransactionNo = transactionRepository.nextAvailableTransactionId();
                nextUserId = transactionRepository.nextAvailableUserId();
            }
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

//    public void setLoginUser(User loginUser)
//    {
//        this.loginUser = loginUser;
//    }

    public List<BankTransaction> getCurrBankTransactionList()
    {
        return currBankTransactionList;
    }

    public void setCurrBankTransactionList(List<BankTransaction> currBankTransactionList)
    {
        this.currBankTransactionList = currBankTransactionList;
    }

    public void setLoginEmail(String loginEmail)
    {
        this.loginEmail = loginEmail;
    }

    public String getLoginEmail()
    {
        return loginEmail;
    }

    public String getLoginPsw()
    {
        return loginPsw;
    }

    public void setLoginPsw(String loginPsw)
    {
        this.loginPsw = loginPsw;
    }

    public String getTransferToUser()
    {
        return transferToUser;
    }

    public void setTransferToUser(String transferToUser)
    {
        this.transferToUser = transferToUser;
    }

    public List<String> getTransferToUserNames()
    {
        return transferToUserNames;
    }

    public void setTransferToUserNames(List<String> transferToUserNames)
    {
        this.transferToUserNames = transferToUserNames;
    }

    public List<TransactionType> getTypes()
    {
        return types;
    }

    public void setTypes(List<TransactionType> types)
    {
        this.types = types;
    }

    public List<String> getTypeNames()
    {
        return typeNames;
    }

    public void setTypeNames(List<String> typeNames)
    {
        this.typeNames = typeNames;
    }

    public String getNewType()
    {
        return newType;
    }

    public void setNewType(String newType)
    {
        this.newType = newType;
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
                String[] names = transferToUser.split(" ");
                for (User u : userList)
                {
                    if (u.getFirstName().equals(names[0]) && u.getLastName().equals(names[1]))
                    {
                      //  int index = u.getUserId();
                        float exbalance = u.getBalance();System.err.println("fff:"+exbalance);
                        u.setBalance(exbalance + currBankTransaction.getTransactionAmount());
                        transactionRepository.editUser(u);// update balance                        
                     System.err.println("fff2:"+u.getBalance());
                        BankTransaction bt = new BankTransaction(nextTransactionNo,currBankTransaction.getTransactionName(),
                                "Transfer To",currBankTransaction.getTransactionDes(),u,currBankTransaction.getTransactionAmount());
                        transactionRepository.addTransaction(bt); 
                        nextTransactionNo++;
                        break;
                    }
                }
            }
            else
            {
                
            }
            currBankTransaction.setUser(loginUser);
            currBankTransaction.setTransactionNo(nextTransactionNo);
            transactionRepository.addTransaction(currBankTransaction);
            nextTransactionNo++;
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
            currUser = transactionRepository.searchUserById(id);
            currUser.setDel("true");
            transactionRepository.editUser(currUser);
            userList = transactionRepository.getAllUsers();
        } catch (Exception ex)
        {
            Logger.getLogger(BankTransactionManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addUser()
    {
        try {
            currUser.setDel("false");
            currUser.setUserId(nextUserId);
            nextUserId++;
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

    public void signIn(String email, String password)
    {
        try
        {
            for (User u : userList)
            {
                if (u.getEmail().equals(email) && u.getPassword().equals(password))
                {
                    loginUser = u;
                    break;
                }
            }
            if(loginUser == null)
            {
                FacesContext.getCurrentInstance().addMessage("1", new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "Wrong Email or Password!", null));
            }
            else
            {//currBankTransactionList = transactionRepository.searchTransactionsByUser(loginUser);
                if (loginUser.getType().equals("Public"))
                {
                   FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml"); 
                }
                else
                {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("wIndex.xhtml"); 
                }
            }
        } catch (Exception ex)
        {
            Logger.getLogger(BankTransactionManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public void signOut(ActionEvent event)
    {
        loginUser = null;
    }

    public void addTransactionType()
    {
        try {
            System.err.println("newtype"+newType);
            if (newType.equals(""))
            {
                FacesContext.getCurrentInstance().addMessage("1", new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "New type can not be empty!", null));
                return;
            }
            int nextId = 1;
            for (String str : typeNames)
            {
                if (str.equals(newType))
                {
                    FacesContext.getCurrentInstance().addMessage("1", new FacesMessage(
                        FacesMessage.SEVERITY_ERROR, "This type is already exist!", null));
                    return;
                }
                nextId++;
            }
            transactionRepository.addTransactionType(new TransactionType(nextId, newType));
            FacesContext.getCurrentInstance().getExternalContext().redirect("wIndex.xhtml");                 
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage("1", new FacesMessage(
                FacesMessage.SEVERITY_ERROR, "This type is already exist!", null));
            Logger.getLogger(BankTransactionManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
 
}

