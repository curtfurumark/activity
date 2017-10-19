package mail;

import java.io.*;
import java.util.*;

public class Accounts {
    //private String m_file;

    List<Account> accounts = new ArrayList<>();

    private String p_read_file(String file) {
        String line = "";
        String all = "";
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(PopperSettings.ACCOUNTS_FILE_NAME)));
            while ((line = in.readLine()) != null) {
                all += line + "\n";
            }

            in.close();
        } catch (IOException ioe) {
            System.out.println(ioe.toString());
        }
        return all;
    }

    public int size() {
        return accounts.size();
    }

    public boolean AddAccount(String name, String ps, String user, String pwd, String port) {
        Account acc = new Account(name, ps, user, pwd, port);
        if (accounts.contains(acc)) {
            System.out.println("accounts with name already exists choose another one");
            return false;
        }
        return accounts.add(acc);
    }

    public Account GetAccount(String name) {
        for (int i = 0; i < accounts.size(); i++) {
            Account tmp = (Account) accounts.get(i);
            if (tmp.getName().equals(name)) {
                return tmp;
            }
        }
        return null;
    }

    public List<Account> GetAccounts() {
        return accounts;
    }

    public void listAccounts() {
        for (Account a : accounts) {
            System.out.println(a);
        }

    }

    private void p_init() {
        accounts.clear();
        String all = p_read_file(PopperSettings.ACCOUNTS_FILE_NAME);
        StringTokenizer token = new StringTokenizer(all, "#");
        while (token.hasMoreTokens()) {
            String s = token.nextToken();
            if (s.length() > 10) {
                accounts.add(new Account(s));
            }
        }
    }

    public Accounts(/*String file*/) {
        //m_file = file;
        p_init();
    }

    public boolean Save() {
        System.out.println("saving to file: " + PopperSettings.ACCOUNTS_FILE_NAME);
        try {
            PrintWriter ofile = new PrintWriter(new FileOutputStream(PopperSettings.ACCOUNTS_FILE_NAME));
            for (int i = 0; i < accounts.size(); i++) {
                Account account = (Account) accounts.get(i);
                ofile.write("name: " + account.getName() + " \n");
                ofile.write("pop_server: " + account.GetPopServer() + " \n");
                ofile.write("user: " + account.GetUser() + " \n");
                ofile.write("pwd: " + account.GetPwd() + " \n");
                ofile.write("port: " + account.GetPort() + " \n#\n");
            }
            ofile.close();
        } catch (IOException io) {
            System.out.println("exception: " + io.toString());
            return false;
        }
        return true;
    }
}
