package bean;

//科目情報
public class Subject implements java.io.Serializable {

	private int subjectId;;
	private String subjectName;
    private int courseId;
    private int grade;
    private int redit;
    private int classNum;
    private boolean isFirstHalf;

    private int unitTime;

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
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

	public int getRedit() {
		return redit;
	}

	public void setRedit(int redit) {
		this.redit = redit;
	}

	public int getClassNum() {
		return classNum;
	}

	public void setClassNum(int classNum) {
		this.classNum = classNum;
	}

	public boolean isFirstHalf() {
		return isFirstHalf;
	}

	public void setFirstHalf(boolean isFirstHalf) {
		this.isFirstHalf = isFirstHalf;
	}

	//	科目の「単位時間」を変えす
	public int getUnitTime() {
		unitTime = classNum * 2;
		return unitTime;
	}


}
