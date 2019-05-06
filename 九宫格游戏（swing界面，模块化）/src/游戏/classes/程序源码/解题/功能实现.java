package 程序源码.解题;

import java.util.*;

public class 功能实现 implements 功能接口 {

    public HashMap<Integer, int[]> 当前排列与上一步排列的对应关系 = new HashMap<Integer, int[]>();
    public int[] 推演队列 = new int[181441];
    public int 指向队列中当前推演的排列数字 = 0;
    public int 指向推演队列的末尾将写入数字 = 0; 

    @Override
    public int[] 当前排列及空白按钮的位置() {
        int[] nextRecord = new int[2];
        if (指向队列中当前推演的排列数字 < 推演队列.length && (推演队列[指向队列中当前推演的排列数字] != 0)) {
            int 当前排列 = 推演队列[指向队列中当前推演的排列数字++];
            int 空白位置 = 当前排列与上一步排列的对应关系.get(当前排列)[1];
            nextRecord[0] = 当前排列;
            nextRecord[1] = 空白位置;
        }
        return nextRecord;
    }

    @Override
    public void 推演_将当前数字排列写入队列末尾(int 当前排列, int 上步排列, int 空白位置) {
        int[] 推演记录 = new int[2];
        推演记录[0] = 上步排列;
        推演记录[1] = 空白位置;
        当前排列与上一步排列的对应关系.put(当前排列, 推演记录);
        推演队列[指向推演队列的末尾将写入数字++] = 当前排列;
    }

    @Override
    public boolean 能否查到当前排列与上一步排列的对应关系(int 当前排列) {
        boolean flag = true;
        if (当前排列与上一步排列的对应关系.get(当前排列) == null) {
            flag = false;
        }
        return flag;
    }

    @Override
    public int 回溯当前排列的上一步排列(int 当前排列) {
        int 上一步排列 = 0;
        if (当前排列与上一步排列的对应关系.get(当前排列) != null) {
            上一步排列 = 当前排列与上一步排列的对应关系.get(当前排列)[0];
        }
        return 上一步排列;
    }

    @Override
    public LinkedList<Integer> 生成解题步骤(int 目标排列, int 起始排列) {
        LinkedList<Integer> 步骤 = new LinkedList<Integer>();
        步骤.add(目标排列);
        int 当前排列 = 回溯当前排列的上一步排列(目标排列);
        while (当前排列 != 起始排列) {
            步骤.add(当前排列);
            当前排列 = 回溯当前排列的上一步排列(当前排列);
        }
        步骤.add(起始排列);
        return 步骤;
    }
}
