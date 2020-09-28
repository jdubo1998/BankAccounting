package bankaccounting;

import java.util.ArrayList;
import java.math.BigDecimal;

public class BankCalculator {
    private final String[] calendar = {"January", "Febuary", "March", "April", "May", "June", "July", 
        "August", "September", "October", "November", "December"};
    private final ArrayList<Activity>[] months = new ArrayList[12];
    private double balance = 0.0;
    
    public BankCalculator(){
        for (int i = 0; i < 12; i++) {
            months[i] = new ArrayList();
        }
    }
    
    public void seperate(String[] activities) {
        for (int i = 1; i < activities.length; i++) {
            Activity activity = new Activity(activities[i]);
            months[activity.month - 1].add(activity);
        }
    }
    
    private double total_D(int month) {
        double total = 0.0;
        
        for (Activity activity : months[month]) {
            total += activity.ammount;
        }
        
        return total;
    }
    
    private String total_Str(int month) {
        double total = total_D(month);
        
        if (total == 0.0) {
            return "";
        }
        
        return "=================================================================\n"
             + "Month: " + calendar[month] + "\n"
             + "Total: " + total + "\n"
             + "Balance: " + (balance += total) +"\n"
             + "=================================================================\n\n";
    }
    
    @Override
    public String toString() {
        String monthsTotals = "";
        for (int i = 0; i < 12; i++) {
            monthsTotals = monthsTotals + total_Str(i);
        }
        return monthsTotals;
    }
    
    public String toString(boolean showAll) {
        String allActivities = "";
        
        for (int i = 0; i < 12; i++) {
            allActivities = allActivities + toString(i);
        }
        
        return allActivities;
    }
    
    public String toString(String month) {
        int theMonth = 0;
        
        for (int i = 1; i < 12; i++) {
            if (calendar[i - 1].equalsIgnoreCase(month)) {
                theMonth = i;
            }
        }
        
        return toString(theMonth);
    }
    
    public String toString(int month) {
        String activities = "";
        
        for (Activity activity : months[month]) {
            activities = activities + activity;
        }
        
        activities = activities + total_Str(month);
        
        return activities;
    }
}


class Activity {
    final int month, day, year;
    final double ammount;
    final int checknum;
    final String payee, memo;
    
    public Activity() {
        this.month = 0;
        this.year = 0;
        this.day = 0;
        this.ammount = 0.0;
        this.checknum = 0;
        this.payee = "";
        this.memo = "";
    }
    
    public Activity(String activity) {
        activity = activity.replaceAll("\"", "");
        
        activity = activity + " ";
        
        String[] fields = activity.split(",");
        
        String[] date = fields[0].split("/");
        this.month = Integer.parseInt(date[0]);
        this.day = Integer.parseInt(date[1]);
        this.year = Integer.parseInt(date[2]);
        
        this.ammount = Double.parseDouble(fields[1]);
        
        if (!"".equals(fields[2]))
            this.checknum = Integer.parseInt(fields[2]);
        else
            this.checknum = 0;
        
        this.payee = fields[3];
        
        this.memo = fields[4];
    }
    
    public Activity(int month, int day, int year, int ammount, int CheckNum, String payee, String memo) {
        this.month = month;
        this.day = day;
        this.year = year;
        this.ammount = ammount;
        this.checknum = CheckNum;
        this.payee = payee;
        this.memo = memo;
    }
    
    @Override
    public String toString() {
        return "-----------------------------------------------------------------\n"
             + "Date: " + month + "/" + day + "/" + year + "\n"
             + "Ammount: " + ammount + "\n"
             + "Check Number: " + checknum + "\n"
             + "Payee: " + payee + "\n"
             + "Memo: " + memo + "\n"
             + "-----------------------------------------------------------------\n";
    }
}
