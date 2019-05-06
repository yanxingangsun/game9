/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package 游戏.界面;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author yanxi
 */
public class 主界面 extends Application{

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader 加载器=new FXMLLoader();
        //l.setCharset(java.nio.charset.Charset.forName("UTF-8"));//不支持gb2312,但支持gbk
        System.out.println(加载器.getCharset());
        加载器.setLocation(getClass().getResource("游戏界面.fxml"));
        加载器.setBuilderFactory(new JavaFXBuilderFactory());
        Parent root=加载器.load();
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("九宫格");
        stage.show();
    }
    
    public static void run(String[] args){
        launch(args);
    }
}
