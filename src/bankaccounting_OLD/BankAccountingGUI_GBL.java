package bankaccounting;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class BankAccountingGUI_GBL extends JFrame implements ActionListener {
    public static Color color = Color.CYAN;
    
    int textpanelsize = 5;
    boolean running = true;
    private final Date DATE = new Date();
    final BankCalculator bnkclc = new BankCalculator();
    
    final JPanel RIGHTPANEL = new JPanel();
    final JPanel LEFTPANEL = new JPanel(new GridLayout(0, 1));
    
    JPanel overview = new JPanel(new GridBagLayout());
    JPanel filterButtons = new JPanel(new GridBagLayout());
    JPanel customFilter = new JPanel(new GridBagLayout());
    JPanel modPanel = new JPanel(new GridBagLayout());
//    JPanel maintextpanel = new JPanel();
    
    JButton allTrans, thisMonth, certMonth, monthsTotals, paycheckTotals;
    JButton addActivity;
    JTextField addActivityAmm, addActivityPay;
    JButton ammountRange;
    JTextField minAmmount, maxAmmount;
    
//    JTextArea text1 = new JTextArea(47, 25);
//    JScrollPane spane1 = new JScrollPane(text1);
    
    public static BankAccountingGUI_GBL run() {
        return new BankAccountingGUI_GBL();
    }
    
    public static void setColor(Color col) {
        color = col;
    }
    
    public BankAccountingGUI_GBL() {
        super("Bank Accounter");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setSize(600, 800);
        super.setResizable(false);
        
        initUI();
        
        while (running) {
            update();
            super.setVisible(true);
        }
    }
    
    private void update() {
        RIGHTPANEL.setPreferredSize(new Dimension(100, textpanelsize));
    }

    private void initUI() {
        super.setLayout(new GridLayout(1, 2));
        
//        spane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//        text1.setEditable(false);
        
        allTrans = new JButton("All Transactions");
        allTrans.addActionListener(this);
        thisMonth = new JButton(BankCalculator.CALENDAR[DATE.getMonth()]);
        thisMonth.addActionListener(this);
        monthsTotals = new JButton("Monthly Totals");
        monthsTotals.addActionListener(this);
        paycheckTotals = new JButton("Paycheck Totals");
        paycheckTotals.addActionListener(this);
        
//        allTrans = new JButton("A");
//        allTrans.addActionListener(this);
//        thisMonth = new JButton("J");
//        thisMonth.addActionListener(this);
//        monthsTotals = new JButton("M");
//        monthsTotals.addActionListener(this);
//        paycheckTotals = new JButton("P");
//        paycheckTotals.addActionListener(this);
        
        addActivity = new JButton("Calculate");
        addActivity.addActionListener(this);
        addActivityAmm = new JTextField("");
        addActivityPay = new JTextField("");
        
        ammountRange = new JButton("In Range");
        minAmmount = new JTextField("");
        maxAmmount = new JTextField("");
        
//        filterButtons.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        GridBagConstraints c = new GridBagConstraints();
        
        /* Overview */
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.PAGE_START;
        c.weightx = 1;
        c.insets = new Insets(5, 5, 5, 5);
        c.ipady = 20;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.weighty = 1;
        overview.add(new Label("Overview", Label.CENTER), c);
        
        /* Filters */
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.PAGE_START;
//        c.weightx = 1;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        filterButtons.add(new Label("Filters", Label.CENTER), c);
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;
        filterButtons.add(allTrans, c);
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = 1;
        filterButtons.add(thisMonth, c);
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 2;
        filterButtons.add(monthsTotals, c);
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = 2;
        filterButtons.add(paycheckTotals, c); // END
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 3;
        filterButtons.add(new Label(""), c);
        
        /* Modify */
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.PAGE_START;
        c.weightx = 1;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 0;
        modPanel.add(new Label("Modify", Label.CENTER), c);
        c.weightx = 0;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;
        modPanel.add(new Label("Payee: ", Label.CENTER), c);
        c.gridwidth = 2;
        c.gridx = 1;
        c.gridy = 1;
        modPanel.add(addActivityPay, c); 
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 2;
        modPanel.add(new Label("Ammount: ", Label.CENTER), c);
        c.gridwidth = 2;
        c.gridx = 1;
        c.gridy = 2;
        modPanel.add(addActivityAmm, c); // END 
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 3;
        modPanel.add(new Label(""), c);
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 4;
        modPanel.add(addActivity, c);
        
        /* Custom */
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.PAGE_START;
        c.weightx = 1;
        c.weighty = 1;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 4;
        customFilter.add(new Label("Custom", Label.CENTER), c);
        c.weightx = 0;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;
        customFilter.add(new Label("Over: "));
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = 1;
        customFilter.add(minAmmount);
        c.gridwidth = 1;
        c.gridx = 2;
        c.gridy = 1;
        customFilter.add(new Label("Under: "));
        c.gridwidth = 1;
        c.gridx = 3;
        c.gridy = 1;
        customFilter.add(maxAmmount);
        c.gridwidth = 4;
        c.gridx = 0;
        c.gridy = 2;
        customFilter.add(ammountRange);
        c.gridwidth = 4;
        c.gridx = 0;
        c.gridy = 3;
        customFilter.add(new JLabel(""));

//        c.fill = GridBagConstraints.HORIZONTAL;
//        c.weightx = 1;
//        c.gridwidth = 2;
//        c.gridx = 0;
//        c.gridy = 1;
//        customFilter.add(new JLabel("TEST", JLabel.CENTER), c);
//        c.gridwidth = 1;
//        c.gridx = 2;
//        c.gridy = 1;
//        customFilter.add(new JTextField(""), c);
//        c.gridwidth = 2;
//        c.gridx = 1;
//        c.gridy = 1;
//        customFilter.add(new JLabel("TEST", JLabel.CENTER), c);
//        c.gridwidth = 2;
//        c.gridx = 2;
//        c.gridy = 1;
//        customFilter.add(new JLabel("TEST", JLabel.CENTER), c);
        
               
        LEFTPANEL.add(overview);
        LEFTPANEL.add(filterButtons);
        LEFTPANEL.add(modPanel);
        LEFTPANEL.add(customFilter);
//        buttonpanel.add(AllTrans);
//        buttonpanel.add(ThisMonth);
//        buttonpanel.add(MonthsTotals);
//        buttonpanel.add(PaycheckTotals);
        
//        buttonpanel.add(new JButton("Over: "));
        
        //================================================================
        // --- Text Panel ---
        //================================================================
        
        RIGHTPANEL.setPreferredSize(new Dimension(100, textpanelsize));
        JScrollPane textscroller = new JScrollPane(RIGHTPANEL);
        
        textscroller.setPreferredSize(new Dimension(300, 800));
        
        //================================================================
        // --- Button Panel ---
        //================================================================
        
        super.add(LEFTPANEL);
        
        super.add(textscroller);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Color c;
        
        //String month = BankCalculator.calendar[date.getMonth()] + "";
        
        ArrayList<Activity> transactions;
        ArrayList<Total> totals;
        
        if (e.getActionCommand().equals("All Transactions")) {
            System.out.println("LOG: All transactions activated");

            bnkclc.showAll();
            transactions = bnkclc.getActivities();

            for (int i = 0; i < transactions.size(); i++) {
                Activity activity = transactions.get(i);
                if (activity.amount < -20.0) {
                    c = Color.RED;
                } else if (activity.payee.contains("Whataburger") || 
                    activity.payee.contains("Deposit")){
                    c = Color.GREEN;
                } else if (activity.amount > 20.0) {
                    c = Color.GREEN;
                } else {
                    c = Color.ORANGE;
                }

                textpanelsize += 105;
                BNKText bnktext = new BNKText(activity, c);
                bnktext.setPreferredSize(new Dimension(300 ,100));
                RIGHTPANEL.add(bnktext);
            }
            /* Transactions for this Month */
        } else if (e.getActionCommand().equals(BankCalculator.CALENDAR[DATE.getMonth()])){
            System.out.println("LOG: Activity for " + BankCalculator.CALENDAR[DATE.getMonth()] 
                    + " activated");

            bnkclc.month(BankCalculator.CALENDAR[DATE.getMonth()]);
            transactions = bnkclc.getActivities();

            for (int i = 0; i < transactions.size(); i++) {
                Activity activity = transactions.get(i);
                if (activity.amount < -20.0) {
                    c = Color.RED;
                } else if (activity.payee.contains("Whataburger") || 
                    activity.payee.contains("Deposit")){
                    c = Color.GREEN;
                } else if (activity.amount > 20.0) {
                    c = Color.GREEN;
                } else {
                    c = Color.ORANGE;
                }

                textpanelsize += 105;
                BNKText bnktext = new BNKText(activity, c);
                bnktext.setPreferredSize(new Dimension(300 ,100));
                RIGHTPANEL.add(bnktext);
            }
            /* Monthly totals */
        } else if (e.getActionCommand().equals("Monthly Totals")) {
            System.out.println("LOG: Monthly totals activated");

            for (int i = 0; i < 12; i++) {
                if (!bnkclc.total_str(i).equals("")) {
                    textpanelsize += 105;
                    BNKText bnktext = new BNKText(bnkclc.total_str(i), color);
                    bnktext.setPreferredSize(new Dimension(300 ,100));
                    RIGHTPANEL.add(bnktext);
                }
            }
        } else if (e.getActionCommand().equals("Paycheck Totals")) {
            System.out.println("LOG: Paycheck totals activated");
            
//            bnkclc.getPaychecks();
            totals = bnkclc.getPaychecks();
            
            for (int i = 0; i < totals.size(); i++) {
                if (totals.get(i).amount < -0.0) {
                    c = Color.RED;
                } else if (totals.get(i).amount > 20.0) {
                    c = Color.GREEN;
                } else {
                    c = Color.ORANGE;
                }

                textpanelsize += 105;
                BNKText bnktext = new BNKText(totals.get(i), c);
                bnktext.setPreferredSize(new Dimension(300 ,100));
                RIGHTPANEL.add(bnktext);
            }
//            int index = 0;
//            
//            for (; index < bnkclc.getPaychecks(); index++) {
//                transactions = bnkclc.paycheckTotals(index);
//                textpanelsize += 105;
//                BNKText bnktext = new BNKText(bnkclc.getTotal() + "<br>" + index, color);
//                bnktext.setPreferredSize(new Dimension(300 ,100));
//                textpanel.add(bnktext);
//            }
        } else if (e.getActionCommand().equals("Calculate")) {
            System.out.println("LOG: Modification calculation activated");
            
            String payee = addActivityPay.getText();
            double ammount = 0;
            String activity;
            addActivityAmm.setText(addActivityAmm.getText().replaceAll("[^0-9.-]", ""));
            
            if (!addActivityAmm.getText().equals("")) {
                ammount = Double.parseDouble(this.addActivityAmm.getText());
            } else {
                System.out.println("ERROR: Invalid decimal input.");
            }
            
            activity = String.format("\"%02d/%02d/%04d\",\"%.2f\",\"\",\"%s\",\"\"", 
                    DATE.getMonth() + 1, DATE.getDate(), DATE.getYear() + 1900, ammount, payee);
            TxtIO.write(activity);
        } else {
            System.out.println("ERROR: Feature not yet implemented.");
        }
    }
}


class JGradientButton extends JButton {
    Color c;
    
    public JGradientButton(String text, Color c){
        super(text);
        this.c = c;
        setContentAreaFilled(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        final Graphics2D g2 = (Graphics2D) g.create();
        g2.setPaint(new GradientPaint(
                new Point(0, 0), 
                Color.WHITE, 
                new Point(0, getHeight()), 
                this.c.darker()));
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.dispose();

        super.paintComponent(g);
    }
}
