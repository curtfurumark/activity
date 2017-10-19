package mail;

import activity.ActivityApp;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.List;
import java.util.Vector;



public class PopperPanel extends JPanel implements ActionListener, ItemListener{
	private Accounts 	accounts = new Accounts();
        private ActivityApp     activityApp;
	/*north*/
	/*west*/
	private JPanel		m_panel_west = new JPanel();
	private JButton 	m_button_check = new JButton("check account");
	private JButton         m_button_check_all = new JButton("check all accounts");
	private JButton         m_button_add_account = new JButton("add account");
	private JCheckBox       m_checkb_verbose = new JCheckBox("verbose");

	/*center*/
	private JTextArea       m_textarea = new JTextArea(12, 12);
	protected JScrollPane   m_scroll = new JScrollPane(m_textarea);

	/*south*/
	private JComboBox	m_combo_accounts = new JComboBox();
	private JPanel		m_panel_dummy = new JPanel();
	private JPanel		m_panel_user_info = new JPanel();
	private JTextField	m_tf_pop_server = new JTextField();
	private JTextField	m_tf_user = new JTextField();
	private JTextField	m_tf_port = new JTextField("110");
	private JPasswordField	m_tf_pwd = new JPasswordField();
	private JTextField	m_tf_account = new JTextField();
	private ButtonGroup 	m_bg_type = new ButtonGroup();
	private JRadioButton	m_rb_top = new JRadioButton("TOP");
	private JRadioButton    m_rb_stat = new JRadioButton("STAT");
	private JRadioButton    m_rb_list = new JRadioButton("LIST");
	private JPanel          m_panel_radiobuttons = new JPanel();
	private POP3CB			m_pop3_cb = new POP3CB(m_textarea);
	private Pop			m_pop = new Pop(m_pop3_cb);
		
	public static void main(String argv[]){
		new Popper();
	}
        public PopperPanel(ActivityApp activityApp){
            this.activityApp = activityApp;
            init();
        }
	private void init(){
		//System.out.println("POPPER ctor");
		setLayout(new BorderLayout());


/*...... USER INFO......(SOUTH)*/
		m_panel_user_info.setLayout(new GridLayout(6, 2));
		m_combo_accounts.addActionListener(this);

		m_panel_user_info.add(m_combo_accounts);
		m_bg_type.add(m_rb_top);
		m_bg_type.add(m_rb_stat);
		m_bg_type.add(m_rb_list);
		m_rb_stat.setSelected(true);

		m_panel_radiobuttons.setLayout(new GridLayout(1,3));
		m_panel_radiobuttons.add(m_rb_top);
		m_panel_radiobuttons.add(m_rb_stat);
		m_panel_radiobuttons.add(m_rb_list);

		m_panel_user_info.add(m_panel_radiobuttons);
		m_panel_user_info.add(new JLabel("account name: "));
		m_panel_user_info.add(m_tf_account);
		m_panel_user_info.add(new JLabel("pop server: "));
		m_panel_user_info.add(m_tf_pop_server);
		m_panel_user_info.add(new JLabel("user: "));
		m_panel_user_info.add(m_tf_user);
		m_panel_user_info.add(new JLabel("pwd: "));
		m_panel_user_info.add(m_tf_pwd);
		m_panel_user_info.add(new JLabel("port: "));
		m_panel_user_info.add(m_tf_port);

/*........WEST..........*/
		m_button_check.addActionListener(this);
		m_button_check_all.addActionListener(this);
		m_button_add_account.addActionListener(this);
		//m_checkb_verbose.addActionListener(this);
		m_checkb_verbose.addItemListener(this);
		m_checkb_verbose.setSelected(true);

		m_panel_west.setLayout(new GridLayout(4, 1));
		m_panel_west.add(m_button_check);
		m_panel_west.add(m_button_check_all);
		m_panel_west.add(m_button_add_account);
		m_panel_west.add(m_checkb_verbose);
		

/*...... FRAME STUFF.............*/
		add("South", m_panel_user_info);
		add("Center", m_scroll);
		add("West", m_panel_west);

		p_init_combo_box();

	}
	private void p_init_combo_box(){
		//Vector v = accounts.GetAccounts();
                List<Account> list = accounts.GetAccounts();
		for(int i = 0; i <list.size(); i++){
			Account acc =  list.get(i);
			m_combo_accounts.addItem(acc.getName());
		}
	}
	private boolean p_check_user_info(){
		if (m_tf_account.getText().length() == 0){
			return false;
		}
		if (m_tf_pop_server.getText().length()== 0){
			return false;
		}
		if (m_tf_user.getText().length() <= 0){
			return false;
		}
		char[] sz_pwd = new char[32];
		sz_pwd = m_tf_pwd.getPassword();
		String pwd = new String(sz_pwd);
		if (pwd.length() <= 0){
			return false;
		}
		if (m_tf_port.getText().length() == 0){
			return false;
		}
		return true;
	}

