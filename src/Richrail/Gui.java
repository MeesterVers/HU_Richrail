package Richrail;

import java.awt.BorderLayout;
import java.util.HashMap;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.*;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;

import Contollers.CommandController;

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class Gui extends javax.swing.JFrame implements ActionListener {
	private JPanel mainScreen;
	private static JPanel innerMainScreen;
	private JPanel rightPanel;
	private JPanel leftPanel;

	private JLabel trainNameLabel;
	private JLabel selectedTrain;

	public static BufferedImage image;

	private JButton btnDeleteWagon1;
	private JButton btnDeleteWagon2;
	private JButton btnDeleteWagon3;

	private JButton btnAddWagon1;
	private JButton btnAddWagon2;
	private JButton btnAddWagon3;

	private JButton btnDeleteTrain;
	private JButton btnChooseTrain;
	private JButton btnNewTrain;
	
	public CommandController cmdController;

	private JComboBox<String> trainDropDown;

	static JTextField trainNameTextField;
	private HashMap<Comparable, Integer> numberOfWagons;

	private int currentNumberOfWagons = 205;
	private int currentTrain = -1;
	private int OFFSET = 100;
	private int TRAINLENGTH = 100;
	private int wagonType;
	private JTextField WagonnameTextfield;
	private JLabel Wagonname;
	static JTextField WagonnameTextfield1;
	private JTextField WagonnameTextfield2;
	private JTextField WagonnameTextfield3;
	
	/**
	 * Auto-generated main method to display this JFrame
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Gui inst = new Gui();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
				
			}
		});
	}

	public Gui() {
		super();
		initGUI();
		this.cmdController = new CommandController();
	}

	private void initGUI() {
		try {
			this.setTitle("Richrail");
			GridBagLayout thisLayout = new GridBagLayout();
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			thisLayout.rowWeights = new double[] { 0.1, 0.1, 0.1, 0.1 };
			thisLayout.rowHeights = new int[] { 7, 7, 7, 7 };
			thisLayout.columnWeights = new double[] { 0.1, 0.1, 0.1, 0.1 };
			thisLayout.columnWidths = new int[] { 7, 7, 7, 7 };
			getContentPane().setLayout(thisLayout);
			{
				mainScreen = new JPanel();
				mainScreen.setLayout(new BorderLayout());
				getContentPane().add(mainScreen, new GridBagConstraints(0, 0, 4, 2, 0.0, 0.0, GridBagConstraints.EAST,
						GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				{
					innerMainScreen = new JPanel();
					innerMainScreen.setBackground(Color.WHITE);
					mainScreen.add(innerMainScreen, BorderLayout.CENTER);
				}
			}
			{
				leftPanel = new JPanel();
				GridBagLayout leftPanelLayout = new GridBagLayout();
				// jGui.setLayout(null);
				leftPanel.setLayout(leftPanelLayout);
				getContentPane().add(leftPanel, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				{
					trainNameLabel = new JLabel();
					leftPanel.add(trainNameLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					leftPanel.setBounds(10, 10, 100, 15);
					leftPanelLayout.rowWeights = new double[] { 0.1, 0.1, 0.1, 0.1 };
					leftPanelLayout.rowHeights = new int[] { 7, 7, 7, 7 };
					leftPanelLayout.columnWeights = new double[] { 0.1, 0.1, 0.1, 0.1 };
					leftPanelLayout.columnWidths = new int[] { 7, 7, 7, 7 };
					trainNameLabel.setText("train name:");
				}
				{
					trainNameTextField = new JTextField(20);
					leftPanel.add(trainNameTextField, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				}
				{
					btnNewTrain = new JButton();
					leftPanel.add(btnNewTrain, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
							GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					btnNewTrain.setText("make new train");
					btnNewTrain.addActionListener(this);
				}
				{
					ComboBoxModel<String> cbAllTrainsModel = new DefaultComboBoxModel<String>(new String[] {});
					trainDropDown = new JComboBox<String>();

					leftPanel.add(trainDropDown, new GridBagConstraints(1, 1, 1, 2, 0.0, 0.0, GridBagConstraints.CENTER,
							GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
					trainDropDown.setModel(cbAllTrainsModel);
				}
				{
					btnChooseTrain = new JButton();
					leftPanel.add(btnChooseTrain, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					btnChooseTrain.setText("select train");
					btnChooseTrain.addActionListener(this);
				}
				{
					btnDeleteTrain = new JButton();
					leftPanel.add(btnDeleteTrain, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					btnDeleteTrain.setText("delete train");
					btnDeleteTrain.addActionListener(this);
				}
			}
			{
				rightPanel = new JPanel();
				GridBagLayout rightPanelLayout = new GridBagLayout();
				getContentPane().add(rightPanel, new GridBagConstraints(1, 2, 2, 3, 0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				rightPanelLayout.rowWeights = new double[] { 0.1, 0.1, 0.1, 0.1 };
				rightPanelLayout.rowHeights = new int[] { 7, 7, 7, 7 };
				rightPanelLayout.columnWeights = new double[] { 0.1, 0.1, 0.1, 0.1 };
				rightPanelLayout.columnWidths = new int[] { 7, 7, 7, 7 };
				rightPanel.setLayout(rightPanelLayout);
				rightPanel.setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));
				{
					selectedTrain = new JLabel();

					rightPanel.add(selectedTrain, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
					selectedTrain.setText("selected: ");
				}
				{
					Wagonname = new JLabel();

					rightPanel.add(Wagonname, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
							GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					Wagonname.setText("Wagon name: ");
				}
				{
					WagonnameTextfield1 = new JTextField(10);
					rightPanel.add(WagonnameTextfield1, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				}
				{
					btnAddWagon1 = new JButton();
					rightPanel.add(btnAddWagon1, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
							GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					btnAddWagon1.setText("add wagon 1");
					btnAddWagon1.addActionListener(this);
				}
				{
					Wagonname = new JLabel();

					rightPanel.add(Wagonname, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
							GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					Wagonname.setText("Wagon name: ");
				}
				{
					WagonnameTextfield2 = new JTextField(10);
					rightPanel.add(WagonnameTextfield2, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				}
				{
					btnAddWagon2 = new JButton();
					rightPanel.add(btnAddWagon2, new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
							GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					btnAddWagon2.setText("add wagon 2");
					btnAddWagon2.addActionListener(this);
				}
				{
					Wagonname = new JLabel();

					rightPanel.add(Wagonname, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
							GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					Wagonname.setText("Wagon name: ");
				}
				{
					WagonnameTextfield3 = new JTextField(10);
					rightPanel.add(WagonnameTextfield3, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				}
				{
					btnAddWagon3 = new JButton();
					rightPanel.add(btnAddWagon3, new GridBagConstraints(3, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
							GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					btnAddWagon3.setText("add wagon 3");
					btnAddWagon3.addActionListener(this);
				}
				{
					btnDeleteWagon1 = new JButton();
					rightPanel.add(btnDeleteWagon1, new GridBagConstraints(4, 0, 1, 1, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					btnDeleteWagon1.setText("delete wagon 1");
					btnDeleteWagon1.addActionListener(this);
				}
				{
					btnDeleteWagon2 = new JButton();
					rightPanel.add(btnDeleteWagon2, new GridBagConstraints(4, 1, 1, 1, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					btnDeleteWagon2.setText("delete wagon 2");
					btnDeleteWagon2.addActionListener(this);
				}
				{
					btnDeleteWagon3 = new JButton();
					rightPanel.add(btnDeleteWagon3, new GridBagConstraints(4, 2, 1, 1, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					btnDeleteWagon3.setText("delete wagon 3");
					btnDeleteWagon3.addActionListener(this);
				}
			}
			pack();
			setSize(1000, 600);
			numberOfWagons = new HashMap<Comparable, Integer>();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == btnNewTrain) {
			String train = trainNameTextField.getText();
			 try {
			 String response = cmdController.executeCommand("new train " + train);
			 String check = response.substring(response.indexOf(" ")+ 11);
			 if (check.equals("exists")) {
				 trainNameTextField.setText("Train already exist");
			 }
			 else {
				 try {
					drawTrain(train);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 }
			 System.out.println(response);
			 } catch (SQLException e) {
			 // TODO Auto-generated catch block
			 e.printStackTrace();
			 }
			 
//			if (train != null && train.trim().length() > 0) {
//
//				// save train
//				Railroad railroad = new Railroad();
//				Controller ControllerInst = new Controller(railroad);
//				TrainDaoImpl newtrain = new TrainDaoImpl();
//				try {
//					if (!train.equals("Train already exist")) {
//						if (ControllerInst.createTrain(train) != null) {
////							ControllerInst.createTrain(train);
//							train = addTrain(train);
//							currentTrain = trainDropDown.getSelectedIndex();
////							newtrain.save(train);
//							drawTrain(train);
//							System.out.println(train);	
//							Start.leftOutput.append("<< new train " + train + "\n");
//							Start.rightOutput.append(">> train " + train + " created" + "\n");
//						} else {
//							trainNameTextField.setText("Train already exist");
//						}
//					}
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
		} else if (event.getSource() == btnChooseTrain) {

			if (trainDropDown.getItemCount() > 0) {
				String selection = (String) trainDropDown.getSelectedItem();
				selectedTrain.setText("selected: " + selection);
				int ti = trainDropDown.getSelectedIndex();
				if (ti != currentTrain) {
					numberOfWagons.put(currentTrain, currentNumberOfWagons);
				}
				currentTrain = ti;
				try {
					currentNumberOfWagons = (Integer) numberOfWagons.get(currentTrain);
				} catch (Exception e) {
					currentNumberOfWagons = 0;
				}
			}
		} else if (event.getSource() == btnDeleteTrain) {
			if (trainDropDown.getItemCount() > 0) {
				String t = (String) trainDropDown.getSelectedItem();
				trainDropDown.removeItemAt(trainDropDown.getSelectedIndex());
				numberOfWagons.remove(t);
				repaint();
				if ((String) trainDropDown.getSelectedItem() != null) {
					currentTrain = trainDropDown.getSelectedIndex();
					selectedTrain.setText("selected: " + (String) trainDropDown.getSelectedItem());
				} else {
					currentTrain = 0;
					selectedTrain.setText("selected: ");
				}
			}
		} else if (event.getSource() == btnAddWagon1) {
			currentNumberOfWagons++;
			String wagon1 = WagonnameTextfield1.getText();
			try {
				drawWagon(205, 0);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			wagonType = 1;
			try {
				cmdController.executeCommand("new wagon " + wagon1);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (event.getSource() == btnAddWagon2) {
			currentNumberOfWagons++;
			String wagon2 = WagonnameTextfield2.getText();
			try {
				drawWagon(410, 0);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			wagonType = 2;
			try {
				cmdController.executeCommand("new wagon " + wagon2);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (event.getSource() == btnAddWagon3) {
			currentNumberOfWagons++;
			String wagon3 = WagonnameTextfield3.getText();
			try {
				drawWagon(615, 0);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			wagonType = 3;
			try {
				cmdController.executeCommand("new wagon " + wagon3);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (event.getSource() == btnDeleteWagon1) {
			if (wagonType == 1) {
				currentNumberOfWagons--;
				repaint(225, 0, OFFSET, OFFSET);
			}
		} else if (event.getSource() == btnDeleteWagon2) {
			if (wagonType == 2) {
				currentNumberOfWagons--;
				repaint(425, 0, OFFSET, OFFSET);
				wagonType = 1;
			}

		}

		else if (event.getSource() == btnDeleteWagon3) {
			if (wagonType == 3) {
				currentNumberOfWagons--;
				repaint(635, 0, OFFSET, OFFSET);
				wagonType = 2;
			}

		}
	}

	public String addTrain(String train) {
		String t = "";
		try {
			t = train.trim();
			for (int i = 0; i < trainDropDown.getItemCount(); i++) {
				String cbTrain = (String) trainDropDown.getItemAt(i);
				if (cbTrain.equalsIgnoreCase(t)) {
					t = "";
					break;
				}
			}
			if (t != "") {
				if (currentTrain >= 0) {
					numberOfWagons.put(currentTrain, currentNumberOfWagons);
				}
				trainDropDown.addItem(t);
				trainDropDown.setSelectedItem(t);
				numberOfWagons.put(t, 0);
			}
		} catch (Exception e) {
		}
		return t;

	}

	public static void drawTrain(String train) throws IOException, SQLException {

		Graphics g = innerMainScreen.getGraphics();
		if (train != "") {
			image = ImageIO.read(new File("src/train.jpg"));
			g.drawImage(image, 0, 0, null);
		
		}

	}

	public static void drawWagon(int width, int hight) throws IOException {
		Graphics g = innerMainScreen.getGraphics();
		image = ImageIO.read(new File("src/Wagon.png"));
		g.drawImage(image, width, hight, null);
	}
}