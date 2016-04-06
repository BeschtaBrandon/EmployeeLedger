import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	private TextPanel textPanel;
	private Toolbar toolbar;
	private FormPanel formPanel;
	
	public MainFrame() throws HeadlessException {
		super("Maverick Employee Ledgar");
		
		setLayout(new BorderLayout());
		
		this.textPanel = new TextPanel();
		this.toolbar = new Toolbar();
		this.formPanel = new FormPanel();
		
		add(this.textPanel, BorderLayout.CENTER);
		add(this.toolbar, BorderLayout.NORTH);
		add(this.formPanel, BorderLayout.WEST);
		
		setJMenuBar(this.createMenuBar());
		
		toolbar.setStringListener(new StringListener(){
			@Override
			public void testString(String text) {
				textPanel.appendText(text);
		}});//END toolbar.setStringListener(new StringListener(){
		
		formPanel.setFormListener(new FormListener(){
			@Override
			public void formEventOccurred(FormEvent e) {
				String fName = e.getfName();
				String lName = e.getlName();
				String title = e.getTitle();
				int employeeType = e.getEmployeeType();
				String empCat = e.getempCat();
				String gender = e.getGender();
				
				textPanel.appendText(fName+" "+lName+" | "+title+" | "+employeeType+" | "+empCat+" | "+gender+".\n\r");
			}});
		
		setSize(600,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}//END public MainFrame() throws HeadlessException {
	
	private JMenuBar createMenuBar(){
		JMenuBar menuBar = new JMenuBar();
		
		//Window Menu
		JMenu windowMenu = new JMenu("Window");
		JMenuItem aboutItem = new JMenuItem("Aboout");
		JMenuItem helpItem = new JMenuItem("Help");
		
		windowMenu.add(aboutItem);
		windowMenu.add(helpItem);
		windowMenu.addSeparator();
		
		//Window Show submenu
		JMenu showMenu = new JMenu("Show");
		JCheckBoxMenuItem showFormItem = new JCheckBoxMenuItem("Employee Form"); showFormItem.setSelected(true);
		showMenu.add(showFormItem);
		
		windowMenu.add(showMenu);
		
		//File Menu
		JMenu fileMenu = new JMenu("File");
		JMenuItem exportDataItem = new JMenuItem("Export Data...");
		JMenuItem importDataItem = new JMenuItem("Import Data...");
		JMenuItem exitItem = new JMenuItem("Exit");
		
		fileMenu.add(exportDataItem);
		fileMenu.add(importDataItem);
		fileMenu.addSeparator();
		fileMenu.add(exitItem);
		
		menuBar.add(fileMenu);
		menuBar.add(windowMenu);
		
		showFormItem.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent ev) {
				JCheckBoxMenuItem menuItem = (JCheckBoxMenuItem)ev.getSource();
				
				formPanel.setVisible(menuItem.isSelected());
			}
			
		});
		
		return menuBar;
	}

}
