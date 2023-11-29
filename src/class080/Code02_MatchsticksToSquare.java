package class080;

// 火柴拼正方形
// 你将得到一个整数数组 matchsticks
// 其中 matchsticks[i] 是第 i 个火柴棒的长度
// 你要用 所有的火柴棍 拼成一个正方形
// 你 不能折断 任何一根火柴棒，但你可以把它们连在一起，而且每根火柴棒必须 使用一次
// 如果你能拼出正方形，则返回 true ，否则返回 false
// 测试链接 : https://leetcode.cn/problems/matchsticks-to-square/
public class Code02_MatchsticksToSquare {

	public static boolean makesquare(int[] matchsticks) {
		int sum = 0;
		for (int num : matchsticks) {
			sum += num;
		}
		if ((sum & 3) != 0) {
			return false;
		}
		int[] dp = new int[1 << matchsticks.length];
		return f(matchsticks, 0, 0, sum >> 2, 4, dp);
	}

	public static boolean f(int[] arr, int s, int cur, int len, int edges, int[] dp) {
		if (dp[s] != 0) {
			return dp[s] == 1;
		}
		boolean ans = false;
		if (edges == 0) {
			ans = s == (1 << arr.length) - 1;
		} else {
			for (int i = 0; i < arr.length && !ans; i++) {
				if (((1 << i) & s) == 0 && cur + arr[i] <= len) {
					if (cur + arr[i] == len) {
						ans |= f(arr, s | (1 << i), 0, len, edges - 1, dp);
					} else {
						ans |= f(arr, s | (1 << i), cur + arr[i], len, edges, dp);
					}
				}
			}
		}
		dp[s] = ans ? 1 : -1;
		return ans;
	}

}