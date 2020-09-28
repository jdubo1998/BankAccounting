package bankaccounting;

import java.awt.Color;
import java.util.ArrayList;

import java.math.BigDecimal;

public class BankCalculator {
    public static final String[] CALENDAR = {"January", "Febuary", "March", "April", "May", "June", "July", 
        "August", "September", "October", "November", "December"};
    private final ArrayList<Activity>[] months = new ArrayList[12];
    
    // ArrayList used for Activities to be passes to the GUI
    private final ArrayList<Activity> activities = new ArrayList();
    
    private BigDecimal balance = BigDecimal.valueOf(0.0);
    
    public BankCalculator() {
        for (int i = 0; i < 12; i++) {
            months[i] = new ArrayList();
        }
        GetFile.copyFile("");
        
        TxtIO reader = new TxtIO();
        String contents = reader.findNewestFile();
        
//        balance = reader.Balance();
        balance = BigDecimal.valueOf(964.00);
        
        String[] theseActivities = contents.split("\n");
        seperate(theseActivities);
    }
    
    /************************************************************************************
     * Creates a new Activity Object, which passes each String in the
     * String[] (activities), to create the 5 fields of an Activity: <br>  <br>
     * Date (MM/DD/YYYY, 3 separate Integers) <br>
     * Amount (Double) <br>
     * Check Number (Integer) <br>
     * Payee (String) <br>
     * Memo(String) <br>
     * @param activities
     ***********************************************************************************/
    public final void seperate(String[] activities) {
        for (int i = 1; i < activities.length; i++) {
            Activity activity = new Activity(activities[i]);
            months[activity.month - 1].add(activity);
        }
    }
    
//    public void setActivities(Activity activity) {
//        this.activities.add(activity);
//    }
//
    public ArrayList<Activity> getActivities() {
        return this.activities;
    }
    
    public ArrayList<Activity> paycheckTotals(int index) {
        this.activities.clear();
        Activity activity;
        
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < months[i].size(); j++) {
                activity = months[i].get(j);
                
                if (activity.payee.contains("PAYROLL") || activity.payee.contains("eDeposit")) {
                    if (index < 1) {
                        return this.activities;
                    } else {
                        index--;
                    }
                } else {
                    if (index < 1) {
                        this.activities.add(activity);
                    }
                }
            }
        }
        
        return this.activities;
    }
    
    public void showAll() {
        this.activities.clear();
        
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < months[i].size(); j++) {
                this.activities.add(months[i].get(j));
            }
        }
    }

    private BigDecimal total_bd(ArrayList<Activity> activities) {
        BigDecimal total = BigDecimal.valueOf(0.0);
        
        for (Activity activity : activities) {
            if (!activity.payee.equals("Card#***3369") && !activity.payee.equals("Descriptive Deposit"))
                total = total.add(BigDecimal.valueOf(activity.amount));
        }
        
        if (total.doubleValue() > 49.99) {
            BankAccountingGUI_GBL.color = Color.GREEN;
        } else if (total.doubleValue() < 15.01 && total.doubleValue() >= 0.00) {
            BankAccountingGUI_GBL.color = Color.ORANGE;
        } else if (total.doubleValue() < 0.00) {
            BankAccountingGUI_GBL.color = Color.RED;
        } else {
            BankAccountingGUI_GBL.color = Color.CYAN;
        }
        return total;
    }
    
    public ArrayList<Activity> thisMonth() {
        return this.activities;
    }
    
    public String total_str(int month) {
        BigDecimal total = total_bd(months[month]);
        
        if (total.equals(BigDecimal.valueOf(0.0))) {
            return "";
        }

        return "<html><div align=\\\"left\\\">&nbsp;&nbsp;Month: " + CALENDAR[month]
             + "<br>&nbsp;&nbsp;Total: " + total
             + "<br>&nbsp;&nbsp;Balance: " + (balance = balance.add(total));
    }
    
    public String getTotal() {
        BigDecimal total = total_bd(this.activities);
        
        if (total.equals(BigDecimal.valueOf(0.0))) {
            return "";
        }
        
        return "<html><div align=\\\"left\\\">&nbsp;&nbsp;Total: " + total
             + "<br>&nbsp;&nbsp;Balance: " + (balance = balance.add(total));
    }
    
    public ArrayList<Total> getPaychecks() {
        return getPaychecks(0);
    }
    
    public ArrayList<Total> getPaychecks(double hours) {
        double total = 0;
        ArrayList<Total> totals = new ArrayList();
        
        for (int i = 0; i < 12; i++) {
            for (Activity activity : months[i]) {
                if (!activity.payee.contains("eDeposit"))
                    total += activity.amount;
                
                if (activity.payee.equals("WHATABURGER")) {
                    totals.add(new Total(total));
                    total = 0;
                }
            }
        }
        
        totals.add(new Total(total + (hours * 8.4 * .85)));
        
//        for (int i = 0; i < totals.size(); i++) {
//            System.out.println(totals.get(i).amount);
//        }
        
        return totals;
    }
    
//    private String total_str(ArrayList<Activity> activities) {
//        BigDecimal total = total_bd(activities);
//        
//        if (total.equals(BigDecimal.valueOf(0.0))) {
//            return "";
//        }
//        
//        return "=======================================\n"
//             + "Total: " + total + "\n"
//             + "Balance: " + (balance = balance.add(total)) +"\n"
//             + "=======================================\n\n";
//    }
    
    /*=================================================================================

                    Print methods

      =================================================================================*/
    
//    @Override
//    public String toString() {
//        String monthsTotals = "";
//        for (int i = 0; i < 12; i++) {
//            monthsTotals = monthsTotals + total_str(i);
//        }
//        return monthsTotals;
//    }
//    
//    public String showAll(int j) {       
//        return "";
//    }
    
    public void month(String month) {
        int theMonth = 0;
        for (int i = 1; i < 12; i++) {
            if (CALENDAR[i - 1].equalsIgnoreCase(month)) {
                theMonth = i;
            }
        }
        
        month(theMonth - 1);
    }
    
    public void month(int month) {
        for (Activity activity : months[month]) {
            if (!activity.payee.equals("Card#***3369")) {
                this.activities.add(activity);
            }
        }
    }
    
//    public String showOver(double ammount) {
//        if (ammount > 0) {
//            ammount -= (ammount * 2);
//        }
//        
//        String over_s = "";
//        ArrayList<Activity> over = new ArrayList();     
//        
//        for (int i = 0; i < 12; i++) {
//            for (Activity activity : months[i]) {
//                if (activity.amount < ammount) {
//                    over_s = over_s + activity;
//                }
//            }
//        }
//        return over_s;
//    }
    
    /***********************************************************************************
     * @return A string of all paychecks from Whataburger.
     ***********************************************************************************/
//    public String whatachecks() {
//        String paychecks_s = "";
//        ArrayList<Activity> paychecks = new ArrayList();
//        
//        for (int i = 0; i < 12; i++) {
//            for (Activity activity : months[i]) {
//                if (activity.payee.contains("PAYROLL") || activity.payee.contains("eDeposit")) {
//                    if (activity.amount > 100f) {
//                        BankAccountingGUI_GBL.setColor(Color.green);
//                    } else if (activity.amount < 50f) {
//                        BankAccountingGUI_GBL.setColor(Color.orange);
//                    } else {
//                        BankAccountingGUI_GBL.setColor(Color.cyan);
//                    }
//                    paychecks_s = paychecks_s + activity;
//                    paychecks.add(activity);
//                }
//            }
//        }
//        
//        return paychecks_s + total_str(paychecks);
//    }
}
