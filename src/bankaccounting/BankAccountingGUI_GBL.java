package bankaccounting;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class BankAccountingGUI_GBL extends JFrame implements ActionListener {
    GridBagConstraints gbc = new GridBagConstraints();
    
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    
    JButton button1;
    JButton button2;
    JButton button3;
    
    JTextArea text1 = new JTextArea(47, 25);
    
    JList list1 = new JList(new DefaultListModel());
    
    JTextField field1 = new JTextField(50);
    
    public static void run() {
        new BankAccountingGUI_GBL();
    }
    
    public BankAccountingGUI_GBL() {
        super("Bank Accounter");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setSize(600, 800);
        super.setResizable(false);
        
        initUI();
        
        super.setVisible(true);
    }

    private void initUI() {
        super.setLayout(new GridLayout(1, 2));
        panel1.setLayout(new GridBagLayout());
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 0.5;
        gbc.weightx = 0.5;
        
        button1 = new JButton("Button 1");
        button1.addActionListener(this);
        
        button2 = new JButton("Button 2");
        button2.addActionListener(this);
        
        panel1.add(button1, gbc);

        gbc.weighty = 0.5;
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel1.add(button2, gbc);
        
//        gbc.gridx = 2;
//        gbc.gridy = 0;
//        gbc.gridheight = 2;
        panel2.add(text1, gbc);

        gbc.gridx = 0;
        gbc.gridy = 0;
//        super.add(panel1, gbc);

        panel1.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        panel2.setBorder(BorderFactory.createLineBorder(Color.RED));
        
        super.add(panel1, 0);
        super.add(panel2, 1);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            default:
                System.out.println("ERROR: Feature not yet implemented.");
        }
    }
}
