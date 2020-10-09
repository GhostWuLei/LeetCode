package leetcode.editor.cn.test0824;

import com.sun.xml.internal.bind.v2.runtime.output.DOMOutput;
import javafx.scene.control.Cell;
import leetcode.editor.cn.test0814.Coal;
import leetcode.editor.cn.test0814.LinearProgram;
import sun.java2d.pipe.SpanIterator;

import java.nio.file.FileAlreadyExistsException;
import java.util.*;

public class CombineAndArrangement {

    public static int num = 0;
    public static int count = 0;
    public static int count2 = 0;
    public static int count3 = 0;
    public static double totalCoal = 0;
    public static double totalSulfurTop = 0;
    public static double totalSulfurBtm = 0;
    public static double totalCalorificTop = 0;
    public static double totalCalorificBtm = 0;
    public static double totalYardCoal = 0;
    public static double totalYardSulfur = 0;
    public static double totalYardCalorific = 0;



    public static void main(String[] args) {

        Coal[] coals = handlInput();

        //考虑一种特殊情况 所有种类的煤都需要买 并且使得价格达到最低的情况
        double[] special = handleSpecial(coals);

        //实例化0-num的数组
        int[] com = new int[num];
        for(int i=0;i<com.length;i++){
            com[i] = i;
        }

        Map<Integer, List<List<Integer>>> map = new HashMap<>();
        for(int i=1;i<com.length;i++){
            List<List<Integer>> combine = combine(0, i, com);
            map.put(i, combine);
        }

        for (Map.Entry<Integer, List<List<Integer>>> entry : map.entrySet()) {
            System.out.println("key = " + entry.getKey() + ", value = " + entry.getValue());
        }

        List<List<Coal>> lists = handleCoal(coals, map, num);

        //对lists进行遍历，遍历出所有的list,然后算出每个list里面怎么买的最低价，把返回的结果保存下来，最后统一
        //对比哪个价格最低
        double finalSolve[][] = new double[1024*32][num+1];
        //对finalSolve最后一列进行初始化
        for (int i=0;i<finalSolve.length;i++){
            finalSolve[i][num] = 1000000000;
        }

        //把特殊情况添加到第0个
        if(special!=null){
            finalSolve[0] = special;
        }

        for (int i=0;i<lists.size();i++) {
            double[] fianlArr = handlePrice1(lists.get(i), com.length);
            count2++;
            if(fianlArr!=null&&fianlArr.length!=0){
                count3++;
                finalSolve[i+1] = fianlArr;
            }
        }

        System.out.println("count2:"+count2+"========"+"count3:"+count3);

        sortByColumn(finalSolve, new int[]{num});
        //up(finalSolve,String.valueOf(num));

        double[][] retArray = handleOutput(finalSolve, coals);


        System.out.println("#######################最后结果########################");
        for (int i=0;i<10;i++){
            for(int j=0;j< finalSolve[i].length;j++){
                System.out.println(finalSolve[i][j]);
            }
            System.out.println("+++++++++++++++++++++");
        }

        //打印返回值
        for(int i=0;i<retArray.length;i++){
            for(int j=0;j<num;j++){
                System.out.println("第x"+(j+1)+"种煤="+retArray[i][j]);
            }
            System.out.println("第"+(i+1)+"种方案的总费用："+retArray[i][num]);
            System.out.println("第"+(i+1)+"种方案的购买量："+retArray[i][num+1]);
            System.out.println("第"+(i+1)+"种方案的平均热值："+retArray[i][num+2]);
            System.out.println("第"+(i+1)+"种方案的平均硫份："+retArray[i][num+3]);
            System.out.println("*********************************************");
        }

    }

