/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.duopan.repository.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author duopan
 */
@Entity
@Table(name = "BANK_USER")
@NamedQueries({
    @NamedQuery(name = User.GET_ALL_QUERY_NAME, query = "SELECT u FROM User u WHERE u.del='false'")})
public class User implements Serializable 
{
    public static final String GET_ALL_QUERY_NAME = "User.getAll";
    
    private int userId;
    private String lastName;
    private String firstName;
    private String email;
    private String password;
    private String type;
    private String address;
    private String phone;
    private float balance;
    private String del; // if delete this user, just hide

    private Set<BankTransaction> transactions;

    public User()
    {
        
    }
    
    public User(int userId, String lastName, String firstName, String email, 
            String password, String type, String address, String phone, float balance)
    {
        this.userId = userId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.password = password;
        this.type = type;
        this.address = address;
        this.phone = phone;
        this.balance = balance;
        this.transactions = new HashSet<>();
        this.del = "false";
    }

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public float getBalance()
    {
        java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");  
        return Float.parseFloat(df.format(balance));
        //return balance;
    }

    public void setBalance(float balance)
    {
        this.balance = balance;
    }

    public String getDel()
    {
        return del;
    }

    public void setDel(String del)
    {
        this.del = del;
    }
    
    

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    public Set<BankTransaction> getTransactions()
    {
        return transactions;
    }

    public void setTransactions(Set<BankTransaction> transactions)
    {
        this.transactions = transactions;
    }
   
    @Override
    public String toString()
    {
        return "User{" + "userId=" + userId + ", lastName=" + lastName + ", firstName=" + firstName + ", email=" + email + ", password=" + password + ", type=" + type + ", address=" + address + ", phone=" + phone + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        return this.userId == other.userId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.userId;
        return hash;
    }
}



