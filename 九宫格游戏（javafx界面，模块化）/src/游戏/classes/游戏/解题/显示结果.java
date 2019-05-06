package 游戏.解题;

import 游戏.界面.控制器;
import java.util.*; 
import javafx.application.Platform; 
import javafx.scene.control.Alert; 

public class 显示结果 extends TimerTask{ 
    LinkedList<Integer> 解题步骤;
    控制器 主控面板;
    Timer timer;
    
    public 显示结果(LinkedList<Integer> 解题步骤, 控制器 主控面板) {
        this.解题步骤 = 解题步骤;
        this.主控面板 = 主控面板; 
        timer = new Timer();
        timer.schedule(this, 0, 500);//定时器的延迟时间及间隔时间   
    } 

    @Override
    public void run() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() { 
                if(解题步骤.size()<=0){
                    timer.cancel();
                    Alert information = new Alert(Alert.AlertType.INFORMATION, "电脑解题完成！");
                    information.setTitle("结束"); //设置标题，不设置默认标题为本地语言的information
                    information.setHeaderText("演示结束"); //设置头标题，默认标题为本地语言的information 
                    information.showAndWait();
                }
                else{
                    主控面板.根据字串排列按钮("" + 解题步骤.pollLast());
                    主控面板.刷新按钮排列();  
                } 
            } 
        }); 
    }
}
