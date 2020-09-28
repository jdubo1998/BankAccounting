package bankaccounting;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        BankCalculator bnkclc = new BankCalculator();
        
        TxtIO reader = new TxtIO();
        String contents = reader.findNewestFile();
        
        String[] activities = contents.split("\n");
        
        bnkclc.seperate(activities);
        
        BankAccountingGUI_GBL.run();
//        System.out.println(bnkclc);
    }
}
