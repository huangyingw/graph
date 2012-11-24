#include "E:\programming\pass\c++\s数据结构与算法\循环队列\CirQueue.h"
#include "E:\programming\pass\c++\s数据结构与算法\栈\stack.h"
template <class Type> struct Arc
{
	int pre;//弧的一结点
	int next;//弧另一结点
	Type weight;//弧的权
	bool isVisited;//标记弧是否被访问过
};

template <class Type> struct EdgeNode
{	
	int adjVex; 
	struct EdgeNode<Type> *next;
	Type weight; //边上的权
};

template <class Type> struct VertexNode//顶点表结点
{
	Type vertex; //顶点域
	EdgeNode<Type> *firstEdge;//边表头指针
};

template <class Type> class Graph
{
private:
	VertexNode<Type>* adjList;//邻接表
	int verNum;//图中当前顶点数s
	int arcNum;//图中当前边数 
	bool nodeVisited[100]; //节点访问标志向量,是全局量
	int arcVisited[100]; //弧访问标志向量是,全局量
	int leastDist[100];//最小距离数组
	Type** adjMatrix;//边权值数组
	void BFS(int k);
	bool isShortestVertex[MAX];//标记当前结点是否在最小边集合
	Type* data;//实现存储的数据
	int* preVertex;//当前结点的前一个结点的下标号 
public:
	Graph(int *data,int dim,int n);
	void CreateAdjMatrix(int *data,int dim);
	void CreatAdjList();//用邻接表存储图
	void DFSTraverse();
	void PrintAdjMatrix();//打印邻接矩阵
	void BFSTraverse();
	int Prim(); //最小生成树PRIM算法
	void Kruscal();
	int Find(int arcVisited[],int f);//找未被访问过的弧的下标
	void InitialShortestVertex();
	int LeastDistAddS();//从当前leastDist数组中找出最小的,并将当前最小的边对应的终结点加入isShortestVertex集合
	void Dijkstra();//计算最短路径，并将结果存在数组中
	void PrintShortestPath();//将存在数组中的结果打印出来
	void InitialLeastDist();/*初始化leastDist数组*/
};

template <class Type> int Graph<Type>:: LeastDistAddS()//从当前leastDist数组中找出最小的,并将当前最小的边对应的终结点加入isShortestVertex集合
{
	//存储最小leastDist value值
	int LeastDistValue=MAX;
	//存储最小leastDist 结点下标值
	int LeastDistIndex=-1;
	for(int i=0;i<5;i++)
	{
		if(leastDist[i]<LeastDistValue&&!isShortestVertex[i])
		{
			LeastDistValue=leastDist[i];
			LeastDistIndex=i;
		}
	}
	isShortestVertex[LeastDistIndex]=true;
	return LeastDistIndex;
}

template <class Type> void Graph<Type>::InitialShortestVertex()
{
	for(int i=0;i<verNum;i++)//dist,path数组初始化
	{
		//邻接矩阵第v行元素复制到dist中
		isShortestVertex[i]=false;							//已求出最短路径的顶点集合初始化
	}
}

template <class Type> void Graph<Type>::InitialLeastDist()/*初始化leastDist数组*/
{
	for(int i=0;i<verNum;i++)//leastDist数组初始化
	{
		leastDist[i]=adjMatrix[0][i];
		if(i!=0&&leastDist[i]<MAX)
		{
			preVertex[i]=0;
		}
		else
		{
			preVertex[i]=-1;					//路径存放数组初始化
		}
	}
}

template <class Type> void Graph<Type>::Dijkstra()//默认从0号结点开始计算最短路径
{
	InitialShortestVertex();//先初始化isShortestVertex集合
	InitialLeastDist();//初始化leastDist数组
	//假设从0号结点出发
	isShortestVertex[0]=true;
	leastDist[0]=0;//v到v的距离为0
	for(int i=0;i<verNum;i++)//从顶点v确定n-1条路径
	{
		int min=MAX;
		int u=0;
		for(int j=0;j<verNum;j++)//选择当前不在集合S中具有最短路径的顶点u
		{
			if(!isShortestVertex[j]&&leastDist[j]<min)
			{
				u=j;
				min=leastDist[j];
			}
		}
		cout<<u<<" was selected into the shortest vertexes arrays"<<endl;
		isShortestVertex[u]=true;//将顶点S加入集合S，表示它已在最短路径上
		for(int w=0;w<verNum;w++)//修改与u相邻的w顶点的最短距离
		{
			if(!isShortestVertex[w]&&adjMatrix[u][w]<MAX&&leastDist[u]+adjMatrix[u][w]<leastDist[w])//w顶点不在最短路径的顶点集合&&u,w相邻&&经u到w的距离小于当前w的最短路径
			{
				leastDist[w]=leastDist[u]+adjMatrix[u][w];//修改w的最短距离
				/*cout<<"now changing the "<<w<<"'s dist to "<<leastDist[w]<<endl;
				cout<<"now changing the "<<w<<"'s neighbor vertex to be "<<u<<endl;*/
				preVertex[w]=u;//修改w的最短路径
			}
		}
	}

}

