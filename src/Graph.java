public class Graph {
	// 存储边的信息
	private Edge[][] edges;
	// 存储节点信息
	private Vertice[] vertices;
	private int vexnum;
	// 记录第i个节点是否被访问过
	private boolean[] visited;

	/**
	 * @param args
	 */

	public Graph(int n) {
		vexnum = n;
		vertices = new Vertice[n];
		edges = new Edge[n][n];
		visited = new boolean[n];
		for (int i = 0; i < vexnum; i++) {
			for (int j = 0; j < vexnum; j++) {
				// edges[i][j] = 0;
			}
		}

	}

	public void addVertex(Vertice[] obj) {
		this.vertices = obj;
	}

	private void backEdgeTraversal(Object v, Object e, Object w) {
		// TODO Auto-generated method stub

	}

	public void DFS() {
		initResult();
		for (Vertice u : vertices()) {
			u.setLabel("UNEXPLORED");
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

	public Vertice[] vertices() {
		// TODO Auto-generated method stub
		return vertices;
	}

	private void visit(int i) {
		// TODO Auto-generated method stub
		System.out.print(vertices[i] + " ");
	}

	public void addEdge(Vertice vertice, Vertice vertice2) {
		// TODO Auto-generated method stub

	}
}
