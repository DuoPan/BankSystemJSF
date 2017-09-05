/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.duopan.repository;
import fit5042.duopan.repository.entities.*;

import java.util.List;
import java.util.Set;
import javax.ejb.Remote;


/**
 *
 * @author duopan
 */
@Remote
public interface TransactionRepository
{
    public void addTransaction(BankTransaction transaction) throws Exception;
    public void removeTransaction(int transactionNo) throws Exception;
    public void editTransaction(BankTransaction transaction) throws Exception;
    public BankTransaction searchTransactionByNo(int transactionNo) throws Exception;
    
    
    public User searchUserById(int userId) throws Exception;
    public void editUser(User user) throws Exception;
    public void addUser(User user) throws Exception;
    public void removeUser(int userId) throws Exception;
    
    public Set<BankTransaction> searchTransactionsByUser(User user) throws Exception;
    
    public List<BankTransaction> getAllBankTransactions() throws Exception;
    public List<User> getAllUsers() throws Exception;
    
//    public BankTransaction searchTransactionsByName(String transactionName) throws Exception;
   
}
