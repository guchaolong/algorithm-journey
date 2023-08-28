package class055;

// 最大矩形
// 给定一个仅包含 0 和 1 、大小为 rows * cols 的二维二进制矩阵
// 找出只包含 1 的最大矩形，并返回其面积
// 测试链接：https://leetcode.cn/problems/maximal-rectangle/
public class Code06_MaximalRectangle {

	public static int maximalRectangle(char[][] map) {
		int n = map.length;
		int m = map[0].length;
		int[] height = new int[m];
		int ans = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				height[j] = map[i][j] == '0' ? 0 : height[j] + 1;
			}
			ans = Math.max(largestRectangleArea(height), ans);
		}
		return ans;
	}

	public static int largestRectangleArea(int[] height) {
		int n = height.length;
		int[] stack = new int[n];
		int r = 0;
		int ans = 0;
		for (int i = 0; i < height.length; i++) {
			while (r != 0 && height[i] <= height[stack[r - 1]]) {
				int j = stack[--r];
				int k = r == 0 ? -1 : stack[r - 1];
				int curArea = (i - k - 1) * height[j];
				ans = Math.max(ans, curArea);
			}
			stack[r++] = i;
		}
		while (r > 0) {
			int j = stack[--r];
			int k = r == 0 ? -1 : stack[r - 1];
			int curArea = (height.length - k - 1) * height[j];
			ans = Math.max(ans, curArea);
		}
		return ans;
	}

}
