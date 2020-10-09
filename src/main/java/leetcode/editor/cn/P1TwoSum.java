//给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。 
//
// 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。 
//
// 
//
// 示例: 
//
// 给定 nums = [2, 7, 11, 15], target = 9
//
//因为 nums[0] + nums[1] = 2 + 7 = 9
//所以返回 [0, 1]
// 
// Related Topics 数组 哈希表 
// 👍 8709 👎 0


package leetcode.editor.cn;
//Java：两数之和
public class P1TwoSum{
    public static void main(String[] args) {
        Solution solution = new P1TwoSum().new Solution();
        // TO TEST
        int [] a = {2, 7, 11, 15, 3, 6, 7, 2};
        int target = 9;
        int[] ints = solution.twoSum(a, target);
        for (int anInt : ints) {
            System.out.println(anInt);
        }
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] a = new int[2];
        for(int i = 0; i < nums.length; i++){
            for(int j = i + 1; j < nums.length; j++ ){
                if(nums[i] + nums[j] == target){
                    a[0] = i;
                    a[1] = j;
                }
            }
        }
        return a;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}