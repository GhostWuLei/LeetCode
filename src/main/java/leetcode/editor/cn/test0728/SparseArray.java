package leetcode.editor.cn.test0728;

import leetcode.editor.cn.test0814.Coal;
import org.junit.Test;

import java.util.*;

public class SparseArray {

//    public static void main(String[] args) {
//        // 创建一个二维数组 11 * 11
//        // 0表示没有棋子，1表示黑子，2表示蓝子
//        int chessArr1[][] = new int[11][11];
//        chessArr1[1][2] = 1;
//        chessArr1[2][3] = 2;
//        chessArr1[4][5] = 2;
//
//        //输出原始的二维数据
//        for (int[] row : chessArr1) {
//            for (int data : row) {
//                System.out.printf("%d\t", data);
//            }
//            System.out.println();
//        }
//
//        // 将二维数组转换为稀疏数组的思路
//        // 1、先遍历稀疏数组 得到非0的数据的个数
//        int sum = 0;
//        for (int i = 0; i < 11; i++) {
//            for (int j = 0; j < 11; j++) {
//                if (chessArr1[i][j] != 0) {
//                    sum++;
//                }
//            }
//        }
//
//        // 2、创建对应的稀疏数组
//        int sparseArr[][] = new int[sum + 1][3];
//        // 给稀疏数组赋值
//        sparseArr[0][0] = 11;
//        sparseArr[0][1] = 11;
//        sparseArr[0][2] = sum;
//
//        //遍历二维数组，将非0的值存放到稀疏数组中
//        int count = 0; //用于记录是第几个非0数据
//        for (int i = 0; i < 11; i++) {
//            for (int j = 0; j < 11; j++) {
//                if (chessArr1[i][j] != 0) {
//                    count++;
//                    sparseArr[count][0] = i;
//                    sparseArr[count][1] = j;
//                    sparseArr[count][2] = chessArr1[i][j];
//                }
//            }
//        }
//
//        // 3、输出稀疏数组的形式
//        System.out.println();
//        System.out.println("输出稀疏数组为~~~~");
//        for (int i = 0; i<sparseArr.length;i++){
//            System.out.printf("%d\t%d\t%d\t\n", sparseArr[i][0],sparseArr[i][1],sparseArr[i][2]);
//        }
//
//        // 将稀疏数组转换为原始的二维数组
//
//
//    }

    public static int count = 0;
    public static double totalCoal = 0;
    public static double totalSulfur = 0;
    public static double totalCalorific = 0;

    //   public static void main(String[] args) {
//        List<String> list = new ArrayList<>();
//        list.add("a");
//        list.add("b");
//        list.add("c");
//        for(int i=0;i<list.size();i++){
//            System.out.println(list.get(i));
//        }
//-------------------------------------------------------
//        double[][] a = new double[3][3];
//        double[] b = new double[3];
//
//        for (int i=0;i<b.length;i++){
//            b[i] = i+1;
//        }
//
//        a[1] = b;
//
//        for(int i=0;i<a.length;i++){
//            for(int j=0;j<a[i].length;j++){
//                System.out.print(a[i][j]+" ");
//            }
//            System.out.println();
//        }
//-----------------------------------------------------
//        Scanner sr = new Scanner(System.in);
//        //输入总的目标
//        System.out.println("请输入采购量总目标、硫份总目标、热值总目标：");
//        totalCoal = sr.nextDouble();
//        double averageSulfur = sr.nextDouble();
//        double averageCalorific = sr.nextDouble();
//        totalSulfur = averageSulfur*totalCoal;
//        totalCalorific = averageCalorific*totalCoal;
//
//        //煤场
//        System.out.println("******************煤场****************");
//        System.out.println("请输入煤场煤种数：");
//        int yardNum = sr.nextInt();
//
//        Coal[] yardCoals = new Coal[yardNum];
//        for(int i=0;i<yardCoals.length;i++){
//            System.out.println("请输入煤场第"+(i+1)+"种煤信息，依次为：名称、热值、硫份、存量");
//            Coal tmpCoal = new Coal();
//            tmpCoal.setName(sr.next());
//            tmpCoal.setCalorific(sr.nextDouble());
//            tmpCoal.setSulfur(sr.nextDouble());
//            tmpCoal.setStock(sr.nextDouble());
//            yardCoals[i] = tmpCoal;
//        }
//
//        for (Coal yardCoal : yardCoals) {
//            System.out.println(yardCoal);
//        }
//------------------------------------------------------------------------------------------

//    }

    public static void main(String[] args) {
        double[][] nums = initialArray(5, 3);

        System.out.println("排序前：");
        printArr(nums);

        System.out.println("排序后:");
        // 先根据第1列比较，若相同则再比较第0列
        sortByColumn(nums, new int[] {1, 0});

        printArr(nums);
    }

    /**
     * 打印二维数组
     * @param nums 数组
     */
    private static void printArr(double[][] nums) {
        for (int i = 0; i < nums.length; i++) {
            System.out.println(Arrays.toString(nums[i]));
        }
    }

    /**
     * 构造一个row * col的二维数组
     * @param row 二维数组的行数
     * @param col 二维数组的列数
     * @return 构造的二维数组
     */
    private static double[][] initialArray(int row, int col) {
        double[][] arr = new double[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                // 0-100的随机数
                arr[i][j] = (Math.random() * 100);
            }
        }
        return arr;
    }

    /**
     * 按列排序
     * @param ob 待排序的数组
     * @param order 列排序的优先级， 如：new int{1, 2} 先根据第一列比较，若相同则再比较第二列
     */
    private static void sortByColumn(double[][] ob, final int[] order) {
        Arrays.sort(ob, new Comparator<Object>() {
            public int compare(Object o1, Object o2) {
                double[] one = (double[]) o1;
                double[] two = (double[]) o2;
                for (int i = 0; i < order.length; i++) {
                    int k = order[i];
                    if (one[k] > two[k]) {
                        return 1;
                    } else if (one[k] < two[k]) {
                        return -1;
                    } else {
                        continue;
                    }
                }
                return 0;
            }
        });
    }


}