template <class Type> void Graph<Type>::PrintShortestPath()//将存在数组中的结果打印出来
{
	int vertexNum;
	for(int i=0;i<verNum;i++)
	{
		vertexNum=i;
		SeqStack<Type> *stack=new SeqStack<Type>();
		stack->InitStack();
		cout<<i<<":";
		if(vertexNum>=0)
		{
			stack->Push(vertexNum);
		}
		vertexNum=preVertex[vertexNum];
		while(vertexNum>=0)
		{	
			stack->Push(vertexNum);
			vertexNum=preVertex[vertexNum];
		}
		
		while(!stack->StackEmpty())
		{
			cout<<stack->Pop()<<",";
		}
		cout<<"->"<<leastDist[i]<<endl;
	}
	
}
template <class Type> void Graph<Type>::CreatAdjList()//用邻接表存储图
{
	EdgeNode<Type> *arc;
	for(int i=0;i<verNum;i++)
	{
		adjList[i].vertex=i;
		adjList[i].firstEdge=NULL;
	}
	for(int i=0;i<verNum;++i)
	{
		for(int j=0;j<verNum;++j)
		{
			if(adjList[i].firstEdge==NULL)
			{
				if(adjMatrix[i][j]>0&&adjMatrix[i][j]<MAX&&j<verNum)
				{
					arc=new EdgeNode<Type>();
					arc->adjVex=j;
					arc->weight=adjMatrix[i][j];
					arc->next=NULL;
					adjList[i].firstEdge=arc;
				}
			}
			else
			{
				if(adjMatrix[i][j]>0&&adjMatrix[i][j]<MAX&&j<verNum)
				{
					arc=new EdgeNode<Type>();
					arc->adjVex=j;
					arc->weight=adjMatrix[i][j];
					arc->next=adjList[i].firstEdge;
					adjList[i].firstEdge=arc;
				}
			}
		}
	}
}

template <class Type> int Graph<Type>::Find(int arcVisited[],int f)//找未被访问过的弧的下标
{
	while(arcVisited[f]>0)
	{
		f=arcVisited[f];
	}
	return f;
}

template <class Type> void Graph<Type>::Kruscal()
{ 
	//初始化edgs数组
	Arc<Type> arcs[20];
	int k=0;
	for(int i=0;i<verNum;++i)
	{
		for(int j=i;j<verNum;++j)
		{
			if(adjMatrix[i][j]<MAX)
			{
				arcs[k].pre=i;
				arcs[k].next=j;
				arcs[k].weight=adjMatrix[i][j];
				arcs[k].isVisited=false;
				++k;
			}
		}
	}

	int preMIN,nextMIN,weightMIN,indexMIN,begG,endG;
	for(int i=0;i<arcNum;++i)
		arcVisited[i]=0; 
	for(int j=0;j<arcNum;++j)
	{
		weightMIN=MAX;//寻找weight值最小的edges,就是最小弧
		for(int i=0;i<arcNum;++i)
		{
			if(!arcs[i].isVisited&&arcs[i].weight<weightMIN)
			{
				weightMIN=arcs[i].weight;
				preMIN=arcs[i].pre;
				nextMIN=arcs[i].next;
				indexMIN=i;
			}
		}
		
		begG=Find(arcVisited,preMIN);
		endG=Find(arcVisited,nextMIN); 
		arcs[indexMIN].isVisited=true;
		if(begG!=endG)//不是联通子图
		{
			/*for(int i=0;i<verNum;i++)
			{
				cout<<i<<":"<<arcVisited[i]<<", ";
			}*/
			
			arcVisited[begG]=endG;//标记begG的邻接节点为endG
			/*cout<<begG<<","<<endG<<endl;*/
			cout<<"("<<preMIN<<","<<nextMIN<<")"<<weightMIN;
			cout<<endl;
		}
	}
}

