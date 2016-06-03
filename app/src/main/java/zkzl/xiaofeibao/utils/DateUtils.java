package zkzl.xiaofeibao.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by zhcf-01 on 2015/12/14.
 */
public class DateUtils {

    public static String formatDateTime(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        String stringDate = simpleDateFormat.format(date);
        return stringDate;
    }
}
