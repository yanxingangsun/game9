package 程序源码.界面;

import 程序源码.模型.控制模型;
import javax.swing.*;
import java.awt.*;

public class 主控面板 extends JPanel {

    public 控制模型 控制模型 = new 控制模型();
    public 按钮控件[] 按钮数组 = new 按钮控件[9];

    private void 准备9个按钮控件() {
        for (int i = 0; i < 按钮数组.length; i++) {
            按钮数组[i] = new 按钮控件(i, this);
            this.add(按钮数组[i]);
        }
    }

    public 主控面板() {
        this.setLayout(new GridLayout(3, 3));
        this.准备9个按钮控件();
    }

    public void paint(Graphics g) {
        super.paint(g);
        for (int i = 0; i < 按钮数组.length; i++) {
            按钮数组[i].setText(控制模型.根据序号找到按钮(i));
        }
    } 
    
    public void 设置9个按钮是否可用(boolean flag) {
        for (int i = 0; i < 按钮数组.length; i++) {
            按钮数组[i].setEnabled(flag);
        }
    }
}
