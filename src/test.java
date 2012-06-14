public class test {
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
}
