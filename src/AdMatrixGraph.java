/* 
 * author:Tammy Pi 
 * function:邻接矩阵的图 
 */
public class AdMatrixGraph {

	// 节点个数
	private int nodenum = 0;
	// 边个数
	private int cirnum = 0;
	// 邻接矩阵
	private int[][] matrix;

	// 构造函数，初始化图
	public AdMatrixGraph(int nodenum, int cirnum) {
		this.nodenum = nodenum;
		this.cirnum = cirnum;
		matrix = new int[nodenum][nodenum];

		for (int i = 0; i < nodenum; i++) {
			for (int j = 0; j < nodenum; j++) {
				matrix[i][j] = 0;
			}
		}
	}

	public int getNodenum() {
		return nodenum;
	}

	public void setNodenum(int nodenum) {
		this.nodenum = nodenum;
	}

	public int getCirnum() {
		return cirnum;
	}

	public void setCirnum(int cirnum) {
		this.cirnum = cirnum;
	}

	public int[][] getMatrix() {
		return matrix;
	}

	public void setMatrix(int[][] matrix) {
		this.matrix = matrix;
	}

	// 无向图
	public void add(int i, int j) {
		this.matrix[i][j] = 1;
		this.matrix[j][i] = 1;
	}

	// 无向图重载
	public void add(int i, int j, int data) {
		this.matrix[i][j] = data;
		this.matrix[j][i] = data;
	}
}