package 程序源码.界面;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class 按钮控件 extends JButton implements ActionListener {

    private int 按钮序号;
    private 主控面板 主控面板;

    public 按钮控件(int 按钮序号, 主控面板 主控面板) {
        this.setFont(new Font("Dialog", Font.BOLD, 30));
        this.按钮序号 = 按钮序号;
        this.主控面板 = 主控面板;
        this.addActionListener(this);
    }

    public void actionPerformed(ActionEvent ae) {
        if (主控面板.控制模型.点击移动按钮(this.按钮序号)) {
            System.out.println("You finished!");
            主控面板.设置9个按钮是否可用(false);
            主控面板.repaint();
            JOptionPane.showMessageDialog(主控面板, "恭喜，达到目标！");
        }
        主控面板.repaint();
    }
}
