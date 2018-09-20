/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import java.util.ArrayList;
import javax.swing.JButton;

/**
 *
 * @author lm
 */
public class MineButton extends JButton{
    static ArrayList<MineButton> coinList = new ArrayList<MineButton>();
    private int coordinatorX;
    private int coordinatorY;
    private String coin;

    
    private boolean isSelected = false;

    public boolean getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public int getCoordinatorX() {
        return coordinatorX;
    }

    public void setCoordinatorX(int coordinatorX) {
        this.coordinatorX = coordinatorX;
    }

    public int getCoordinatorY() {
        return coordinatorY;
    }

    public void setCoordinatorY(int coordinatorY) {
        this.coordinatorY = coordinatorY;
    }
    public String value="0";

//    private MineButton(String name){
//        super();
//        this.coin = name;
//    }
    
//    public static MineButton getMineButton(String name){
//        for(MineButton m: coinList){
//            if(m.getCoin() == name){
//                return m;
//            }
//        }
//        return new MineButton(name);
//    }
    public MineButton(int x, int y){
        super();
        this.coordinatorX = x;
        this.coordinatorY = y;
    }
    
}
