import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Graph {
	// 存储边的信息
	private int[][] edges;
	// 存储节点信息
	private Object[] vertices;
	private int vexnum;
	// 记录第i个节点是否被访问过
	private boolean[] visited;

	/**
	 * @param args
	 */

	public Graph(int n) {
		vexnum = n;
		vertices = new Object[n];
		edges = new int[n][n];
		visited = new boolean[n];
		for (int i = 0; i < vexnum; i++) {
			for (int j = 0; j < vexnum; j++) {
				edges[i][j] = 0;
			}
		}

	}

	public void addEdge(int i, int j) {
		if (i == j)
			return;
		edges[i][j] = 1;
		edges[j][i] = 1;
	}

	public void addVertex(Object[] obj) {
		this.vertices = obj;
	}

	private void backEdgeTraversal(Object v, Object e, Object w) {
		// TODO Auto-generated method stub

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

	public void DFS() {
		initResult();
		for (Object u : vertices()) {
			setLabel(u, "UNEXPLORED");
		}
		for (Object e : edges()) {
			setLabel(e, "UNEXPLORED");
		}
		for (Object v : vertices()) {
			if (getLabel(v) == "UNEXPLORED") {
				preComponentVisit(v);
				DFS(v);
				postComponentVisit(v);
			}
		}
		result();
	}

	public void DFS(Object v) {
		Object w = null;
		setLabel(v, "VISITED");
		startVertexVisit(v);
		for (Object e : incidentEdges(v)) {
			if (getLabel(e) == "UNEXPLORED") {
				w = opposite(v, e);
			}
			if (getLabel(w) == "UNEXPLORED") {
				preDiscoveryTraversal(v, e, w);
				setLabel(e, "DISCOVERY");
				DFS(w);
				postDiscoveryTraversal(v, e, w);
			} else {
				setLabel(e, "BACK");
				backEdgeTraversal(v, e, w);
			}
		}
		finishVertexVisit(v);
	}

	private Object[] edges() {
		// TODO Auto-generated method stub
		return null;
	}

	private void finishVertexVisit(Object v) {
		// TODO Auto-generated method stub

	}

	public int firstAdjVex(int i) {
		for (int j = 0; j < vexnum; j++) {
			if (edges[i][j] > 0)
				return j;
		}
		return -1;
	}

	public String getLabel(Object v) {
		return null;
	}

	private Object[] incidentEdges(Object v) {
		// TODO Auto-generated method stub
		return null;
	}

	private void initResult() {
		// TODO Auto-generated method stub

	}

	// 最后一个
	public int lastAdjVex(int i) {
		for (int j = vexnum - 1; j >= 0; j--) {
			if (edges[i][j] > 0)
				return j;
		}
		return -1;
	}

	// 上一个
	public int lastAdjVex(int i, int k) {
		for (int j = k - 1; j >= 0; j--) {
			if (edges[i][j] > 0)
				return j;
		}
		return -1;
	}

	public int nextAdjVex(int i, int k) {
		for (int j = k + 1; j < vexnum; j++) {
			if (edges[i][j] > 0)
				return j;
		}
		return -1;
	}

	private Object opposite(Object v, Object e) {
		// TODO Auto-generated method stub
		return null;
	}

	private void postComponentVisit(Object v) {
		// TODO Auto-generated method stub

	}

	private void postDiscoveryTraversal(Object v, Object e, Object w) {
		// TODO Auto-generated method stub

	}

	private void preComponentVisit(Object v) {
		// TODO Auto-generated method stub

	}

	private void preDiscoveryTraversal(Object v, Object e, Object w) {
		// TODO Auto-generated method stub

	}

	private void result() {
		// TODO Auto-generated method stub

	}

	private void setLabel(Object v, String string) {
		// TODO Auto-generated method stub

	}

	private void startVertexVisit(Object v) {
		// TODO Auto-generated method stub

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

	public Object[] vertices() {
		// TODO Auto-generated method stub
		return vertices;
	}

	private void visit(int i) {
		// TODO Auto-generated method stub
		System.out.print(vertices[i] + " ");
	}
}
