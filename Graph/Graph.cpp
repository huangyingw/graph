// Graph.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include<iostream>
using namespace std;
#define MAX 10000         //假设文件长度
#include "Graph.h"

int _tmain(int argc, _TCHAR* argv[])
{
	int data[7][7]={{MAX,28,MAX,MAX,MAX,10,MAX}
				   ,{28,MAX,16,MAX,MAX,MAX,14}
				   ,{MAX,16,MAX,12,MAX,MAX,MAX}
				   ,{MAX,MAX,12,MAX,22,MAX,18}
				   ,{MAX,MAX,MAX,22,MAX,25,24}
				   ,{10,MAX,MAX,MAX,25,MAX,MAX}
				   ,{MAX,14,MAX,18,24,MAX,MAX}};
	Graph<int>* graph=new Graph<int>(*data,7,7);
	graph->CreateAdjMatrix(*data,7);
	graph->CreatAdjList();
	/*graph->PrintAdjMatrix();*/
	/*graph->DFSTraverse();
	graph->BFSTraverse();
	graph->Prim();*/
	/*graph->Kruscal();*/
	graph->Dijkstra();
	graph->PrintShortestPath();
	return 0;
}

