package 程序源码.解题;

import java.util.*;

public interface 功能接口 {

    public int[] 当前排列及空白按钮的位置();

    public void 推演_将当前数字排列写入队列末尾(int 当前排列, int 上步排列, int 空白位置);

    public boolean 能否查到当前排列与上一步排列的对应关系(int 当前排列);

    public int 回溯当前排列的上一步排列(int 当前排列);

    public LinkedList<Integer> 生成解题步骤(int 目标排列, int 起始排列);
}
