/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mail;

/**
 *
 * @author curt
 */
public class TestPopper {

   // private static String accountFileName = "c:\\users\\curt\\popperAccounts.txt";
    private Accounts accounts = new Accounts();

    public static void main(String[] args) {
        new TestPopper().run();
    }

    private void run() {
        testAddAccount();
    }

    private void testAddAccount() {
        System.out.println("Account.testAddAccount()");
        accounts.AddAccount("one", "server", "user", "pwd", "187");
        accounts.AddAccount("one", "server", "user", "pwd", "187");
        System.out.println("number of accounts: " + accounts.size());
        accounts.listAccounts();
    }
}
