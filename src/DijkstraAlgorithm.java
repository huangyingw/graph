/* 
 * author:Tammy Pi 
 * function:单源最短路径Dijkstra 
 */
public class DijkstraAlgorithm {

	private AdMatrixGraph graph;
	// 用于存放S集合
	private int[] s = null;
	// 当前节点
	private int currentNode = 0;
	// 目的节点
	private int targetNode = 0;
	// 用于记录
	private int[] pre;
	// 用于记录各个节点与当前节点的距离
	private int[] record;
	private int index = -1;
	private int index1 = -1;

	public void createTree(int nodenum, int cirnum) {
		graph = new AdMatrixGraph(nodenum, cirnum);
		s = new int[graph.getNodenum()];
		pre = new int[graph.getNodenum()];
		record = new int[graph.getNodenum()];

		int[][] kk = new int[this.graph.getNodenum()][this.graph.getNodenum()];
		for (int i = 0; i < kk.length; i++) {
			for (int j = 0; j < kk.length; j++) {
				kk[i][j] = Integer.MAX_VALUE;
			}
		}
		this.graph.setMatrix(kk);
		this.graph.add(0, 1, 4);
		this.graph.add(0, 4, 1);
		this.graph.add(0, 3, 5);
		this.graph.add(4, 1, 1);
		this.graph.add(1, 2, 1);
		this.graph.add(2, 3, 1);

		// 源节点为0，目标节点为2
		this.currentNode = 0;
		this.targetNode = 2;
		s[++index] = 0;
		for (int i = 0; i < this.graph.getNodenum(); i++) {
			pre[i] = -1;
		}
		for (int i = 0; i < this.graph.getNodenum(); i++) {
			record[i] = Integer.MAX_VALUE;
		}
		record[0] = 0;
	}

	public boolean isContain(int j) {
		for (int i = 0; i <= index; i++) {
			if (s[i] == j) {
				return true;
			}
		}
		return false;
	}

	// 找到最近的点
	public int getNearest() {
		int minIndex = -1;
		int minValue = Integer.MAX_VALUE;
		for (int i = 0; i < this.graph.getNodenum(); i++) {
			if (isContain(i)) {
				continue;
			}
			if (record[i] < minValue) {
				minValue = record[i];
				minIndex = i;
			}
		}
		return minIndex;
	}

	// Dijkstra算法
	public void dijkstra() {
		while (!(index == this.graph.getNodenum() - 1)) {
			for (int i = 0; i < this.graph.getNodenum(); i++) {
				if (i != currentNode
						&& !isContain(i)
						&& this.graph.getMatrix()[currentNode][i] != Integer.MAX_VALUE) {
					if (record[currentNode]
							+ this.graph.getMatrix()[currentNode][i] < record[i]) {
						record[i] = record[currentNode]
								+ this.graph.getMatrix()[currentNode][i];
						pre[i] = currentNode;
					}
				}// if i
			}// for i
			currentNode = getNearest();
			s[++index] = currentNode;
		}// while s

		// 输出
		System.out.println("Dijkstra算法求得单源最短路径：");
		for (int i = 1; i < this.graph.getNodenum(); i++) {
			System.out.println(i + "距离0最短距离：" + record[i] + "，它的前驱为：" + pre[i]);
		}
		System.out.println();
	}

	public static void main(String[] args) {
		DijkstraAlgorithm dijkstra = new DijkstraAlgorithm();
		dijkstra.createTree(5, 6);
		dijkstra.dijkstra();
	}
}
