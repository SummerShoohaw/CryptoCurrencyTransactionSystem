/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NewUI;


import NewUI.DisplayPanel.WelcomePanel;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

/**
 *
 * @author summershoohaw
 */
public class LayoutMnger {
    private JPanel ups;
    private CardLayout layout;

    public CardLayout getLayout() {
        return layout;
    }
    
    public LayoutMnger(JPanel ups){
        this.ups = ups;
        layout = (CardLayout) ups.getLayout();
    }

    public JPanel getUps() {
        return ups;
    }
    
//    public void setTotalDefault(){
//        ups.removeAll();
//        NewJFrame.setjSplitPane2(new JSplitPane());
//        NewJFrame.resetSplitPane2();
//        ups.add(NewJFrame.getjSplitPane2());
//        layout.next(ups);
//    }
    
    public void next(JPanel next){
        ups.add(next);
        layout.next(ups);
    }
    
    public void previous(){
        layout.previous(ups);
        ups.remove(ups.getComponentCount()-1);
    }
    
    public JPanel getPreviousPanel(){
        JPanel previous = (JPanel)ups.getComponent(ups.getComponentCount()-2);
        return previous;
    }
    
    public void clearAllPanel(){
        ups.removeAll();
    }
    
}
