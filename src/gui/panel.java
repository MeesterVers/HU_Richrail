package gui;



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

import javax.swing.*;
import javax.swing.border.BevelBorder;

import domain.train;
import domain.wagon;

/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class panel extends javax.swing.JFrame implements ActionListener 
{
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

	
	train trainInst = new train();
	wagon wagonInst = new wagon();
	
	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) 
	{
		SwingUtilities.invokeLater(new Runnable() 
		{
			public void run() 
			{
				panel inst = new panel();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	public panel() 
	{
		super();
		initGUI();
	}
	
	private void initGUI() 
	{
		try 
		{
			this.setTitle("RichRail");
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
				//jPanel2.setLayout(null);
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
		if (event.getSource()== btnNewTrain)
		{
			String train = trainNameTextField.getText();
			if (train != null && train.trim().length()>0)
			{
				train = trainInst.addTrain(train);
				currentTrain = trainDropDown.getSelectedIndex();
				Graphics g = mainScreen.getGraphics();
				trainInst.drawTrain(train, g);
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
		}
		else if (event.getSource() == btnAddWagon1)
		{
			currentNumberOfWagons++;
			Graphics g = mainScreen.getGraphics();
			wagonInst.drawWagon("Wagon1", g);
		}
		else if (event.getSource() == btnAddWagon2)
		{
			currentNumberOfWagons++;
			Graphics g = mainScreen.getGraphics();
			wagonInst.drawWagon("Wagon2", g);
		}
		else if (event.getSource() == btnAddWagon3)
		{
			currentNumberOfWagons++;
			Graphics g = mainScreen.getGraphics();
			wagonInst.drawWagon("Wagon3", g);
		}
		else if (event.getSource() == btnDeleteWagon1)
		{
			repaint(30+TRAINLENGTH,80+currentTrain*OFFSET,1,1);
		}
		else if (event.getSource() == btnDeleteWagon2)
		{
			repaint(30+TRAINLENGTH,80+currentTrain*OFFSET,1,1);		
		}
		else if (event.getSource() == btnDeleteWagon3)
		{
			repaint(30+TRAINLENGTH,80+currentTrain*OFFSET,1,1);		
		}
	}
}