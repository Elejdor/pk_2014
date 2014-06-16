import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import javax.swing.BoxLayout;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class MyCinemaProgram extends JApplet
{
	public MyCinemaProgram() {}
	
	//fields
	public static MyCinemaProgram program = null;
	
	public MyCinemaController myCinemaController = null; 
	
	public CinemaContentPanel pickUserType;
	public CinemaContentPanel sellerView;
	public CinemaContentPanel customerView;
	
	private ActionListener pickCustomer;
	private ActionListener pickSeller;
	
	public JPanel loginPanelSeller;
	public JPanel loginPanelCustomer;
	
	public void init()
	{ 
		try
		{
			SwingUtilities.invokeLater(new Runnable()
				{ 
					public void run()
					{ 
						CreateApplet();
					}
				}
			);
		}
		catch (Exception e) 
		{
	        System.err.println("CreateApplet didn't complete successfully");
		}
	}
	
	public void CreateApplet()
	{
		MyCinemaProgram.program = this;
		myCinemaController = new MyCinemaController();
		
		this.setSize(800, 400);
		this.getContentPane().setLayout(null);
		
		this.InitializePanels();
		
		this.setContentPane(pickUserType);
	}
	private void InitializePanels()
	{
		InitializePickUserType();
		
		
	}
	
	public void InitializePickUserType()
	{
		pickUserType = new CinemaContentPanel();
		pickUserType.setLayout(null);
		
		JButton buttonPickCustomer = new JButton();
		buttonPickCustomer.setFont(new Font("Tahoma", Font.PLAIN, 16));
		buttonPickCustomer.setText("Customer");
		buttonPickCustomer.setLocation(150, 180);
		buttonPickCustomer.setSize(150, 40);
		pickUserType.add(buttonPickCustomer);
		
		JButton buttonPickSeller = new JButton();
		buttonPickSeller.setFont(new Font("Tahoma", Font.PLAIN, 16));
		buttonPickSeller.setText("Seller");
		buttonPickSeller.setLocation(500, 180);
		buttonPickSeller.setSize(150, 40);
		pickUserType.add(buttonPickSeller);
		
		JLabel labelUserType = new JLabel();
		labelUserType.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelUserType.setLocation(200, 80);
		labelUserType.setHorizontalAlignment(SwingConstants.CENTER);
		labelUserType.setSize(400, 60);
		labelUserType.setText("Pick application type:");
		pickUserType.add(labelUserType);
		
		
		ActionListener pickSellerAction = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//check if login-pass is a match
				//for now
				JPanel loginPanelSeller = new JPanel();
				JTextField loginFieldSeller = new JTextField();
				JPasswordField passFieldSeller = new JPasswordField();
				loginPanelSeller.setLayout(new GridLayout(2,2));
				loginPanelSeller.add(new JLabel("Login:"));
				loginPanelSeller.add(loginFieldSeller);
				loginPanelSeller.add(new JLabel("Password:"));
				loginPanelSeller.add(passFieldSeller);
				int result = JOptionPane.showConfirmDialog(null, loginPanelSeller, "Enter login and password", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
				
				if(result == JOptionPane.OK_OPTION)
				{
					if( MyCinemaProgram.program.myCinemaController.loginSeller(loginFieldSeller.getText(), passFieldSeller.getText()) )
					{
						sellerView = new MyCinemaSellerView(myCinemaController);
						MyCinemaProgram.program.setContentPane(MyCinemaProgram.program.sellerView);
					}
					else
						JOptionPane.showMessageDialog(null, "Incorrect login or password.");
				}
				else
				{
					
				}
				
			}
		};
		buttonPickSeller.addActionListener(pickSellerAction);
		
		loginPanelCustomer = new JPanel();
		JTextField loginField = new JTextField();
		JTextField passField = new JTextField();
		loginPanelCustomer.setLayout(new GridLayout(2,2));
		loginPanelCustomer.add(new JLabel("Login:"));
		loginPanelCustomer.add(loginField);
		loginPanelCustomer.add(new JLabel("Password:"));
		loginPanelCustomer.add(passField);
		ActionListener pickCustomerAction = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JPanel loginPanelSeller = new JPanel();
				JTextField loginFieldSeller = new JTextField();
				JPasswordField passFieldSeller = new JPasswordField();
				loginPanelSeller.setLayout(new GridLayout(2,2));
				loginPanelSeller.add(new JLabel("Login:"));
				loginPanelSeller.add(loginFieldSeller);
				loginPanelSeller.add(new JLabel("Password:"));
				loginPanelSeller.add(passFieldSeller);
				int result = JOptionPane.showConfirmDialog(null, loginPanelSeller, "Enter login and password", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
				
				
				if(result == JOptionPane.OK_OPTION)
				{
					
					if( MyCinemaProgram.program.myCinemaController.loginCustomer(loginFieldSeller.getText(), passFieldSeller.getText()) )
					{
						customerView = new MyCinemaCustomerView(myCinemaController);
						MyCinemaProgram.program.setContentPane(MyCinemaProgram.program.customerView);
					}
					else
						JOptionPane.showMessageDialog(null, "Incorrect login or password.");
				}
				else
				{
						
				}
			}
		};
		buttonPickCustomer.addActionListener(pickCustomerAction);
	}
}
