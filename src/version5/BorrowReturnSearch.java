package version5;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class BorrowReturnSearch extends JTabbedPane
{
	private Library library;
	
	private File bookIcon;
	private File articleIcon;
	private File cdIcon;
	private File dvdIcon;
	
	private String BOOK;
	private String ARTICLE;
	private String CD;
	private String DVD;
	
	
	
	
	// Save a copy of the Person in a field for security
	private Person selectedPerson = null;
	
	// BORROW
	JTextField borrowIDfield = new JTextField(20);
	JTextField itemTitle = new JTextField(25);
	JTextField itemUniqueID = new JTextField(25);
	
	JLabel nameLabel = new JLabel("---");
	JLabel emailLabel = new JLabel("---");
	JLabel addrLabel = new JLabel("---");
	JLabel statusField = new JLabel("---");
	
	JTabbedPane borrowedReservedList = new JTabbedPane();
	
	String[] types = {"Choose an item type", "Book", "Article", "CD", "DVD"};
	JComboBox itemTypeBorrow = new JComboBox(types);
	
	// RETURN
	JTextField ritemTitle = new JTextField(25);
//	String[] types = {"Choose an item type", "Book", "Article", "CD", "DVD"};
	JComboBox itemType = new JComboBox(types);
	JTextField ritemUniqueID = new JTextField(25);
	
	
	// SEARCH
    JTextField searchField = new JTextField(50);
	
	public BorrowReturnSearch(Library l)
	{
		super();
		
		File jarFile = null;
		try {
			jarFile = new File(this.getClass().getProtectionDomain()
					.getCodeSource().getLocation().toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
   
   
		bookIcon = new File("file:" + jarFile.getParentFile() + "\\" + "book-icon.png");
		articleIcon = new File("file:" + jarFile.getParentFile() + "\\Article-Icon.gif");
		cdIcon = new File("file:" + jarFile.getParentFile() + "\\cdicon.png");
		dvdIcon = new File("file:" + jarFile.getParentFile() + "\\" + "dvdicon.png");
		
		
		
//		BOOK = "<html><body><img width=25 height=25 src=" + bookIcon.toString() + "></body></html>";
//		ARTICLE = "<html><body><img width=25 height=25 src=" + articleIcon.toString() + "></body></html>";
//		CD = "<html><body><img width=25 height=25 src=file:" + cdIcon.toString() + "></body></html>";
//		DVD = "<html><body valign=center><img width=25 height=25 src=" + dvdIcon.toString() + "></body></html>";
		
		BOOK = "Book";
		ARTICLE = "Article";
		CD = "CD";
		DVD = "DVD";
		
		library = l;
		
//		JTabbedPane mainPanel = new JTabbedPane();
		
		setTabPlacement(JTabbedPane.LEFT);
		
		JPanel borrow = new JPanel();
		
		createBorrowTab(borrow);
		
		JPanel returnP = new JPanel();
		
		createReturnPanel(returnP);
		
		JPanel search = new JPanel(new BorderLayout());
		
		createSearchPanel(search, this);
		
		
		borrow.setOpaque(false);
		
		add("<html><body><h4>Borrow</h4></body></html>", borrow);
		add("<html><body><h4>Return</h4></body></html>", returnP);
		add("<html><body><h4>Search</h4></body></html>", search);
		
		
		ChangeListener mainTabListener = new ChangeListener()
		{

			@Override
			public void stateChanged(ChangeEvent e) {
				if(getSelectedIndex() == 0)
				{
					clearBorrow();
				}
				else if(getSelectedIndex() == 1)
				{
					clearReturn();
				}
				else if(getSelectedIndex() == 2)
				{
					clearSearch();
				}
				
			}
			
		};
		
		addChangeListener(mainTabListener);
	}
	
	
	// BORROW
	public void createBorrowTab(JPanel panel)
	{
		panel.setLayout(new BorderLayout());
		
		
		// Create top panel with search bar
		JPanel topPanel = new JPanel(new BorderLayout());
		
		panel.add(topPanel, BorderLayout.NORTH);
		
		JPanel topLeftPanel = new JPanel();
		
		topPanel.add(topLeftPanel, BorderLayout.WEST);
		
		JPanel topRightPanel = new JPanel(new BorderLayout());
		
		
		JPanel topCenterPanel = new JPanel(new BorderLayout());
		
		
		topPanel.add(topCenterPanel, BorderLayout.CENTER);
		topPanel.add(topRightPanel, BorderLayout.EAST);
		
		// ADD / EDIT USER BUTTONS - start
		
		JPanel leftCenterPanel = new JPanel();
		
		leftCenterPanel.setLayout(new BoxLayout(leftCenterPanel, BoxLayout.LINE_AXIS));
		
		topCenterPanel.add(leftCenterPanel, BorderLayout.WEST);
		
		JButton editUserButton = new JButton("Edit Account");
		JButton addUserButton = new JButton("Add an account");
		
		leftCenterPanel.add(Box.createRigidArea(new Dimension(50,0)));	
		leftCenterPanel.add(editUserButton);
		
		
		topRightPanel.add(addUserButton);
		
		ActionListener addUserButtonListener = new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				JFrame addUserFrame = new AddUser(library);
				
				addUserFrame.setLocationRelativeTo(null);
			}
		};
		
		addUserButton.addActionListener(addUserButtonListener);
		
		ActionListener editUserButtonListener = new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(selectedPerson != null)
				{
					JFrame editUserFrame = new JFrame("Edit User | ID #" + selectedPerson.getId());
					
					editUserFrame.setSize(new Dimension(400, 500));

					JPanel panel = new EditUser(selectedPerson.getId(), library);
					
					editUserFrame.setLocationRelativeTo(null);
					
					editUserFrame.add(panel);
					
					
					
					editUserFrame.setVisible(true);
				}
				else if(selectedPerson == null || borrowIDfield.getText().isEmpty())
				{
					JFrame editUserFrame = new JFrame("Edit User");
					
					editUserFrame.setSize(new Dimension(400, 500));

					JPanel panel = new EditUser(library);
					
					editUserFrame.setLocationRelativeTo(null);
					
					editUserFrame.add(panel);
					
					editUserFrame.setVisible(true);
				}
			}
		};
		
		editUserButton.addActionListener(editUserButtonListener);
		
		// ADD / EDIT USER BUTTONS - end
		
		topLeftPanel.add(Box.createRigidArea(new Dimension(20,20)));
		
		
		topLeftPanel.add(new JLabel("Borrower ID: "));
		
		borrowIDfield.setMargin(new Insets(2, 2, 2, 2));
		
		topLeftPanel.add(borrowIDfield);
		
		
		// Create information area
		JPanel centerPanel = new JPanel(new BorderLayout());
		
		centerPanel.add(Box.createRigidArea(new Dimension(20,20)));
		
		centerPanel.setBorder(BorderFactory.createRaisedBevelBorder());
		
		
		panel.add(centerPanel, BorderLayout.CENTER);
		
		
		JPanel leftCenter = new JPanel();
		leftCenter.add(Box.createRigidArea(new Dimension(20,20)));
		JPanel rightCenter = new JPanel();
		rightCenter.add(Box.createRigidArea(new Dimension(20,20)));
		JPanel bottomCenter = new JPanel(new BorderLayout());
		bottomCenter.add(Box.createRigidArea(new Dimension(20,20)));
		
		centerPanel.add(leftCenter, BorderLayout.WEST);
		centerPanel.add(rightCenter, BorderLayout.CENTER);
		centerPanel.add(bottomCenter, BorderLayout.SOUTH);
		
		TitledBorder title;
		title = BorderFactory.createTitledBorder("<html><body><font size=4>Borrower Information</font></body></html>");
		leftCenter.setBorder(title);
		
		leftCenter.setPreferredSize(new Dimension(300, 200));
		
		TitledBorder titleBorrow;
		titleBorrow = BorderFactory.createTitledBorder("<html><body><font size=4>Borrow an Item</font></body></html>");
		rightCenter.setBorder(titleBorrow);
		
		// Create the Borrow an Item box
		
		rightCenter.setLayout(new BorderLayout());
		
		JPanel borrowItemContainer = new JPanel(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		
		c.anchor = GridBagConstraints.WEST;
		c.gridx = 0;
		c.gridy = 0;
		c.weighty = 0.1;
		c.insets = new Insets(10, 10, 0, 0);
		borrowItemContainer.add(new JLabel("Item title:"), c);
		
		
		c.anchor = GridBagConstraints.WEST;
		c.gridx = 1;
		c.gridy = 0;
		c.weighty = 0.1;
		c.insets = new Insets(10, 10, 0 ,0);

		itemTitle.setMargin(new Insets(2, 2, 2, 2));
		borrowItemContainer.add(itemTitle, c);
		
		c.anchor = GridBagConstraints.WEST;
		c.gridx = 0;
		c.gridy = 1;
		c.weighty = 0.1;
		c.insets = new Insets(10, 10, 0, 0);
		borrowItemContainer.add(new JLabel("Item type"), c);
		
		c.anchor = GridBagConstraints.WEST;
		c.gridx = 1;
		c.gridy = 1;
		c.weighty = 0.1;
		c.insets = new Insets(10, 10, 0 ,0);

		borrowItemContainer.add(itemTypeBorrow, c);	
		
		c.anchor = GridBagConstraints.WEST;
		c.gridx = 0;
		c.gridy = 2;
		c.weighty = 0.1;
		c.insets = new Insets(10, 10, 0, 0);
		borrowItemContainer.add(new JLabel("Unique ID:"), c);
		
		c.anchor = GridBagConstraints.WEST;
		c.gridx = 1;
		c.gridy = 2;
		c.weighty = 0.1;
		c.insets = new Insets(10, 10, 0 ,0);
		
		itemUniqueID.setMargin(new Insets(2, 2, 2, 2));
		borrowItemContainer.add(itemUniqueID, c);		
		

		
		c.anchor = GridBagConstraints.WEST;
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 2;
		c.weighty = 0.1;
		c.insets = new Insets(10, 10, 0, 0);
		JButton borrowItem = new JButton("Borrow Item");
		borrowItem.setMargin(new Insets(5, 5, 5, 5));
		borrowItemContainer.add(borrowItem, c);
		
		
		ActionListener borrowItemListener = new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(!(selectedPerson == null && itemUniqueID.getText().isEmpty() && itemTitle.getText().isEmpty() && itemTypeBorrow.getSelectedIndex() == 0))
				{
					// Try borrowing the item to the person
					
//					Item getItem = library.getAdapter().getItem(itemTitle.getText());
					
					Item getItem = null;
					
					Item[] allItems = library.getAdapter().getAllItems();
					
					if(allItems != null)
					{
						for(int i = 0; i < allItems.length; i++)
						{
							if( allItems[i].getTitle().toUpperCase().equals(itemTitle.getText().toUpperCase()) && 
									allItems[i].getType().toUpperCase().equals(((String) itemTypeBorrow.getSelectedItem()).toUpperCase()) )
							{
								getItem = allItems[i];
								break;
							}
						}
					}
					
					
					if(getItem != null)
					{
						if(library.checkUniqueIDExists(getItem, getItem.getTitle() + "-" + getItem.getType() + "-" + itemUniqueID.getText()))
						{
							library.borrowItem(getItem.getTitle() + "-" + getItem.getType() + "-" + itemUniqueID.getText(), selectedPerson, getItem);
							
							itemUniqueID.setText("");
							itemTitle.setText("");
							itemTypeBorrow.setSelectedIndex(0);
							
							
							borrowedReservedList.removeAll();
							createBorrowList(borrowedReservedList, selectedPerson.getId());
							createReservedList(borrowedReservedList, selectedPerson.getId());
						}
						else
						{
							JOptionPane.showMessageDialog(null, "The Unique ID was not found in the item database. Please check information.", "Check information", JOptionPane.ERROR_MESSAGE);
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "No item with such a name found in the item database.", "Check information", JOptionPane.ERROR_MESSAGE);
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Please search for a borrower or fill in all the required fields for lending out items.", "Check information", JOptionPane.ERROR_MESSAGE);
				}
			}
		};
		
		borrowItem.addActionListener(borrowItemListener);
		
		rightCenter.add(borrowItemContainer, BorderLayout.WEST);
		
		
		// Add left center information labels
		leftCenter.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		
		// Name field
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 0.1;
		gbc.insets = new Insets(10, 10, 0, 0);
		leftCenter.add(new JLabel("Name: "), gbc);
		
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.insets = new Insets(10, 10, 0, 0);
		
		nameLabel.setFont(nameLabel.getFont().deriveFont(
	            nameLabel.getFont().getStyle() & ~Font.BOLD));
		leftCenter.add(nameLabel, gbc);
		
		
		// Email field
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.insets = new Insets(10, 10, 0, 0);
		leftCenter.add(new JLabel("E-mail: "), gbc);
		
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.insets = new Insets(10, 10, 0, 0);
		
		emailLabel.setFont(emailLabel.getFont().deriveFont(
	            emailLabel.getFont().getStyle() & ~Font.BOLD));
		leftCenter.add(emailLabel, gbc);
		
		// Address field
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.insets = new Insets(10, 10, 0, 0);
		leftCenter.add(new JLabel("Address: "), gbc);
		
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.insets = new Insets(10, 10, 0, 0);
		
		addrLabel.setFont(addrLabel.getFont().deriveFont(
	            addrLabel.getFont().getStyle() & ~Font.BOLD));
		leftCenter.add(addrLabel, gbc);
		
		// Status field
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.insets = new Insets(10, 10, 0, 0);
		leftCenter.add(new JLabel("Status: "), gbc);
		
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.insets = new Insets(10, 10, 0, 0);
		
		statusField.setFont(statusField.getFont().deriveFont(
	            statusField.getFont().getStyle() & ~Font.BOLD));
		leftCenter.add(statusField, gbc);
		
		// Create tabbed pane south
		
		
		borrowedReservedList.setPreferredSize(new Dimension(300, 450));
		
		String[] columns = {"ID", "Type", "Title", "Author", "ISBN", "Return date"};
		
		Object[][] data = new Object[0][0];
		
		JTable table = new JTable(data, columns);
		
		JScrollPane scroll = new JScrollPane(table);
		
		
		String[] rcolumns = {"Type", "Title", "Author", "ISBN"};
		
		Object[][] rdata = new Object[0][0];
		
		JTable rtable = new JTable(rdata, rcolumns);
		
		JScrollPane rscroll = new JScrollPane(rtable);
		
		borrowedReservedList.addTab("<html><body><h4>Borrowed Items</h4></body></html>", scroll);
		borrowedReservedList.addTab("<html><body><h4>Reserved Items</h4></body></html>", rscroll);
		
		bottomCenter.add(borrowedReservedList, BorderLayout.CENTER);
		
		
		KeyListener borrowIDfieldListener = new KeyListener()
		{

			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					
					addrLabel.setText("---");
					emailLabel.setText("---");
					nameLabel.setText("---");
					statusField.setText("---");
					
					itemUniqueID.setText("");
					itemTitle.setText("");
					
					if(borrowedReservedList.getTabCount() > 0)
					{
						borrowedReservedList.removeAll();
					}
					
					selectedPerson = null;
					
					Person getPerson = library.getPerson(borrowIDfield.getText());
					
					if(getPerson != null)
					{
						selectedPerson = getPerson.copy();
						
						if(getPerson.isTeacher())
							statusField.setText("Teacher");
						else
							statusField.setText("Student");
						
						addrLabel.setText(getPerson.getAddress());
						emailLabel.setText(getPerson.getEmail());
						nameLabel.setText(getPerson.getName());
						
						createBorrowList(borrowedReservedList, borrowIDfield.getText());
						createReservedList(borrowedReservedList, borrowIDfield.getText());
					}
					else
					{
						createBlankBorrow(borrowedReservedList);
						createBlankReserve(borrowedReservedList);
						JOptionPane.showMessageDialog(null, "No borrower found. Please try again.", "No Borrower Found", JOptionPane.ERROR_MESSAGE);
					}
				}
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		};
		
		borrowIDfield.addKeyListener(borrowIDfieldListener);
		
	}
	
	
	public void createBlankBorrow(JTabbedPane tabPane)
	{
		String[] columns = {"ID", "Type", "Title", "Author", "ISBN", "Return date"};
		
		Object[][] data = new Object[0][0];
		
		JTable table = new JTable(data, columns);
		
		JScrollPane scroll = new JScrollPane(table);
		
		tabPane.addTab("<html><body><h4>Borrowed Items</h4></body></html>", scroll);
	}
	
	public void createBlankReserve(JTabbedPane tabPane)
	{
		String[] columns = {"Type", "Title", "Author", "ISBN"};
		
		Object[][] data = new Object[0][0];
		
		JTable table = new JTable(data, columns);
		
		JScrollPane scroll = new JScrollPane(table);
		
		tabPane.addTab("<html><body><h4>Reserved Items</h4></body></html>", scroll);
	}
	
	public void createBorrowList(JTabbedPane tabPane, String personID)
	{
		String[] columns = {"ID", "Type", "Title", "Author", "ISBN", "Return date"};
		
		Object[][] data = new Object[0][0];
		
		JTable table = new JTable(data, columns);
		
	    table.setFillsViewportHeight(true);
	    table.setFocusable(false);
	    table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    table.setRowHeight(35);

	    table.getColumnModel().getColumn(0)
	            .setCellRenderer(new MultiColorRenderer(true));
	    table.getColumnModel().getColumn(1)
	            .setCellRenderer(new MultiColorRenderer(true));
	    table.getColumnModel().getColumn(2)
	            .setCellRenderer(new MultiColorRenderer());
	    table.getColumnModel().getColumn(3)
	            .setCellRenderer(new MultiColorRenderer());
	    table.getColumnModel().getColumn(4)
        .setCellRenderer(new MultiColorRenderer());
	    table.getColumnModel().getColumn(5)
        .setCellRenderer(new MultiColorRenderer());
	     
	    table.getColumnModel().getColumn(0).setMaxWidth(100);
	     
	    table.getColumnModel().getColumn(1).setMaxWidth(60);
		
		Borrowed[] getBorrowedItems = library.getBorrowedBy(personID);
		
		Object[][] content = null;
		
		
		if(getBorrowedItems != null)
		{
			content = new Object[library.getBorrowedBy(personID).length][6];
			
			for(int i = 0; i < getBorrowedItems.length; i++)
			{
	            if (getBorrowedItems[i].getItem() instanceof Book) 
	            {
	                Book other = (Book) getBorrowedItems[i].getItem();
	                content[i][0] = getBorrowedItems[i].getUniqueID().split("-")[getBorrowedItems[i].getUniqueID().split("-").length-1];
	                content[i][1] = BOOK;
	                content[i][2] = other.getTitle();
	                content[i][3] = other.getAuthor();
	                content[i][4] = other.getISBN();
	                content[i][5] = getBorrowedItems[i].getReturnDate();
	            } 
	            else if (getBorrowedItems[i].getItem() instanceof Article) 
	            {
	                Article other = (Article) getBorrowedItems[i].getItem();
	                 
	                content[i][0] = getBorrowedItems[i].getUniqueID().split("-")[getBorrowedItems[i].getUniqueID().split("-").length-1];
	                content[i][1] = ARTICLE;
	                content[i][2] = other.getTitle();
	                content[i][3] = other.getAuthor();
	                content[i][4] = "---";
	                content[i][5] = getBorrowedItems[i].getReturnDate();
	            } 
	            else if (getBorrowedItems[i].getItem() instanceof CD) 
	            {
	                CD other = (CD) getBorrowedItems[i].getItem();
	                 
	                content[i][0] = getBorrowedItems[i].getUniqueID().split("-")[getBorrowedItems[i].getUniqueID().split("-").length-1];
	                content[i][1] = CD;
	                content[i][2] = other.getTitle();
	                content[i][3] = other.getArtist();
	                content[i][4] = "---";
	                content[i][5] = getBorrowedItems[i].getReturnDate();
	            } 
	            else if (getBorrowedItems[i].getItem() instanceof DVD) 
	            {
	                DVD other = (DVD) getBorrowedItems[i].getItem();
	                 
	                content[i][0] = getBorrowedItems[i].getUniqueID().split("-")[getBorrowedItems[i].getUniqueID().split("-").length-1];
	                content[i][1] = DVD;
	                content[i][2] = other.getTitle();
	                content[i][3] = other.getDirector();
	                content[i][4] = "---";
	                content[i][5] = getBorrowedItems[i].getReturnDate();
	            } 
	            else
	            {
	                content[i][0] = "N/A";
	                content[i][1] = "N/A";
	                content[i][2] = "Unknown";
	                content[i][3] = "Unknown";
	                content[i][4] = "Unknown";
	                content[i][5] = "Unknown";
	            }
			}
		}
		else
		{
			content = new Object[0][0];
		}
		
		MyModel dtm = new MyModel(content, columns);
		
		table.setModel(dtm);
	    table.getColumnModel().getColumn(0)
        .setCellRenderer(new MultiColorRenderer(true));
		table.getColumnModel().getColumn(1)
		        .setCellRenderer(new MultiColorRenderer(true));
		table.getColumnModel().getColumn(2)
		        .setCellRenderer(new MultiColorRenderer());
		table.getColumnModel().getColumn(3)
		        .setCellRenderer(new MultiColorRenderer());
		table.getColumnModel().getColumn(4)
		.setCellRenderer(new MultiColorRenderer());
		table.getColumnModel().getColumn(5)
		.setCellRenderer(new MultiColorRenderer());


	table.getColumnModel().getColumn(0).setMaxWidth(100);
	
	table.getColumnModel().getColumn(1).setMaxWidth(60);
	
		dtm.fireTableDataChanged();
		
		JScrollPane scroll = new JScrollPane(table);
		
		
		
		tabPane.addTab("<html><body><h4>Borrowed Items ( " + content.length + " )</h4></body></html>", scroll);
	}
	
	public void createReservedList(JTabbedPane tabPane, String personID)
	{
		String[] columns = {"Type", "Title", "Author", "ISBN"};
		
		Object[][] data = new Object[0][0];
		
		JTable table = new JTable(data, columns);
		
	    table.setFillsViewportHeight(true);
	    table.setFocusable(false);
	    table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    table.setRowHeight(35);

	    table.getColumnModel().getColumn(0)
        .setCellRenderer(new MultiColorRenderer(true));
		table.getColumnModel().getColumn(1)
		        .setCellRenderer(new MultiColorRenderer(true));
		table.getColumnModel().getColumn(2)
		        .setCellRenderer(new MultiColorRenderer());
		table.getColumnModel().getColumn(3)
		        .setCellRenderer(new MultiColorRenderer());


	table.getColumnModel().getColumn(0).setMaxWidth(60);
		
		Reserved[] getBorrowedItems = library.getReservedBy(personID);
		
		Object[][] content = null;
		
		if(getBorrowedItems != null)
		{
			content = new Object[library.getReservedBy(personID).length][4];
			
			for(int i = 0; i < getBorrowedItems.length; i++)
			{
	            if (getBorrowedItems[i].getItem() instanceof Book) 
	            {
	                Book other = (Book) getBorrowedItems[i].getItem();
	                content[i][0] = BOOK;
	                content[i][1] = other.getTitle();
	                content[i][2] = other.getAuthor();
	                content[i][3] = other.getISBN();
	            } 
	            else if (getBorrowedItems[i].getItem() instanceof Article) 
	            {
	                Article other = (Article) getBorrowedItems[i].getItem();
	                 
	                content[i][0] = ARTICLE;
	                content[i][1] = other.getTitle();
	                content[i][2] = other.getAuthor();
	                content[i][3] = "---";
	            } 
	            else if (getBorrowedItems[i].getItem() instanceof CD) 
	            {
	                CD other = (CD) getBorrowedItems[i].getItem();
	                 
	                content[i][0] = CD;
	                content[i][1] = other.getTitle();
	                content[i][2] = other.getArtist();
	                content[i][3] = "---";
	            } 
	            else if (getBorrowedItems[i].getItem() instanceof DVD) 
	            {
	                DVD other = (DVD) getBorrowedItems[i].getItem();
	                 
	                content[i][0] = DVD;
	                content[i][1] = other.getTitle();
	                content[i][2] = other.getDirector();
	                content[i][3] = "---";
	            } 
	            else
	            {
	                content[i][0] = "N/A";
	                content[i][1] = "Unknown";
	                content[i][2] = "Unknown";
	                content[i][3] = "Unknown";
	            }
			}
		}
		else
		{
			content = new Object[0][0];
		}
		
		MyModel dtm = new MyModel(content, columns);
		
		table.setModel(dtm);
	    table.getColumnModel().getColumn(0)
        .setCellRenderer(new MultiColorRenderer(true));
		table.getColumnModel().getColumn(1)
		        .setCellRenderer(new MultiColorRenderer(true));
		table.getColumnModel().getColumn(2)
		        .setCellRenderer(new MultiColorRenderer());
		table.getColumnModel().getColumn(3)
		        .setCellRenderer(new MultiColorRenderer());


	table.getColumnModel().getColumn(0).setMaxWidth(60);
	
		dtm.fireTableDataChanged();
		
		JScrollPane scroll = new JScrollPane(table);
		
		
		
		tabPane.addTab("<html><body><h4>Reserved Items ( " + content.length + " )</h4></body></html>", scroll);
	}

	// RETURN
	public void createReturnPanel(JPanel panel)
	{
		panel.setLayout(new BorderLayout());
		
		JPanel wrapperPanel = new JPanel(new BorderLayout());
		
		JPanel returnPanel = new JPanel(new GridBagLayout());
		
		
	     
	    GridBagConstraints c = new GridBagConstraints();
	     
		
		c.anchor = GridBagConstraints.WEST;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(10, 10, 0, 0);
		returnPanel.add(new JLabel("Item title:"), c);
		
		
		c.anchor = GridBagConstraints.WEST;
		c.gridx = 1;
		c.gridy = 0;
		c.insets = new Insets(10, 10, 0 ,0);
		
		itemTitle.setMargin(new Insets(2, 2, 2, 2));
		returnPanel.add(ritemTitle, c);
		
		c.anchor = GridBagConstraints.WEST;
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(10, 10, 0, 0);
		returnPanel.add(new JLabel("Item type"), c);
		
		c.anchor = GridBagConstraints.WEST;
		c.gridx = 1;
		c.gridy = 1;
		c.weighty = 0.1;
		c.insets = new Insets(10, 10, 0 ,0);

		returnPanel.add(itemType, c);	
		
		c.anchor = GridBagConstraints.WEST;
		c.gridx = 0;
		c.gridy = 2;
		c.insets = new Insets(10, 10, 0, 0);
		returnPanel.add(new JLabel("Unique ID:"), c);
		
		c.anchor = GridBagConstraints.WEST;
		c.gridx = 1;
		c.gridy = 2;
		c.insets = new Insets(10, 10, 0 ,0);
		
		itemUniqueID.setMargin(new Insets(2, 2, 2, 2));
		returnPanel.add(ritemUniqueID, c);		
		

		
		c.anchor = GridBagConstraints.WEST;
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 2;
		c.insets = new Insets(10, 10, 0, 0);
		JButton borrowItem = new JButton("Return Item");
		borrowItem.setMargin(new Insets(5, 5, 5, 5));
		returnPanel.add(borrowItem, c);
		
		
	    ActionListener returnItemListener = new ActionListener()
	    {
	        public void actionPerformed(ActionEvent e)
	        {    
	        	if(!(ritemTitle.getText().isEmpty() && itemType.getSelectedIndex() == 0 && ritemUniqueID.getText().isEmpty()))
	        	{
	        		library.returnItem(ritemTitle.getText() + "-" + (String)itemType.getSelectedItem() + "-" + ritemUniqueID.getText());
	        		
	        		JOptionPane.showMessageDialog(null, "The item has been returned successfully", "Return successful", JOptionPane.INFORMATION_MESSAGE);
	        	}
	        	else
	        	{
	        		JOptionPane.showMessageDialog(null, "Please fill out all fields", "Error", JOptionPane.ERROR_MESSAGE);
	        	}
	        }
	    };
	     
	    borrowItem.addActionListener(returnItemListener);
	    wrapperPanel.add(returnPanel, BorderLayout.NORTH);
	    panel.add(wrapperPanel, BorderLayout.WEST);
	}

	
	
	// CLEAR PANEL METHODS
	
	public void clearBorrow()
	{
		borrowIDfield.setText("");
		
		addrLabel.setText("---");
		emailLabel.setText("---");
		nameLabel.setText("---");
		statusField.setText("---");
		
		itemUniqueID.setText("");
		itemTitle.setText("");
		
		itemTypeBorrow.setSelectedIndex(0);
		
		borrowedReservedList.removeAll();
		
		selectedPerson = null;
		
		createBlankBorrow(borrowedReservedList);
		createBlankReserve(borrowedReservedList);
	}
	
	public void clearReturn()
	{
		ritemTitle.setText("");
		itemType.setSelectedIndex(0);
		ritemUniqueID.setText("");
	}
	
	public void clearSearch()
	{
		
		searchField.setText("");
		Object[][] content = new Object[0][0];
	    DefaultTableModel model = new MyModel(content, columns);
	
	    table.setModel(model);
	     
	    table.getColumnModel().getColumn(0).setMaxWidth(100);
	    table.getColumnModel().getColumn(1).setMaxWidth(60);
	
	    table.getColumnModel().getColumn(0)
	            .setCellRenderer(new MultiColorRenderer(true));
	    table.getColumnModel().getColumn(1)
	            .setCellRenderer(new MultiColorRenderer(true));
	    table.getColumnModel().getColumn(2)
	            .setCellRenderer(new MultiColorRenderer());
	    table.getColumnModel().getColumn(3)
	            .setCellRenderer(new MultiColorRenderer());
	    table.getColumnModel().getColumn(4)
	    .setCellRenderer(new MultiColorRenderer());
	
	    model.fireTableDataChanged();
	}

	public void clearAll()
	{
		clearBorrow();
		clearReturn();
		clearSearch();
	}
	
	
	// SEARCH
    private String[] columns = {"<html><body><strong>Available</strong></body></html>",
            "<html><body><strong>Type</strong></body></html>",
            "<html><body><strong>Title</strong></body></html>",
            "<html><body><strong>Author</strong></body></html>",
            "<html><body><strong>ISBN</strong></body></html>" };
    
    private JTable table;
    
	public void createSearchPanel(JPanel panel, JTabbedPane tabbedPane) 
	{
	
	    JPanel wrapper = new JPanel(new BorderLayout());
	    JPanel leftMaster = new JPanel();
	    leftMaster.setLayout(new GridBagLayout());
	
	    GridBagConstraints gbc = new GridBagConstraints();
	
	    /* Left Search Start */
	
	    Object[][] content = new Object[0][0];	
	
	    table = new JTable(content, columns);
	
	    table.setFillsViewportHeight(true);
	    table.setFocusable(false);
	    table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    table.setRowHeight(35);
	
	    table.getColumnModel().getColumn(0)
	            .setCellRenderer(new MultiColorRenderer(true));
	    table.getColumnModel().getColumn(1)
	            .setCellRenderer(new MultiColorRenderer());
	    table.getColumnModel().getColumn(2)
	            .setCellRenderer(new MultiColorRenderer());
	    table.getColumnModel().getColumn(3)
	            .setCellRenderer(new MultiColorRenderer());
	     
	    table.getColumnModel().getColumn(0).setMaxWidth(150);
	     
	    table.getColumnModel().getColumn(1).setMaxWidth(60);
	
	    JScrollPane scroll = new JScrollPane(table);
	
	    table.addMouseListener(new MouseAdapter() 
	    {
	        public void mousePressed(MouseEvent me) 
	        {
	            JTable table = (JTable) me.getSource();
	            Point p = me.getPoint();
	            int row = table.rowAtPoint(p);
	            if (me.isPopupTrigger()) 
	            {
	            	if(row==-1)
	            		return;
	                 
	            	if(table.getValueAt(row, 0) == "No")
	            	{
	            		doPop(me, (String) table.getValueAt(row, 2));
	            	}
	            	
	            }
	        }
	        
	        public void mouseReleased(MouseEvent me) 
	        {
	            JTable table = (JTable) me.getSource();
	            Point p = me.getPoint();
	            int row = table.rowAtPoint(p);
	            if (me.isPopupTrigger()) 
	            {
	            	if(row==-1)
	            		return;
	                 
	            	if(table.getValueAt(row, 0) == "No")
	            	{
	            		doPop(me, (String) table.getValueAt(row, 2));
	            	}
	            	
	            }
	        }

	        
	        public void doPop(MouseEvent e, String title)
	        {
	        	SearchPanelContext menu = new SearchPanelContext(title, library);
	        	menu.show(e.getComponent(), e.getX(), e.getY());
	        }
	    });
	
	    KeyListener searchFieldEnterListener = new KeyListener() {
	
	        @Override
	        public void keyPressed(KeyEvent e) {
	            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
	                if (searchField.getText().equals(""))
	                    return;
	
	                if (searchField.getText().equals(null))
	                    return;
	
	                Item[] getSearchItems = library.getAdapter().getItems(
	                        searchField.getText());
	
	                if (getSearchItems.length == 0) {
	                    JOptionPane
	                            .showMessageDialog(null,
	                                    "No results was found. Please specify your search.");
	                    clearSearch();
	                    
	                    
	                    return;
	                }
	
	                Object[][] content = new Object[getSearchItems.length][5];
	
	                for (int i = 0; i < getSearchItems.length; i++) 
	                {
    	                Reserved[] getItemReserves = library.getItemReserverList(getSearchItems[i].getTitle(), getSearchItems[i].getType());
    	                
    	                String available = "N/A";
    	                
    	                if(getItemReserves != null)
    	                {
	    	                if((getSearchItems[i].getUniqueIDs().length - getItemReserves.length) < 1)
	    	                {
	    	                	available = "No";
	    	                }
	    	                else
	    	                {
	    	                	available = "Yes";
	    	                }
    	                }
    	                else
    	                {
    	                	if(getSearchItems[i].getUniqueIDs().length > 0)
    	                	{
    	                		available = "Yes";
    	                	}
    	                	else
    	                	{
    	                		available = "No";
    	                	}
    	                }
    	                
	    	            if (getSearchItems[i] instanceof Book) 
	    	            {
	    	                Book other = (Book) getSearchItems[i];

	    	                
	    	                content[i][0] = available;
	    	                content[i][1] = BOOK;
	    	                content[i][2] = other.getTitle();
	    	                content[i][3] = other.getAuthor();
	    	                content[i][4] = other.getISBN();
	    	            } 
	    	            else if (getSearchItems[i] instanceof Article) 
	    	            {
	    	                Article other = (Article) getSearchItems[i];
	    	                 
	    	                content[i][0] = available;
	    	                content[i][1] = ARTICLE;
	    	                content[i][2] = other.getTitle();
	    	                content[i][3] = other.getAuthor();
	    	                content[i][4] = "---";
	    	            } 
	    	            else if (getSearchItems[i] instanceof CD) 
	    	            {
	    	                CD other = (CD) getSearchItems[i];
	    	                 
	    	                content[i][0] = available;
	    	                content[i][1] = CD;
	    	                content[i][2] = other.getTitle();
	    	                content[i][3] = other.getArtist();
	    	                content[i][4] = "---";
	    	            } 
	    	            else if (getSearchItems[i] instanceof DVD) 
	    	            {
	    	                DVD other = (DVD) getSearchItems[i];
	    	                 
	    	                content[i][0] = available;
	    	                content[i][1] = DVD;
	    	                content[i][2] = other.getTitle();
	    	                content[i][3] = other.getDirector();
	    	                content[i][4] = "---";
	    	            } 
	    	            else
	    	            {
	    	                content[i][0] = "N/A";
	    	                content[i][1] = "N/A";
	    	                content[i][2] = "Unknown";
	    	                content[i][3] = "Unknown";
	    	                content[i][4] = "Unknown";
	    	            }
	                }
	
	                DefaultTableModel model = new MyModel(content, columns);
	
	                table.setModel(model);
	                 
	                table.getColumnModel().getColumn(0).setMaxWidth(150);
	                table.getColumnModel().getColumn(1).setMaxWidth(60);
	
	                table.getColumnModel().getColumn(0)
	                        .setCellRenderer(new MultiColorRenderer(true));
	                table.getColumnModel().getColumn(1)
	                        .setCellRenderer(new MultiColorRenderer(true));
	                table.getColumnModel().getColumn(2)
	                        .setCellRenderer(new MultiColorRenderer());
	                table.getColumnModel().getColumn(3)
	                        .setCellRenderer(new MultiColorRenderer());
	                table.getColumnModel().getColumn(4)
	                .setCellRenderer(new MultiColorRenderer());
	
	                model.fireTableDataChanged();
	
	            }
	
	        }

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
	    };
	
	    searchField.addKeyListener(searchFieldEnterListener);
	
	    gbc.anchor = GridBagConstraints.WEST;
	    gbc.gridx = 0;
	    gbc.insets = new Insets(5, 5, 5, 5);
	    gbc.gridy = 0;
	    leftMaster.add(new JLabel("Search for an item"), gbc);
	
	    searchField.setMargin(new Insets(2, 2, 2, 2));
	
	    gbc.anchor = GridBagConstraints.WEST;
	    gbc.gridx = 0;
	    gbc.gridy = 1;
	    gbc.insets = new Insets(5, 5, 5, 5);
	    leftMaster.add(searchField, gbc);
	
	    /* Left Search End */
	
	    JPanel infoPane = new JPanel(new GridBagLayout());
	    infoPane.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
	    
	    wrapper.add(leftMaster, BorderLayout.NORTH);
	    panel.add(wrapper, BorderLayout.WEST);
	    panel.add(scroll, BorderLayout.SOUTH);
	    
	}
}
	