    //处理最后的返回结果
    public static double[][] handleOutput(double[][] arr, Coal[] coals){

        System.out.println("#######################最后结果########################");
        double[][] tmpArr = new double[10][num+1];
        double[][] ret = new double[10][num+4];
        //获取前10的最佳方案
        for (int i=0;i<10;i++){
            for(int j=0;j< arr[i].length;j++){
                tmpArr[i][j] = arr[i][j];
            }
        }
        //对返回的ret进行遍历 算出这个方案的平均热值和平均硫份（包含煤场的） 并添加到数组的末尾
        for(int i=0;i<tmpArr.length;i++){
            double tcalorfic = 0;
            double tcoals = 0;
            double tsulflur = 0;
            for(int j=0;j<tmpArr[i].length-1;j++){
                tcalorfic += coals[j].getCalorific()*tmpArr[i][j];
                tcoals += tmpArr[i][j];
                tsulflur += coals[j].getSulfur()*tmpArr[i][j];
                ret[i][j] = tmpArr[i][j];
            }
            ret[i][num] = tmpArr[i][num];
            System.out.println("第"+(i+1)+"种方案的购买量===平均热值===平均硫份为："+tcoals+"==="+tcalorfic/tcoals+"==="+tsulflur/tcoals );
            ret[i][num+1] = tcoals;
            ret[i][num+2] = (tcalorfic+totalYardCalorific)/(tcoals+totalYardCoal);
            ret[i][num+3] = (tsulflur+totalYardSulfur)/(tcoals+totalYardCoal);
        }
        return ret;
    }

    //所有的都买的情况下最低价格
    public static double[] handleSpecial(Coal[] coals) {

        double[][] tmp = new double[num][num+1];
        for(int i=0;i<tmp.length;i++){
            tmp[i][i] = 1;
            tmp[i][num] = coals[i].getAmount();
        }

        double[][] a = new double[5][num+1];
        double x[] = new double[num];

        for(int i=0;i<coals.length;i++){
            a[0][i] = coals[i].getSulfur();
            a[1][i] = coals[i].getCalorific();
            a[2][i] = 1;
            a[3][i] = coals[i].getSulfur();
            a[4][i] = coals[i].getCalorific();
            x[i] = coals[i].getPrice()+coals[i].getTransFare()+coals[i].getUnloadFare()+coals[i].getOtherFare();
        }
        a[0][a[0].length-1] = totalSulfurTop;
        a[1][a[1].length-1] = totalCalorificTop;
        a[2][a[2].length-1] = totalCoal;
        a[3][a[3].length-1] = totalSulfurBtm;
        a[4][a[4].length-1] = totalCalorificBtm;

        //两个数组约束条件合并
        double[][] finalArr = new double[num+5][num+1];
        for(int i=0;i<3;i++){
            for(int j=0;j<finalArr[i].length;j++){
                finalArr[i][j] = a[i][j];
            }
        }

        for(int i=3;i<finalArr.length-2;i++){
            for(int j=0;j<finalArr[i].length;j++){
                finalArr[i][j] = tmp[i-3][j];
            }
        }

        finalArr[finalArr.length-2] = a[3];
        finalArr[finalArr.length-1] = a[4];

        LinearProgram lp = new LinearProgram(-1,num+5,num,2,1,num+2,finalArr,x);
        double[] solve = lp.solve();
        System.out.println("打印完毕！！！");
        return solve;


    }


    //处理输入
    public static Coal[] handlInput(){
        Scanner sr = new Scanner(System.in);
        //输入总的目标
        System.out.println("请输入采购量总目标、硫份总目标、热值总目标、硫份允许偏差值、热值允许偏差值：");
        totalCoal = sr.nextDouble();
        double averageSulfur = sr.nextDouble();
        double averageCalorific = sr.nextDouble();
        double sulfurOffset = sr.nextDouble();
        double CalorificOffset = sr.nextDouble();
        totalSulfurTop = (averageSulfur+sulfurOffset)*totalCoal;
        totalSulfurBtm = (averageSulfur-sulfurOffset)*totalCoal;
        totalCalorificTop = (averageCalorific+CalorificOffset)*totalCoal;
        totalCalorificBtm = (averageCalorific-CalorificOffset)*totalCoal;

        //煤场
        System.out.println("******************煤场****************");
        System.out.println("请输入煤场煤种数：");
        int yardNum = sr.nextInt();

        Coal[] yardCoals = new Coal[yardNum];
        for(int i=0;i<yardCoals.length;i++){
            System.out.println("请输入煤场第"+(i+1)+"种煤信息，依次为：名称、热值、硫份、存量");
            Coal tmpCoal1 = new Coal();
            tmpCoal1.setName(sr.next());
            tmpCoal1.setCalorific(sr.nextDouble());
            tmpCoal1.setSulfur(sr.nextDouble());
            tmpCoal1.setStock(sr.nextDouble());
            yardCoals[i] = tmpCoal1;
        }

        //需采购的煤种信息
        System.out.println("******************采购****************");
        System.out.println("请输入需要采购的煤种数：");
        num = sr.nextInt();

        Coal[] coals = new Coal[num];
        for(int i=0;i<coals.length;i++){
            System.out.println("请输入第"+(i+1)+"种煤信息，依次为：名称、类型、是否分仓、热值、硫份、平仓价、运费、卸煤费、其他费用、最小吨位数");
            Coal tmpCoal2 = new Coal();
            tmpCoal2.setName(sr.next());
            tmpCoal2.setType(sr.nextInt());
            tmpCoal2.setIsPart(sr.nextInt());
            tmpCoal2.setCalorific(sr.nextDouble());
            tmpCoal2.setSulfur(sr.nextDouble());
            tmpCoal2.setPrice(sr.nextDouble());
            tmpCoal2.setTransFare(sr.nextDouble());
            tmpCoal2.setUnloadFare(sr.nextDouble());
            tmpCoal2.setOtherFare(sr.nextDouble());
            tmpCoal2.setAmount(sr.nextDouble());
            if(tmpCoal2.getIsPart()==1){
                tmpCoal2.setAmount(tmpCoal2.getAmount()/2);
            }
            coals[i] = tmpCoal2;
        }
        handleYardCoal(yardCoals);
        return coals;

    }

