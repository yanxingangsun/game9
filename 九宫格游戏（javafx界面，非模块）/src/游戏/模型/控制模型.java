package 游戏.模型;

public class 控制模型 {
    //9表示空白按钮
    public int 空白按钮序号 = 8;
    public int 当前按钮排列 = 123456789;
    public int 目标按钮排列 = 987654321; 

    public String 根据序号找到按钮(int 序号) {
        int powN = (int) Math.pow(10, 8 - 序号);
        int 按钮数字 = (当前按钮排列 / powN) % 10;
        return 按钮数字 == 9 ? "" : "" + 按钮数字;
    }  
    
    public int 根据字符串找空白序号(String str){
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '9') { 
                return i;
            }
        }
        return 0;
    }

    public boolean 点击移动按钮(int 被点按钮序号) {
        if ((Math.abs(被点按钮序号 - 空白按钮序号) == 3)
                || (被点按钮序号 - 空白按钮序号 == 1 && 被点按钮序号 != 3 && 被点按钮序号 != 6)
                || (被点按钮序号 - 空白按钮序号 == -1 && 被点按钮序号 != 2 && 被点按钮序号 != 5)) {

            int powN = (int) Math.pow(10, 8 - 被点按钮序号);
            int pow9 = (int) Math.pow(10, 8 - 空白按钮序号);
            int 按钮数字 = (当前按钮排列 / powN) % 10;//得出要与9交换的数字
            当前按钮排列 += (9 - 按钮数字) * (powN - pow9);//n与9交换
            空白按钮序号 = 被点按钮序号;
        }
        return 当前按钮排列 == 目标按钮排列;//如果到达目标就返回true
    }
}
