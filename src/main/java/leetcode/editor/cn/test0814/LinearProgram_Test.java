package leetcode.editor.cn.test0814;


import java.util.Scanner;

/**
 * @author Administrator
 * 单纯形法
 * 
 * 第一行第一个数字为1代表求目标函数的最大值，-1代表求目标函数的最小值；
 * 第二个数字代表约束条件的个数；
 * 第三个数字代表变量个数；
 * 第四、五、六个数字分别代表≤的约束条件个数，=的约束条件个数，≥的约束条件个数；
 * 接下来输入约束条件的系数矩阵和右端项，注意按照≤，=，≥的顺序；
 * 最后按照变量下标顺序输入目标函数的价值系数；

-1 3 3 1 1 1
1 -2 1 11
-2 0 1 1
-4 1 2 3
-3 1 1

代表：

       (x1)-2(x2)+(x3)<=11
      -2(x1)      +(x3)=1
      -4(x1)+(x2)+2(x3)>=3

min z=-3(x1)+(x2)+(x3)
 *
 */
public class LinearProgram_Test {

	/**
	 * @param args 线性规划 单纯型法
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		double a[][] = {{1,-2,1,11},{-2,0,1,1},{-4,1,2,3}};//系数矩阵
//		int x[] = {-3,1,1};//目标函数的价值系数
//		LinearProgram lp = new LinearProgram(-1,3,3,1,1,1,a,x);
//		lp.solve();


		 /**第一行第一个数字为1代表求目标函数的最大值，-1代表求目标函数的最小值；
		 * 第二个数字代表约束条件的个数；
		 * 第三个数字代表变量个数；
		 * 第四、五、六个数字分别代表≤的约束条件个数，=的约束条件个数，≥的约束条件个数；
		 * 接下来输入约束条件的系数矩阵和右端项，注意按照≤，=，≥的顺序；
		 * 最后按照变量下标顺序输入目标函数的价值系数**/

		 // 以下测试成功
		 /*double a[][] = {{0.6,0.5,0.4,0.6,0.4,25},{1,1,1,1,1,50},{4600,4700,4600,5200,5300,250000}};
		 int x[] = {780,803,813,853,900};
		 LinearProgram lp = new LinearProgram(-1,3,5,1,1,1,a,x);
		 lp.solve();*/

		 // 以下测试成功
		 /*double a[][] = {{0.6,0.49,25},{1,1,50},{4600,5200,250000}};
		 int x[] = {780,803};
		 LinearProgram lp = new LinearProgram(-1,3,2,1,1,1,a,x);
		 lp.solve();*/

		 //输入采购量总目标 硫份总目标 热值总目标
		Scanner sr = new Scanner(System.in);
		System.out.println("请输入采购量总目标、硫份总目标、热值总目标：");
		double tCoal = sr.nextDouble();
		double tSulfur = sr.nextDouble();
		double tCalorific = sr.nextDouble();

		 //输入煤的种类数
		System.out.println("请输入煤的种类数：");
		int coalNum = sr.nextInt();

		//System.out.println(tCoal+"==="+ tSulfur+"==="+tCalorific);
		//System.out.println(coalNum);

		Coal[] coals = new Coal[coalNum];
		for(int i=0;i<coalNum;i++){
			System.out.println("请输入第"+(i+1)+"种煤信息");
			Coal tmpCoal = new Coal();
			tmpCoal.setCalorific(sr.nextDouble());
			tmpCoal.setSulfur(sr.nextDouble());
			tmpCoal.setStock(sr.nextDouble());
			tmpCoal.setPrice(sr.nextDouble());
			coals[i] = tmpCoal;
		}

//		for (Coal coal : coals) {
//			System.out.println(coal);
//		}

		// 到此为止还是测试成功的


		/**
		 * 单纯形法
		 *
		 * 第一行第一个数字为1代表求目标函数的最大值，-1代表求目标函数的最小值；
		 * 第二个数字代表约束条件的个数；
		 * 第三个数字代表变量个数；
		 * 第四、五、六个数字分别代表≤的约束条件个数，=的约束条件个数，≥的约束条件个数；
		 * 接下来输入约束条件的系数矩阵和右端项，注意按照≤，=，≥的顺序；
		 * 最后按照变量下标顺序输入目标函数的价值系数；

		-1 3 3 1 1 1
		1 -2 1 11
		-2 0 1 1
		-4 1 2 3
		-3 1 1

		代表：

		(x1)-2(x2)+(x3)<=11
		-2(x1)      +(x3)=1
		-4(x1)+(x2)+2(x3)>=3

		min z=-3(x1)+(x2)+(x3)
		 *
		 */
		//double a[][] = {{4600,4700,4600,5200,5300,250000},{0.6,0.5,0.4,0.6,0.4,25},{1,1,1,1,1,50}};

		double[][] a = new double[3][coalNum+1];
		int x[] = new int[coalNum];

		for(int i=0;i<coals.length;i++){
			a[0][i] = coals[i].getSulfur();
			a[1][i] = 1;
			a[2][i] = coals[i].getCalorific();
			x[i] = new Double(coals[i].getPrice()).intValue();
		}
		a[0][a[0].length-1] = tCoal*tSulfur;
		a[1][a[1].length-1] = tCoal;
		a[2][a[2].length-1] = tCoal*tCalorific;




		for(int i=0;i<a.length;i++){
			for(int j=0;j<a[i].length;j++){
				System.out.print(a[i][j]+" ");
			}
			System.out.println();
		}

		System.out.println("+++++++++++++++++++");
		for (double v : x) {
			System.out.print(v + " ");
		}

		System.out.println();
		System.out.println("****************结果如下*****************");

		//LinearProgram lp = new LinearProgram(1,3,coalNum,1,1,1,a,x);
		//lp.solve();




	}

}
