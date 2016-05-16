package version5;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ReserveNotification extends JPanel {
	private JTable list;
	private Library library;
	private MyButtonListener emailSentListener;
	private JCheckBox emailWasSentReally;
	private JTextArea autoTextmessage;
	private JTextField emailField;
	private JButton emailWasSent;
	private JLabel emailLabel;
	private JLabel autoTextLabel;

	public ReserveNotification(Library l) {
		super();
		setLayout(new BorderLayout());
		library = l;

		JPanel left = new JPanel();
		left.setLayout(new FlowLayout());

		emailSentListener = new MyButtonListener();

		Reserved[] fourDaysBefore = library.getAllAvailableReserved();

		DefaultTableModel tableModel = new MyModel();
		if (fourDaysBefore != null) {
			tableModel.setRowCount(fourDaysBefore.length);
		}
		tableModel.addColumn("Name");
		tableModel.addColumn("Email");
		tableModel.addColumn("Title");

		if (fourDaysBefore != null) {

			for (int i = 0; i < fourDaysBefore.length; i++) {
				if (fourDaysBefore[i] != null) {
					tableModel.setValueAt(fourDaysBefore[i].getReserver()
							.getName(), i, 0);
					tableModel.setValueAt(fourDaysBefore[i].getReserver()
							.getEmail(), i, 1);
					tableModel.setValueAt(fourDaysBefore[i].getItem()
							.getTitle(), i, 2);
				}

			}

		}

		list = new JTable(tableModel);
		list.setVisible(true);

		JScrollPane scrollPane = new JScrollPane(list);

		left.add(scrollPane);

		JPanel middle = new JPanel();
		middle.setLayout(new BoxLayout(middle, BoxLayout.PAGE_AXIS));

		JPanel emailFieldAndLabel = new JPanel();
		emailFieldAndLabel.setLayout(new BoxLayout(emailFieldAndLabel,
				BoxLayout.LINE_AXIS));

		emailLabel = new JLabel();
		emailLabel.setSize(200, 40);
		emailLabel.setText("Email:");

		emailField = new JTextField();
		emailField.setSize(400, 40);
		if (fourDaysBefore.length > 0) {
			emailField.setText(fourDaysBefore[0].getReserver().getEmail());
		}

		emailFieldAndLabel.add(emailLabel);
		emailFieldAndLabel.add(emailField);

		middle.add(emailFieldAndLabel);

		JPanel autoTextLabelPanel = new JPanel();
		autoTextLabelPanel.setLayout(new BoxLayout(autoTextLabelPanel,
				BoxLayout.LINE_AXIS));
		autoTextLabelPanel.setSize(200, 40);

		autoTextLabel = new JLabel();
		autoTextLabel.setSize(400, 40);
		autoTextLabel.setText("Auto text:");

		autoTextLabelPanel.add(autoTextLabel);
		autoTextLabelPanel.add(Box.createGlue());

		middle.add(autoTextLabelPanel);

		JPanel autoTextFieldPanel = new JPanel();
		autoTextFieldPanel.setLayout(new FlowLayout());

		autoTextmessage = new JTextArea(10, 30);
		if (fourDaysBefore.length > 0) {
			autoTextmessage.setText("Hello "
					+ fourDaysBefore[0].getReserver().getName()
					+ "\nA copy of " + fourDaysBefore[0].getItem().getTitle()
					+ " is available for you to pick up"
					+ "\nWith kind regards FairyTale Libary");
		}
		autoTextmessage.setSize(new Dimension(300, 300));

		JScrollPane autoTextScroll = new JScrollPane(autoTextmessage);
		
		autoTextFieldPanel.add(autoTextScroll);
		middle.add(autoTextFieldPanel);

		JPanel checkBoxPanel = new JPanel();
		checkBoxPanel.setLayout(new FlowLayout());

		JLabel checkBoxLabel = new JLabel("Email was sent");
		checkBoxPanel.add(checkBoxLabel);

		emailWasSentReally = new JCheckBox();
		emailWasSentReally.addActionListener(emailSentListener);

		checkBoxPanel.add(emailWasSentReally);

		middle.add(checkBoxPanel);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());

		emailWasSent = new JButton("Email was sent");
		emailWasSent.addActionListener(emailSentListener);

		buttonPanel.add(emailWasSent);

		middle.add(buttonPanel);

		JPanel temp = new JPanel();
		temp.setLayout(new FlowLayout());

		temp.add(left);
		temp.add(new Box.Filler(new Dimension(20, 10), new Dimension(20, 10),
				new Dimension(20, 10)));
		temp.add(middle);

		add(temp, BorderLayout.CENTER);
		add(new Box.Filler(new Dimension(100, 10), new Dimension(100, 100),
				new Dimension(100, 300)), BorderLayout.NORTH);

	}

	public class MyButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == emailWasSent) {
				if (emailWasSentReally.getSelectedObjects() != null) {
					Reserved[] temp = library.getAllAvailableReserved();
					if (temp != null) {

						String titleFirst = temp[0].getItem().getTitle();
						String typeFirst = temp[0].getItem().getType();
						String idFirst = temp[0].getReserver().getId();

						library.removeFromAvailableReserved(titleFirst,
								typeFirst, idFirst);

						Reserved[] fourDaysBefore = library
								.getAllAvailableReserved();

						DefaultTableModel model = (DefaultTableModel) list
								.getModel();
						model.setRowCount(fourDaysBefore.length);

						for (int i = 0; i < fourDaysBefore.length; i++) {
							list.getModel().setValueAt(
									fourDaysBefore[i].getReserver().getName(),
									i, 0);
							list.getModel().setValueAt(
									fourDaysBefore[i].getReserver().getEmail(),
									i, 1);
							list.getModel().setValueAt(
									fourDaysBefore[i].getItem().getTitle(), i,
									2);
						}

						if (fourDaysBefore[0] != null) {
							emailField.setText(fourDaysBefore[0].getReserver()
									.getEmail());
							autoTextmessage.setText("Hello "
									+ fourDaysBefore[0].getReserver().getName()
									+ "\nA copy of "
									+ fourDaysBefore[0].getItem().getTitle()
									+ " is available for you to pick up"
									+ "\nWith kind regards FairyTale Libary");
						} else {
							emailField.setText("");
							autoTextmessage.setText("");
						}

					}
					emailWasSentReally.setSelected(false);

				} else
					JOptionPane.showMessageDialog(null,
							"Confirm you have sent message", "Confirm!",
							JOptionPane.WARNING_MESSAGE);
			}

		}

	}

	public void updateTable() {
		Reserved[] fourDaysBefore = library
				.getAllAvailableReserved();

		DefaultTableModel model = (DefaultTableModel) list
				.getModel();
		model.setRowCount(fourDaysBefore.length);

		for (int i = 0; i < fourDaysBefore.length; i++) {
			list.getModel().setValueAt(
					fourDaysBefore[i].getReserver().getName(),
					i, 0);
			list.getModel().setValueAt(
					fourDaysBefore[i].getReserver().getEmail(),
					i, 1);
			list.getModel().setValueAt(
					fourDaysBefore[i].getItem().getTitle(), i,
					2);
		}

		if(fourDaysBefore != null)
		{
			if (fourDaysBefore.length > 0) {
				emailField.setText(fourDaysBefore[0].getReserver()
						.getEmail());
				autoTextmessage.setText("Hello "
						+ fourDaysBefore[0].getReserver().getName()
						+ "\nA copy of "
						+ fourDaysBefore[0].getItem().getTitle()
						+ " is available for you to pick up"
						+ "\nWith kind regards FairyTale Libary");
			} 
			else 
			{
				emailField.setText("");
				autoTextmessage.setText("");
			}
		}
		
		emailWasSentReally.setSelected(false);
	}
}
