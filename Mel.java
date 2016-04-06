import javax.swing.SwingUtilities;

public class Mel {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){

			public void run() {
				new MainFrame();
			}});

	}

}
