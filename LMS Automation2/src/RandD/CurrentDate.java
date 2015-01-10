package RandD;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
public class CurrentDate
{
	public static void main(String[] args) throws java.text.ParseException
	{
		DateFormat FulldateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		// get a calendar instance, which defaults to "now"
		Calendar calendar = Calendar.getInstance();
		// add 11 days to the date/calendar
		calendar.add(Calendar.DAY_OF_YEAR, 11);
		// now get after 11 days date
		Date future = calendar.getTime();
		String Req_date = FulldateFormat.format(future);
		System.out.println("Future Date --> after 11 days from Today is :    " + Req_date);
	}
}
