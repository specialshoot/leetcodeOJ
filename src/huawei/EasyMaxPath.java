package huawei;

import java.util.Scanner;

/**
 * 字串的连接最长路径
 * 
 * 描述 给定n个字串，其中一个字串的后m个字符，是下一个字串的开始。 找出所有字串中的能连接起来的最长路径。 知识点 查找,排序 运行时间限制 10M
 * 内存限制 128
 * 
 * 样例输入 ABCC ABCD BCCE BCDE CCEF BCCE CCEG CEGF
 * 
 * 样例输出 ABCCEGF
 * 
 * 解析: 这个题目可以理解为寻找最长路径，并记录下来这条路径。 首先第一部是判断那两个点之间是可以联通的，对应题目就是，判断那两个字符串可以首尾匹配；
 * 其次，从这些可以联通的点之间，找到一条最长的路径： 1、从A点开始遍历，当A跟B可以联通，此时路径长度为A-B；
 * 2、再从B点开始遍历，当B跟C可以联通，记录下来，此时路径长度为A-B-C； 3、将所有可以联通的路径长度都记录下来，然后找出最长的那条
 * 
 * @author han
 *
 */
public class EasyMaxPath {

	private static String longestPath(String[] input) {
		int[][] path = new int[input.length][input.length]; // 定义数组记录可以连接的字串
		String[][] connectString = new String[input.length][input.length]; // 定义数组存储可以连接字串的尾部

		for (int i = 0; i < input.length; i++) {
			for (int j = i + 1; j < input.length; j++) {
				if (input[j].indexOf(input[i].substring(1)) == 0) { // 当一个字串从index为1开始的一段，是在另一个字串index=0的部分
					path[i][j] = 1; // 记录这两个字串的坐标
					connectString[i][j] = input[j].substring(input[j].length() - 1); // 记录截取的末段字串
				}
			}
		}

		for (int i = 0; i < path.length; i++) {
			for (int j = 0; j < path.length; j++) {
				for (int k = 0; k < path.length; k++) {
					if (path[i][j] != 0 && path[j][k] != 0) { // 如果input[j]可以和input[i]和input[k]前后相接
						int dist = path[i][j] + path[j][k]; // 记录下这段路径长度
						if (dist > path[i][k]) { // 如果这段路径大于原始的距离则数值交换
							path[i][k] = dist;
							connectString[i][k] = connectString[i][j] + connectString[j][k]; // 连接input[j]和input[k]的末段
						}
					}
				}
			}
		}

		int max = 0;
		int start = 0;
		int end = 0;
		for (int i = 0; i < path.length; i++)
			for (int j = 0; j < path.length; j++) { // 遍历找出最长的路径
				if (max < path[i][j]) {
					max = path[i][j]; // 记录路径的起始和末位坐标
					start = i;
					end = j;
				}
			}

		String result = null;
		if (start != end)
			result = input[start] + connectString[start][end];// 将其实的字串和后面相接的末段连接在一起
		return result;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNext()) {
			String str = scanner.nextLine();
			String[] strs = str.split(" ");
			System.out.println(longestPath(strs));
		}
		scanner.close();
	}

}
