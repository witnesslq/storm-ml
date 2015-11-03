package cn.disruptive.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @ClassName: DateUtils
 * @Description: TODO
 */

public class DateUtils {
	private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat tf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	/**
	 * 将日期类型转换成自定义格式的字符串
	 * @param date
	 * @param format
	 * @return
	 */
	public static String formatDate(Date date,String format) {
		String ret = "";
		try {
			SimpleDateFormat df = new SimpleDateFormat(format);
			ret = df.format(date);
		} catch (Exception e) {
			ret = "";
		}
		return ret;
	}
	/**
	 * 将字符串转换成日期类型
	 * 
	 * @param date
	 *            传入的字符串
	 * @return 转换成的日期值
	 */
	public static Date parseDate(String date) {
		Date ret = null;
		if (date != null && date != "") {
			try {
				ret = df.parse(date);
			} catch (ParseException e) {
				ret = null;
			}
		}
		return ret;
	}
	public static Date parseDate(String date,String format) {
		Date ret = null;
		SimpleDateFormat fd = new SimpleDateFormat(format);
		if (date != null && date != "") {
			try {
				ret = fd.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
				ret = null;
			}
		}
		return ret;
	}
	/**
	 * 将日期类型转换成"yyyy-MM-dd"字符串
	 * 
	 * @param date
	 *            传入的日期值
	 * @return 字符串类型的日期值
	 */
	public static String formatDate(Date date) {
		String ret = "";
		try {
			ret = df.format(date);
		} catch (Exception e) {
			ret = "";
		}
		return ret;
	}

	/**
	 * 将日期类型转换成"yyyy-MM-dd hh:mm:ss"字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String formatTime(Date date) {
		String ret = "";
		try {
			ret = tf.format(date);
		} catch (Exception e) {
			ret = "";
		}
		return ret;
	}

	/**
	 * 将日期类型转换成"yyyyMMddhhmmssSSS"字符串
	 * @param date
	 * @return
	 */
	public static String formatMs(Date date){
		String ret = "";
		try {
			ret = sf.format(date);
		} catch (Exception e) {
			ret = "";
		}
		return ret;
	}
	/**
	 * 返回java.sql.Time
	 * @param strDate
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static java.sql.Time strToTime(String strDate){
		//String str=strDate;
		SimpleDateFormat df = new SimpleDateFormat("hh:mm:ss");
		java.util.Date d=null;
		try{
			d=df.parse(strDate);
		}catch(Exception e){
			e.printStackTrace();
		}
		java.sql.Time time=new java.sql.Time(d.getTime());
		return time.valueOf(strDate);
	}
	
	/**
	 * 
	 * Description: 获取指定月份的第一天
	 * Date:2014-2-12
	 * @param year
	 * @param month
	 * @return 
	 * @return String
	 */
	public static String getFirstDayInSelMonth(int year,int month){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month-1);
		calendar.set(Calendar.DAY_OF_MONTH,
				calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		return formatDate(calendar.getTime(),"yyyy-MM-dd");
	}
	
	/**
	 * 
	 * Description: 获取指定月份的最后一天
	 * Date:2014-2-12
	 * @param year
	 * @param month
	 * @return 
	 * @return String
	 */
	public static String getLastDayInSelMonth(int year,int month){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month-1);
		calendar.set(Calendar.DAY_OF_MONTH,
				calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return formatDate(calendar.getTime(),"yyyy-MM-dd");
	}
	
