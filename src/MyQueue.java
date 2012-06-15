import java.util.LinkedList;
import java.util.List;


/* 
 * author:Tammy Pi 
 * function:队列容器 
 */  
public class MyQueue {  

  private List<Integer> queue;  

  public MyQueue()  
  {  
    queue=new LinkedList<Integer>();  
  }  
  public void addElement(int elem)  
  {  
    queue.add(elem);  
  }  
  public boolean isEmtpy()  
  {  
    if(queue==null||queue.size()==0)  
    {  
      return true;  
    }  
    return false;  
  }  
  public int removeElement()  
  {  
    return  queue.remove(0);  
  }  
  public int getSize()  
  {  
    if(queue!=null)  
    {  
      return queue.size();  
    }  
    return 0;  
  }  
  //清空队列  
  public void clear()  
  {  
    queue.clear();  
  }  

  //用于测试的主函数  
  public static void main(String[] args)  
  {  
    int[] a=new int[]{2,3,1,5};  
    MyQueue queue=new MyQueue();  
    for(int i=0;i<a.length;i++)  
    {  
      queue.addElement(a[i]);  
    }  
    for(int i=0;i<a.length;i++)  
    {  
      System.out.print(queue.removeElement()+" ");  
    }  
    System.out.println("queue："+queue.getSize());  
  }  
}