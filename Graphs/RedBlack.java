enum Color {
Red, Black
}

class TreeNode {
public int data;
public TreeNode right;
public TreeNode left;
public TreeNode parent;
public Color color;

public TreeNode(int data) {
  this.left = null;
  this.right = null;
  this.parent = null;
  this.data = data;
  this.color = Color.Red;
}
}

class RedBlackTree {
TreeNode root;
TreeNode NIL;

public RedBlackTree() {
  TreeNode nilNode = new TreeNode(0);
  nilNode.color = Color.Black;
  this.NIL = nilNode;
  this.root = this.NIL;
}

public void leftRotate(TreeNode x) {
  TreeNode y = x.right;
  x.right = y.left;
  if(y.left != this.NIL) {
    y.left.parent = x;
  }
  y.parent = x.parent;
  if(x.parent == this.NIL) { 
    this.root = y;
  }
  else if(x == x.parent.left) { 
    x.parent.left = y;
  }
  else { 
    x.parent.right = y;
  }
  y.left = x;
  x.parent = y;
}

public void rightRotate(TreeNode x) {
  TreeNode y = x.left;
  x.left = y.right;
  if(y.right != this.NIL) {
    y.right.parent = x;
  }
  y.parent = x.parent;
  if(x.parent == this.NIL) { 
    this.root = y;
  }
  else if(x == x.parent.right) { 
    x.parent.right = y;
  }
  else { 
    x.parent.left = y;
  }
  y.right = x;
  x.parent = y;
}

public void insertFixup(TreeNode z) {
  while(z.parent.color == Color.Red) {
    if(z.parent == z.parent.parent.left) { 

      TreeNode y = z.parent.parent.right; 

      if(y.color == Color.Red) { 
        z.parent.color = Color.Black;
        y.color = Color.Black;
        z.parent.parent.color = Color.Red;
        z = z.parent.parent;
      }
      else { 
        if(z == z.parent.right) { 
          z = z.parent; 
          leftRotate(z);
        }
       
        z.parent.color = Color.Black; 
        z.parent.parent.color = Color.Red; 
        rightRotate(z.parent.parent);
      }
    }
    else { 
      TreeNode y = z.parent.parent.left; 

      if(y.color == Color.Red) {
        z.parent.color = Color.Black;
        y.color = Color.Black;
        z.parent.parent.color = Color.Red;
        z = z.parent.parent;
      }
      else {
        if(z == z.parent.left) {
          z = z.parent; 
          rightRotate(z);
        }
        z.parent.color = Color.Black; 
        z.parent.parent.color = Color.Red; 
        leftRotate(z.parent.parent);
      }
    }
  }
  this.root.color = Color.Black;
}

public void insert(TreeNode z) {
  TreeNode y = this.NIL; 
  TreeNode temp = this.root;

  while(temp != this.NIL) {
    y = temp;
    if(z.data < temp.data)
      temp = temp.left;
    else
      temp = temp.right;
  }
  z.parent = y;

  if(y == this.NIL) { 
    this.root = z;
  }
  else if(z.data < y.data) 
    y.left = z;
  else
    y.right = z;

  z.right = this.NIL;
  z.left = this.NIL;

  insertFixup(z);
}

public void inorder(TreeNode n) {
  if(n != this.NIL) {
    inorder(n.left);
    System.out.println(n.data);
    inorder(n.right);
  }
}

public static void main(String[] args) {
  RedBlackTree t = new RedBlackTree();

  TreeNode a, b, c, d, e, f, g, h, i, j, k, l, m;
  a = new TreeNode(10);
  b = new TreeNode(20);
  c = new TreeNode(30);
  d = new TreeNode(100);
  e = new TreeNode(90);
  f = new TreeNode(40);
  g = new TreeNode(50);
  h = new TreeNode(60);
  i = new TreeNode(70);
  j = new TreeNode(80);
  k = new TreeNode(150);
  l = new TreeNode(110);
  m = new TreeNode(120);

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

  t.inorder(t.root);
}
}