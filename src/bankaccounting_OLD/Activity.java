

 bankaccounting;

import java.time.LocalDate;
import static bankaccounting.BankCalculator.CALENDAR;

class Total {
    double amount;
    String payee, memo;
    
    double balance;
    
    protected Total() {
        amount = 0;
    }
    
    protected Total(double amount) {
        this.amount = amount;
    }
    
    @Override
    public String toString() {
        int month = LocalDate.now().getMonthValue();
        
        return "<html><div align=\\\"left\\\">&nbsp;&nbsp;Month: " + CALENDAR[month]
             + "<br>&nbsp;&nbsp;Total: " + String.format("%.2f", amount)
             + "<br>&nbsp;&nbsp;Balance: " + String.format("%.2f", balance);
    }
}

public class Activity extends Total {
    final int month, day, year;
    final int checknum;

    public Activity(String activity) {
        super();
        activity = activity.replaceAll("\"", "");

        activity = activity + " ";

        String[] fields = activity.split(",");

        String[] date = fields[0].split("/");
        this.month = Integer.parseInt(date[0]);
        this.day = Integer.parseInt(date[1]);
        this.year = Integer.parseInt(date[2]);
        
        this.amount = Double.parseDouble(fields[1]);

        if (!"".equals(fields[2]))
            this.checknum = Integer.parseInt(fields[2]);
        else
            this.checknum = 0;

        String oldPayee = fields[3];
        String tempPayee = "";

        while (oldPayee.length() > 25) {
            tempPayee = tempPayee + oldPayee.substring(0, 25) + "\n              ";
            oldPayee = oldPayee.substring(25);
        }

        tempPayee = tempPayee + oldPayee;

        this.payee = tempPayee;

        this.memo = fields[4];
    }

    public Activity(int month, int day, int year, double amount, int CheckNum, String payee, String memo) {
        super(amount);
        this.month = month;
        this.day = day;
        this.year = year;
        this.checknum = CheckNum;
        this.payee = payee;
        this.memo = memo;
    }

    public String getDate() {
        return "Date: " + this.month + "/" + this.day + "/" + this.year;
    }

    public String getAmmount() {
        return "Ammount: " + String.format("%.2f", this.amount);
    }

    public String getPayee() {
        return "Payee: " + this.payee;
    }

    public String output() {
        String checknum = "", memo = "";

        if (this.checknum != 0) {
            memo = "Check Number: " + this.checknum + "\n";
        }

        if (!this.memo.equals(" ")) {
            memo = "Memo: " + this.memo + "\n";
        }

        return "-----------------------------------------------------------------\n"
             + "Date: " + month + "/" + day + "/" + year + "\n"
             + "Ammount: " + amount + "\n"
             + checknum
             + "Payee: " + payee + "\n"
             + memo
//                 + "Balance: " + balance + "\n"
             + "-----------------------------------------------------------------\n";
    }

    @Override
    public String toString() {
        String checknum = "", memo = "";

        if (this.checknum != 0) {
            memo = "Check Number: " + this.checknum + "\n";
        }

        if (!this.memo.equals(" ")) {
            memo = "Memo: " + this.memo + "\n";
        }

        return "<html><div align=\"left\">&nbsp;&nbsp;" + getPayee() + 
          "<br>&nbsp;&nbsp;" + getAmmount() + 
          "<br>&nbsp;&nbsp;" + getDate() + "</div></html>";
    }
}