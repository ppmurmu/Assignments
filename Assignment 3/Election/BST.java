
import java.util.Queue;
import java.util.LinkedList;
public class BST<T extends Comparable <T>, E extends Comparable <E>> //implements BSTInterface<T, E>
{
 /*
	* Do not touch the code inside the upcoming block
	* If anything tempered your marks will be directly cut to zero
 */
 /**public static void main() {
	 BSTDriverCode BDC = new BSTDriverCode();
	 System.setOut(BDC.fileout());
 }**/
 /*
	* end code
	* start writing your code from here
	*/
	class Node
	{
		T key;
		E value;
		Node l,r;
		public Node(T k,E v)
		{
			key=k;
			value=v;
			l=r=null;
		}
	}
 Node root;

	 public void insert(T key, E value)
	 {

		 root=recurInsert(root,key,value);
	 }
	 public Node recurInsert(Node root, T key, E value)
	 {
		 if(root==null)
		 {
			 root=new Node(key,value);
			 return root;
		 }
		 if(value.compareTo(root.value)<0)
		 {
			 root.l=recurInsert(root.l,key,value);
		 }
		 else if(value.compareTo(root.value)>0)
		 {
			 root.r=recurInsert(root.r,key,value);
		 }
		 return root;
	 }

	 public E searchKey(Node n, T key)
	 {
		 if(n.key.compareTo(key)==0)
		 {
			 return n.value;
		 }
		 if(n==null)
		 {
			 return null;
		 }
		 E x=searchKey(n.l,key);
		 if(x!=null)
		 {
			 return x;
		 }
		 E y=searchKey(n.r,key);
		 return y;
	 }
	 public void recurUpdate(Node ro, E value, E newvalue)
	 {
		 if(ro.value.compareTo(value)==0)
		 {
			 ro.value=newvalue;
		 }
		 if(ro.value.compareTo(value)>0)
		 {
			 recurUpdate(ro.l,value,newvalue);
		 }
		 else if(ro.value.compareTo(value)<0)
		 {
			 recurUpdate(ro.r,value,newvalue);
		 }

	 }

	 public void update(T key, E value)
	 {
		 E val=searchKey(root,key); //val will give us the value at that particular key
		 recurUpdate(root,val,value);	//using that val we will update with the value passed in the argument
	 }
	 E minValue(Node ro)
	 {
			 E minv = ro.value;
			 while (ro.l!=null)
			 {
					 minv = ro.l.value;
					 ro = ro.l;
			 }
			 return minv;

	 }
	 public  Node recurDelete(Node ro, E value)
	 {
		 if(ro==null)
		 {
			 return ro;
		 }
		 if(value.compareTo(ro.value)<0)
		 {
			 ro.l=recurDelete(ro.l,value);
		 }
		 else if(value.compareTo(ro.value)>0)
		 {
			 ro.r=recurDelete(ro.r,value);
		 }
		 else
		 {
			 if(ro.l==null)
			 {
				 return ro.r;
			 }
			 else if(ro.r==null)
			 {
				 return ro.l;
			 }
			 ro.value=minValue(ro.r);
			 ro.r=recurDelete(ro.r,ro.value);

		 }
		 return ro;
	 }
	 public void delete(T key)
	 {
		 E val=searchKey(root,key);
		 root=recurDelete(root,val);
	 }
	 public void printBST()
	 {
		 if(root==null)
		 {
			 return;
		 }
		 Queue<Node> q = new LinkedList<>();
		 q.add(root);
		 q.add(null);
		 while (!q.isEmpty())
		 {
			 Node curr = q.poll();
			 if (curr == null)
			 {
				 if (!q.isEmpty())
				 {
					 q.add(null);
					 //System.out.println();
				 }
			 }
			 else
			 {
				 if (curr.l != null)
				 {
					 q.add(curr.l);
				 }
			 // Pushing right child current node
				 if (curr.r != null)
				 {
					 q.add(curr.r);
				 }
				 System.out.println(curr.key+", "+curr.value);
			 }
		 }
	 }
}
