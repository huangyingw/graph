/* 
 * author:Tammy Pi 
 * function:最小生成树算法 
 */
public class PrimAlgorithm {

	// 用于测试的主函数
	public static void main(String[] args) {
		PrimAlgorithm prim = new PrimAlgorithm();
		prim.createTree(4, 4);
		prim.prim();
	}

	private AdMatrixGraph graph;
	private int index = -1;

	private int[] s = null;

	public void createTree(int nodenum, int cirnum) {
		graph = new AdMatrixGraph(nodenum, cirnum);
		s = new int[graph.getNodenum()];

		this.graph.add(0, 1, 1);
		this.graph.add(0, 3, 1);
		this.graph.add(0, 2, 3);
		this.graph.add(3, 2, 1);
	}

	public int getMinValue(int node) {
		int minValue = Integer.MAX_VALUE;
		for (int i = 0; i <= index; i++) {
			if (this.graph.getMatrix()[i][node] != 0
					&& this.graph.getMatrix()[i][node] < minValue) {
				minValue = this.graph.getMatrix()[i][node];
			}
		}
		return minValue;
	}

	public boolean isContain(int j) {
		for (int k = 0; k <= index; k++) {
			if (s[k] == j) {
				return true;
			}
		}
		return false;
	}

	public void prim() {
		// 首先将0节点加入集合S中
		s[++index] = 0;
		System.out.print("用Prim算法得到的最小生成树为：" + 0 + " ");

		while (!(index == graph.getNodenum() - 1)) {
			int minIndex = 0;
			int minValue = Integer.MAX_VALUE;
			// 计算与集合s中的点距离最小的点
			for (int j = 0; j < graph.getNodenum(); j++) {
				if (!isContain(j)) {
					if (getMinValue(j) < minValue) {
						minIndex = j;
						minValue = getMinValue(j);
					}
				}
			}
			System.out.print(minIndex + " ");
			s[++index] = minIndex;
		}
		System.out.println();
	}
}