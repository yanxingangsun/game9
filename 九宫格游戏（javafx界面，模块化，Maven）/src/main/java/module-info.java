/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

module 游戏 {
    requires javafx.controls;
    requires javafx.fxml;
    
    //src.界面和src都需要open
    opens 游戏.界面 to javafx.fxml; 
    opens 游戏 to javafx.fxml; 
    
    //src.界面和src都需要export
    exports 游戏.界面;
    exports 游戏;
}
