package col106.assignment3.BST;
import java.util.Queue;
import java.util.LinkedList;
public class BST<T extends Comparable <T>, E extends Comparable <E>> implements BSTInterface<T, E>
{
	 /*
		* Do not touch the code inside the upcoming block
		* If anything tempered your marks will be directly cut to zero
	 */
	 public static void main() {
			 BSTDriverCode BDC = new BSTDriverCode();
			 System.setOut(BDC.fileout());
	 }
	 /*
		* end code
		* start writing your code from here
		*/

		/**public static void main()
		{
			 BST<Integer, Integer> obj = new BST<>();
			 obj.insert(1,15);
			 obj.insert(2,5);
			 obj.insert(3,1);
			 obj.insert(4,6);
			 obj.insert(5,20);
			 obj.insert(6,16);
			 obj.insert(7,25);
			 obj.insert(8,21);
			 obj.insert(9,26);
			 obj.printBST();
			 obj.delete(5);
			 System.out.println("dbassjbfk");
			 obj.printBST();
			 obj.update(9,300);
			 obj.printBST();
			 // obj.delete(8);
			 // obj.prev(8);
			 // System.out.println(obj.prev(7).valuebst);
			 // obj.update(7,0);
			 // System.out.println(obj.height());
			 // obj.insert(11,31);
			 // obj.update(0, 3);
			 // System.out.println(obj.prev(5).valuebst);
			 // obj.update(3,8);
			 // obj.search(3);
			 // obj.print_level(2);
			 //System.out.println();



	 }**/
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
					 if(n==null)
					 {
							 return null;
					 }
					 E x=searchKey(n.l,key);
					 x=searchKey(n.r,key);
					 if(n.key.compareTo(key)==0)
					 {
							 return n.value;
						}
					 return x;
			 }
			 public void recurUpdate(Node ro, E value, E newvalue)
			 {
					 if(ro!=null)
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
	 }

	 public void update(T key, E value)
			 {
					 E val=searchKey(root,key); //val will give us the value at that particular key
					 recurUpdate(root,val,value);    //using that val we will update with the value passed in the argument
			 }
	 Node minValue(Node ro)
	 {
			 Node minv = ro;
			 while (ro.l!=null)
			 {
					 minv = ro.l;
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
			 ro.value=minValue(ro.r).value;
			 ro.key=minValue(ro.r).key;
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
