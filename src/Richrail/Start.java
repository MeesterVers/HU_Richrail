package Richrail;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;



import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;

import Contollers.CommandController;
import Model.Railroad;
import Model.Train;

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

	private JButton openGui;

	static CommandController cmdController;
	private Railroad railroad;

	private double[] weights = new double[] { 0.1, 0.1, 0.1, 0.1 };
	private int[] heights = new int[] { 7, 7, 7, 7 };

	private JPanel mainScreen;
	private JPanel innerMainScreen;
	private JPanel rightPanel;
	private JPanel leftPanel;

	private JLabel trainNameLabel;
	private JLabel selectedTrain;

	private JButton btnDeleteWagon1;
	private JButton btnDeleteWagon2;
	private JButton btnDeleteWagon3;

	private JButton btnAddWagon1;
	private JButton btnAddWagon2;
	private JButton btnAddWagon3;

	private JButton btnDeleteTrain;
	private JButton btnChooseTrain;
	private JButton btnNewTrain;

	private JComboBox trainDropDown;

	private JTextField trainNameTextField;
	private HashMap numberOfWagons;

	private int currentNumberOfWagons;
	private int currentTrain = -1;
	private int OFFSET = 100;
	private int TRAINLENGTH = 100;
	private int wagonType;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Start richrail = new Start();
				richrail.setLocationRelativeTo(null);
				richrail.setVisible(true);
			}
		});
	}

	public Start() {
		super();
		this.railroad = new Railroad();
		this.cmdController = new CommandController(railroad);
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
			LeftPanel.setBounds(10, 10, 100, 15);
			LeftPanel.setLayout(gridBagLayout1);
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

			// openGui Button
			openGui = new JButton();
			RightPanel.add(openGui, new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
					GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
			openGui.setText("openGui");
			openGui.addActionListener(this);

			pack();
			setSize(1000, 800);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == ExecuteButton) {
			String command = CommandField.getText();
			leftOutput.append("<< " + command + "\n");
			String response = null;
			try {
				response = cmdController.executeCommand(command);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			responseOutput(response);
		
			Gui.trainNameTextField.setText(response);
			try {
				Gui.drawTrain(response);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		Gui.main(null);
	}

	

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