	private void p_check_account(Account acc){
		m_textarea.append("...ACCOUNT...: " + acc.getName() + "\n");
		try{
			if (m_rb_stat.isSelected()){
				STAT stat = m_pop.DoSTAT(acc.GetPopServer(), acc.GetUser(), acc.GetPwd(), Integer.parseInt(acc.GetPort()));
				if(stat.IsOK()){
					m_textarea.append("number of messages: " + stat.GetNumberMessages() + "\n");
					m_textarea.append("size: " + stat.GetTotalSize() + " octets\n");
				}else{
					m_textarea.append("error\n");
				}
			}
			if (m_rb_top.isSelected()){
				Vector messages = m_pop.DoTOP(acc, 0);
				for(int i = 0; i < messages.size(); i++){
					Pop3Message msg = (Pop3Message)messages.get(i);
					m_textarea.append("..........................\n");
					m_textarea.append("date: " + msg.GetHeader("date") + "\n");
					m_textarea.append("subject: " + msg.GetHeader("subject") + "\n");
					m_textarea.append("sender: " + msg.GetHeader("from") + "\n");
					m_textarea.append("..........................\n");
				}
			}
			if (m_rb_list.isSelected()){
				LIST list = m_pop.DoLIST(acc);
				for(int i = 1; i <= list.GetNumberMessages();i++){
					m_textarea.append("message: " + i + " size: " + list.GetSize(i) + " octets\n");
				}
			}
		}catch (Pop3Exception pe){
			m_textarea.append("error: " + pe.getMessage());
		}
	}
/**
* checks for messages
* if user wants to check an account not in the account list
*/
	private void p_check_for_messages(){
		m_textarea.setText("");
		if(!p_check_user_info()){
			m_textarea.append("insufficient information");
			return;
		}
		if (accounts.GetAccount(m_tf_account.getText()) == null){
			System.out.println("could not find account");
			String pop_server = m_tf_pop_server.getText();
			String user = m_tf_user.getText();
			char[] sz_pwd = new char[32];
			sz_pwd = m_tf_pwd.getPassword();
			String pwd = new String(sz_pwd);
			String port = m_tf_port.getText();
			int iport = Integer.parseInt(port);
			Account a = new Account("temp", pop_server, user, pwd, port);
			p_check_account(a);
		}else{
			p_check_account(accounts.GetAccount(m_tf_account.getText()));
		}
	}

	private void p_on_account_changed(){
		String name = (String)m_combo_accounts.getSelectedItem();
		Account acc = accounts.GetAccount(name);
		if(acc == null){
			m_textarea.setText("error");
			return;
		}
		m_tf_account.setText(acc.getName());
		m_tf_pop_server.setText(acc.GetPopServer());
		m_tf_user.setText(acc.GetUser());
		m_tf_pwd.setText(acc.GetPwd());
		m_tf_port.setText(acc.GetPort());
	}
	private void p_add_account_to_checklist(){
		m_textarea.setText("");
		if(!p_check_user_info()){
			m_textarea.append("insufficient information");
		}
		else{
			char[] sz_pwd = new char[32];
			sz_pwd = m_tf_pwd.getPassword();
			String pwd = new String(sz_pwd);
			if (accounts.AddAccount( 	m_tf_account.getText(), 
											m_tf_pop_server.getText(), 
											m_tf_user.getText(),
											pwd, 
											m_tf_port.getText() )
										){
				accounts.Save();
				m_combo_accounts.addItem(m_tf_account.getText());
				m_textarea.setText("added account: " + m_tf_account.getText());
			}
			else{
				m_textarea.setText("an account with that name already exists");
			}
		}
	}
	private void p_check_all_accounts(){
		m_textarea.setText("");
		List<Account> list = accounts.GetAccounts();
		for(int i = 0; i < list.size(); i++){
			p_check_account( list.get(i));			
		}
	}
	public void actionPerformed(ActionEvent e){

		if(e.getSource() == m_button_check){
			System.out.println("call check");
			p_check_for_messages();
			return;
		}
		if(e.getSource() == m_button_add_account){
			p_add_account_to_checklist();
			return;
		}
		if(e.getSource() == m_button_check_all){
			p_check_all_accounts();
			return;
		}
		if(e.getSource() == m_combo_accounts){
			System.out.println("combo box");
			System.out.println("selected: " + m_combo_accounts.getSelectedItem());
			p_on_account_changed();
		}

	}
	public void itemStateChanged(ItemEvent e){
		if (e == null){
			System.out.println("item event is null");
			return;
		}
		System.out.println("item state changed");
		if (e.getSource() == m_checkb_verbose){
			System.out.println("checkbox debug");
			if(m_checkb_verbose.isSelected()){
				m_pop3_cb.SetVerbose(true);
			}else{
				m_pop3_cb.SetVerbose(false);
			}
		}
	}

}