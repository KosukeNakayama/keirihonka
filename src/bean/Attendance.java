package bean;

import java.util.Date;

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

	//	statusに該当する文字列を返す
	//	1=欠席、2=遅刻、3=早退、4=遅刻早退 5=その他
	public String getStatusStr() {
		switch (status) {
			case 1: statusStr = "欠席";
			break;

			case 2: statusStr = "遅刻";
			break;

			case 3: statusStr = "早退";
			break;

			case 4: statusStr = "遅刻早退";
			break;

			case 5: statusStr = "その他";
		}
		return statusStr;
	}

}