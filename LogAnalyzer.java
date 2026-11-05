
/**
 * Read web server data and analyse hourly access patterns.
 * 
 * @author David J. Barnes and Michael Kölling.
 * @version 7.0
 */
public class LogAnalyzer
{
    public static final int HOURS_PER_DAY = 24;
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    // Use a LogfileReader to access the data.
    private LogfileReader reader;

    /**
     * Create an object to analyze hourly web accesses.
     */
    public LogAnalyzer()
    { 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[HOURS_PER_DAY];
        // Create the reader to obtain the data.
        reader = new LogfileReader();
    }
    
    public LogAnalyzer(String filename)
    { 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[HOURS_PER_DAY];
        // Create the reader to obtain the data.
        reader = new LogfileReader(filename);
    }

    /**
     * Analyze the hourly access data from the log file.
     */
    public void analyzeHourlyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int hour = entry.getHour();
            hourCounts[hour]++;
        }
    }

    /**
     * Print the hourly counts.
     * These should have been set with a prior
     * call to analyzeHourlyData.
     */
    public void printHourlyCounts()
    {
        System.out.println("Hr: Count");
        int hour = 0;
        while (hour < hourCounts.length){
            System.out.println(hour + " " + hourCounts[hour]);
            hour++;
        }
    }
    
    /**
     * Print the lines of data read by the LogfileReader
     */
    public void printData()
    {
        reader.printData();
    }
    
    /**
     * Returns the number of accesses recorded in the log file.
     */
    public int numberOfAccesses(){
        int total = 0;
        for(int hour = 0; hour < hourCounts.length; hour++){
            total += hourCounts[hour];
        }
        return total;
    }
    
    /**
     * Returns the busiest hour.
     */
    public int busiestHour(){
        int busiestHour = 0;
        for(int hour = 0; hour < hourCounts.length; hour++){
            if (hourCounts[busiestHour] < hourCounts[hour]){
                busiestHour = hour;
            }
        }
        return busiestHour;
    }
    
    /**
     * Returns the least busy hour.
     */
    public int quietestHour(){
        int quietestHour = 0;
        for(int hour = 0; hour < hourCounts.length; hour++){
            if (hourCounts[quietestHour] > hourCounts[hour]){
                quietestHour = hour;
            }
        }
        return quietestHour;
    }
}
