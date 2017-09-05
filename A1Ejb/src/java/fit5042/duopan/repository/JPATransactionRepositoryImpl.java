/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.duopan.repository;

import fit5042.duopan.repository.entities.BankTransaction;
import fit5042.duopan.repository.entities.User;
import java.util.List;
import java.util.Set;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author duopan
 */
@Stateful
public class JPATransactionRepositoryImpl implements TransactionRepository
{
    @PersistenceContext(unitName = "A1EjbPU")
    private EntityManager entityManager;

    @Override
    public List<BankTransaction> getAllBankTransactions() throws Exception
    {
        return this.entityManager.createNamedQuery(BankTransaction.GET_ALL_QUERY_NAME).getResultList(); 
    }
    
    @Override
    public void editTransaction(BankTransaction transaction) throws Exception
    {
        entityManager.merge(transaction);
    }

    @Override
    public void removeTransaction(int transactionNo) throws Exception
    {
        BankTransaction bt = searchTransactionByNo(transactionNo);
        if (bt != null)
        {
            entityManager.remove(bt);               
        }
    }

    @Override
    public void addTransaction(BankTransaction transaction) throws Exception
    {
        entityManager.persist(transaction);
    }

    @Override
    public BankTransaction searchTransactionByNo(int transactionNo) throws Exception
    {
        return entityManager.find(BankTransaction.class, transactionNo);
    }

    @Override
    public List<User> getAllUsers() throws Exception
    {
        return this.entityManager.createNamedQuery(User.GET_ALL_QUERY_NAME).getResultList();
    }

    @Override
    public User searchUserById(int userId) throws Exception
    {
        return entityManager.find(User.class, userId);
    }

    @Override
    public void editUser(User user) throws Exception
    {
        entityManager.merge(user);
    }

    @Override
    public void addUser(User user) throws Exception
    {
        entityManager.persist(user);
    }

    @Override
    public void removeUser(int userId) throws Exception
    {
        User u = searchUserById(userId);
        if (u != null)
        {
            entityManager.remove(u);               
        }
    }

    @Override
    public Set<BankTransaction> searchTransactionsByUser(User user) throws Exception
    {
        user = entityManager.find(User.class, user.getUserId());
        user.getTransactions().size();// can not delete this line
        entityManager.refresh(user);

        return user.getTransactions();
    }
    
   
}