    //处理煤场煤
    private static void handleYardCoal(Coal[] yardCoals) {
        System.out.println("******************处理煤场煤****************");
        totalYardCoal = 0;
        totalYardSulfur = 0;
        totalYardCalorific = 0;
        for(int i=0;i<yardCoals.length;i++){
            totalYardCoal = totalYardCoal + yardCoals[i].getStock();
            totalYardSulfur = totalYardSulfur + yardCoals[i].getSulfur()*yardCoals[i].getStock();
            totalYardCalorific = totalYardCalorific + yardCoals[i].getCalorific()*yardCoals[i].getStock();
        }

        totalCoal = totalCoal - totalYardCoal;
        totalSulfurTop = totalSulfurTop - totalYardSulfur;
        totalSulfurBtm = totalSulfurBtm - totalYardSulfur;

        totalCalorificTop = totalCalorificTop - totalYardCalorific;
        totalCalorificBtm = totalCalorificBtm - totalYardCalorific;
    }


    //获取这种类型下的最低价 可能是无界解等
    public static double[] handlePrice1(List<Coal> list, int num){

        int gtNum = 0;
        //对list进行遍历 找出大于0的有多少个
        for (Coal coal : list) {
            if(coal.getAmount()>0.1){
                gtNum++;
            }
        }

        double[][] tmp = new double[num][num+1];
        //tmp里面原来的就都是0 如果里面不等于0 那么就要大于它
        for(int i=0;i<tmp.length;i++){
            tmp[i][i]=1;
            if(list.get(i).getAmount()>0.1){
                tmp[i][num] = list.get(i).getAmount();
            }
        }

        //对数组进行按列排序 最后一行为0的放在最前面
        sortByColumn(tmp, new int[]{num});

        double[][] a = new double[5][num+1];
        double x[] = new double[num];
        for(int i=0;i<list.size();i++){
            a[0][i] = list.get(i).getSulfur();
            a[1][i] = list.get(i).getCalorific();
            a[2][i] = 1;
            a[3][i] = list.get(i).getSulfur();
            a[4][i] = list.get(i).getCalorific();
            x[i] = list.get(i).getPrice()+list.get(i).getTransFare()+list.get(i).getUnloadFare()+list.get(i).getOtherFare();
        }
        a[0][a[0].length-1] = totalSulfurTop;
        a[1][a[1].length-1] = totalCalorificTop;
        a[2][a[2].length-1] = totalCoal;
        a[3][a[3].length-1] = totalSulfurBtm;
        a[4][a[4].length-1] = totalCalorificBtm;

        //两个数组约束条件合并
        double[][] finalArr = new double[num+5][num+1];
        for(int i=0;i<3;i++){
            for(int j=0;j<finalArr[i].length;j++){
                finalArr[i][j] = a[i][j];
            }
        }

        for(int i=3;i<finalArr.length-2;i++){
            for(int j=0;j<finalArr[i].length;j++){
                finalArr[i][j] = tmp[i-3][j];
            }
        }

        finalArr[finalArr.length-2] = a[3];
        finalArr[finalArr.length-1] = a[4];


        System.out.println("*************************");
        for(int i=0;i<finalArr.length;i++){
            for(int j=0;j<finalArr[i].length;j++){
                System.out.print(finalArr[i][j]+"  ");
            }
            System.out.println();
        }


        LinearProgram lp = new LinearProgram(-1,num+5,num,2,num-gtNum+1,gtNum+2,finalArr,x);
        double[] solve = lp.solve();
        System.out.println("打印完毕！！！");
        return solve;

    }

