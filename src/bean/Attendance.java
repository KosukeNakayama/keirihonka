package bean;

import java.sql.Date;

//出欠情報
public class Attendance implements java.io.Serializable {

	private String studentId;
    private Date date;
    private char status;
	private String memo;

	private String statusStr;

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getStatusStr() {
		return statusStr;
	}

}