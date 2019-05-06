package 程序源码;

import 程序源码.界面.主控面板;
import 程序源码.界面.游戏菜单;
import javax.swing.*;
import java.awt.*;

public class 启动 extends JFrame {

    public 启动() {
        super();
        init();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        this.setTitle("九宫格");
        try {
            //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); 
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                System.out.println(info.getName());
                
                //if ("Windows".equals(info.getName())) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    SwingUtilities.updateComponentTreeUI(this);
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {ex.printStackTrace();        
        } catch (InstantiationException ex) {ex.printStackTrace();
        } catch (IllegalAccessException ex) {ex.printStackTrace();
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {ex.printStackTrace();}
        
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void init() {
        this.setSize(300, 350);
        this.setResizable(false);

        this.setLayout(new GridLayout(1, 1));
        主控面板 p9 = new 主控面板();
        this.setJMenuBar(new 游戏菜单(p9));
        this.add(p9);
    }

    public static void main(String args[]) {
        new 启动();
    }
}
