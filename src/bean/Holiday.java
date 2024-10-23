package bean;

import java.util.Date;

//休日情報
public class Holiday implements java.io.Serializable {

	private Date holiday;
	private int classId;
	private boolean isManaged;

	public Date getHoliday() {
		return holiday;
	}
	public void setHoliday(Date holiday) {
		this.holiday = holiday;
	}
	public int getClassId() {
		return classId;
	}
	public void setClassId(int classId) {
		this.classId = classId;
	}
	public boolean isManaged() {
		return isManaged;
	}
	public void setManaged(boolean isManaged) {
		this.isManaged = isManaged;
	}

}
