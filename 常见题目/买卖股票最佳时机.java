//给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
//如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润

// 时间复杂度o(n),空间o(1)
//只要用一个变量记录一个历史最低价格 minprice，我们就可以假设自己的股票是在那天买的。
//那么我们在第 i 天卖出股票能得到的利润就是 prices[i] - minprice。
//因此，我们只需要遍历价格数组一遍，记录历史最低点，
//然后在每一天考虑这么一个问题：如果我是在历史最低点买进的，那么我今天卖出能赚多少钱？
//当考虑完所有天数之时，我们就得到了最好的答案

public class Solution {
    public int maxProfit(int prices[]) {
        int minprice = Integer.MAX_VALUE;
        int maxprofit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minprice) {
                minprice = prices[i];
            } else if (prices[i] - minprice > maxprofit) {
                maxprofit = prices[i] - minprice;
            }
        }
        return maxprofit;
    }
}



//给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
//设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）

// 时间复杂度o(n), 空间o(1)
// 贪心
class Solution {
    public int maxProfit(int[] prices) {
        int ans = 0;
        int n = prices.length;
        for (int i = 1; i < n; ++i) {
            ans += Math.max(0, prices[i] - prices[i - 1]);
        }
        return ans;
    }
}

//给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
//设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。

// 动态规划
// 即求完第i天的值后，就不需要第i-1天的数据了，dp数组是不断滚动更新的，所以我们可以用5个变量来代替二维数组。

//dp0：初始化状态
//dp1：第一次买入
//dp2：第一次卖出
//dp3：第二次买入
//dp4：第二次卖出


class Solution {
    public int maxProfit(int[] prices) {
        if(prices==null || prices.length==0) {
            return 0;
        }
        int n = prices.length;
        //定义5种状态，并初始化第一天的状态
        int dp0 = 0;
        int dp1 = -prices[0];
        int dp2 = 0;
        int dp3 = -prices[0];
        int dp4 = 0;
        for(int i=1;i<n;++i) {
            //这里省略dp0，因为dp0每次都是从上一个dp0来的相当于每次都是0
            //处理第一次买入、第一次卖出
            dp1 = Math.max(dp1,dp0-prices[i]);
            dp2 = Math.max(dp2,dp1+prices[i]);
            //处理第二次买入、第二次卖出
            dp3 = Math.max(dp3,dp2-prices[i]);
            dp4 = Math.max(dp4,dp3+prices[i]);
        }
        //返回最大值
        return Math.max(0,Math.max(Math.max(dp1,dp2),Math.max(dp3,dp4)));
    }
}



//给定一个整数数组 prices ，它的第 i 个元素 prices[i] 是一支给定的股票在第 i 天的价格。
//设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。

// 动态规划
// 第一次买入：从初始状态转换而来，或者第一次买入后保持不动
//dp[i][0][1] = max(dp[i-1][0][1],dp[i-1][0][0]-prices[i])

//第一次卖出：从第一次买入转换而来，或者第一次卖出后保持不动
//dp[i][1][0] = max(dp[i-1][1][0],dp[i-1][0][1]+prices[i])

//第二次买入：从第一次卖出转换而来，或者第二次买入后保持不动
//dp[i][1][1] = max(dp[i-1][1][1],dp[i-1][1][0]-prices[i])

//第二次卖出：从第二次买入转换而来，或者第二次卖出后保持不动
//dp[i][2][0] = max(dp[i-1][2][0],dp[i-1][1][1]+prices[i])

//第三次买入：
//dp[i][2][1] = max(dp[i-1][2][1],dp[i-1][2][0]-prices[i])

//第三次卖出：   
//dp[i][3][0] = max(dp[i-1][3][0],dp[i-1][2][1]+prices[i])

//第k次买入： 从第k-1次卖出转换而来，或者第k次买入后保持不动
//dp[i][j-1][1] = max(dp[i-1][j-1][1],dp[i-1][j-1][0]-prices[i])

//第k次卖出： 从第k次买入后转换而来，或者是第k次卖出后保持不动
//dp[i][j][0] = max(dp[i-1][j][0],dp[i-1][j-1][1]+prices[i])

//第k+1次买入：
//dp[i][j][1] = max(dp[i-1][j][1],dp[i-1][j][0]-prices[i])

//我们用两个循环来计算这个推导：

//外层的循环是n，即遍历数组
//内层的循环是k，遍历k次交易
//最后求的利润最大值就保存在dp[n-1][k][0]中，我们直接返回即可。


class Solution {
    public int maxProfit(int k, int[] prices) {
        if(prices==null || prices.length==0) {
            return 0;
        }
        int n = prices.length;
        //当k非常大时转为无限次交易
        if(k>=n/2) {
            int dp0=0,dp1=-prices[0];
            for(int i=1;i<n;++i) {
                int tmp = dp0;
                dp0 = Math.max(dp0,dp1+prices[i]);
                dp1 = Math.max(dp1,dp0-prices[i]);
            }
            return Math.max(dp0,dp1);
        }
        //定义二维数组，交易了多少次、当前的买卖状态  
        int[][] dp = new int[k+1][2];
        int res = 0;
        for(int i=0;i<=k;++i) {
            dp[i][0] = 0;
            dp[i][1] = -prices[0]; 
        }
        for(int i=1;i<n;++i) {
            for(int j=k;j>0;--j) {
                //处理第k次买入
                dp[j-1][1] = Math.max(dp[j-1][1], dp[j-1][0]-prices[i]);
                //处理第k次卖出
                dp[j][0] = Math.max(dp[j][0], dp[j-1][1]+prices[i]);

            }
        }
        return dp[k][0];
    }
}






















