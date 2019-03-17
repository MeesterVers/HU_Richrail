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

	private JButton btnDeleteWagon;

	private JButton btnAddWagon;

	private JButton btnDeleteTrain;
	private JButton btnChooseTrain;
	public JButton btnNewTrain;

	public CommandController cmdController;

	private JComboBox<String> trainDropDown;

	static JTextField trainNameTextField;
	private HashMap<Comparable, Integer> numberOfWagons;

	private int currentNumberOfWagons = 210;
	private int currentTrain = -1;
	private int OFFSET = 100;
	private int wagonlocation = 205;
	private JLabel Wagonname;
	private JLabel Wagonseats;
	static JTextField WagonnameTextfield1;
	static JTextField SeatsTextfield1;

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
				getContentPane().add(rightPanel, new GridBagConstraints(3, 2, 2, 3, 0.0, 0.0, GridBagConstraints.CENTER,
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
					btnAddWagon = new JButton();
					rightPanel.add(btnAddWagon, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
							GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					btnAddWagon.setText("add wagon");
					btnAddWagon.addActionListener(this);
				}
				{
					btnDeleteWagon = new JButton();
					rightPanel.add(btnDeleteWagon, new GridBagConstraints(4, 0, 1, 1, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					btnDeleteWagon.setText("delete wagon");
					btnDeleteWagon.addActionListener(this);
				}
				{
					Wagonseats = new JLabel();

					rightPanel.add(Wagonseats, new GridBagConstraints(0, 1, 2, 1, 0.0, 0.0, GridBagConstraints.WEST,
							GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					Wagonseats.setText("seats: ");
				}
				{
					SeatsTextfield1 = new JTextField(10);
					rightPanel.add(SeatsTextfield1, new GridBagConstraints(1, 1, 2, 1, 0.0, 0.0,
							GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				}

			}
			pack();
			setSize(1000, 600);
			numberOfWagons = new HashMap<Comparable, Integer>();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String Gettrains(String trains) {
		String command = "get all trains";
		try {
			trains = cmdController.executeCommand(command);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		trainDropDown.addItem(trains);
		System.out.println(trains);
		return trains;
	}

	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == btnNewTrain) {
			AddTrain();
		} else if (event.getSource() == btnChooseTrain) {
			btnChooseTrain();
		} else if (event.getSource() == btnDeleteTrain) {
			DeleteTrain();
		} else if (event.getSource() == btnAddWagon) {
			try {
				AddWagon();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (event.getSource() == btnDeleteWagon) {
			DeleteWagon();
		}
	}

	public void AddTrain() {
		String train = trainNameTextField.getText();
		try {
			String response = cmdController.executeCommand("new train " + train);
			String check = response.substring(response.indexOf(" ") + 11);
			if (check.equals("exists")) {
				trainNameTextField.setText("Train already exist");
			} else {
				train = addTrain(train);
				currentTrain = trainDropDown.getSelectedIndex();

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
	}

	public void DeleteTrain() {
		String train = trainNameTextField.getText();
		try {
			String t = (String) trainDropDown.getSelectedItem();
			trainDropDown.removeItemAt(trainDropDown.getSelectedIndex());
			numberOfWagons.remove(t);
			cmdController.executeCommand("delete train " + train);
			trainNameTextField.setText("");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		repaint();

	}

	public void AddWagon() throws SQLException, IOException {
		String wagonname = WagonnameTextfield1.getText();
		String trainname = trainNameTextField.getText();
		String seats = null;
		String seatscustom = SeatsTextfield1.getText();
		try {

			cmdController.executeCommand("new wagon " + wagonname);
			cmdController.executeCommand("add " + wagonname + " to " + trainname);
			if (SeatsTextfield1.getText().equals("")) {
				seats = cmdController.executeCommand("getnumseats wagon " + wagonname);
				drawWagon(wagonlocation, 0, wagonname, seats);
			}
			else {
				drawWagon(wagonlocation, 0, wagonname, seatscustom);
			}
			wagonlocation = wagonlocation + 210;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void btnChooseTrain() {
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
	}

	public void DeleteWagon() {
		String wagonname = WagonnameTextfield1.getText();
		wagonlocation = wagonlocation - 210;
		mainScreen.repaint(wagonlocation, 0, 800, 500);
		try {
			cmdController.executeCommand("delete wagon " + wagonname);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		trainNameTextField.setText("");
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

	public void drawTrain(String train) throws IOException, SQLException {
		Graphics g = innerMainScreen.getGraphics();
		if (train != "") {
			image = ImageIO.read(new File("src/train.jpg"));
			g.drawImage(image, 0, 0, null);
			g.drawString(trainNameTextField.getText(), wagonlocation - 100, 200);

		}

	}

	public void drawWagon(int width, int hight, String wagon, String seats) throws IOException, SQLException {
		Graphics g = innerMainScreen.getGraphics();
		image = ImageIO.read(new File("src/Wagon.png"));
		g.drawImage(image, width, hight, null);
		g.drawString(wagon, wagonlocation + 85, 200);
		g.drawString(seats + "seats", wagonlocation + 115, 200);
	}
}