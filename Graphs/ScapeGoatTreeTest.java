import java.util.Scanner;    
class SGTNode    
{
    SGTNode right, left, parent;
    int value;
    public SGTNode(int val)
    {
        value = val;
    }
}
class ScapeGoatTree
{
    private SGTNode root;
    private int n, q;
    public ScapeGoatTree()
    {
        root = null;
        n = 0;       
    }
    public boolean isEmpty()
    {
        return root == null;
    }
    public void makeEmpty()
    {
        root = null;
        n = 0;
    } 
    private int size(SGTNode r)
    {
        if (r == null)
            return 0;
        else
        {
            int l = 1;
            l += size(r.left);
            l += size(r.right);
            return l;
        }
    }
    public boolean search(int val)
    {
        return search(root, val);
    }
    private boolean search(SGTNode r, int val)
    {
        boolean found = false;
        while ((r != null) && !found)
        {
            int rval = r.value;
            if (val < rval)
                r = r.left;
            else if (val > rval)
                r = r.right;
            else
            {
                found = true;
                break;
            }
            found = search(r, val);
        }
        return found;
    }  
    public int size() 
    {
        return n;
    }
    public void inorder()
    {
        inorder(root);
    }
    private void inorder(SGTNode r)
    {
        if (r != null)
        {
            inorder(r.left);
            System.out.println(r.value +" ");
            inorder(r.right);
        }
    }    
    private static final int log32(int q) 
    {
        final double log23 = 2.4663034623764317;
        return (int)Math.ceil(log23*Math.log(q));
    }
    /* Function to insert an element */
    public boolean add(int x) 
    {
        
        SGTNode u = new SGTNode(x);
        int d = addWithDepth(u);
        if (d > log32(q)) {

            SGTNode w = u.parent;
            while (3*size(w) <= 2*size(w.parent))
                w = w.parent;
            rebuild(w.parent);
        }
        return d >= 0;
    }
    protected void rebuild(SGTNode u) 
    {
        int ns = size(u);
        SGTNode p = u.parent;
        SGTNode[] a = new SGTNode[ns];
        packIntoArray(u, a, 0);
        if (p == null) 
        {
            root = buildBalanced(a, 0, ns);
            root.parent = null;
        } 
        else if (p.right == u) 
        {
            p.right = buildBalanced(a, 0, ns);
            p.right.parent = p;
        } 
        else 
        {
            p.left = buildBalanced(a, 0, ns);
            p.left.parent = p;
        }
    }
    protected int packIntoArray(SGTNode u, SGTNode[] a, int i) 
    {
        if (u == null) 
        {
            return i;
        }
        i = packIntoArray(u.left, a, i);
        a[i++] = u;
        return packIntoArray(u.right, a, i);
    }
    protected SGTNode buildBalanced(SGTNode[] a, int i, int ns) 
    {
        if (ns == 0)
            return null;
        int m = ns / 2;
        a[i + m].left = buildBalanced(a, i, m);
        if (a[i + m].left != null)
            a[i + m].left.parent = a[i + m];
        a[i + m].right = buildBalanced(a, i + m + 1, ns - m - 1);
        if (a[i + m].right != null)
            a[i + m].right.parent = a[i + m];
        return a[i + m];
    }
    public int addWithDepth(SGTNode u) 
    {
        SGTNode w = root;
        if (w == null) 
        {
            root = u;
            n++; 
            q++;
            return 0;
        }
        boolean done = false;
        int d = 0;
        do {
            if (u.value < w.value) 
            {
                if (w.left == null) 
                {
                    w.left = u;
                    u.parent = w;
                    done = true;
                } 
                else 
                {
                    w = w.left;
                }
            } 
            else if (u.value > w.value) 
            {
                if (w.right == null) 
                {
                    w.right = u;
                    u.parent = w;
                    done = true;
                }
                w = w.right;
            } 
            else 
            {
                return -1;
            }
            d++;
        } while (!done);
        n++; 
        q++;
        return d;
    }
}
public class ScapeGoatTreeTest
{
    public static void main(String[] args)
    {                 
        Scanner scan = new Scanner(System.in);
        ScapeGoatTree sgt = new ScapeGoatTree(); 
        sgt.add(10);
        sgt.add(20);
        sgt.add(40);
        sgt.add(50);
        sgt.inorder();
    }

}