/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.duopan.repository.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author duopan
 */
@Entity
@Table(name = "BANK_TRANSACTION")
@NamedQueries({
    @NamedQuery(name = BankTransaction.GET_ALL_QUERY_NAME, query = "SELECT t FROM BankTransaction t")})
public class BankTransaction implements Serializable 
{
    public static final String GET_ALL_QUERY_NAME = "BankTransaction.getAll";
    
    private int transactionNo;
    private String transactionName;
    private String transactionType;
    private String transactionDes;
    private float transactionAmount;
    
    private User user;

    public BankTransaction()
    {
    }

    
    public BankTransaction(int transactionNo, String transactionName, 
            String transactionType, String transactionDes, User user, 
            float transactionAmount)
    {
        this.transactionNo = transactionNo;
        this.transactionName = transactionName;
        this.transactionType = transactionType;
        this.transactionDes = transactionDes;
        this.user = user;
        this.transactionAmount = transactionAmount;
    }

    @Id
    @Column(name = "transaction_no")
    public int getTransactionNo()
    {
        return transactionNo;
    }

    public void setTransactionNo(int transactionNo)
    {
        this.transactionNo = transactionNo;
    }

    public String getTransactionName()
    {
        return transactionName;
    }

    public void setTransactionName(String transactionName)
    {
        this.transactionName = transactionName;
    }

    public String getTransactionType()
    {
        return transactionType;
    }

    public void setTransactionType(String transactionType)
    {
        this.transactionType = transactionType;
    }

    public String getTransactionDes()
    {
        return transactionDes;
    }

    public void setTransactionDes(String transactionDes)
    {
        this.transactionDes = transactionDes;
    }

    public float getTransactionAmount()
    {
        return transactionAmount;
    }

    public void setTransactionAmount(float amount)
    {
        this.transactionAmount = amount;
    }
    
    

    @ManyToOne
    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    @Override
    public String toString()
    {
        return "BankTransaction{" + "transactionNo=" + transactionNo + ", transactionName=" + transactionName + ", transactionType=" + transactionType + ", transactionDes=" + transactionDes + ", user=" + user.toString() + '}';
    }

    
    
}


    