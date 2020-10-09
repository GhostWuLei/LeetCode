//package leetcode.editor.cn.test0814;
//
//import com.sun.corba.se.impl.orbutil.ObjectStreamClassUtil_1_3;
//
//import java.util.Scanner;
//
//public class CoalMain {
//
//    private static double totalCoal = 0;
//    private static double totalSulfur = 0;
//    private static double totalCalorific = 0;
//
//
//    public static void main(String[] args) {
//
///*        Scanner sr = new Scanner(System.in);
//        System.out.println("请输入采购量总目标、硫份总目标、热值总目标：");
//        totalCoal = sr.nextDouble();
//        double averageSulfur = sr.nextDouble();
//        double averageCalorific = sr.nextDouble();
//
//        totalSulfur = averageSulfur * totalCoal;
//        totalCalorific = averageCalorific * totalCoal;
//
//        //煤场
//        System.out.println("******************煤场****************");
//        System.out.println("请输入煤场煤种数：");
//        int typeNum_A = sr.nextInt();
//
//        Coal[] coals_A = new Coal[typeNum_A];
//        for(int i=0;i<coals_A.length;i++){
//            System.out.println("请输入煤场第"+(i+1)+"种煤信息，依次为：热值、硫份、存量");
//            Coal tmpCoal_A = new Coal();
//            tmpCoal_A.setCalorific(sr.nextDouble());
//            tmpCoal_A.setSulfur(sr.nextDouble());
//            tmpCoal_A.setStock(sr.nextDouble());
//            coals_A[i] = tmpCoal_A;
//        }
//
//        //国内与进口长协
//        System.out.println("******************国内/进口长协****************");
//        System.out.println("请输入国内/进口长协的煤种数：");
//        int typeNum_B = sr.nextInt();
//
//        Coal[] coals_B = new Coal[typeNum_B];
//        for(int i=0;i<coals_B.length;i++){
//            System.out.println("请输入国内/进口长协第"+(i+1)+"种煤信息，依次为：热值、硫份、吨数、单价、运费");
//            Coal tmpCoal_B = new Coal();
//            tmpCoal_B.setCalorific(sr.nextDouble());
//            tmpCoal_B.setSulfur(sr.nextDouble());
//            tmpCoal_B.setAmount(sr.nextDouble());
//            tmpCoal_B.setPrice(sr.nextDouble());
//            tmpCoal_B.setFare(sr.nextDouble());
//            coals_B[i] = tmpCoal_B;
//        }
//
//        //国内与进口现货
//        System.out.println("******************国内/进口现货****************");
//        System.out.println("请输入国内/进口现货的煤种数：");
//        int typeNum_C = sr.nextInt();
//
//        Coal[] coals_C = new Coal[typeNum_C];
//        for(int i=0;i<coals_C.length;i++){
//            System.out.println("请输入国内/进口现货第"+(i+1)+"种煤信息，依次为：热值、硫份、单价、运费");
//            Coal tmpCoal_C = new Coal();
//            tmpCoal_C.setCalorific(sr.nextDouble());
//            tmpCoal_C.setSulfur(sr.nextDouble());
//            tmpCoal_C.setPrice(sr.nextDouble());
//            tmpCoal_C.setFare(sr.nextDouble());
//            coals_C[i] = tmpCoal_C;
//        }
//
//        System.out.println();
//        System.out.println("******************处理煤场和长协数据****************");
//        handleInput(coals_A,coals_B);
//
//        System.out.println("******************测试****************");
//        System.out.println(totalCoal+"   "+totalSulfur+"  "+totalCalorific);
//        System.out.println("******************测试完毕****************");*/
//
//        //根据现货组合矩阵数据
////        double[][] a = new double[3][typeNum_C+1];
////        double x[] = new double[typeNum_C];
////
////        for(int i=0;i<coals_C.length;i++){
////            a[0][i] = coals_C[i].getSulfur();
////            a[1][i] = 1;
////            a[2][i] = coals_C[i].getCalorific();
////            //x[i] = new Double(coals_C[i].getPrice()+coals_C[i].getFare()).intValue();
////            x[i] = coals_C[i].getPrice()+coals_C[i].getFare();
////        }
////
////        a[0][a[0].length-1] = totalSulfur;
////        a[1][a[1].length-1] = totalCoal;
////        a[2][a[2].length-1] = totalCalorific;
////
////        LinearProgram lp = new LinearProgram(-1,3,typeNum_C,1,1,1,a,x);
////        lp.solve();
//
//        /**
//         * 单纯形法
//         *
//         * 第一行第一个数字为1代表求目标函数的最大值，-1代表求目标函数的最小值；
//         * 第二个数字代表约束条件的个数；
//         * 第三个数字代表变量个数；
//         * 第四、五、六个数字分别代表≤的约束条件个数，=的约束条件个数，≥的约束条件个数；
//         * 接下来输入约束条件的系数矩阵和右端项，注意按照≤，=，≥的顺序；
//         * 最后按照变量下标顺序输入目标函数的价值系数；
//
//         -1 3 3 1 1 1
//         1 -2 1 11
//         -2 0 1 1
//         -4 1 2 3
//         -3 1 1
//
//         代表：
//
//         (x1)-2(x2)+(x3)<=11
//         -2(x1)      +(x3)=1
//         -4(x1)+(x2)+2(x3)>=3
//
//         min z=-3(x1)+(x2)+(x3)
//         *
//         */
//
//        double targetSulfur = 0.52;
//        double targetCalorific = 5400;
//        totalCoal = 90;
//        totalSulfur = targetSulfur*totalCoal;
//        totalCalorific = targetCalorific*totalCoal;
//
//        int coalNum = 9;
//
//        Coal coal_C = new Coal("C煤", 0, 0, 5300, 0.7, 780, 0, 30, 22, 0);
//        Coal coal_D = new Coal("D煤", 0, 0, 5200, 0.5, 770, 0, 30, 22, 0);
//        Coal coal_E = new Coal("E煤", 1, 0, 4700, 0.4, 700, 0, 50, 22, 0);
//        Coal coal_F = new Coal("F煤", 1, 0, 5700, 0.6, 800, 0, 50, 22, 0);
//        Coal coal_G = new Coal("G煤", 2, 0, 5000, 0.7, 760, 0, 25, 22, 0);
//        Coal coal_H = new Coal("H煤", 2, 0, 5700, 0.5, 820, 0, 30, 22, 0);
//        Coal coal_I = new Coal("I煤", 2, 0, 6000, 0.7, 850, 0, 30, 22, 0);
//        Coal coal_J = new Coal("J煤", 3, 0, 5000, 0.4, 770, 0, 60, 22, 0);
//        Coal coal_K = new Coal("K煤", 3, 0, 5500, 0.4, 800, 0, 60, 22, 0);
//
//        Coal[] coals = new Coal[coalNum];
//        coals[0] = coal_C;
//        coals[1] = coal_D;
//        coals[2] = coal_E;
//        coals[3] = coal_F;
//        coals[4] = coal_G;
//        coals[5] = coal_H;
//        coals[6] = coal_I;
//        coals[7] = coal_J;
//        coals[8] = coal_K;
//
//        for (Coal coal : coals) {
//            System.out.println(coal);
//        }
//
//
//
//
//
//    }
//
//    /**
//     *  处理煤场和长协数据
//     * @param coals_A 煤场
//     * @param coals_B 长协
//     */
//    private static void handleInput(Coal[] coals_A, Coal[] coals_B) {
//        double totalCoal_A = 0;
//        double totalSulfur_A = 0;
//        double totalCalorific_A = 0;
//        double totalCoal_B = 0;
//        double totalSulfur_B = 0;
//        double totalCalorific_B = 0;
//
//        // 计算煤场总的存量、热值总量、硫份总量
//        for(int i=0;i<coals_A.length;i++){
//            totalCoal_A = totalCoal_A+coals_A[i].getStock();
//            totalSulfur_A = totalSulfur_A+coals_A[i].getSulfur()*coals_A[i].getStock();
//            totalCalorific_A = totalCalorific_A+coals_A[i].getCalorific()*coals_A[i].getStock();
//        }
//
//        //计算长协的总吨数、热值总量、硫份总量
//        for(int i=0;i<coals_B.length;i++){
//            totalCoal_B = totalCoal_B+coals_B[i].getAmount();
//            totalSulfur_B = totalSulfur_B+coals_B[i].getSulfur()*coals_B[i].getAmount();
//            totalCalorific_B = totalCalorific_B+coals_B[i].getCalorific()*coals_B[i].getAmount();
//        }
//
//        totalCoal = totalCoal - totalCoal_A - totalCoal_B;
//        totalCalorific = totalCalorific - totalCalorific_A - totalCalorific_B;
//        totalSulfur = totalSulfur - totalSulfur_A - totalSulfur_B;
//    }
//
//
//}
