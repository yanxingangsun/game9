/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package 游戏.界面;

import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button; 
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import 游戏.模型.控制模型;
import 游戏.解题.功能实现;
import 游戏.解题.显示结果;
import 游戏.解题.游戏规则;
import 游戏.解题.电脑解题;

/**
 * FXML Controller class
 *
 * @author yanxi
 */
public class 控制器 implements Initializable {
    public 控制模型 控制模型 = new 控制模型();
    public List<Button> 按钮数组 = new ArrayList();
    
    @FXML Button b1;
    @FXML Button b2;
    @FXML Button b3;
    @FXML Button b4;
    @FXML Button b5;
    @FXML Button b6;
    @FXML Button b7;
    @FXML Button b8;
    @FXML Button b9;
    @FXML MenuItem 电脑解题菜单;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        准备9个按钮控件();
    }    
    
    private void 准备9个按钮控件() {
        按钮数组.add(b1);
        按钮数组.add(b2);
        按钮数组.add(b3);
        按钮数组.add(b4);
        按钮数组.add(b5);
        按钮数组.add(b6);
        按钮数组.add(b7);
        按钮数组.add(b8);
        按钮数组.add(b9); 
        
        for(Button b:按钮数组)
            b.setOnAction(e->{ 
                boolean 成功=控制模型.点击移动按钮(按钮数组.indexOf(b));
                刷新按钮排列();
                if (成功) { 
                    设置9个按钮不可用(true);   
                    Alert information = new Alert(Alert.AlertType.INFORMATION,"恭喜，达到目标！");
                    information.setTitle("完成"); //设置标题，不设置默认标题为本地语言的information
                    information.setHeaderText("成功"); //设置头标题，默认标题为本地语言的information 
                    information.showAndWait();
                }  
            });
    } 
    
    public void 刷新按钮排列(){
        for (int i = 0; i < 按钮数组.size(); i++) {
            按钮数组.get(i).setText(控制模型.根据序号找到按钮(i));
        }
    } 
    
    //只显示，不改变当前排列，只用于显示目标
    public void 根据字串显示按钮(String str) { 
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '9') 
                按钮数组.get(i).setText(""); 
            else
                按钮数组.get(i).setText(""+str.charAt(i));
        } 
    }
    
    public void 设置9个按钮不可用(boolean flag) {
        for (int i = 0; i < 按钮数组.size(); i++) {
            按钮数组.get(i).setDisable(flag);
        }
    } 
    
    public void 根据字串排列按钮(String str) {
        控制模型.当前按钮排列 = Integer.parseInt(str);
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '9') {
                控制模型.空白按钮序号 = i;
                break;
            }
        }
        
        刷新按钮排列();
    } 
    
    @FXML
    public void 菜单_随机开始(){ 
        int end = 控制模型.目标按钮排列;
        int 新序列=游戏规则.随机生成一个排列();
        while(new 电脑解题(新序列, end, 控制模型.根据字符串找空白序号(""+新序列)).解题计算(new 功能实现())==null){
            System.out.println(新序列+"->"+控制模型.目标按钮排列+"无解");
            新序列=游戏规则.随机生成一个排列(); 
        } 
        根据字串排列按钮("" + 新序列); 
        设置9个按钮不可用(false); 
        电脑解题菜单.setDisable(false);
    }
    
    @FXML
    public void 菜单_退出(){
        System.exit(0);
    }
    
    @FXML
    public void 菜单_设置初态(){
        TextInputDialog dialog = new TextInputDialog("912345678");
        dialog.setTitle("起始排列");
        dialog.setHeaderText("请输入游戏的起始排列:\n"
                + "注：9代表空白，其它数字是1-8。");
        dialog.setContentText("输入：");

        // 传统的获取输入值的方法
        Optional result = dialog.showAndWait();
        if (result.isPresent()) { 
            int 新序列=Integer.valueOf(result.get().toString());
            int end = 控制模型.目标按钮排列;
            if(new 电脑解题(新序列, end, 控制模型.根据字符串找空白序号(""+新序列)).解题计算(new 功能实现())==null){
                Alert information = new Alert(Alert.AlertType.INFORMATION,"这个初始值不能达到目标状态，请重新设置。");
                information.setTitle("初始值无效"); //设置标题，不设置默认标题为本地语言的information
                information.setHeaderText("初始值无解："); //设置头标题，默认标题为本地语言的information 
                information.showAndWait();
            }else{
                根据字串排列按钮("" + result.get()); 
                设置9个按钮不可用(false); 
                电脑解题菜单.setDisable(false);
            } 
        } 
    }
    
    @FXML
    public void 菜单_设置目标(){
        TextInputDialog dialog = new TextInputDialog(控制模型.目标按钮排列+"");
        dialog.setTitle("目标排列");
        dialog.setHeaderText("请输入游戏的目标排列:\n"
                + "注：9代表空白，其它数字是1-8。");
        dialog.setContentText("输入：");

        // 传统的获取输入值的方法
        Optional result = dialog.showAndWait();
        if (result.isPresent()) {  
            int end = Integer.valueOf(result.get().toString());
            if(new 电脑解题(控制模型.当前按钮排列, end, 控制模型.空白按钮序号).解题计算(new 功能实现())==null){
                Alert information = new Alert(Alert.AlertType.INFORMATION,"这个目标值无法达到，请重新设置。");
                information.setTitle("目标值无效"); //设置标题，不设置默认标题为本地语言的information
                information.setHeaderText("目标值无解："); //设置头标题，默认标题为本地语言的information 
                information.showAndWait();
            }else{
                控制模型.目标按钮排列=Integer.valueOf(result.get().toString());
            } 
        } 
    }
    
    @FXML
    public void 菜单_查看目标(){
        根据字串显示按钮("" + 控制模型.目标按钮排列); 
        设置9个按钮不可用(true); 
        
        Alert information = new Alert(Alert.AlertType.INFORMATION,""+控制模型.目标按钮排列);
        information.setTitle("查看目标"); //设置标题，不设置默认标题为本地语言的information
        information.setHeaderText("目标排列："); //设置头标题，默认标题为本地语言的information 
        information.showAndWait();
        
        根据字串显示按钮("" + 控制模型.当前按钮排列); 
        设置9个按钮不可用(false);  
    }
    
    @FXML
    public void 菜单_电脑解题(){
        int start = 控制模型.当前按钮排列;
        int end = 控制模型.目标按钮排列;
        if (end != start) {
            电脑解题 r = new 电脑解题(start, end, 控制模型.空白按钮序号);

            long t1 = System.currentTimeMillis();
            LinkedList<Integer> trace = r.解题计算(new 功能实现());
            long t2 = System.currentTimeMillis(); 
            
            if (trace != null) { 
                Alert information = new Alert(Alert.AlertType.CONFIRMATION,
                        "解题完成!\n用时："+
                        (t2 - t1) + "ms\n" +
                        "共"+(trace.size() - 1)+"步。\n"+
                        "看电脑走请点[是]");
                information.setTitle("完成"); //设置标题，不设置默认标题为本地语言的information
                information.setHeaderText("电脑解题成功。"); //设置头标题，默认标题为本地语言的information  
                Optional result = information.showAndWait();
                if (result.get() == ButtonType.OK) { 
                    new 显示结果(trace, this);
                    电脑解题菜单.setDisable(true);
                    设置9个按钮不可用(true); 
                } 
            } else { 
                Alert information = new Alert(Alert.AlertType.INFORMATION,
                        "此题无解!\n用时："+
                        (t2 - t1) + "ms\n" );
                information.setTitle("完成"); //设置标题，不设置默认标题为本地语言的information
                information.setHeaderText("电脑解题完成。"); //设置头标题，默认标题为本地语言的information 
                information.showAndWait();
            }
        }
    }
    
    @FXML
    public void 菜单_关于(){
        Alert information = new Alert(Alert.AlertType.INFORMATION,
                "九宫格小游戏。\n"
                + "点击空白旁边的格子可以移动，移动到目标排列就成功。\n"
                + "可以设置起始排列和目标排列，也可以随机生成一个排列开始。\n"
                + "还有无解的情况哦，这是在没有做电脑解题前不知道的。" );
        information.setTitle("说明"); //设置标题，不设置默认标题为本地语言的information
        information.setHeaderText("作者：人工智能"); //设置头标题，默认标题为本地语言的information 
        information.showAndWait();
    }
}
