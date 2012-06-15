/* 
 * author:Tammy Pi 
 * function:图的邻接矩阵表示，即DFS和BFS 
 */
public class BFSVSDFS {

	private AdMatrixGraph graph;
	private int[] visited = null;
	private MyQueue queue = null;

	public BFSVSDFS(AdMatrixGraph graph) {
		this.graph = new AdMatrixGraph(graph.getNodenum(), graph.getCirnum());

		visited = new int[this.graph.getNodenum()];
		queue = new MyQueue();

		this.graph.add(0, 1);
		this.graph.add(0, 2);
		this.graph.add(0, 3);
		this.graph.add(1, 5);
		this.graph.add(5, 4);
		this.graph.add(2, 6);
		this.graph.add(3, 7);
	}

	// DFS
	public void DFSTraverse(int i) {
		queue.addElement(i);

		while (!queue.isEmtpy()) {
			int node = queue.removeElement();
			visited[i] = 1;
			System.out.print(i + " ");

			for (int j = 0; j < graph.getNodenum(); j++) {
				if (graph.getMatrix()[node][j] == 1 && visited[j] == 0) {
					DFSTraverse(j);
				}
			}
		}
	}// DFSTraverse

	public void DFS() {
		// 初始化，设置所有节点均未访问过
		for (int i = 0; i < graph.getNodenum(); i++) {
			visited[i] = 0;
		}
		queue.clear();

		// 开始遍历
		for (int i = 0; i < graph.getNodenum(); i++) {
			if (visited[i] == 0) {
				DFSTraverse(i);
			}
		}
	}// DFS

	// BFS
	public Integer getNearNode(int i) {
		for (int j = 0; j < this.graph.getNodenum(); j++) {
			if (this.graph.getMatrix()[j][i] == 1 && visited[j] == 0) {
				return j;
			}
		}
		return null;
	}

	public void BFS() {
		// 初始化，设置node未访问
		for (int i = 0; i < this.graph.getNodenum(); i++) {
			visited[i] = 0;
		}
		queue.clear();

		// 广度遍历
		queue.addElement(0);
		System.out.print("0 ");
		visited[0] = 1;

		while (!queue.isEmtpy()) {
			int node = queue.removeElement();
			// 遍历它的所有节点
			for (Integer j = getNearNode(node); j != null; j = getNearNode(node)) {
				System.out.print(j + " ");
				visited[j] = 1;
				queue.addElement(j);
			}
		}
	}

	// 用于测试的主函数
	public static void main(String[] args) {
		AdMatrixGraph graph = new AdMatrixGraph(8, 7);
		BFSVSDFS bfs = new BFSVSDFS(graph);
		System.out.print("深度遍历的结果为：");
		bfs.DFS();
		System.out.println();
		System.out.print("广度遍历的结果为：");
		bfs.BFS();
	}
}