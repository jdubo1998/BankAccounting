package bankaccounting;

import java.awt.Color;

public class BNKText extends JGradientButton {
    public BNKText(Activity activity, Color c) {
        super(activity.toString(), c);
        
        alignLeft();
    }
    
    public BNKText(Total total, Color c) {
        super(total.toString(), c);
        
        alignLeft();
    }
    
    
    public static BNKText getTotals(Activity[] activities, Color c) {
        String currActivity = null;
        
        return new BNKText(currActivity, c);
    }
    
//    public BNKText(Activity[] activities, Color c) {
//        
//    }
    
    public BNKText(String content, Color c) {
        super(content, c);
        alignLeft();
    }
    
    private void alignLeft() {
        this.setHorizontalAlignment(LEFT);
    }
}
