import javax.swing.SwingUtilities;

import gui.panel;

class start{
	public static void main(String[] args) {
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
}