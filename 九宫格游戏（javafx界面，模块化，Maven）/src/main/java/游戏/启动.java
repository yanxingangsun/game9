/*
JavaFX的Application类及其子类不能做为启动类，所以多写个启动类。
 */
package 游戏;

import 游戏.界面.主界面;

public class 启动 {
    public static void main(String[] args){
        主界面.run(args);
    }
}
