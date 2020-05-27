package col106.assignment3.Heap;
import java.util.ArrayList;
public class Heap<T extends Comparable <T>, E extends Comparable<E>> implements HeapInterface <T, E>
{
   /*
    * Do not touch the code inside the upcoming block
    * If anything tempered your marks will be directly cut to zero
   */
   public static void main()
   {
       HeapDriverCode HDC = new HeapDriverCode();
       System.setOut(HDC.fileout());
   }
   /*
    * end code
    ***/
   private ArrayList<E> ar;
   private ArrayList<T> ke;
   int size;
    public Heap()
   {
       ar=new ArrayList<E>();
       ke=new ArrayList<T>();;
       ar.add(null);
       ke.add(null);
       size=0;

   }
    /**public static void main()
   {
       Heap<Integer, Integer> obj = new Heap<>();
       obj.insert(1,100);
       obj.insert(2,10);
       obj.insert(3,30);
       obj.insert(4,50);
       obj.insert(5,150);
       obj.insert(6,1);
       obj.insert(7,3);
       obj.printHeap();
       //System.out.println("Extract max");


       //obj.printHeap();
       System.out.println("delete");
       obj.delete(1);
       obj.printHeap();

       obj.insert(8,500);
       System.out.println("insert");
       obj.printHeap();
       System.out.println(obj.extractMax());
       obj.printHeap();
       System.out.println("insertion");
       obj.increaseKey(7,70);
       obj.printHeap();
   }**/



   public int parent(int pos)
   {
       return pos/2;
   }
   public int leftChild(int pos)
   {
       return (2*pos);
   }
   public int rightChild(int pos)
   {
       return (2*pos)+1;
   }
   public boolean checkLeaf(int pos)
   {
       if(pos>=(size/2) && pos<=size)
       {
           return true;
       }
       return false;
   }
   public void swap(int f, int s)
   {
       E t;
       T k;
       k=ke.get(f);
       t=ar.get(f);
       ar.set(f,ar.get(s));
       ke.set(f,ke.get(s));
       ar.set(s,t);
       ke.set(s,k);

   }
   public void recurMax(int pos)
   {
       if(checkLeaf(pos))
       {
           return;
       }
       if(ar.get(pos).compareTo(ar.get(leftChild(pos)))<0 || ar.get(pos).compareTo(ar.get(rightChild(pos)))<0)
       {
           if(ar.get(leftChild(pos)).compareTo(ar.get(rightChild(pos)))>0)
           {
               swap(pos,leftChild(pos));
               recurMax(leftChild(pos));
           }
           else
           {
               swap(pos,rightChild(pos));
               recurMax(rightChild(pos));
           }
       }
   }
   public void insert(T key, E value)
   {
       ar.add(value);
       size++;
       ke.add(key);
       int current=size;
       if(size>=2)
       {
       while(ar.get(current).compareTo(ar.get(parent(current)))>0)
       {
           swap(current,parent(current));
           current=parent(current);
           if(current==1)
           {
               break;
           }
       }
       //recurMax(current);
   }


   }

   public E extractMax()
   {
       E val=ar.get(1);
       ar.set(1,ar.get(size));
       ke.set(1,ke.get(size));
       ar.remove(size);
       ke.remove(size);
       size--;
       recurMax(1);
       return val;

   }
   public int searchPos(T k)
   {
       int p=0;
       for(int i=1;i<=size;i++)
       {
           if(ke.get(i).compareTo(k)==0)
           {
               p=i;
               break;
           }
       }
       return p;
   }
   public void delete(T key)
   {
       int p=searchPos(key);
       ar.set(p,ar.get(size));
       ke.set(p,ke.get(size));
       ar.remove(size);
       ke.remove(size);
       size--;
       recurMax(p);

   }

   public void increaseKey(T key, E value)
   {
       int p=searchPos(key);
       ar.set(p,value);
       while(ar.get(p).compareTo(ar.get(parent(p)))>0 && p>=1)
       {
           swap(p,parent(p));
           p=parent(p);
       }

   }

   public void printHeap()
   {

       for(int i=1;i<=size;i++)
       {
           System.out.println(ke.get(i)+", "+ar.get(i));
       }

   }
}
