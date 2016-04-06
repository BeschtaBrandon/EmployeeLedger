import java.util.EventObject;

public class FormEvent extends EventObject {
	private static final long serialVersionUID = -6506741579068920348L;
	private String fName;
	private String lName;
	private String title;
	private int employeeType;
	private String empCat;
	private String taxID;
	private boolean taxable;
	private String gender;

	public FormEvent(Object source) {
		super(source);
	}
	
	public FormEvent(Object source, String fName, String lName, String title,int employeeType, String empCat,String taxID, boolean taxable,String gender) {
		super(source);
		
		this.setfName(fName);
		this.setlName(lName);
		this.setTitle(title);
		this.setEmployeeType(employeeType);
		this.setempCat(empCat);
		this.setTaxable(taxable);
		this.setTaxID(taxID);
		this.setGender(gender);
	}

	public String getfName() {return fName;}
	public void setfName(String fName) {this.fName = fName;}

	public String getlName() {return lName;}
	public void setlName(String lName) {this.lName = lName;}

	public String getTitle() {return this.title;}
	public void setTitle(String title) {this.title = title;}
	
	public int getEmployeeType() {return this.employeeType;}
	public void setEmployeeType(int employeeType) {this.employeeType = employeeType;}
	
	public String getempCat() {return empCat;}
	public void setempCat(String empCat) {this.empCat = empCat;}

	public boolean isTaxable() {return taxable;}
	public void setTaxable(boolean taxable) {this.taxable = taxable;}

	public String getTaxID() {return taxID;}
	public void setTaxID(String taxID) {this.taxID = taxID;}

	public String getGender() {return gender;}
	public void setGender(String gender) {this.gender = gender;}

}
