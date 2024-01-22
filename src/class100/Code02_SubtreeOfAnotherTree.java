package class100;

import java.util.ArrayList;

// 测试链接 : https://leetcode.cn/problems/subtree-of-another-tree/
public class Code02_SubtreeOfAnotherTree {

	// 不要提交这个类
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
	}

	// 方法1
	public static boolean isSubtree(TreeNode t1, TreeNode t2) {
		if (t1 != null && t2 != null) {
			return sameTree(t1, t2) || isSubtree(t1.left, t2) || isSubtree(t1.right, t2);
		}
		return t2 == null;
	}

	public static boolean sameTree(TreeNode t1, TreeNode t2) {
		if (t1 == null && t2 == null) {
			return true;
		}
		if (t1 != null && t2 != null) {
			return t1.val == t2.val && sameTree(t1.left, t2.left) && sameTree(t1.right, t2.right);
		}
		return false;
	}

	// 方法2
	public static boolean isSubtree2(TreeNode t1, TreeNode t2) {
		if (t1 != null && t2 != null) {
			ArrayList<String> s1 = new ArrayList<>();
			ArrayList<String> s2 = new ArrayList<>();
			serial(t1, s1);
			serial(t2, s2);
			return kmp(s1, s2) != -1;
		}
		return t2 == null;
	}

	public static void serial(TreeNode head, ArrayList<String> path) {
		if (head == null) {
			path.add(null);
		} else {
			path.add(String.valueOf(head.val));
			serial(head.left, path);
			serial(head.right, path);
		}
	}

	public static int kmp(ArrayList<String> s1, ArrayList<String> s2) {
		int n = s1.size(), m = s2.size(), x = 0, y = 0;
		int[] next = nextArray(s2, m);
		while (x < n && y < m) {
			if (isEqual(s1.get(x), s2.get(y))) {
				x++;
				y++;
			} else if (y == 0) {
				x++;
			} else {
				y = next[y];
			}
		}
		return y == m ? x - y : -1;
	}

	public static int[] nextArray(ArrayList<String> s, int m) {
		if (m == 1) {
			return new int[] { -1 };
		}
		int[] next = new int[m];
		next[0] = -1;
		next[1] = 0;
		int i = 2, cn = 0;
		while (i < next.length) {
			if (isEqual(s.get(i - 1), s.get(cn))) {
				next[i++] = ++cn;
			} else if (cn > 0) {
				cn = next[cn];
			} else {
				next[i++] = 0;
			}
		}
		return next;
	}

	public static boolean isEqual(String a, String b) {
		if (a == null && b == null) {
			return true;
		}
		if (a != null && b != null) {
			return a.equals(b);
		}
		return false;
	}

}