	/**
	 * 得到本月的第一天
	 * 
	 * @return
	 */
	public static Date getMonthFirstDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH,
				calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		return calendar.getTime();
	}

	/**
	 * 得到本月的最后一天
	 * 
	 * @return
	 */
	public static Date getMonthLastDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH,
				calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return calendar.getTime();
	}

	/**
	 * 
	 * Description: 获取本周第一天(周一) Date:2013-7-25
	 * 
	 * @return
	 * @return Date
	 */
	public static String getWeekFirstDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_WEEK,
				calendar.getActualMinimum(Calendar.DAY_OF_WEEK) + 1);
		return formatDate(calendar.getTime());
	}

	/**
	 * 
	 * Description: 获取本周最后一天(周日) Date:2013-7-25
	 * 
	 * @return
	 * @return Date
	 */
	public static String getWeekLastDay() {
		Date date = parseDate(getWeekFirstDay());
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, 6);
		Date rightDateStr = c.getTime();
		return formatDate(rightDateStr);
	}
	
	/**
	 * 得到30天后的日期
	 * 
	 * @return
	 */
	public static Date getValidityTime() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_WEEK, 29);
		return c.getTime();
	}
	
	public static  String getNextDay(String dateStr,String format)
	{
		Date date = parseDate(dateStr, format);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, 1);
		Date rightDateStr = c.getTime();
		return formatDate(rightDateStr, format);
	}
	public static  String getLastDay(String dateStr,String format)
	{
		Date date = parseDate(dateStr, format);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE,-1);
		Date rightDateStr = c.getTime();
		return formatDate(rightDateStr, format);
	}
	public static  String getNextDay(Date date ,String format)
	{
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, 1);
		Date rightDateStr = c.getTime();
		return formatDate(rightDateStr, format);
	}
	public static  String getLastDay(Date date,String format)
	{
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE,-1);
		Date rightDateStr = c.getTime();
		return formatDate(rightDateStr, format);
	}
	/**
     * 两个时间相差秒数
     * @param lastTime
     * @param curTime
     * @return
     */
    public static long diffSecond(Date lastTime,Date curTime) {     
       long diff = curTime.getTime() - lastTime.getTime(); 
       long second = diff / (1000);
       return second;  
    }
    
    /**
     * 
     * Description: 两个日期相差的天数
     * Date:2014-2-13
     * @param fDate
     * @param oDate
     * @return 
     * @return int
     */
    public static int diffDays(Date beginDate, Date endDate) {

    	 long margin = 0;

    	    margin = endDate.getTime() - beginDate.getTime();

    	    margin = margin/(1000*60*60*24);

    	    return Integer.parseInt(String.valueOf(margin));
     }
    
    /**
     * 
     * Description: 获得当前日期年份
     * Date:2014-2-13
     * @return 
     * @return int
     */
    public static int getYearCurDate(){
    	Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.YEAR);
    }
    
    /**
     * 
     * Description: 获得当前日期月份
     * Date:2014-2-13
     * @return 
     * @return int
     */
    public static int getMonthCurDate(){
    	Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.MONTH) + 1;
    }
    
    
    /**
     * Description:根据传入的月份，返回上一个或后一个月份
     * @param flag -1为获得前一个月份，1为获得后一个月份
     * @return String
     * @throws ParseException
     */
    public static Integer getLastMonth(int flag){
    	Calendar calendar = Calendar.getInstance();   
    	calendar.add(Calendar.MONTH, flag);    //得到前一个月   
    	int month = calendar.get(Calendar.MONTH)+1;
    	return month;
    }
    
    /**
	 * 
	 * Description: 获取N天前或N天后的日期
	 * Date:2014-2-21
	 * @param dateStr
	 * @param format
	 * @param days
	 * @return 
	 * @return String
	 */
	public static String getSelLastOrNextDays(String dateStr,String format,Integer days){
		Date date = parseDate(dateStr, format);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE,days);
		Date rightDateStr = c.getTime();
		return formatDate(rightDateStr, format);
	}
    
    public static Date getLastDateOfMonth(Date date){
    	Calendar cal = Calendar.getInstance(); 
		cal.setTime(date); 
		cal.set(Calendar.DAY_OF_MONTH, 1); 
		cal.roll(Calendar.DAY_OF_MONTH, -1); 
		return cal.getTime();
		
//		public Date lastDayOfMonth2(Date date) { 
//			　　Calendar cal = Calendar.getInstance(); 
//			　　cal.setTime(date); 
//			　　int value = cal.getActualMaximum(Calendar.DAY_OF_MONTH); 
//			　　cal.set(Calendar.DAY_OF_MONTH, value); 
//			　　return cal.getTime(); 
//			　　} 
//			　　public Date lastDayOfMonth3(Date date) { 
//			　　Calendar cal = Calendar.getInstance(); 
//			　　cal.setTime(date); 
//			　　cal.set(Calendar.DAY_OF_MONTH, 1); 
//			　　cal.add(Calendar.MONTH, 1); 
//			　　cal.add(Calendar.DATE, -1); 
//			　　return cal.getTime(); 
//			　　} 
//			　　public Date lastDayOfMonth4(Date date) { 
//			　　Calendar cal = Calendar.getInstance(); 
//			　　cal.setTime(date); 
//			　　do { 
//			　　cal.add(Calendar.DATE, 1); 
//			　　} 
//			　　while (cal.get(Calendar.DATE) != 1); 
//			　　cal.add(Calendar.DATE, -1); 
//			　　return cal.getTime(); 
//			　　} 
//			　　}

    }
    
    
    /**
     * 
     * Description:
     * Date:2014-2-17
     * @param str  yyyyMMddHHmmss
     * @return 
     * @return String
     */
    public static Date timeStampToDateToStr(String str) {
        //字符串转换成时间格式
         SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
         Date date = null;
         try {
          date = format.parse(str);
         } catch (ParseException e) {
          e.printStackTrace();
         }
    //时间格式转换成字符串
         return date;
      }
    
    /**
     * 格式化日期字符串
     * @param str  yyyyMMddHHmmss
     * @return ret yyyy-MM-dd HH:mm:ss
     */
    public static String dateToStr(String str) {
         
       String ret = null;
       Date date = timeStampToDateToStr(str);
       try {
          ret = formatTime(date);
       }catch (Exception e) {
          e.printStackTrace();
       }
       //时间格式转换成字符串
       return ret;
    }
    
	public static void main(String[] args) throws ParseException {
		/*System.out.println(getFirstDayInSelMonth(2014,2));
		System.out.println(getLastDayInSelMonth(2014,2));
		System.out.println(diffDays(parseDate("2014-02-01"), parseDate("2014-02-05")));*/
		System.out.println(getLastDayInSelMonth(2015,12));
	}
}
