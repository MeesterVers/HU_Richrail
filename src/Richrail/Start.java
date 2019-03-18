package Richrail;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;

import Contollers.CommandController;

@SuppressWarnings("serial")
public class Start extends javax.swing.JFrame implements ActionListener {

	private JButton ExecuteButton;
	private JPanel RightPanel;
	private JPanel LeftPanel;
	private JPanel drawPanel;
	private JLabel CommandLabel;
	private JTextField CommandField;
	static JTextArea rightOutput;
	static JTextArea leftOutput;

	public CommandController cmdController;

	public Gui tGui;

	private double[] weights = new double[] { 0.1, 0.1, 0.1, 0.1 };
	private int[] heights = new int[] { 7, 7, 7, 7 };

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Start richrail = new Start();
				richrail.setLocationRelativeTo(null);
				richrail.setVisible(true);
				Gui.main(null);
			}
		});
	}

	public Start() {
		super();
		this.cmdController = new CommandController();
		this.tGui = new Gui();
		initCLIGUI();
	}

	// Create the GUI
	private void initCLIGUI() {
		try {
			this.setTitle("RichRail");
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

			GridBagLayout gridBagLayout = new GridBagLayout();
			gridBagLayout.rowWeights = weights;
			gridBagLayout.rowHeights = heights;
			gridBagLayout.columnWeights = weights;
			gridBagLayout.columnWidths = heights;
			getContentPane().setLayout(gridBagLayout);

			// Upper Draw Panel
			drawPanel = new JPanel();
			drawPanel.setLayout(new BorderLayout());
			drawPanel.setBackground(Color.WHITE);
			getContentPane().add(drawPanel, new GridBagConstraints(0, 0, 4, 2, 0.0, 0.0, GridBagConstraints.EAST,
					GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
			drawPanel.setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));

			GridBagLayout gridBagLayout1 = new GridBagLayout();
			gridBagLayout1.rowWeights = weights;
			gridBagLayout1.rowHeights = heights;
			gridBagLayout1.columnWeights = weights;
			gridBagLayout1.columnWidths = heights;

			// Left Panel
			LeftPanel = new JPanel();
			LeftPanel.setBounds(100, 10, 100, 15);
			LeftPanel.setLayout(gridBagLayout);
			getContentPane().add(LeftPanel, new GridBagConstraints(0, 2, 1, 2, 0.0, 0.0, GridBagConstraints.CENTER,
					GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
			LeftPanel.setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));

			// Left Output
			leftOutput = new JTextArea();
			leftOutput.setLineWrap(true);
			leftOutput.setWrapStyleWord(true);
			leftOutput.setEditable(false);
			LeftPanel.add(leftOutput, new GridBagConstraints(0, 0, 4, 3, 0.0, 0.0, GridBagConstraints.EAST,
					GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

			// Label Before Text Field For Commands
			CommandLabel = new JLabel();
			CommandLabel.setText("Command:");
			LeftPanel.add(CommandLabel, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
					GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

			// Text Field For Commands
			CommandField = new JTextField(20);
			LeftPanel.add(CommandField, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
					GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

			// Execute Button
			ExecuteButton = new JButton();
			LeftPanel.add(ExecuteButton, new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
					GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
			ExecuteButton.setText("Execute");
			ExecuteButton.addActionListener(this);

			// Grid Layout
			GridBagLayout gridBagLayout2 = new GridBagLayout();
			gridBagLayout2.rowWeights = weights;
			gridBagLayout2.rowHeights = heights;
			gridBagLayout2.columnWeights = weights;
			gridBagLayout2.columnWidths = heights;

			// Right Panel
			RightPanel = new JPanel();
			getContentPane().add(RightPanel, new GridBagConstraints(1, 2, 2, 2, 0.0, 0.0, GridBagConstraints.CENTER,
					GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
			RightPanel.setLayout(gridBagLayout2);
			RightPanel.setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));

			// Right Output
			rightOutput = new JTextArea();
			rightOutput.setLineWrap(true);
			rightOutput.setBackground(Color.black);
			rightOutput.setForeground(Color.white);
			rightOutput.setWrapStyleWord(true);
			rightOutput.setEditable(false);
			RightPanel.add(rightOutput, new GridBagConstraints(0, 0, 4, 3, 0.0, 0.0, GridBagConstraints.EAST,
					GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

			pack();
			setSize(1000, 800);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Button click actions
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == ExecuteButton) {
			// Get command from command inputfield
			String command = CommandField.getText();
			// Place command on leftOutput
			leftOutput.append("<< " + command + "\n");

			String response = null;
			try {
				// execute command
				response = cmdController.executeCommand(command);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// Set response on screen
			responseOutput(response);

			// Get trainname from command
			String trainname = command.substring(command.indexOf(" ") + 7);
			String test = command.substring(0, 5);
			String testdelete = command.substring(7, 8);

			// Place Train on Gui
			if (test.equals("new t") && !(response.equals("Train " + trainname + " already exists"))) {
				// Set trainname inputfield from Gui.java
				Gui.trainNameTextField.setText(trainname);
				tGui.wagonlocation = 205;
				try {
					tGui.drawTrain(trainname);
				} catch (IOException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			// Get wagonname from command
			String wagonname = command.substring(command.indexOf(" ") + 7);

			// Split command
			int length = command.length();
			String arr[] = command.split(" ");

			// Place wagon on Gui
			if (test.equals("new w") && !response.equals("Wagon " + wagonname + " already exists")) {
				if (!(length >= 20)) {
					// Get wagonname from command
					wagonname = command.substring(command.indexOf(" ") + 7);
					// Set wagonname inputfield from Gui.java
					Gui.WagonnameTextfield1.setText(wagonname);
					try {
						// Get numseats
						String seats = cmdController.executeCommand("getnumseats wagon " + wagonname);
						// Place numseats in seats input field from Gui.java
						Gui.SeatsTextfield1.setText(seats);
						tGui.drawWagon(tGui.wagonlocation, 0, wagonname, seats);
						tGui.wagonlocation = tGui.wagonlocation + 210;
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					// if command is like new wagon "wagonname" numseats "number"
					// Gets wagonname from command
					wagonname = arr[2];
					// Place wagonname on wagonname input field from Gui.java
					Gui.WagonnameTextfield1.setText(wagonname);
					try {
						// Gets seatsnumber from command
						String seats = arr[4];
						// Place seatsnumber on numseats input field from Gui.java
						Gui.SeatsTextfield1.setText(seats);
						tGui.wagonlocation = tGui.wagonlocation + 210;
						tGui.drawWagon(tGui.wagonlocation, 0, wagonname, seats);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

			// Delete Wagon from Gui
			// Checks if command is like delete wagon "wagonname"
			if (testdelete.equals("w") && tGui.WagonnameTextfield1.getText().equals(wagonname)
					&& !response.equals("Wagon " + wagonname + " does not exists")) {
				if (tGui.wagonlocation < 210) {
					tGui.wagonlocation = 205;
					// deletes wagon from outputscreen Gui.java
					tGui.mainScreen.repaint(tGui.wagonlocation, 0, 800, 500);
				} else {
					// deletes wagon from outputscreen Gui.java
					tGui.wagonlocation = tGui.wagonlocation - 210;
					tGui.mainScreen.repaint(tGui.wagonlocation, 0, 800, 500);
				}
				tGui.WagonnameTextfield1.setText("");
				Gui.SeatsTextfield1.setText("");
			}

			// Delete train from Gui
			if (testdelete.equals("t") && tGui.trainNameTextField.getText().equals(trainname)
					&& !response.equals("Train " + trainname + " does not exists")) {
				tGui.wagonlocation = 205;
				// deletes train from outputscreen Gui.java
				Gui.mainScreen.repaint(0, 0, 800, 500);
				// Clear all inputfields from Gui.java
				tGui.WagonnameTextfield1.setText("");
				tGui.trainNameTextField.setText("");
				tGui.SeatsTextfield1.setText("");
			}
		}
	}

	// place response on richtOutput screen
	public void responseOutput(String response) {
		rightOutput.append(">> " + response + "\n");
	}

	/*
	 * 0 new train tr1; // response is “train tr1 created” 1 new wagon wg1; //
	 * response is “wagon wg1 created with 20 seats” 2 new wagon wg2 numseats 15; //
	 * response is “wagon wg2 created with 15 seats” 3 add wg1 to tr1; // response:
	 * “wagon wg1 added to train tr1” 4 getnumseats train tr1; // response: “number
	 * of seats in train tr1: 20” 5 getnumseats wagon wg2; // response: “number of
	 * seats in wagon wg2: 15” 6 delete train tr1; // response: “train tr1 deleted”
	 * delete train tr2; // response: “train tr2 does not exist” 7 delete wagon wg1;
	 * // response: “wagon wg1 deleted” 8 remove wg1 from tr1; // response: “wagon
	 * wg1 removed from train tr1”
	 */

}
