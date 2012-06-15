
/* 
 * author:Tammy Pi 
 * function:用Kruscal算法得到最小生成树 
 */  
public class KruscalAlgorithm {  

  private AdMatrixGraph graph;  
  private int[] s=null;  
  private int index=-1;  
  private int index1=-1;  

  public void createTree(int nodenum,int cirnum)  
  {  
    graph=new AdMatrixGraph(nodenum,cirnum);  
    s=new int[graph.getNodenum()];  

    this.graph.add(0,1,1);  
    this.graph.add(0,3,1);  
    this.graph.add(0,2,3);  
    this.graph.add(3,2,1);  
  }  
  //在S集合中包含  
  public boolean isContain(int j)  
  {  
    for(int k=0;k<=index;k++)  
    {  
      if(s[k]==j)  
      {  
        return true;  
      }  
    }  
    return false;  
  }  
  //找到最短边的两端节点序号  
  public int[] getMinCir()  
  {  
    int[] minIndex=new int[2];  
    int minValue=Integer.MAX_VALUE;  

    for(int j=0;j<this.graph.getNodenum();j++)  
    {  
      for(int k=0;k<this.graph.getNodenum();k++)  
      {  
        //j和k不能相同  
        if(j==k)  
        {  
          continue;  
        }  
        //最小生成树不能包括环  
        if(isContain(j)&&isContain(k))  
        {  
          continue;  
        }  
        if(this.graph.getMatrix()[j][k]!=0&&this.graph.getMatrix()[j][k]<minValue)  
        {  
          minValue=this.graph.getMatrix()[j][k];  
          index1=-1;  
          if(!isContain(j))  
          {  
            minIndex[++index1]=j;  
          }  
          if(!isContain(k))  
          {  
            minIndex[++index1]=k;  
          }  
        }  
      }//for k  
    }//for j  
    return minIndex;  
  }  
  public void kruscal()  
  {  
    System.out.print("Kruscal算法得到的最小生成树为：");  
    while(!(index==this.graph.getNodenum()-1))  
    {  
      int[] minIndex=getMinCir();  
      for(int i=0;i<=index1;i++)  
      {  
        s[++index]=minIndex[i];  
        System.out.print(minIndex[i]+" ");  
      }  
    }  
    System.out.println();  
  }  
  //用于测试的主函数  
  public static void main(String[] args)  
  {  
    KruscalAlgorithm kruscal=new KruscalAlgorithm();  
    kruscal.createTree(4,4);  
    kruscal.kruscal();   
  }  
}