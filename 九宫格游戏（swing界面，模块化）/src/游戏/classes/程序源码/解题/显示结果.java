package 程序源码.解题;



import 程序源码.界面.主控面板;
import java.util.*;
import javax.swing.*;

public class 显示结果 extends Thread{
    LinkedList<Integer> 解题步骤;
    主控面板 主控面板;

    public 显示结果(LinkedList<Integer> 解题步骤,主控面板 主控面板){
        this.解题步骤=解题步骤;
        this.主控面板=主控面板;
    }

    @Override
    public void run(){
        for(int i=解题步骤.size()-1;i>=0;i--){
            主控面板.控制模型.根据字串显示按钮(""+解题步骤.get(i));
            主控面板.repaint();
            try{Thread.sleep(400);}
            catch(Exception e){}
        }
        JOptionPane.showMessageDialog(主控面板,"完成");
    }
}
