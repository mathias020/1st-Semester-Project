package version5;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class GUI extends JFrame
{
	private Library library;
	
	public GUI()
	{
		library = new Library();
		
		
		// Set settings for main JFrame
		setMinimumSize(new Dimension(1024, 768));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		addWindowListener(new WindowListener()
		{

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosing(WindowEvent e) {
				onBeforeExit();
				
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});

		JTabbedPane mainTabs = new JTabbedPane();
		
		// Make the new JPanels
		BorrowReturnSearch main = createMain();
		JPanel emailTab = createEmailTab();
		JPanel overdueTab = createOverdueTab();
		ReserveNotification reserveNoti = createReservationNotificationTab();
		GUIAddRemoveItem addRemov = createAddRemoveTab();
		
		
		mainTabs.addTab("<html><body marginwidth=25><h2>Main</h2></body></html>",main);
		
		JTabbedPane notifications = new JTabbedPane();
		
		notifications.setTabPlacement(JTabbedPane.LEFT);
		
		
		notifications.addTab("<html><body><h4>Reservations</h4></body></html>", reserveNoti);
		notifications.addTab("<html><body><h4>Reminders</h4></body></html>", emailTab);
		notifications.addTab("<html><body><h4>Overdues</h4></body></html>", overdueTab);
		
		
		
		mainTabs.addTab("<html><body marginwidth=25><h2>Notifications</h2></body></html>",notifications);
		
		mainTabs.addTab("<html><body marginwidth=25><h2>Add / Remove Items</h2></body></html>",addRemov);
		
		ChangeListener mainTabsListener = new ChangeListener()
		{

			@Override
			public void stateChanged(ChangeEvent e) {
				if(mainTabs.getSelectedIndex() == 0)
				{
					main.clearAll();
				}
				else if(mainTabs.getSelectedIndex()==1)
				{
					reserveNoti.updateTable();
				}
				else if(mainTabs.getSelectedIndex()==2)
				{
					addRemov.clearAll();
				}
			}
			
		};
		
		mainTabs.addChangeListener(mainTabsListener);
		
		
		add(mainTabs);
		
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		setVisible(true);
		
	}
	
	public void onBeforeExit()
	{
//		System.exit(0);
		if(library.isBefore().length == 0 && library.isPast().length == 0)
		{
			int choice = JOptionPane.showConfirmDialog(null, "<html><body><b>Are you sure you want to exit?</b></body></html>");
			
			if(choice == JOptionPane.YES_OPTION)
			{
				library = null;
				System.exit(0);
			}
		}
		else
		{
			if(library.isBefore().length > 0 || library.isPast().length > 0)
			{
				int b = 0, p = 0;
				if(library.isBefore() != null)
				{
					b = library.isBefore().length;
				}
				
				if(library.isPast() != null)
				{
					p = library.isPast().length;
				}
				
				
				int choice = JOptionPane.showConfirmDialog(null, "<html><body><b>You have " + (b + p) + " emails to send.</b><br /> Are you sure you want to exit?</body></html>");
				
				if(choice == JOptionPane.YES_OPTION)
				{
					library = null;
					System.exit(0);
				}
			}
		}
	}
	
	public BorrowReturnSearch createMain()
	{
		BorrowReturnSearch main = new BorrowReturnSearch(library);
		
		return main;
	}
	
	public JPanel createEmailTab()
	{
		JPanel emailTab = new EmailTab2(library);
		
		return emailTab;
	}
	
	public ReserveNotification createReservationNotificationTab()
	{
		ReserveNotification resnoti = new ReserveNotification(library);
		
		return resnoti;
	}
	
	public GUIAddRemoveItem createAddRemoveTab()
	{
		GUIAddRemoveItem addRemove = new GUIAddRemoveItem(library);
		
		return addRemove;
	}
	
	public JPanel createOverdueTab()
	{
		JPanel overdueTab = new OverduesPanel(library);
		
		return overdueTab;
	}

	public static void main(String[] args)
	{
		new GUI();
	}
}	