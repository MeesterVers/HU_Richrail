package Richrail;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.HashMap;

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


@SuppressWarnings("serial")
public class Start extends javax.swing.JFrame implements ActionListener {
	
	private JButton ExecuteButton;	
	private JPanel RightPanel;
	private JPanel LeftPanel;
	private JPanel drawPanel;
	private JLabel CommandLabel;	
	private JTextField CommandField;
	private JTextArea rightOutput;
	private JTextArea leftOutput;
	
	private JButton openGui;
	
	private CommandController cmdController;	
	private Railroad railroad;
	
	
	private double[] weights = new double[] { 0.1, 0.1, 0.1, 0.1 };
	private int[]    heights = new int[]    { 7,   7,   7,   7   }; 
	

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
			getContentPane().add(drawPanel, new GridBagConstraints(0, 0, 4, 2, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
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
			getContentPane().add(LeftPanel, new GridBagConstraints(0, 2, 1, 2, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
			LeftPanel.setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));
			
			
			// Left Output
			leftOutput = new JTextArea();				
			leftOutput.setLineWrap(true);
			leftOutput.setWrapStyleWord(true);
			leftOutput.setEditable(false);
			LeftPanel.add(leftOutput, new GridBagConstraints(0, 0, 4, 3, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
			
						
			// Label Before Text Field For Commands
			CommandLabel = new JLabel();
			CommandLabel.setText("Command:");
			LeftPanel.add(CommandLabel, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
			
			
			// Text Field For Commands
			CommandField = new JTextField(20);
			LeftPanel.add(CommandField, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
			
			
			// Execute Button			
			ExecuteButton = new JButton();
			LeftPanel.add(ExecuteButton, new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
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
			getContentPane().add(RightPanel, new GridBagConstraints(1, 2, 2, 2, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
			RightPanel.setLayout(gridBagLayout2);
			RightPanel.setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));
			
			
			
			// Right Output
			rightOutput = new JTextArea();				
			rightOutput.setLineWrap(true);
			rightOutput.setBackground(Color.black);
			rightOutput.setForeground(Color.white);
			rightOutput.setWrapStyleWord(true);
			rightOutput.setEditable(false);			
			RightPanel.add(rightOutput, new GridBagConstraints(0, 0, 4, 3, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
			
			
			// openGui Button			
			openGui = new JButton();
			RightPanel.add(openGui, new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
			openGui.setText("openGui");
			openGui.addActionListener(this);
			
			pack();
			setSize(1000, 800);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//Gui with trains
	private void initTrainGui() {
		try 
		{
			JFrame frame = new JFrame("trains");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setTitle("PoorRail");
			GridBagLayout thisLayout = new GridBagLayout();
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			thisLayout.rowWeights = new double[] {0.1, 0.1, 0.1, 0.1};
			thisLayout.rowHeights = new int[] {7, 7, 7, 7};
			thisLayout.columnWeights = new double[] {0.1, 0.1, 0.1, 0.1};
			thisLayout.columnWidths = new int[] {7, 7, 7, 7};
			getContentPane().setLayout(thisLayout);
			{
				mainScreen = new JPanel();
				mainScreen.setLayout(new BorderLayout());
				getContentPane().add(mainScreen, new GridBagConstraints(0, 0, 4, 2, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				{
					innerMainScreen = new JPanel();
					innerMainScreen.setBackground(Color.WHITE);
					mainScreen.add(innerMainScreen,BorderLayout.CENTER);
				}
			}
			{
				leftPanel = new JPanel();
				GridBagLayout leftPanelLayout = new GridBagLayout();
				leftPanel.setLayout(leftPanelLayout);
				getContentPane().add(leftPanel, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				{
					trainNameLabel = new JLabel();
					leftPanel.add(trainNameLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					leftPanel.setBounds(10, 10, 100, 15);
					leftPanelLayout.rowWeights = new double[] {0.1, 0.1, 0.1, 0.1};
					leftPanelLayout.rowHeights = new int[] {7, 7, 7, 7};
					leftPanelLayout.columnWeights = new double[] {0.1, 0.1, 0.1, 0.1};
					leftPanelLayout.columnWidths = new int[] {7, 7, 7, 7};
					trainNameLabel.setText("train name:");
				}
				{
					trainNameTextField = new JTextField(20);
					leftPanel.add(trainNameTextField, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				}
				{
					btnNewTrain = new JButton();
					leftPanel.add(btnNewTrain, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					btnNewTrain.setText("make new train");
					btnNewTrain.addActionListener(this);
				}
				{
					ComboBoxModel cbAllTrainsModel = 
						new DefaultComboBoxModel(
								new String[] { });
					trainDropDown = new JComboBox();

					leftPanel.add(trainDropDown, new GridBagConstraints(1, 1, 1, 2, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
					trainDropDown.setModel(cbAllTrainsModel);
				}
				{
					btnChooseTrain = new JButton();
					leftPanel.add(btnChooseTrain, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					btnChooseTrain.setText("select train");
					btnChooseTrain.addActionListener(this);
				}
				{
					btnDeleteTrain = new JButton();
					leftPanel.add(btnDeleteTrain, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					btnDeleteTrain.setText("delete train");
					btnDeleteTrain.addActionListener(this);
				}
			}
			{
				rightPanel = new JPanel();
				GridBagLayout rightPanelLayout = new GridBagLayout();
				getContentPane().add(rightPanel, new GridBagConstraints(1, 2, 2, 3, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				rightPanelLayout.rowWeights = new double[] {0.1, 0.1, 0.1, 0.1};
				rightPanelLayout.rowHeights = new int[] {7, 7, 7, 7};
				rightPanelLayout.columnWeights = new double[] {0.1, 0.1, 0.1, 0.1};
				rightPanelLayout.columnWidths = new int[] {7, 7, 7, 7};
				rightPanel.setLayout(rightPanelLayout);
				rightPanel.setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));
				{
					selectedTrain = new JLabel();

					rightPanel.add(selectedTrain, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
					selectedTrain.setText("selected: ");
				}
				{
					btnAddWagon1 = new JButton();
					rightPanel.add(btnAddWagon1, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					btnAddWagon1.setText("add wagon 1");
					btnAddWagon1.addActionListener(this);
				}
				{
					btnAddWagon2 = new JButton();
					rightPanel.add(btnAddWagon2, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					btnAddWagon2.setText("add wagon 2");
					btnAddWagon2.addActionListener(this);
				}
				{
					btnAddWagon3 = new JButton();
					rightPanel.add(btnAddWagon3, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					btnAddWagon3.setText("add wagon 3");
					btnAddWagon3.addActionListener(this);
				}
				{
					btnDeleteWagon1 = new JButton();
					rightPanel.add(btnDeleteWagon1, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					btnDeleteWagon1.setText("delete wagon 1");
					btnDeleteWagon1.addActionListener(this);
				}
				{
					btnDeleteWagon2 = new JButton();
					rightPanel.add(btnDeleteWagon2, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					btnDeleteWagon2.setText("delete wagon 2");
					btnDeleteWagon2.addActionListener(this);
				}
				{
					btnDeleteWagon3 = new JButton();
					rightPanel.add(btnDeleteWagon3, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					btnDeleteWagon3.setText("delete wagon 3");
					btnDeleteWagon3.addActionListener(this);
				}
			}
			pack();
			setSize(800, 600);
			numberOfWagons = new HashMap();
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public void actionPerformed(ActionEvent event)
	{
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
		}
		if (event.getSource() == openGui) {
            Gui.main(null);
        }
		if (event.getSource()== btnNewTrain)
		{
			String train = trainNameTextField.getText();
			if (train != null && train.trim().length()>0)
			{
				train = addTrain(train);
				currentTrain = trainDropDown.getSelectedIndex();
				drawTrain(train);
			}
		}
		else if (event.getSource() == btnChooseTrain)
		{
			if (trainDropDown.getItemCount() > 0)
			{
				String selection = (String)trainDropDown.getSelectedItem();
				selectedTrain.setText("selected: " + selection);
				int ti = trainDropDown.getSelectedIndex();
				if (ti != currentTrain)
				{
					numberOfWagons.put(currentTrain, currentNumberOfWagons);
				}
				currentTrain = ti;
				try
				{
					currentNumberOfWagons = (Integer) numberOfWagons.get(currentTrain);
				}
				catch (Exception e)
				{
					currentNumberOfWagons = 0;
				}			
			}
		}
		else if (event.getSource() == btnDeleteTrain)
		{
			if (trainDropDown.getItemCount() > 0)
			{
				String t = (String)trainDropDown.getSelectedItem();
				trainDropDown.removeItemAt(trainDropDown.getSelectedIndex());
				numberOfWagons.remove(t);
				repaint();
				if ((String)trainDropDown.getSelectedItem() != null)
				{
					currentTrain = trainDropDown.getSelectedIndex();
					selectedTrain.setText("selected: " + (String)trainDropDown.getSelectedItem());
				}
				else
				{
					currentTrain = 0;
					selectedTrain.setText("selected: ");
				}
			}
		}else if (event.getSource() == btnAddWagon1)
		{
			currentNumberOfWagons++;
			drawWagon("Wagon1");
			wagonType=1;
		}
		else if (event.getSource() == btnAddWagon2)
		{
			currentNumberOfWagons++;
			drawWagon("Wagon2");
			wagonType=2;
		}
		else if (event.getSource() == btnAddWagon3)
		{
			currentNumberOfWagons++;
			drawWagon("Wagon3");
			wagonType=3;
		}
		else if (event.getSource() == btnDeleteWagon1)
		{
			if(wagonType==1) {
			currentNumberOfWagons--;
			repaint(30+TRAINLENGTH,80+currentTrain*OFFSET,1,1);
			}
		}
		else if (event.getSource() == btnDeleteWagon2)
		{
			if(wagonType==2) {
			currentNumberOfWagons--;
			repaint(30+TRAINLENGTH,80+currentTrain*OFFSET,1,1);
			}
		}
			
		else if (event.getSource() == btnDeleteWagon3)
		{
			if(wagonType==3) {
			currentNumberOfWagons--;
			repaint(30+TRAINLENGTH,80+currentTrain*OFFSET,1,1);		
			}
		}
	}
	
	public String addTrain(String train)
	{
		String t = "";
		try
		{
			t = train.trim();
			for (int i = 0; i < trainDropDown.getItemCount();i++ )
			{
				String cbTrain = (String)trainDropDown.getItemAt(i);
				if (cbTrain.equalsIgnoreCase(t))
				{
					t = "";
					break;
				}
			}
			if (t != "")
			{
				if (currentTrain >= 0)
				{
					numberOfWagons.put(currentTrain,currentNumberOfWagons);
				}
				trainDropDown.addItem(t);
				trainDropDown.setSelectedItem(t);
				numberOfWagons.put(t, 0);
			}
		}
		catch (Exception e)
		{
		}
		return t;
			
	}
	
	public void drawTrain(String train) 
	{
		if (train != "")
		{
			Graphics g = innerMainScreen.getGraphics();
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(30,80+currentTrain*OFFSET,80,40);
			g.fillRect(80,60+currentTrain*OFFSET,30,30);
			g.drawRoundRect(85, 40+currentTrain*OFFSET, 20, 20, 20, 20);
			g.drawRoundRect(85, currentTrain*OFFSET, 40, 40, 40, 40);
			g.setColor(Color.BLACK);
			g.fillRoundRect(35, 120+currentTrain*OFFSET, 20, 20, 20, 20);
			g.fillRoundRect(80, 120+currentTrain*OFFSET, 20, 20, 20, 20);
			g.drawString(train,40,105+currentTrain*OFFSET);
		}
    }
	
	public void drawWagon(String wagon) 
	{
		Graphics g = innerMainScreen.getGraphics();
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(30+currentNumberOfWagons*TRAINLENGTH,80+currentTrain*OFFSET,80,40);
		g.setColor(Color.BLACK);
		g.fillRoundRect(35+currentNumberOfWagons*TRAINLENGTH, 120+currentTrain*OFFSET, 20, 20, 20, 20);
		g.fillRoundRect(80+currentNumberOfWagons*TRAINLENGTH, 120+currentTrain*OFFSET, 20, 20, 20, 20);
		g.drawString(wagon,40+currentNumberOfWagons*TRAINLENGTH,105+currentTrain*OFFSET);
    }
	
	public void responseOutput(String response) {
		rightOutput.append(">> " + response + "\n");
	}
	
	
/*
0		new train tr1; 				// response is “train tr1 created”
1		new wagon wg1; 				// response is “wagon wg1 created with 20 seats”
2	new wagon wg2 numseats 15; 	// response is “wagon wg2 created with 15 seats”
3	add wg1 to tr1; 			// response: “wagon wg1 added to train tr1”
4		getnumseats train tr1; 		// response: “number of seats in train tr1: 20”
5		getnumseats wagon wg2; 		// response: “number of seats in wagon wg2: 15”
6	 	delete train tr1; 			// response: “train tr1 deleted”		delete train tr2; 			// response: “train tr2 does not exist”
7		delete wagon wg1; 			// response: “wagon wg1 deleted”
8	remove wg1 from tr1; 		// response: “wagon wg1 removed from train tr1”
*/	
		

	
	

	
	
	
	
	
	
}
