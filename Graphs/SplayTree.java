class Node {
  public int data;
  public Node left;
  public Node right;
  public Node parent;

  public Node(int data) 
  {
    this.data = data;
    this.parent = null;
    this.left = null;
    this.right = null;
  }
}

class SplayTree 
{
  public Node root;

  public SplayTree() 
  {
    this.root = null;
  }

  public Node maximum(Node x) 
  {
    while(x.right != null)
    {
      x = x.right;
      
    }
    return x;
  }

  public void rotate_left(Node x) 
  {
    Node y = x.right;
    x.right = y.left;
    if(y.left != null) 
    {
      y.left.parent = x;
    }
    y.parent = x.parent;
    if(x.parent == null) 
    { 
      this.root = y;
    }
    else if(x == x.parent.left) 
    {
      x.parent.left = y;
    }
    else 
    { 
      x.parent.right = y;
    }
    y.left = x;
    x.parent = y;
  }

  public void rotate_right(Node x) 
  {
    Node y = x.left;
    x.left = y.right;
    if(y.right != null) 
    {
      y.right.parent = x;
    }
    y.parent = x.parent;
    if(x.parent == null) 
    { 
      this.root = y;
    }
    else if(x == x.parent.right) 
    { 
      x.parent.right = y;
    }
    else 
    { 
      x.parent.left = y;
    }
    y.right = x;
    x.parent = y;
  }

  public void splay(Node n) 
  {
    while(n.parent != null) 
    {
      if(n.parent == this.root) 
      { 
        if(n == n.parent.left) 
        {
          this.rotate_right(n.parent);
        }
        else 
        {
          this.rotate_left(n.parent);
        }
      }
      else 
      {
        Node p = n.parent;
        Node g = p.parent;

        if(n.parent.left == n && p.parent.left == p) 
        { 
          this.rotate_right(g);
          this.rotate_right(p);
        }
        else if(n.parent.right == n && p.parent.right == p) 
        { 
          this.rotate_left(g);
          this.rotate_left(p);
        }
        else if(n.parent.right == n && p.parent.left == p) 
        {
          this.rotate_left(p);
          this.rotate_right(g);
        }
        else if(n.parent.left == n && p.parent.right == p) 
        {
          this.rotate_right(p);
          this.rotate_left(g);
        }
      }
    }
  }

  public void insert(Node n) 
  {
    Node y = null;
    Node temp = this.root;
    while(temp != null) 
    {
      y = temp;
      if(n.data < temp.data)
      {
        temp = temp.left;
      }
      else
      {
        temp = temp.right;
      }
    }
    n.parent = y;

    if(y == null)
    { 
      this.root = n;
    }
    else if(n.data < y.data)
    {
      y.left = n;
    }
    else
    {
      y.right = n;
    }
    this.splay(n);
  }

  public Node search(Node n, int x) 
  {
    if(x == n.data) 
    {
      this.splay(n);
      return n;
    }
    else if(x < n.data)
    {
      return this.search(n.left, x);
    }
    else if(x > n.data)
    {
      return this.search(n.right, x);
    }
    else
    {
      return null;
    }
  }
  public void delete(Node n) 
  {
    this.splay(n);

    SplayTree left_tree = new SplayTree();
    left_tree.root = this.root.left;
    if(left_tree.root != null)
    {
      left_tree.root.parent = null;
    }
    SplayTree right_tree = new SplayTree();
    right_tree.root = this.root.right;
    if(right_tree.root != null)
    {
      right_tree.root.parent = null;
    }
    if(left_tree.root != null) 
    {
      Node m = left_tree.maximum(left_tree.root);
      left_tree.splay(m);
      left_tree.root.right = right_tree.root;
      this.root = left_tree.root;
    }
    else 
    {
      this.root = right_tree.root;
    }
  }

  public void inorder(Node n) 
  {
    if(n != null) 
    {
      inorder(n.left);
      System.out.println(n.data);
      inorder(n.right);
    }
  }

  public static void main(String[] args) 
  {
    SplayTree t = new SplayTree();
    Node a = new Node(10);
    Node b = new Node(20);
    Node c = new Node(30);
    Node d = new Node(100);
    Node e = new Node(90);
    Node f = new Node(40);
    Node g = new Node(50);
    Node h = new Node(60);
    Node i = new Node(70);
    Node j = new Node(80);
    Node k = new Node(150);
    Node l = new Node(110);
    Node m = new Node(120);

    t.insert(a);
    t.insert(b);
    t.insert(c);
    t.insert(d);
    t.insert(e);
    t.insert(f);
    t.insert(g);
    t.insert(h);
    t.insert(i);
    t.insert(j);
    t.insert(k);
    t.insert(l);
    t.insert(m);

    t.delete(a);
    t.delete(m);

    t.inorder(t.root);
  }
}
