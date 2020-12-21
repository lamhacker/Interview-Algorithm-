// 给定不同面额银币和总金额，求可以凑成总金额的最少硬币个数

//动态规划 - 自上而下
//时间复杂度o(sn) s为金额，n为面额数。我们一共需要计算 SS 个状态的答案，且每个状态 F(S)F(S) 由于上面的记忆化的措施只计算了一次，而计算一个状态的答案需要枚举 nn 个面额值，所以一共需要 O(Sn)O(Sn) 的时间复杂度。
//空间复杂度：O(S)O(S)，我们需要额外开一个长为 SS 的数组来存储计算出来的答案 F(S)F(S)
// F(S): 组成金额s所需的最少硬币数量
// [c0...cn-1]： 可选的n枚硬币面额值
// 就是问题由一个最优子结构，最优解可以从子问题的最优解构造出来，假设
// 我们知道f(s),也就是组成金额s最少的硬币数量，最后一枚面值为c，
// 那么转移方程为f(s) = f(s-c) + 1
// f(s) = min f(s-ci)+1  f(s) = 0 when s = 0, f(s) = -1 when n = 0
public class Solution {
    public int coinChange(int[] coins, int amount) {
        if (amount < 1) {
            return 0;
        }
        return coinChange(coins, amount, new int[amount]);
    }

    private int coinChange(int[] coins, int rem, int[] count) {
        if (rem < 0) {
            return -1;
        }
        if (rem == 0) {
            return 0;
        }
        if (count[rem - 1] != 0) {
            return count[rem - 1];
        }
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = coinChange(coins, rem - coin, count);
            if (res >= 0 && res < min) {
                min = 1 + res;
            }
        }
        count[rem - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
        return count[rem - 1];
    }
}

// 动态规划 自下而上
// 时间复杂度：O(Sn)O(Sn)，其中 SS 是金额，nn 是面额数。我们一共需要计算 O(S)O(S) 个状态，SS 为题目所给的总金额。对于每个状态，每次需要枚举 nn 个面额来转移状态，所以一共需要 O(Sn)O(Sn) 的时间复杂度。
// 空间复杂度：O(S)O(S)。DP 数组需要开长度为总金额 SS 的空间。

//就定义f(i)为组成金额i所需最少的硬币数量，假设在计算f(i)之前已经计算出了
// f(0)-f(i-1),所以f(i) = min(f(i-cj) + 1)
// 其中cj代表的是第j枚硬币的面值，就是我们枚举最后一枚硬币面额为cj，那么需要从i-cj这个
// 金额状态f(i-cj)再算上枚举的这枚硬币数量 11 的贡献，由于要硬币数量最少，所以 F(i)F(i) 为前面能转移过来的状态的最小值加上枚举的硬币数量 11 。
// 例子 假设 coins = [1,2,5], amount=11
// f(1)        最小硬币数量
// f(0)        0 金额为0不能由硬币组成
// f(1)        1 F(1) = min(F(1-1), F(1-2), F(1-5)) + 1 = 1
// f(2)        1 F(2) = min(F(2-1), F(2-2), F(2-5)) + 1 = 1
// f(3)        2 F(3) = min(F(3-1), F(3-2), F(3-5)) + 1 = 2
// f(4)        2 F(4) = min(F(4-1), F(4-2), F(4-5)) + 1 = 2
// ...
// f(11)       3 F(11) = min(F(11-1), F(11-2), F(11-5)) + 1 = 3
public class Solution {
    public int coinChange(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
}

// 第二题
// 给定不同面额的硬币和总金额，求出凑成总金额的硬币组合数
// 动态规划 时间复杂度o(n * amount), 其中n为coins数组的长度， 空间复杂度o(amount)
// 以基本情况没有***开始组合数量。dp[0] = 1，然后其余等于 0。
// 遍历所有***面值：
// 对于每个***，我们将从金额 0 遍历到 amount：
// 对于每个 x，计算组合数：dp[x] += dp[x - coin]。
// 返回 dp[amount]。


class Solution {
  public int change(int amount, int[] coins) {
    int[] dp = new int[amount + 1];
    dp[0] = 1;

    for (int coin : coins) {
      for (int x = coin; x < amount + 1; ++x) {
        dp[x] += dp[x - coin];
      }
    }
    return dp[amount];
  }
}





















