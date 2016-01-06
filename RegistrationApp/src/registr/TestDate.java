package registr;

import java.util.Date;

public class TestDate 
{
	private Date DOB;
	public Date getDt() {
		return DOB;
	}
	public void setDt(Date dt) {
		this.DOB = dt;
	}
	public String getSt() {
		return PersonName;
	}
	public void setSt(String PersonName) {
		this.PersonName = PersonName;
	}
	private String PersonName;
	TestDate(String PersonName,Date d){
		this.PersonName=PersonName;
		DOB=d;
	}
	
}