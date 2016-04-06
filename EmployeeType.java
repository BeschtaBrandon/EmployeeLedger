public class EmployeeType {
	private int id;
	private String text;
	
	public EmployeeType(int id, String text){
		this.setEmployeeTypeId(id);
		this.setEmployeeText(text);
	}
	
	public int getEmployeeTypeId(){return this.id;}
	public void setEmployeeTypeId(int id){this.id = id;}
	
	public String getEmployeeText(){return this.text;}
	public void setEmployeeText(String text){this.text = text;}
	
	@Override
	public String toString(){
	
		return this.getEmployeeText();		
	}
}
