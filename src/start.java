import javax.swing.SwingUtilities;

import gui.panel;
import gui.panel2;

class start{
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() 
		{
			public void run() 
			{
				panel2 inst = new panel2();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
}