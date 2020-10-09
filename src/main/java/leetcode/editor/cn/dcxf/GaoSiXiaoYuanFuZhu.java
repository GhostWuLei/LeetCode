package leetcode.editor.cn.dcxf;
 
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;
/**
 * 单纯形法需要用到的高斯消元法
 * @author 郑立源
 * @time 2017年09月22日 19:30
 */
public class GaoSiXiaoYuanFuZhu {
	/**
	 * 得到标准形式的字符串
	 * @param a
	 * @return
	 */
	public static String getstandard(String a){
//		String umerator="";//分子
//		String denominator="";//分母
		String [] as = a.split("/");
		if(as.length !=2){
			a=a+"/1";
		}
		return a;
	}
	/**
	 * 过滤字符串，
	 * @param a
	 * @return
	 */
	public static String filter(String a){
		String count="";
		String [] as = a.split("/");
		if(as.length==2){
			//分子分母同时存在负号,去掉负号
			if(as[0].substring(0, 1).equals("-")&&as[1].substring(0, 1).equals("-")){
				as[0]=as[0].substring(1, as[0].length());
				as[1]=as[1].substring(1, as[1].length());
			}
			count=getint(as[0]+"/"+as[1]);
		}else{
			count=a;
		}
		return count;
	}
	/**
	 * 过滤结果集
	 * @param a
	 * @return
	 */
	public static String getint(String a){
		String count=a;
		String [] as = a.split("/");
		if(as.length==2){
			BigInteger as0=new BigInteger(as[0]);
			BigInteger as1=new BigInteger(as[1]);
			if(String.valueOf(as0.remainder(as1)).equals("0")){
				count=String.valueOf(as0.divide(as1));
			}
		}else{
			count=a;
		}
		return count;
	}
	/**
	 * 
	 * @param a 参数1
	 * @param b 参数2
	 * @param type 0代表加法，其他代表减法
	 * @return
	 */
	public static String plus(String a,String b,int type){
		String count="";//计算结果
		String umerator="";//分子
		String denominator="";//分母
		//字符串格式判断
		a=getstandard(a);
		b=getstandard(b);
		String[] a1 = a.split("/");//a的分子和分母
		String [] b1 = b.split("/");//b的分子和分母
		BigInteger a10=new BigInteger(a1[0]);
		BigInteger a11=new BigInteger(a1[1]);
		BigInteger b10=new BigInteger(b1[0]);
		BigInteger b11=new BigInteger(b1[1]);
		BigInteger fenzi;
		BigInteger fenmu=a11.multiply(b11);
//		int fenmu=Integer.parseInt(a1[1])*Integer.parseInt(b1[1]);
//		int fenzi;
		if(type==0){
//			fenzi=Integer.parseInt(a1[0])*Integer.parseInt(b1[1])+Integer.parseInt(b1[0])*Integer.parseInt(a1[1]);
			fenzi=(a10.multiply(b11)).add((b10.multiply(a11)));
		}else{
//			fenzi=Integer.parseInt(a1[0])*Integer.parseInt(b1[1])-Integer.parseInt(b1[0])*Integer.parseInt(a1[1]);
			fenzi=(a10.multiply(b11)).subtract((b10.multiply(a11)));
		}
		denominator=String.valueOf(fenmu);
		umerator=String.valueOf(fenzi);
		if(denominator.equals(umerator)){
			count="1";
		}else if(umerator.equals("0")){
			count="0";
		}else{
			//加法计算
			count=umerator+"/"+denominator;
		}
		return filter(count);
	}
	/**
	 * 
	 * @param a 参数1
	 * @param b 参数2
	 * @param type 0代表乘法，1代表除法
	 * @return
	 */
	public static String getMul(String a,String b,int type){
		String count="";
		String umerator="";//分子
		String denominator="";//分母
		//字符串格式判断
		a=getstandard(a);
		b=getstandard(b);
		String[] a1 = a.split("/");//a的分子和分母
		String [] b1 = b.split("/");//b的分子和分母
		if(type==1){
			//除法
			String temp;
			temp=b1[0];
			b1[0]=b1[1];
			b1[1]=temp;
		}
		BigInteger a10=new BigInteger(a1[0]);
		BigInteger a11=new BigInteger(a1[1]);
		BigInteger b10=new BigInteger(b1[0]);
		BigInteger b11=new BigInteger(b1[1]);
		BigInteger fenzi=a10.multiply(b10);
		BigInteger fenmu=a11.multiply(b11);
//		int fenzi=Integer.parseInt(a1[0])*Integer.parseInt(b1[0]);
//		int fenmu=Integer.parseInt(a1[1])*Integer.parseInt(b1[1]);
		denominator=String.valueOf(fenmu);
		umerator=String.valueOf(fenzi);
		if(denominator.equals(umerator)){
			count="1";
		}else if(umerator.equals("0")){
			count="0";
		}else{
			//加法计算
			count=umerator+"/"+denominator;
		}
		return filter(count);
	}
	public static void gSXY(String a[][],int equationNum,int unknownNumS){
		int unknownNum=unknownNumS-1;
		String[][] fangcheng=a;
		System.out.println("此时的方程为：");
		for(int i=0;i<equationNum;i++){
			for(int j=0;j<unknownNum+1;j++){
				if(j != unknownNum){
					System.out.print(fangcheng[i][j]+"  ");
				}else{
					System.out.print("|"+fangcheng[i][j]);
				}
			}
			System.out.println();
		}
		if(equationNum>unknownNum){
			//无解
			System.out.println("方程无解");
		}else{
			//有解
			for(int i=0;i<equationNum-1;i++){
				//选取主元素
				//如果主元素为0，则找该列不为0的行交换
				if(fangcheng[i][i].equals("0")){
					//查找该列下不为0的行
					for(int j=i+1;j<equationNum;j++){
						if(!fangcheng[j][i].equals("0")){
							//交换两行
							for(int k=i;k<equationNum;k++){
								for(int m=0;m<unknownNum+1;m++){
									//交换操作
									String temp;
									temp=fangcheng[k][m];
									fangcheng[k][m]=fangcheng[j][m];
									fangcheng[j][m]=temp;
								}
							}
						break;
						}
					}
					System.out.println("交换后的方程为：");
					for(int p=0;p<equationNum;p++){
						for(int q=0;q<unknownNum+1;q++){
							if(q==unknownNum){
								System.out.print("|"+fangcheng[p][q]);
							}else{
								System.out.print(fangcheng[p][q]+"  ");
							}
						}
						System.out.println();
					}
				}
				System.out.println("此时的主元素为："+fangcheng[i][i]);
				//进行消元操作,循环当前行下面的所有行，进行减法运算
				for(int i1=i+1;i1<equationNum;i1++){
					if(!fangcheng[i][i].equals("0")&&!fangcheng[i1][i].equals("0")){
						String gys=getMul(fangcheng[i][i], fangcheng[i1][i], 1);
						System.out.println("公约数为："+gys);
						for(int i2=i;i2<unknownNum+1;i2++){
							//为每一行从新赋值
							if(!fangcheng[i1][i2].equals("0")){
								fangcheng[i1][i2]=plus(getMul(fangcheng[i1][i2], gys, 0), fangcheng[i][i2], 1);
							}
						}
					}
				}
				System.out.println("计算后的上三角为：");
				for(int p=0;p<equationNum;p++){
					for(int q=0;q<unknownNum+1;q++){
						if(q==unknownNum){
							System.out.print("|"+fangcheng[p][q]);
						}else{
							System.out.print(fangcheng[p][q]+"  ");
						}
					}
					System.out.println();
				}
			}//正向结束
			//反向开始
			//消元完成进行反转，当equationNum=unknownNum满秩的时候，有唯一解直接求解，当equationNum<unknownNum，有无数解；
			for(int m=equationNum-1;m>=0;m--){
				System.out.println("反向旋转此时的主元素为："+fangcheng[m][m]);
				for(int m1=m-1;m1>=0;m1--){
					if(!fangcheng[m][m].equals("0")&&!fangcheng[m1][m].equals("0")){
						String gys=getMul(fangcheng[m][m], fangcheng[m1][m], 1);
						System.out.println("公约数为："+gys);
						for(int m2=0;m2<unknownNum+1;m2++){
							if(!fangcheng[m1][m2].equals("0")){
								fangcheng[m1][m2]=plus(getMul(fangcheng[m1][m2], gys, 0), fangcheng[m][m2], 1);	
							}
						}
					}
				}
				System.out.println("计算后的对角矩阵为：");
				for(int p=0;p<equationNum;p++){
					for(int q=0;q<unknownNum+1;q++){
						if(q==unknownNum){
							System.out.print("|"+fangcheng[p][q]);
						}else{
							System.out.print(fangcheng[p][q]+"  ");
						}
					}
					System.out.println();
				}
			}//反向求解结束
			//化简称单位矩阵
			for(int n=0;n<equationNum;n++){
				String fuzhu=fangcheng[n][n];
				for(int n1=0;n1<unknownNum+1;n1++){
					if(!fangcheng[n][n1].equals("0")){
						fangcheng[n][n1]=getMul(fangcheng[n][n1], fuzhu, 1);
					}
				}
			}
			System.out.println("转化后的单位矩阵为：");
			for(int p=0;p<equationNum;p++){
				for(int q=0;q<unknownNum+1;q++){
					if(q==unknownNum){
						System.out.print("|"+fangcheng[p][q]);
					}else{
						System.out.print(fangcheng[p][q]+"  ");
					}
				}
				System.out.println();
			}
		}
	}
	public static void main(String[] args) {
		String[][] a={{"5","6","7","8","38"},{"0","2","3","3","15"},{"6","3","4","5","24"}};
		gSXY(a, 3, 5);
	}
}