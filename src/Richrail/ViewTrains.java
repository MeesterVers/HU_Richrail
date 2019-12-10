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
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;

import Contollers.CommandController;
import Dao.TrainDaoImpl;
import Dao.WagonDaoImpl;
import Model.Train;
import Model.Wagon;

public class ViewTrains extends javax.swing.JFrame implements ActionListener{
	static JPanel mainScreen;
	private static JPanel innerMainScreen;
	private JPanel rightPanel;
	private JPanel leftPanel;

	private JLabel trainNameLabel;
	private JLabel selectedTrain;

	public static BufferedImage image;

	private JButton btnDeleteWagon;
	private JButton btnAddWagon;
	private JButton btnDeleteTrain;
	public JButton btnNewTrain;
	public JButton btnNewGui;

	public CommandController cmdController;

	private JComboBox<String> trainDropDown;

	static JTextField trainNameTextField;
	private HashMap<Comparable, Integer> numberOfWagons;

	int wagonlocation = 205;
	int wgn_y = 200;
	int y = 200;
	int img_y =0;
	int wagon_img_y = 0;
	private JLabel Wagonname;
	private JLabel Wagonseats;
	static JTextField WagonnameTextfield1;
	static JTextField SeatsTextfield1;
	
	TrainDaoImpl traindao = new TrainDaoImpl();
	WagonDaoImpl wagondao = new WagonDaoImpl();

	/**
	 * Auto-generated main method to display this JFrame
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				ViewTrains inst = new ViewTrains();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}

	public ViewTrains() {
		super();
		initGUI();
	}

	
	private void initGUI() {
		try {
			this.setTitle("Richrail");
			GridBagLayout thisLayout = new GridBagLayout();
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			thisLayout.rowWeights = new double[] { 0.5, 0.1, 0.1, 0.1 };
			thisLayout.rowHeights = new int[] { 7, 7, 7, 7 };
			thisLayout.columnWeights = new double[] { 0.1, 0.1, 0.1, 0.1 };
			thisLayout.columnWidths = new int[] { 7, 7, 7, 7 };
			getContentPane().setLayout(thisLayout);
			{
				// Upper Draw Panel(Train and wagon output)
				mainScreen = new JPanel();
				mainScreen.setLayout(new BorderLayout());
				getContentPane().add(mainScreen, new GridBagConstraints(0, 0, 4, 3, 0.0, 0.0, GridBagConstraints.EAST,
						GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				{
					innerMainScreen = new JPanel();
					innerMainScreen.setBackground(Color.WHITE);
					mainScreen.add(innerMainScreen, BorderLayout.CENTER);
				}
			}

				{
					// Right Panel
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
					
					btnNewTrain = new JButton();
					rightPanel.add(btnNewTrain, new GridBagConstraints(1, 6, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER,
							GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					btnNewTrain.setText("View Trains");
					btnNewTrain.addActionListener(this);
		
				}
			
			pack();
			setSize(1000, 800);
			numberOfWagons = new HashMap<Comparable, Integer>();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btnNewTrain) {
			try {
				drawTrain();
			} catch (IOException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
		// Draw Train
		public void drawTrain() throws IOException, SQLException {
			List<Train> Trains = traindao.findAll();
			Graphics g = innerMainScreen.getGraphics();
			for (Train train: Trains) {
				wagonlocation = 205;
				image = ImageIO.read(new File("src/train.jpg"));
				g.drawImage(image, 0, img_y, null);
				// Set trainname on image
				g.drawString(train.getName(), wagonlocation - 100, y);
				y= y + 200;
				img_y = img_y + 200;
				int id= train.getID();
				List<Wagon> Wagons = wagondao.findWagonByTrainID(id);
				for (Wagon wagon: Wagons) {
					
					image = ImageIO.read(new File("src/Wagon.png"));
					g.drawImage(image, wagonlocation, img_y - 200, null);
					g.drawString(wagon.getName(), wagonlocation + 85, y -200);
					// Set wagonname on image
					g.drawString(wagon.getnumSeats() + "seats", wagonlocation + 140, y - 200);
					wagonlocation = wagonlocation + 210;
				}

			}
		}

		public void drawWagon() throws IOException, SQLException {
			List<Wagon> Wagons = wagondao.findAll();
			Graphics g = innerMainScreen.getGraphics();
			for (Wagon wagon: Wagons) {
				int trainid = wagon.getTrain_id();
				if (trainid == wagon.getTrain_id()) {
					image = ImageIO.read(new File("src/Wagon.png"));
					g.drawImage(image, wagonlocation, 0, null);
					g.drawString(wagon.getName(), wagonlocation + 85, 200);
					// Set wagonname on image
					g.drawString(wagon.getnumSeats() + "seats", wagonlocation + 115, 200);
				} else {
			image = ImageIO.read(new File("src/Wagon.png"));
			g.drawImage(image, wagonlocation, wagon_img_y, null);
			g.drawString(wagon.getName(), wagonlocation + 85, wgn_y);
			// Set wagonname on image
			g.drawString(wagon.getnumSeats() + "seats", wagonlocation + 115, wgn_y);
			wgn_y = wgn_y + 200;
			wagon_img_y = wagon_img_y + 200;
				}
		}
		}
}
