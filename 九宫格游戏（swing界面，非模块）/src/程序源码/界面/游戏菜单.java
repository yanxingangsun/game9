package 程序源码.界面;

import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import 程序源码.解题.电脑解题;
import 程序源码.解题.游戏规则;
import 程序源码.解题.显示结果;
import 程序源码.解题.功能实现;

public class 游戏菜单 extends JMenuBar {

    private 主控面板 主控面板;
    private JMenu[] 主菜单组 = new JMenu[4];
    private ResourceBundle res = ResourceBundle.getBundle("资源文件/菜单", Locale.getDefault());

    public 游戏菜单(主控面板 p9) {
        this.主控面板 = p9;

        主菜单组[0] = new JMenu(res.getString("game"));//game=游戏
        主菜单组[1] = new JMenu(res.getString("edit"));//edit=设置
        主菜单组[2] = new JMenu(res.getString("solve"));//solve=解题
        主菜单组[3] = new JMenu(res.getString("help"));//help=帮助
        for (int i = 0; i < 主菜单组.length; i++) {
            this.add(主菜单组[i]);
        }

        initMenu0();
        initMenu1();
        initMenu2();
        initMenu3();
    }

    //Menu0 初始化
    JMenuItem 游戏开始菜单 = new JMenuItem(res.getString("random_start"));//random_start=随机开始
    JMenuItem 退出游戏菜单 = new JMenuItem(res.getString("exit"));//exit=退出

    public void initMenu0() {
        主菜单组[0].add(游戏开始菜单);
        主菜单组[0].addSeparator();
        主菜单组[0].add(退出游戏菜单);

        游戏开始菜单.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) { 
                int end = 主控面板.控制模型.目标按钮排列;
                int 新序列=游戏规则.随机生成一个排列();
                while(new 电脑解题(新序列, end, 主控面板.控制模型.根据字符串找空白序号(""+新序列)).解题计算(new 功能实现())==null){
                    System.out.println(新序列+"->"+主控面板.控制模型.目标按钮排列+"无解");
                    新序列=游戏规则.随机生成一个排列(); 
                }
                主控面板.控制模型.根据字串显示按钮("" + 新序列);
                主控面板.设置9个按钮是否可用(true);
                主控面板.repaint();
                电脑解题菜单.setEnabled(true);
            }
        });

        退出游戏菜单.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });
    }

    //Menu1 初始化    
    JMenuItem 设置开始菜单 = new JMenuItem(res.getString("set_init"));//set_init=设置初态...
    JMenuItem 设置目标菜单 = new JMenuItem(res.getString("set_target"));//set_target=设置目标...
    JMenuItem 查看目标菜单 = new JMenuItem(res.getString("show_target"));//show_target=查看目标...

    public void initMenu1() {
        主菜单组[1].add(设置开始菜单);
        主菜单组[1].add(设置目标菜单);
        主菜单组[1].addSeparator();
        主菜单组[1].add(查看目标菜单);

        设置开始菜单.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String start = JOptionPane.showInputDialog(主控面板,
                        res.getString("input_init"), "912345678");//input_init=请输入初始状态.如[912345678]
                if (start != null) {
                    int end = 主控面板.控制模型.目标按钮排列;
                    int 新序列=游戏规则.随机生成一个排列();
                    if(new 电脑解题(新序列, end, 主控面板.控制模型.根据字符串找空白序号(start)).解题计算(new 功能实现())==null){
                        JOptionPane.showMessageDialog(主控面板, "这个初始值不能达能目标状态，请重新设置。" );
                    }
                    else{
                        主控面板.控制模型.根据字串显示按钮(start);
                        主控面板.设置9个按钮是否可用(true);
                        主控面板.repaint();
                        电脑解题菜单.setEnabled(true);
                    }
                }
            }
        });

        设置目标菜单.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String target = JOptionPane.showInputDialog(主控面板,
                        res.getString("input_target"), "" + 主控面板.控制模型.目标按钮排列);//input_target=请输入目标状态.如[192345678]
                if (target != null) {
                    int start = 主控面板.控制模型.当前按钮排列; 
                    if(new 电脑解题(start, Integer.valueOf(target), 主控面板.控制模型.根据字符串找空白序号(""+start)).解题计算(new 功能实现())==null){
                        JOptionPane.showMessageDialog(主控面板, "这个目标状态不能达到，请重新设置。" );
                    }
                    else{
                        主控面板.控制模型.设置目标排列(target);
                        电脑解题菜单.setEnabled(true);
                    }
                }
            }
        });

        查看目标菜单.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                JOptionPane.showMessageDialog(主控面板, "" + 主控面板.控制模型.目标按钮排列);
            }
        });
    }

    //Menu2 初始化
    JMenuItem 电脑解题菜单 = new JMenuItem(res.getString("compute_think"));//compute_think=电脑解题

    public void initMenu2() {
        主菜单组[2].add(电脑解题菜单);

        电脑解题菜单.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                int start = 主控面板.控制模型.当前按钮排列;
                int end = 主控面板.控制模型.目标按钮排列;
                if (end != start) {
                    电脑解题 r = new 电脑解题(start, end, 主控面板.控制模型.空白按钮序号);

                    long t1 = System.currentTimeMillis();
                    LinkedList<Integer> trace = r.解题计算(new 功能实现());
                    long t2 = System.currentTimeMillis();

                    if (trace != null) {
                        int k = JOptionPane.showConfirmDialog(主控面板,
                                res.getString("complete_detail") + (t2 - t1) + "ms\n"
                                +//complete_detail=解题完成!\n用时：
                                res.getString("all") + (trace.size() - 1) + res.getString("step") + "\n"
                                +//all=共 step=步。
                                res.getString("computer_run"), //computer_run=(看电脑走请点[是])
                                res.getString("complete_title"), JOptionPane.YES_NO_OPTION);//complete_title=解题完成
                        if (k == JOptionPane.OK_OPTION) {
                            new 显示结果(trace, 主控面板).start();
                            电脑解题菜单.setEnabled(false);
                            主控面板.设置9个按钮是否可用(false);
                            主控面板.repaint();
                        }
                    } else {
                        JOptionPane.showMessageDialog(主控面板, res.getString("no_result") + (t2 - t1) + "ms");//no_result=该题无解!\n用时：
                        电脑解题菜单.setEnabled(false);
                    }
                }
            }
        });
    }

    //Menu3 初始化
    JMenuItem 游戏帮助菜单 = new JMenuItem(res.getString("about"));//about=关于...

    public void initMenu3() {
        主菜单组[3].add(游戏帮助菜单);

        游戏帮助菜单.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                JOptionPane.showMessageDialog(主控面板, res.getString("game_9_box"));//game_9_box=九宫格游戏 V1.0\n[作者：晏新钢]
            }
        });
    }
}
