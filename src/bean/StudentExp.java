package bean;

import java.util.ArrayList;

//学生情報
public class StudentExp extends Student implements java.io.Serializable {

	private ArrayList<Score> scoreList;
    private ArrayList<Attendance> attendanceList;
	private ArrayList<ClassHistory> classHistoryList;
    private ClassC classC;
    private Course course;

    private double sumOfAttendance;
    private double sumOfAttendanceAtMonth;
    private String disposal;

	public ArrayList<Score> getScoreList() {
		return scoreList;
	}
	public void setScoreList(ArrayList<Score> scoreList) {
		this.scoreList = scoreList;
	}
	public ArrayList<Attendance> getAttendanceList() {
		return attendanceList;
	}
	public void setAttendanceList(ArrayList<Attendance> attendanceList) {
		this.attendanceList = attendanceList;
	}
	public ArrayList<ClassHistory> getClassHistoryList() {
		return classHistoryList;
	}
	public void setClassHistoryList(ArrayList<ClassHistory> classHistoryList) {
		this.classHistoryList = classHistoryList;
	}
	public ClassC getClassC() {
		return classC;
	}
	public void setClassC(ClassC classC) {
		this.classC = classC;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public double getSumOfAttendance() {
		return sumOfAttendance;
	}
	public double getSumOfAttendanceAtMonth() {
		return sumOfAttendanceAtMonth;
	}
	public String getDisposal() {
		double  sumOfAttendance = getSumOfAttendance();

		if (20 <= sumOfAttendance && sumOfAttendance < 40) {
			disposal = "訓告";
		} else  if (40 <= sumOfAttendance && sumOfAttendance < 60) {
			disposal = "戒告";
		} else  if (60 <= sumOfAttendance && sumOfAttendance < 80) {
			disposal = "再戒告";
		} else if (80 <= sumOfAttendance) {
			disposal = "退学戒告";
		}

		return disposal;
	}

}