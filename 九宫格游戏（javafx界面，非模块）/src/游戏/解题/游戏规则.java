package 游戏.解题;

import java.util.*;

public class 游戏规则 {

    //result 在8种情况下对应的8组结果
    private static int[][] 结果 = new int[][]{
        new int[2],
        new int[3],
        new int[2],
        new int[3],
        new int[4],
        new int[3],
        new int[2],
        new int[3],
        new int[2]
    };

    //indexN 在8种情况下对应的8组n的位置(索引)值
    public static int[][] 数字N的位置计算规则 = new int[][]{
        {0 + 1, 0 + 3},
        {1 - 1, 1 + 1, 1 + 3},
        {2 - 1, 2 + 3},
        {3 - 3, 3 + 1, 3 + 3},
        {4 - 3, 4 - 1, 4 + 1, 4 + 3},
        {5 - 3, 5 - 1, 5 + 3},
        {6 - 3, 6 + 1},
        {7 - 3, 7 - 1, 7 + 1},
        {8 - 3, 8 - 1}
    };

    /**
     * 当前排列-=n*powN; 
     * //要与9交换的数字所在位清0 当前排列-=9*pow9; 
     * //9所在位清0 当前排列+=n*pow9; 
     * //与9交换的数字放在9原来所在的位上 当前排列+=9*powN; 
     * //9放在原来数字所在的位上
     * <==>等价于 当前排列=当前排列-n*powN-9*pow9+n*pow9+9*powN
     * <==>等价于 当前排列=当前排列+(9-n)*powN+(n-9)*pow9
     * <==>等价于 当前排列+=(9-n)*(powN-pow9);//n与9交换
    }
     */
    public static int[] 根据当前排列和空白的位置计算下一步所有的可能排列(int 当前排列, int 空白位置) {
        int pow9 = (int) Math.pow(10, 8 - 空白位置), powN, 数字N;
        for (int i = 0; i < 数字N的位置计算规则[空白位置].length; i++) {
            powN = (int) Math.pow(10, 8 - 数字N的位置计算规则[空白位置][i]);
            数字N = (当前排列 / powN) % 10;//得出要与9交换的数字
            结果[空白位置][i] = 当前排列 + (9 - 数字N) * (powN - pow9);
        }
        return 结果[空白位置];
    }

    public static int 随机生成一个排列() {
        java.util.List<String> list = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9");
        Collections.shuffle(list);
        String s = "";
        for (int i = 0; i < list.size(); i++) {
            s += list.get(i);
        }
        return Integer.parseInt(s);
    }
}
