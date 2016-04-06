import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class FormPanel extends JPanel {
	private JLabel fNameLabel; private JTextField fNameField;
	private JLabel lNameLabel; private JTextField lNameField;
	private JLabel titleLable; private JTextField titleField;
	private JCheckBox taxableCheck; private JTextField taxField; private JLabel taxLabel;
	private JButton okBtn;
	private FormListener formListener;
	private JList<EmployeeType> employeeTypeList;
	@SuppressWarnings("rawtypes")
	private JComboBox empCombo;
	private JRadioButton maleGenderRadio;
	private JRadioButton femaleGenderRadio;
	private ButtonGroup genderGroup;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public FormPanel(){
		Dimension dim = getPreferredSize();
		dim.width = 250;
		setPreferredSize(dim);
		
		this.fNameField = new JTextField(10);
		this.fNameLabel = new JLabel("First Name: ");
		this.lNameField = new JTextField(10);
		this.lNameLabel = new JLabel("Last Name: ");
		this.titleLable = new JLabel("Title: ");
		this.titleField = new JTextField(10);
		this.employeeTypeList = new JList<EmployeeType>();
		this.empCombo = new JComboBox();
		this.taxableCheck = new JCheckBox();
		this.taxField = new JTextField(10);
		this.taxLabel = new JLabel("Tax ID: ");
		this.okBtn = new JButton("OK");
		this.maleGenderRadio = new JRadioButton("male"); this.maleGenderRadio.setActionCommand("male");
		this.femaleGenderRadio = new JRadioButton("female"); this.femaleGenderRadio.setActionCommand("female");
		this.genderGroup = new ButtonGroup();
		
		//Set up gender radios
		this.genderGroup.add(maleGenderRadio);
		this.genderGroup.add(femaleGenderRadio);
		
		
		//Setup EmployeeTypeBox
		DefaultListModel<EmployeeType> employeeTypeModel = new DefaultListModel<EmployeeType>();
		employeeTypeModel.addElement(new EmployeeType(001, "Interns"));
		employeeTypeModel.addElement(new EmployeeType(002, "Contractors"));
		employeeTypeModel.addElement(new EmployeeType(003, "Full-Time"));
		employeeTypeModel.addElement(new EmployeeType(004, "Part-Time"));
		
		employeeTypeList.setModel(employeeTypeModel);
		
		employeeTypeList.setPreferredSize(new Dimension(115,66));
		employeeTypeList.setBorder(BorderFactory.createEtchedBorder());
		
		//Set up Employee Combo box
		DefaultComboBoxModel empModel = new DefaultComboBoxModel();
		empModel.addElement("On Site");
		empModel.addElement("Off site");
		empModel.addElement("Hybrid");
		this.empCombo.setModel(empModel);
				
		//Setup Layout 
		Border innerBorder = BorderFactory.createTitledBorder("Add Person");
		Border outterBorder = BorderFactory.createEmptyBorder(5,5,5,5);
		setBorder(BorderFactory.createCompoundBorder(innerBorder, outterBorder));
		
		this.layoutComponent();
		
		//Setup tax components
		this.taxLabel.setEnabled(false);
		this.taxField.setEnabled(false);
		
		//Setup actions
		this.taxableCheck.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				boolean isTaxable = taxableCheck.isSelected();
				
				taxLabel.setEnabled(isTaxable);
				taxField.setEnabled(isTaxable);
			}});

		this.okBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				String fName = fNameField.getText();
				String lName = lNameField.getText();
				String title = titleField.getText();
				String empCat = (String)empCombo.getSelectedItem();
				String taxID = taxField.getText();
				boolean taxable = taxableCheck.isSelected();
				String gender = genderGroup.getSelection().getActionCommand();
				
				EmployeeType employeeType = (EmployeeType)employeeTypeList.getSelectedValue();
				
				if(employeeType == null){
					JOptionPane.showMessageDialog(null, "You must select an employee type");
				}
				
				
				if(employeeType != null){
					FormEvent ev = new FormEvent(this, fName, lName, title,employeeType.getEmployeeTypeId(),empCat,taxID,taxable,gender);
					if(formListener != null && ev !=null){
						formListener.formEventOccurred(ev);
					}
				}
				
			}});
		
		
		
	}//END public FormPanel(){
	
	public void layoutComponent(){
		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		gc.fill = GridBagConstraints.NONE;
		gc.gridy = 0;
		
		////Row 1 /////////
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx=0;
		gc.anchor = GridBagConstraints.LINE_END;
		add(this.fNameLabel,gc);
		
		gc.gridx=1;
		gc.anchor = GridBagConstraints.LINE_START;
		add(this.fNameField,gc);
		
		////Row 2/////
		gc.gridy++;
		
		gc.weightx = 0;
		gc.weighty = 0.1;
		
		gc.gridx=0;
		gc.anchor = GridBagConstraints.LINE_END;
		add(this.lNameLabel,gc);
		
		gc.gridx=1;
		gc.anchor = GridBagConstraints.LINE_START;
		add(this.lNameField,gc);
		
		
		//////Row 3//////////
		gc.gridy++;
		gc.weighty = 0.1;
		
		gc.gridx=0;
		gc.anchor = GridBagConstraints.LINE_END;
		add(this.titleLable,gc);
		
		gc.gridx=1;
		gc.anchor = GridBagConstraints.LINE_START;
		add(this.titleField,gc);
		
		
		/////Employee Type Row//////////
		gc.gridy++;
		
		gc.weighty =0.2;
		
		gc.gridx = 0;
		gc.insets = new Insets(0,0,0,5);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		add(new JLabel("Employee Type"),gc);
		
		gc.gridx=1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(this.employeeTypeList,gc);
		
		
		/////Combo box row//////////
		gc.gridy++;
		
		gc.weighty = 0.2;
		
		gc.gridx = 0;
		gc.insets = new Insets(0,0,0,5);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		add(new JLabel("Combo Box"),gc);
		
		gc.gridx=1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(this.empCombo,gc);
		
		/////Taxable Checkbox row//////////
		gc.gridy++;
		
		gc.weighty = 0.2;
		
		gc.gridx = 0;
		gc.insets = new Insets(0,0,0,5);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		add(new JLabel("Taxable"),gc);
		
		gc.gridx=1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(this.taxableCheck,gc);
		
		/////Taxable TextField row//////////
		gc.gridy++;
		
		gc.weighty = 0.2;
		
		gc.gridx = 0;
		gc.insets = new Insets(0,0,0,5);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		add(new JLabel("Tax ID"),gc);
		
		gc.gridx=1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(this.taxField,gc);	
		
		/////Gender Male row//////////
		gc.gridy++;
		
		gc.weighty = 0.02;
		
		gc.gridx = 0;
		gc.insets = new Insets(0,0,0,5);
		gc.anchor = GridBagConstraints.LINE_END;
		add(new JLabel("Gender"),gc);
		
		gc.gridx=1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(this.maleGenderRadio,gc);			
		
		/////Gender Female row//////////
		gc.gridy++;
		
		gc.weighty = 0.2;
		
		gc.gridx=1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(this.femaleGenderRadio,gc);
		
		/////Row 5//////////
		gc.weighty =2.0;
		
		gc.gridx=1;
		gc.gridy++;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(this.okBtn,gc);
		
	}//END public void layoutComponent(){

	public void setFormListener(FormListener formListener) {
		this.formListener = formListener;
	}

	
}//END public class FormPanel extends JPanel {
