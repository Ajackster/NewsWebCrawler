import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The NewsWebCrawler traverses major news websites and scrapes it for articles.
 * The articles content are stored in a text (.txt) file
 *
 * @author Andrew Jackson
 * @version 1.0
 * @since 2015-04-21
 */
public class DateFinder
{
    private String day;
    private String yesterday;
    private String month;
    private String year;
    private String fileDate;

    public DateFinder() {
        this.day = null;
        this.yesterday = null;
        this.month = null;
        this.year = null;
        this.fileDate = null;
    }

    /*
      getYesterday() is used when the website hasn't updated the articles for
      the day, so the program checks if the headline article is from yesterday.
    */
    public String getYesterday() {
        DateFormat dayFormat = new SimpleDateFormat("dd");
        Date dayDate = new Date();
        String day = dayFormat.format(dayDate);
        int yesterdayDate = Integer.parseInt(day) - 1;
        this.yesterday = Integer.toString(yesterdayDate);
        return this.yesterday;
    }

    public String getDay() {
        DateFormat dayFormat = new SimpleDateFormat("dd");
        Date dayDate = new Date();
        this.day = dayFormat.format(dayDate);
        return this.day;
    }

    public String getMonth() {
        DateFormat monthFormat = new SimpleDateFormat("MM");
        Date monthDate = new Date();
        this.month =  monthFormat.format(monthDate);
        return this.month;
    }

    public String getYear() {
        DateFormat yearFormat = new SimpleDateFormat("MM");
        Date yearDate = new Date();
        this.year = yearFormat.format(yearDate);
        return this.year;
    }

    /*
      getFileDate() is used for getting date in proper format for the filename
    */
    public String getFileDate() {
        DateFormat dateFormatForFile = new SimpleDateFormat("yyyyMMdd");
        Date dateForFile = new Date();
        this.fileDate = dateFormatForFile.format(dateForFile);
        return this.fileDate;
    }
}
