package util;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import bean.ClassC;
import bean.Users;

public class Checker {

	public static boolean isLogined(HttpServletRequest request){
		HttpSession session = request.getSession();
		Users user = (Users)session.getAttribute("user");
		return !Objects.isNull(user);
	}

	public static boolean isManage(HttpServletRequest request){
		HttpSession session = request.getSession();
		Users user = (Users)session.getAttribute("user");
		if(Objects.nonNull(user)){
			return user.getManaged();
		}else{
			return false;
		}
	}

	public static boolean isWithinSchoolYear(int schoolYear,Calendar date){
		Calendar startDate = Calendar.getInstance();
		startDate.set(schoolYear, 3, 31);
		Calendar endDate = Calendar.getInstance();
		endDate.set(schoolYear+1, 4, 1);

		if(date.after(startDate) && date.before(endDate)){
			return true;
		}else{
			return false;
		}
	}

	public static boolean isWithinSchoolYearAndBeforeClassDate(ClassC cls,Calendar date){
		Calendar startDate = Calendar.getInstance();
		startDate.set(cls.getSchoolYear(), 3, 31);
		Calendar endDate = Calendar.getInstance();
		endDate.set(cls.getSchoolYear()+1, 4, 1);
		Calendar clsStartDate = Calendar.getInstance();
		Date dateU = cls.getStartDate();
		dateU.setDate(-1);
		clsStartDate.set(cls.getSchoolYear(), dateU.getMonth(), dateU.getDate());

		if(date.after(startDate) && date.before(endDate) && date.after(clsStartDate)){
			return true;
		}else{
			return false;
		}
	}

}
