package servlet.attend;

import java.sql.Date;
import java.util.Calendar;

public class SchoolYear {

	public static int returnSchoolYear(Date date) {

		//日付をカレンダーオブジェクトに設定
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        //年度を取得するためカレンダーオブジェクトで指定月-3(MONTHは0オリジン）
        calendar.add(Calendar.MONTH, -3);

        //カレンダーオブジェクトから年を取得
        int schoolYear = calendar.get(Calendar.YEAR);

	    return schoolYear;
	}
}