template <class Type> int Graph<Type>::Prim() //最小生成树PRIM算法
{
	Type* lowCost=new Type[verNum];//LOWCOST[i]存储i当前集合U分别到剩余结点的最短路径
	Type* preVex=new Type[verNum]; //preVex[i]存储i的前一个结点,preVex[]存储最短路径在U中的结点

	
	int i,j,k,MIN; 
	
	for(i=1;i<verNum;i++) //n个顶点，n-1条边 
	{
		lowCost[i]=adjMatrix[0][i]; //初始化 
		preVex[i]=0; //顶点未加入到最小生成树中 
	}	
	
	lowCost[0]=0; //标志顶点1加入U集合,
	
	for(i=1;i<verNum;i++) //形成n-1条边的生成树 
	{
		MIN=MAX; 
		k=0; 

		for(j=1;j<verNum;j++) //寻找最小边,满足一个顶点在U,另一个顶点在V的 
		{
			if((lowCost[j]<MIN)&&(lowCost[j]!=0)) //lowCost[j]!=0说明顶点j不在U
			{
				MIN=lowCost[j]; 
				k=j; 
			} 
		}

		cout<<preVex[k]<<","<<k<<","<<MIN<<endl;
		lowCost[k]=0; //顶点k加入U 
		
		for(j=1;j<verNum;j++) //修改由顶点k到其他顶点边的权值 
		{
			if(lowCost[j]>adjMatrix[k][j]) 
			{
				lowCost[j]=adjMatrix[k][j]; 
				preVex[j]=k; //在此处更新preVex的值
				/*cout<<j<<"->"<<k<<":"<<lowCost[j]<<endl;*/
			} 
		}
	}  
	return 0;
} 

template <class Type> void Graph<Type>::CreateAdjMatrix(int *data,int dim)
{
	adjMatrix=new Type*[dim];
	for(int i=0;i<dim;i++)
	{
		adjMatrix[i]=new Type[dim];
		for(int j=0;j<dim;j++)
		{
			adjMatrix[i][j]=data[i*dim+j];
		}
	}
}

template <class Type> Graph<Type>::Graph(int *data,int dim,int n)//此方法是把新结点插在头结点的后面
{
	verNum=n;
	arcNum=20;
	adjList=new VertexNode<Type>[verNum];
	preVertex=new int[verNum];
	for(int i=0;i<dim;i++)
	{
		adjList[i].firstEdge=NULL;
		for(int j=i+1;j<dim;j++)
		{
			if(data[i*dim+j]<MAX)
			{
				EdgeNode<Type> *edgeNode=new EdgeNode<Type>;
				edgeNode->adjVex=j;
				edgeNode->next=adjList[i].firstEdge;
				adjList[i].firstEdge=edgeNode;
			}
		}
	}
}

template <class Type> void Graph<Type>::PrintAdjMatrix()//打印邻接矩阵
{
	EdgeNode<Type> *node;
	for(int i=0;i<verNum;i++)
	{
		node=adjList[i].firstEdge;
		cout<<adjList[i].vertex;
		while(NULL!=node)
		{
			cout<<"->"<<node->adjVex<<"("<<node->weight<<")";
			node=node->next;
		}
		cout<<endl;
	}
}

template <class Type> void Graph<Type>::BFSTraverse()
{ 
	int i;
	for(i=0;i<verNum;i++)
		nodeVisited[i]=false;
	for(i=0;i<verNum;i++)
	{
		if(!nodeVisited[i])
		{
			BFS(i);
		}
	}
	cout<<endl;
}

template <class Type> void Graph<Type>::BFS(int k)
{
	cout<<k<<",";
	nodeVisited[k]=true;
	CirQueue<int> *q=new CirQueue<int>;
	q->InitQueue();
	EdgeNode<Type> *node=adjList[k].firstEdge;
	while(node!=NULL&&!nodeVisited[node->adjVex])
	{
		cout<<node->adjVex<<",";
		q->EnQueue(node->adjVex);
		nodeVisited[node->adjVex]=true;
		node=node->next;
	}
	if(!q->QueueEmpty())
	{
		k=q->DeQueue();
		if(!nodeVisited[k])
		{
			BFS(k);
		}
	}
	else
	{
		return;
	}
}

template <class Type> void Graph<Type>::DFSTraverse()//深度优先搜索
{
	int count=0;
	for(int i=0;i<verNum;i++)
		nodeVisited[i]=false;
	for(int i=0;i<verNum;i++)
	{
		if(nodeVisited[i]==false)
		{
			nodeVisited[i]=true;
			cout<<i<<",";
			EdgeNode<Type> *edgeNode=adjList[i].firstEdge;
			while(edgeNode!=NULL)
			{
				if(nodeVisited[edgeNode->adjVex]==false)
				{
					cout<<edgeNode->adjVex<<",";
					nodeVisited[edgeNode->adjVex]=true;
				}
				edgeNode=edgeNode->next;
			}
		}
	}
	cout<<endl;
}


