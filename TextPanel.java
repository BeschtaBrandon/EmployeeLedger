import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class TextPanel extends JPanel {
	private JTextArea textArea;
	
	public TextPanel(){
		this.textArea = new JTextArea();
		
		setLayout(new BorderLayout());
		
		add(new JScrollPane(this.textArea), BorderLayout.CENTER);
	}
	
	public void appendText(String text){
		this.textArea.append(text);
	}
}
