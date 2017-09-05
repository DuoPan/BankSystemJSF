/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.duopan;

import fit5042.duopan.repository.TransactionRepository;
import fit5042.duopan.repository.entities.*;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author duopan
 */
public class BankSystem
{
    @EJB
    private static TransactionRepository transactionRepository;

    public BankSystem()
    {
    }
    
    public void initView() 
    {
        //gui
        System.out.println("ssss");
        this.displayAllBankTransactions();
        System.out.println("finish");
    }
    private void displayAllBankTransactions() {
        try {
            List<BankTransaction> transactions = transactionRepository.getAllBankTransactions();
            for (BankTransaction transaction : transactions)
            {
                System.out.println(transaction.toString());
            }
//            if (properties != null) {
//                this.gui.displayPropertyDetails(properties);
//            }
            
        } catch (Exception ex) {
          //  this.gui.displayMessageInDialog("Failed to retrieve properties: " + ex.getMessage());
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
         try {
            final BankSystem bankSystem = new BankSystem();
            //JDK 1.7
//            SwingUtilities.invokeLater(new Runnable() {
//                @Override
//                public void run() {
//                    agency.initView();
//                }
//            });
            bankSystem.initView();
            
//            //JDK 1.8
//            SwingUtilities.invokeLater(()-> {
//                agency.initView();
//            });
        } catch (Exception ex) {
            System.out.println("Failed to run application: " + ex.getMessage());
        }
    
    }
    
}