    /**
     * 按列排序
     * @param ob 待排序的数组
     * @param order 列排序的优先级， 如：new int{1, 2} 先根据第一列比较，若相同则再比较第二列
     */
    public static void sortByColumn(double[][] ob, final int[] order) {
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


    /**
     * 组合
     * @param start
     * @param k
     * @param arr
     * @return
     */
    public static List<List<Integer>> combine(int start, int k, int arr[]){
        List<List<Integer>> retList = new ArrayList<>();
        List<Integer> tmpList = new ArrayList<>();
        recursion(retList, tmpList, start, k, arr);
        return retList;
    }

    /**
     * 递归算法
     * 按一定的顺序取出元素，就是组合,元素个数[C arr.len 3]
     *
     * @param index 元素位置
     * @param k     选取的元素个数
     * @param arr   数组
     */
    public static void recursion(List<List<Integer>> retList, List<Integer> tmpList, int index, int k, int[] arr) {
        if (k == 1) {
            for (int i = index; i < arr.length; i++) {
                tmpList.add(arr[i]);
                retList.add(new ArrayList<>(tmpList));
                tmpList.remove((Object) arr[i]);
            }
        } else if (k > 1) {
            for (int i = index; i <= arr.length - k; i++) {
                tmpList.add(arr[i]); //tmpArr都是临时性存储一下
                recursion(retList, tmpList, i + 1, k - 1, arr); //索引右移，内部循环，自然排除已经选择的元素
                tmpList.remove((Object) arr[i]); //tmpArr因为是临时存储的，上一个组合找出后就该释放空间，存储下一个元素继续拼接组合了
            }
        }
    }

    /**
     *
     * @param map 哪些煤取0
     * @param num 煤种数量
     */
    public static List<List<Coal>> handleCoal(Coal[] coals, Map<Integer, List<List<Integer>>> map, int num){

        List<List<Coal>> retList = new ArrayList<>();

        System.out.println(totalCoal + "---->" + totalSulfurBtm + "---->" + totalCalorificBtm);
        System.out.println(totalCoal + "---->" + totalSulfurTop + "---->" + totalCalorificTop);

        //如果用户没有输入偏差值，则硫份和热值最大最小值会相等，可能会引起错误，在这里进行判断，如果相等
        //则总热值最大加10，总硫份最小值减小0.01
        if(totalCalorificBtm == totalCalorificTop){
            totalCalorificTop = totalCalorificTop + 10;
            if(totalCalorificBtm > totalCalorificTop){
                throw new RuntimeException("输入热值数据有误！！！");
            }
        }
        if(totalSulfurBtm == totalSulfurTop){
            totalSulfurBtm = totalSulfurBtm -0.01;
            if(totalSulfurBtm > totalSulfurTop){
                throw new RuntimeException("输入硫份数据有误！！！");
            }
        }

        System.out.println("=========================以下处理煤=============================");

        for(int i=1;i<num;i++){
            List<List<Integer>> list = map.get(i);
            for (List<Integer> listArr : list) {
                Coal[] tmpCoals = new Coal[coals.length];
                tmpCoals = coals.clone();
                deep_clone(tmpCoals);
                for (Integer integer : listArr) {
                    tmpCoals[integer].setAmount(0.0);
                }
                System.out.println("*******************************************");
                for (Coal coal : tmpCoals) {
                    System.out.println(coal);
                }
                Coal[] arr = new Coal[tmpCoals.length];
                arr = tmpCoals.clone();
                deep_clone(arr);
                retList.add(Arrays.asList(arr));
                count++;
            }
        }
        System.out.println(count);
        return retList;

    }

    //对象数组深拷贝
    public static void deep_clone(Coal[] coals){
        for(int i=0;i<coals.length;i++){
            coals[i] = (Coal) coals[i].clone();
        }
    }

}