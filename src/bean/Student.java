package bean;

import java.sql.Date;

//学生情報
public class Student implements java.io.Serializable {

	private String studentId;
    private String studentName;
	private int enrollmentYear;
    private int courseId;
    private Date graduationDay;
    private Date withdrawalDay;

	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public int getEnrollmentYear() {
		return enrollmentYear;
	}
	public void setEnrollmentYear(int enrollmentYear) {
		this.enrollmentYear = enrollmentYear;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public Date getGraduationDay() {
		return graduationDay;
	}
	public void setGraduationDay(Date graduationDay) {
		this.graduationDay = graduationDay;
	}
	public Date getWithdrawalDay() {
		return withdrawalDay;
	}
	public void setWithdrawalDay(Date withdrawalDay) {
		this.withdrawalDay = withdrawalDay;
	}

}