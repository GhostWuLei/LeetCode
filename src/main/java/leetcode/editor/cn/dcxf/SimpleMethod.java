package leetcode.editor.cn.dcxf;

import java.math.BigInteger;
import java.util.Scanner;

import com.sun.org.apache.regexp.internal.recompile;

/**
 * 单纯形算法
 * @author 郑立源
 * @time 2017年09月23日  00:32
 */
public class SimpleMethod {
    private GaoSiXiaoYuanFuZhu gaoSiXiaoYuanFuZhu;

    public static void main(String[] args) {
//		//请求用户输入矩阵
//		Scanner sr=new Scanner(System.in);
//		System.out.println("请输入方程个数：");
//		int equationNum=sr.nextInt();
//		System.out.println("请输入未知数个数：");
//		int unknownNum=sr.nextInt();
//		System.out.println("您输入的方程个数为："+equationNum);
//		System.out.println("您输入的未知数个数为"+unknownNum);
//		String[][] fangcheng=new String[equationNum][unknownNum+1];
//		for(int i=0;i<equationNum;i++){
//			System.out.println("请输入第"+(i+1)+"个方程的未知数系数，系数之间用;隔开,例如1;2;3");
//			String xishu=sr.next();
//			String [] as = xishu.split(";");
//			for(int j=0;j<unknownNum;j++){
//				fangcheng[i][j]="".equals(as[j])?"0":as[j];
//			}
//		}
//		//函数的结果集合
//		System.out.println("请输入每个方程的结果，用;隔开");
//		String[] result=(sr.next()).split(";");
//		for(int i=0;i<equationNum;i++){
//			for(int j=0;j<equationNum;j++){
//				fangcheng[i][unknownNum]=result[i];
//			}
//		}
//		//请求用户输入目标函数
//		String[] objectFunction=new String[unknownNum];
//		System.out.println("请按顺序输入目标函数中各变量的系数，系数之间用;隔开,例如1;2;3");
//		String ratio=sr.next();
//		String [] as2 = ratio.split(";");
//		for(int i=0;i<unknownNum;i++){
//			objectFunction[i]=as2[i].equals("")?"0":as2[i];
//		}
        //方程
        //String[][] fangcheng = new String[][]{{"5", "6", "7", "8", "38"}, {"2", "2", "3", "3", "15"}, {"6", "3", "4", "5", "24"}};
        String[][] fangcheng = new String[][]{{"1", "2", "1", "0", "0", "8"}, {"4", "0", "0", "1", "0", "16"}, {"0", "4", "0", "0", "1", "12"}};
        //目标函数
        //String[] objectFunction = new String[]{"1", "2", "3", "4"};
        String[] objectFunction = new String[]{"2", "3", "0", "0", "0"};
//        int equationNum = 3;
//        int unknownNum = 4;
        int equationNum = 3;
        int unknownNum = 5;
        //组合矩阵和目标函数
        for (int i = 0; i < equationNum; i++) {
            for (int j = 0; j < unknownNum + 1; j++) {
                if (j != unknownNum) {
                    System.out.print(fangcheng[i][j] + "  ");
                } else {
                    System.out.print("|" + fangcheng[i][j]);
                }
            }
            System.out.println();
        }
        GaoSiXiaoYuanFuZhu.gSXY(fangcheng, equationNum, unknownNum + 1);
        //输出可行解
        String[] resultNum = new String[unknownNum];
        //初始化可行基
        for (int i = 0; i < unknownNum; i++) {
            resultNum[i] = "0";
        }
        for (int i = 0; i < equationNum; i++) {
            resultNum[i] = fangcheng[i][unknownNum];
        }
        for (int i = 0; i < resultNum.length; i++) {
            if (i == 0) {
                System.out.print("初始化可行基为：[" + resultNum[i] + ",");
            } else if (i == resultNum.length - 1) {
                System.out.print(resultNum[i] + "]");
            } else {
                System.out.print(resultNum[i] + ",");
            }
        }
        System.out.println();
        System.out.println("********************************************************************");
        getOptimumSolution(fangcheng, objectFunction, equationNum, unknownNum);
    }

