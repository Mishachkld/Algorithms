package SecondSemester.Lab18;

public class Lab18 {
    public static boolean subsetSum(int[] nums, int summ) {
        int n = nums.length;
        boolean[][] dp = new boolean[n + 1][summ + 1];

        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= summ; j++) {
                if (nums[i - 1] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
                }
            }
        }

        return dp[n][summ];
    }

    public static void main(String[] args) {
        int[] nums = {3, 34, 4, 12, 5, 2};
        int summ = 9;
        System.out.println(subsetSum(nums, summ));
    }

}
