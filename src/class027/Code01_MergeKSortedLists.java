package class027;

import java.util.ArrayList;
import java.util.PriorityQueue;

// 合并K个有序链表
// 测试链接：https://www.nowcoder.com/practice/65cfde9e5b9b4cf2b6bafa5f3ef33fa6
public class Code01_MergeKSortedLists {

	// 不要提交这个类
	public static class ListNode {
		public int val;
		public ListNode next;
	}

	// 提交以下的方法
	public static ListNode mergeKLists(ArrayList<ListNode> arr) {
		// 小根堆 因为比较策略是谁小谁在堆顶
		PriorityQueue<ListNode> heap = new PriorityQueue<>((a, b) -> a.val - b.val);
		for (ListNode h : arr) {
			// 遍历所有的头！
			if (h != null) {
				heap.add(h);
			}
		}
		if (heap.isEmpty()) {
			return null;
		}
		// 先弹出一个节点，做总头部 最后会返回这个
		ListNode h = heap.poll();
		ListNode pre = h;
		// 把弹出节点的下一个节点加入堆
		if (pre.next != null) {
			heap.add(pre.next);
		}
		while (!heap.isEmpty()) {
			ListNode cur = heap.poll();
			pre.next = cur;
			pre = cur;
			if (cur.next != null) {
				heap.add(cur.next);
			}
		}
		return h;
	}

}
