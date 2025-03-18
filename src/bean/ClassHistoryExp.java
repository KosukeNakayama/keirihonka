package bean;

//学生情報
public class ClassHistoryExp extends ClassHistory implements java.io.Serializable {

    private ClassC classC;

	public ClassC getClassC() {
		return classC;
	}
	public void setClassC(ClassC classC) {
		this.classC = classC;
	}


}