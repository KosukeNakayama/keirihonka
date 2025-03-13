package util;

import java.util.Calendar;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
		startDate.set(schoolYear, 4, 1);
		Calendar endDate = Calendar.getInstance();
		endDate.set(schoolYear+1, 3, 31);

		if(date.after(startDate) && date.before(endDate)){
			return true;
		}else{
			return false;
		}
	}

}
