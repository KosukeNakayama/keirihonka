package servlet.attend;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SchoolYear {

	public static Date returnToday(Date date) {

		//日付をカレンダーオブジェクトに設定
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        //日付のフォーマット変換＆SQL用日付型に変換
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String todayString = sdf.format(calendar.getTime());
		Date today = java.sql.Date.valueOf(todayString);

	    return today;
	}


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
