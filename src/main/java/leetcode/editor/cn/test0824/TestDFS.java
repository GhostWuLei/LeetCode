package leetcode.editor.cn.test0824;

import com.sun.org.apache.xalan.internal.xsltc.dom.SortingIterator;
import leetcode.editor.cn.test0814.Coal;

public class TestDFS {

    public static void main(String[] args) {

        int num = 9;//输入9 0-8
        int a[] = new int[num];
        for(int i=0;i<a.length;i++){
            a[i] = i;
        }

//        for(int i=0;i<a.length;i++){
//            System.out.println(a[i]);
//        }





    }


//    private static void dfs(int[] input, int[] output, int index, int start) {
//        if(index == output.length){
//            Coal[] arr = new Coal[output.length];
//            for(int i=0;i<output.length;i++){
//                arr[i] = output[i];
//            }
//            list.add(arr);
//            // System.out.println(Arrays.toString(output));
//        }else{
//            for(int j=start;j<input.length;j++){
//                output[index]=input[j];
//                dfs(input, output, index + 1, j + 1);
//            }
//        }
//    }
}
