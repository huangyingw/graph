public class test {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Graph g = new Graph(8);
		Vertice[] vertices = { new Vertice('A'), new Vertice('B'),
				new Vertice('C'), new Vertice('D'), new Vertice('E'),
				new Vertice('F'), new Vertice('G'), new Vertice('H') };
		g.addVertex(vertices);
		g.addEdge(vertices[0], vertices[1]);
		g.addEdge(vertices[0], vertices[2]);
		g.addEdge(vertices[1], vertices[3]);
		g.addEdge(vertices[1], vertices[4]);
		g.addEdge(vertices[3], vertices[7]);
		g.addEdge(vertices[4], vertices[7]);
		g.addEdge(vertices[2], vertices[5]);
		g.addEdge(vertices[2], vertices[6]);
		System.out.println("深度优先遍历：");
		g.DFS();
		System.out.println();
	}
}
