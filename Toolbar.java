import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Toolbar extends JPanel implements ActionListener {
	private JButton btn1;
	private JButton btn2;
	private StringListener textListener;
	
	public Toolbar(){
		setBorder(BorderFactory.createEtchedBorder());
		this.btn1 = new JButton("Hello");
		this.btn2 = new JButton("Goodbye");
		
		this.btn1.addActionListener(this);
		this.btn2.addActionListener(this);
		
		setLayout(new FlowLayout(FlowLayout.LEFT));
		
		add(this.btn1);
		add(this.btn2);
	}
	
	public void setStringListener(StringListener listener){
		this.textListener = listener;
	}

	@Override
	public void actionPerformed(ActionEvent e){
		JButton clicked = (JButton)e.getSource();
		
		if(clicked == this.btn1){
			if(this.textListener != null){
				this.textListener.testString("Saying Hello");
			}
		}else if(clicked == this.btn2){
			if(this.textListener != null){
				this.textListener.testString("Saying Goodbye");
			}
		}				
	}//END public void actionPerformed(ActionEvent e){
}