class MyModel extends DefaultTableModel 
{
public MyModel(Object[][] data, Object[] columns) 
{
    super(data, columns);
}

public MyModel()
{
	super();
}

@Override
public boolean isCellEditable(int row, int col) 
{
    return false;
}
}

class MultiColorRenderer extends DefaultTableCellRenderer 
{
private boolean centerText = false;

public MultiColorRenderer() 
{
    super();
}

public MultiColorRenderer(boolean centerText) 
{
    this.centerText = centerText;
}

public Component getTableCellRendererComponent(JTable table, Object value,
        boolean isSelected, boolean hasFocus, int row, int column) 
{
    Component comp = super.getTableCellRendererComponent(table, value,
            isSelected, hasFocus, row, column);

    if ((row % 2) == 0 && !isSelected) 
    {
        comp.setBackground(new Color(0, 0, 0, 20));
    } 
    else if ((row % 2) != 0 && !isSelected) 
    {
        comp.setBackground(Color.white);
    } 
    else if (isSelected) 
    {
        comp.setBackground(new Color(0, 0, 255, 70));
    }

    if (centerText) 
    {
        setHorizontalAlignment(JLabel.CENTER);
    }

    return comp;
}
}

class SearchPanelContext extends JPopupMenu
{
	JMenuItem reserve;
	
	public SearchPanelContext(String title, Library l)
	{
		super();
		reserve = new JMenuItem("Reserve Item");
		
		reserve.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				String input = JOptionPane.showInputDialog(null, "Enter a Borrower ID", "Reserve an Item", JOptionPane.DEFAULT_OPTION);
				
				if(!input.isEmpty() && input != null)
				{
					Item getItem = l.getAdapter().getItem(title);
					
					Person person = l.getPerson(input);
					
					if(getItem != null && person != null)
					{
						l.reserveItem(new Reserved(person, getItem));
						JOptionPane.showMessageDialog(null, getItem.getTitle() + " has been reserved to " + person.getName(), "Reservation Success", JOptionPane.INFORMATION_MESSAGE);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "An error occured on reserving the item. Please check Borrower ID", "Check Information", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
			
		});
		add(reserve);
	}
}