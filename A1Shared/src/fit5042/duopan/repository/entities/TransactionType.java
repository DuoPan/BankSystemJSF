/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.duopan.repository.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
/**
 *
 * @author duopan
 */
@Entity
@Table(name = "TRANSACTION_TYPE")
@NamedQueries({
        @NamedQuery(name = TransactionType.GET_ALL_QUERY_NAME, query = "SELECT t FROM TransactionType t")})
public class TransactionType implements Serializable 
{
    public static final String GET_ALL_QUERY_NAME = "TransactionType.getAll";
    
    private int typeId;
    private String typeName;

    public TransactionType()
    {
    }

    public TransactionType(int typeId, String typeName)
    {
        this.typeId = typeId;
        this.typeName = typeName;
    }

    @Id
    @GeneratedValue
    @Column(name = "type_id")
    public int getTypeId()
    {
        return typeId;
    }

    public void setTypeId(int typeId)
    {
        this.typeId = typeId;
    }

    public String getTypeName()
    {
        return typeName;
    }

    public void setTypeName(String typeName)
    {
        this.typeName = typeName;
    }

    @Override
    public String toString()
    {
        return "Transaction Type {" + "id=" + typeId + ", name=" + typeName + "}";
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TransactionType other = (TransactionType) obj;
        return this.typeId == other.typeId;
    }
    
}




