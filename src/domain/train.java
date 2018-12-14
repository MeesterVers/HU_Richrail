package domain;

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

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.SwingUtilities;

public class train {
	private HashMap numberOfWagons;
	private int currentNumberOfWagons;
	private int currentTrain = -1;
	private int OFFSET = 100;
	private int TRAINLENGTH = 100;
	private JPanel drawPanel;

	private JComboBox cbAllTrains;


	public train () {

	}

	public HashMap getNumberOfWagons() {
		return numberOfWagons;
	}

	public void setNumberOfWagons(HashMap numberOfWagons) {
		this.numberOfWagons = numberOfWagons;
	}

	public int getCurrentNumberOfWagons() {
		return currentNumberOfWagons;
	}

	public void setCurrentNumberOfWagons(int currentNumberOfWagons) {
		this.currentNumberOfWagons = currentNumberOfWagons;
	}

	public int getCurrentTrain() {
		return currentTrain;
	}

	public void setCurrentTrain(int currentTrain) {
		this.currentTrain = currentTrain;
	}

	public int getOFFSET() {
		return OFFSET;
	}

	public void setOFFSET(int oFFSET) {
		OFFSET = oFFSET;
	}

	public int getTRAINLENGTH() {
		return TRAINLENGTH;
	}

	public void setTRAINLENGTH(int tRAINLENGTH) {
		TRAINLENGTH = tRAINLENGTH;
	}

	public String addTrain(String train){
		String t = "";
		try
		{
			t = train.trim();
			for (int i = 0; i < cbAllTrains.getItemCount();i++ )
			{
				String cbTrain = (String)cbAllTrains.getItemAt(i);
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
				cbAllTrains.addItem(t);
				cbAllTrains.setSelectedItem(t);
				numberOfWagons.put(t, 0);
			}
		}
		catch (Exception e)
		{
		}
		return t;

	}

	public void drawTrain(String train) {
		if (train != "")
		{
			Graphics g = drawPanel.getGraphics();
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

}