    /**
     * 循环获得最优化
     * @param fangcheng 矩阵方程（包含结果即可行解）
     * @param objectFunction 目标函数
     * @param equationNum 方程个数
     * @param unknownNum 未知数个数
     * 1、循环求各个变量的检验数，都<=0,证明当前可行解即是最优解，否则，进行2
     * 2、求出还如变量，求出换出变量
     * 3、旋转运算
     * 4、递归调用此函数；
     */
    public static void getOptimumSolution(String[][] fangcheng,String[] objectFunction,int equationNum,int unknownNum){
        System.out.println("*******************************************************");

        //检验数数组
        String[] examine=new String[unknownNum];
        for(int i=0;i<unknownNum;i++){
            if(i<equationNum){
                examine[i]="0";
            }else{
                String sum="0";
                for(int j=0;j<equationNum;j++){
                    sum=GaoSiXiaoYuanFuZhu.plus(sum, GaoSiXiaoYuanFuZhu.getMul(fangcheng[j][i], objectFunction[j], 0), 0);
                }
                examine[i]=GaoSiXiaoYuanFuZhu.plus(objectFunction[i], sum, 1);
            }
        }
        System.out.println("此时的检验数为:");
        for(int i=0;i<unknownNum;i++){
            if(i==0){
                System.out.print("["+examine[i]+",");
            }else if(i==unknownNum-1){
                System.out.print(examine[i]+"]");
            }else{
                System.out.print(examine[i]+",");
            }
        }
        System.out.println();
        int bi=0;
        for(int j=0;j<examine.length;j++){
            if(examine[j] != null){
                if(!examine[j].equals("0")&&!(examine[j].toString().substring(0, 1)).equals("-")){
                    bi=1;
                }
            }
        }
        if(bi==0){
            System.out.println("当前可行解为最优解!");
            //输出可行解
            String[] resultNum=new String[unknownNum];
            //初始化可行基
            for(int i=0;i<unknownNum;i++){
                resultNum[i]="0";
            }
            for(int i=0;i<equationNum;i++){
                resultNum[i]=fangcheng[i][unknownNum];
            }
            for(int i=0;i<resultNum.length;i++){
                if(i==0){
                    System.out.print("最优解为：["+resultNum[i]+",");
                }else if(i==resultNum.length-1){
                    System.out.print(resultNum[i]+"]");
                }else{
                    System.out.print(resultNum[i]+",");
                }
            }
            System.out.println();
            return;
        }
        //找出换入变量,检验数最大的那一列
        int swapin=getMax(examine);
        System.out.println("此时的换入变量为第"+swapin+"列");
        //计算换出变量
        //换出变量数组
        String[] swapoutArray=new String[equationNum];
        for(int i=0;i<equationNum;i++){
            //结果集除以换入列的系数
            if(fangcheng[i][swapin-1].equals("0")){
                swapoutArray[i]=null;
            }else{
                swapoutArray[i]=GaoSiXiaoYuanFuZhu.getMul(fangcheng[i][unknownNum], fangcheng[i][swapin-1], 1);
            }
        }
        System.out.println("此时的换出变量数组为：");
        for(int i=0;i<equationNum;i++){
            if(i==0){
                System.out.print("["+swapoutArray[i]+",");
            }else if(i==equationNum-1){
                System.out.print(swapoutArray[i]+"]");
            }else{
                System.out.print(swapoutArray[i]+",");
            }
        }
        System.out.println();
        //求出换出变量，检验数最消的那一行
        int swapout=getMin(swapoutArray);
        System.out.println("此时的换出变量为第"+swapout+"行");
        System.out.println("故此时的主元素为方程组第"+swapout+"行和第"+swapin+"列的交叉出，此元素为："+fangcheng[swapout-1][swapin-1]);
        //消元
        //更换列，并且更换目标函数的位置
        for(int i=0;i<equationNum;i++){
            String temp;
            temp=fangcheng[i][swapin-1];
            fangcheng[i][swapin-1]=fangcheng[i][swapout-1];
            fangcheng[i][swapout-1]=temp;
        }
        //交换目标函数
        String objectTemp;
        objectTemp=objectFunction[swapin-1];
        objectFunction[swapin-1]=objectFunction[swapout-1];
        objectFunction[swapout-1]=objectTemp;
        //调用高斯进行消元
        GaoSiXiaoYuanFuZhu.gSXY(fangcheng, equationNum, unknownNum+1);
        //将更换过的矩阵，目标函数进行递归
        getOptimumSolution(fangcheng, objectFunction, equationNum, unknownNum);
    }

    //得到数组中最大的元素位置
    public static int getMax(String[] a){
        //默认最大数为第一个
//		String count_=a[0]==null?"0":a[0];
        String count_=a[0];
        int maxId=0;
        for(int i=1;i<a.length;i++){
            //求每个数和当前最大数的差
            String differ=GaoSiXiaoYuanFuZhu.plus(a[i], count_, 1);
            //如果这个差不为负数，并且不为0，则证明当前叔大于最大数
            if(!differ.substring(0, 1).equals("-")&&!differ.substring(0, 1).equals("0")){
                count_=a[i];
                maxId=i;
            }
        }
        return maxId+1;
    }


    //得到数组中最小的元素位置
    public static int getMin(String[] a){
        String count_=a[0];
        int minId = 0;
        for(int i=0;i<a.length;i++){
            if(!(a[i]==null)){
                count_=a[i];
                minId=i;
                break;
            }
        }
        for(int j=0;j<a.length;j++){
            if(a[j] != null){
                String differ=GaoSiXiaoYuanFuZhu.plus(a[j], count_, 1);
                //如果差为负数
                if(differ.substring(0, 1).equals("-")){
                    count_=a[j];
                    minId=j;
                }
            }
        }
        return minId+1;
    }


}
