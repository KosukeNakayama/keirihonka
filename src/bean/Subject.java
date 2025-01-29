package bean;

//科目情報
public class Subject implements java.io.Serializable {

	private String subjectId;;
	private String subjectName;
    private int courseId;
    private int grade;
    private int credit;
    private int classNum;
    private boolean isFirstHalf;

    private int unitTime;

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int redit) {
		this.credit = redit;
	}

	public int getClassNum() {
		return classNum;
	}

	public void setClassNum(int classNum) {
		this.classNum = classNum;
	}

	public boolean getisFirstHalf() {
		return isFirstHalf;
	}

	public void setisFirstHalf(boolean isFirstHalf) {
		this.isFirstHalf = isFirstHalf;
	}

	//	科目の「単位時間」を変えす
	public int getUnitTime() {
		unitTime = classNum * 2;
		return unitTime;
	}


}
