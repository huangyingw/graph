import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Graph {
	// 存储节点信息
	private Object[] vertices;
	// 存储边的信息
	private int[][] arcs;
	private int vexnum;
	// 记录第i个节点是否被访问过
	private boolean[] visited;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Graph g = new Graph(8);
		Character[] vertices = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H' };
		g.addVertex(vertices);
		g.addEdge(0, 1);
		g.addEdge(0, 2);
		g.addEdge(1, 3);
		g.addEdge(1, 4);
		g.addEdge(3, 7);
		g.addEdge(4, 7);
		g.addEdge(2, 5);
		g.addEdge(2, 6);
		System.out.println("深度优先遍历：");
		g.depthTraverse();
		System.out.println();

		System.out.println("广度优先遍历：");
		g.broadTraverse();
		System.out.println();

		System.out.println("深度优先遍历非递归：");
		g.depth();
		System.out.println();
	}

	public Graph(int n) {
		vexnum = n;
		vertices = new Object[n];
		arcs = new int[n][n];
		visited = new boolean[n];
		for (int i = 0; i < vexnum; i++) {
			for (int j = 0; j < vexnum; j++) {
				arcs[i][j] = 0;
			}
		}

	}

	public void addVertex(Object[] obj) {
		this.vertices = obj;
	}

	public void addEdge(int i, int j) {
		if (i == j)
			return;
		arcs[i][j] = 1;
		arcs[j][i] = 1;
	}

	public int firstAdjVex(int i) {
		for (int j = 0; j < vexnum; j++) {
			if (arcs[i][j] > 0)
				return j;
		}
		return -1;
	}

	public int nextAdjVex(int i, int k) {
		for (int j = k + 1; j < vexnum; j++) {
			if (arcs[i][j] > 0)
				return j;
		}
		return -1;
	}

	// 深度优先遍历
	public void depthTraverse() {
		for (int i = 0; i < vexnum; i++) {
			visited[i] = false;
		}
		for (int i = 0; i < vexnum; i++) {
			if (!visited[i])
				traverse(i);
		}
	}

	// 一个连通图的深度递归遍历
	public void traverse(int i) {
		// TODO Auto-generated method stub
		visited[i] = true;
		visit(i);
		for (int j = this.firstAdjVex(i); j >= 0; j = this.nextAdjVex(i, j)) {
			if (!visited[j])
				this.traverse(j);
		}
	}

	// 广度优先遍历
	public void broadTraverse() {
		// LinkedList实现了Queue接口
		Queue<Integer> q = new LinkedList<Integer>();
		for (int i = 0; i < vexnum; i++) {
			visited[i] = false;
		}
		for (int i = 0; i < vexnum; i++) {
			if (!visited[i]) {
				q.add(i);
				visited[i] = true;
				visit(i);
				while (!q.isEmpty()) {
					int j = (Integer) q.remove().intValue();

					for (int k = this.firstAdjVex(j); k >= 0; k = this
							.nextAdjVex(j, k)) {
						if (!visited[k]) {
							q.add(k);
							visited[k] = true;
							visit(k);
						}
					}

				}
			}
		}
	}

	private void visit(int i) {
		// TODO Auto-generated method stub
		System.out.print(vertices[i] + " ");
	}

	// 深度非递归遍历
	public void depth() {
		Stack<Integer> s = new Stack<Integer>();
		for (int i = 0; i < vexnum; i++) {
			visited[i] = false;
		}
		for (int i = 0; i < vexnum; i++) {
			if (!visited[i]) {
				s.add(i);
				// 设置第i个元素已经进栈
				visited[i] = true;
				while (!s.isEmpty()) {
					int j = (Integer) s.pop();
					visit(j);
					for (int k = this.lastAdjVex(j); k >= 0; k = this
							.lastAdjVex(j, k)) {
						if (!visited[k]) {
							s.add(k);
							visited[k] = true;
						}
					}
				}
			}
		}
	}

	// 最后一个
	public int lastAdjVex(int i) {
		for (int j = vexnum - 1; j >= 0; j--) {
			if (arcs[i][j] > 0)
				return j;
		}
		return -1;
	}

	// 上一个
	public int lastAdjVex(int i, int k) {
		for (int j = k - 1; j >= 0; j--) {
			if (arcs[i][j] > 0)
				return j;
		}
		return -1;
	}
}