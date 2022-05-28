class TwoThreeFour {	
	private Node root = new Node(); // make root node

	// insert a NodeData
	public void insert(int dValue)
	{
		Node curNode = root;
		NodeData tempItem = new NodeData(dValue);

		while (true) {
			if (curNode.isFull()) // if node full,
			{
				split(curNode); 
				curNode = curNode.getParent(); 
				curNode = getNextChild(curNode, dValue);
			} 

			else if (curNode.isLeaf()) 
				break; 
			else
				curNode = getNextChild(curNode, dValue);
		} 

		curNode.insertItem(tempItem); 
	} 

	public void split(Node thisNode) // split the node
	{
		// assumes node is full
		NodeData itemB, itemC;
		Node parent, child2, child3;
		int itemIndex;

		itemC = thisNode.removeItem(); 
		itemB = thisNode.removeItem(); 
		child2 = thisNode.disconnectChild(2); 
		child3 = thisNode.disconnectChild(3); 

		Node newRight = new Node(); 

		if (thisNode == root)
		{
			root = new Node(); 
			parent = root; 
			root.connectChild(0, thisNode); 
		} else
			
			parent = thisNode.getParent(); 

		
		itemIndex = parent.insertItem(itemB); 
		int n = parent.getNumItems(); 

		for (int j = n - 1; j > itemIndex; j--) 
		{ 
			Node temp = parent.disconnectChild(j); 
			parent.connectChild(j + 1, temp); 
		}
		
		parent.connectChild(itemIndex + 1, newRight);
		newRight.insertItem(itemC); 
		newRight.connectChild(0, child2); 
		newRight.connectChild(1, child3); 
	} 

	
	public Node getNextChild(Node theNode, int theValue) {
		int j;
		
		int numItems = theNode.getNumItems();
		for (j = 0; j < numItems; j++) 
		{ 
			if (theValue < theNode.getItem(j).dData)
				return theNode.getChild(j); // return left child
		} 
		return theNode.getChild(j); // return right child
	}

	public void displayTree(int i) {
		if (i == 0) {
			recDisplayTree(root, 0, 0);
		} else
			inorderdisplay(root, 0, 0);
		System.out.println();
	}

	private void recDisplayTree(Node thisNode, int level, int childNumber) {
		System.out.print("level=" + level + " child=" + childNumber + " ");
		thisNode.displayNode(); 
		System.out.println();
		
		int numItems = thisNode.getNumItems();
		for (int j = 0; j < numItems + 1; j++) {
			Node nextNode = thisNode.getChild(j);
			if (nextNode != null)
				recDisplayTree(nextNode, level + 1, j);
			else
				return;
		}
	}

	public void inorderdisplay(Node thisNode, int level, int childNumber) {
		int numItems = thisNode.getNumItems();
		for (int j = 0; j < numItems + 1; j++) {
			Node nextNode = thisNode.getChild(j);
			if (nextNode != null)
				inorderdisplay(nextNode, level + 1, j);
			else {
				thisNode.displayNode();
				return;
			}
			if (j < thisNode.getNumItems())
				thisNode.displayvalue(j);
		}
	}

	public Node find(int theValue) {
		return findvalue(root, theValue);
	}

	public Node findvalue(Node theNode, int theValue) {

		Node l = null;
		
		int numItems = theNode.getNumItems();
		
		for (int j = 0; j < numItems; j++) 
		{ 
			if (theValue == theNode.getItem(j).dData) {
				l = theNode;
				break;
			} else if (theValue < theNode.getItem(j).dData && !theNode.isLeaf()) {
				l = findvalue(theNode.getChild(j), theValue); 

				break;
			} else if (theValue > theNode.getItem(j).dData && !theNode.isLeaf()) {
				l = findvalue(theNode.getChild(j + 1), theValue); 

			}
		}
		return l;
	}
	public Node delete(Node currnode, int theValue) {
		Node y = null;
		
		if (currnode.isLeaf()) {
			if (currnode.getNumItems() > 1) {
				currnode.deletenodevalue(theValue);
				return currnode;
			} else {
				y = deleteleaf_cases(currnode, theValue);
				return y;
			}
		} else {
			Node n = getNextChild(currnode, theValue);
			Node c = getinordernode(n);
			NodeData d = c.getItem(0);
			int k = d.dData;
			delete(c, d.dData);
					
			Node found = find(theValue);
			for(int i = 0; i < found.getNumItems();i++){
				if(found.getItem(i).dData==theValue){
					found.getItem(i).dData=k;
				}
			}
			return found;
			
		}
	}

	public Node deleteleaf_cases(Node thisNode, int theValue) {
		String sibling_side = "l";
		Node p = thisNode.getParent();
		Node sibling = thisNode.getsibiling(theValue);
		if (sibling == null) {
			sibling_side = "r";
			sibling = p.getChild(1);
		}

		if (sibling.getNumItems() == 1) {
			for (int i = 0; i <= p.getNumItems(); i++) {
				if (p.getChild(i) == sibling && sibling_side == "l") {
					thisNode.setItem(thisNode.getNumItems() - 1, null);
					thisNode.setNumItems(thisNode.getNumItems() - 1);
					NodeData d = p.getItem(i);
					sibling.insertItem(d);
					p.disconnectChild(i + 1);
					for (int j = i; j < p.getNumItems(); j++) {
						if (j + 1 < p.getNumItems()) {
							p.setItem(j, p.getItem(j + 1));
							if (j + 2 <= p.getNumItems()) {
								p.connectChild(j + 1, p.disconnectChild(j + 2));
							}
						}
					}
					p.setItem(p.getNumItems() - 1, null);
					p.setNumItems(p.getNumItems() - 1);

					// Check if parent is null
					if (p.getNumItems() == 0) {
						if (p != root) {
							p = balancetree(p);
						} else {
							root = sibling;
						}
					}

					return p;

				} else if (p.getChild(i) == sibling && sibling_side == "r") {
					thisNode.setItem(thisNode.getNumItems() - 1, null);
					thisNode.setNumItems(thisNode.getNumItems() - 1);
					NodeData d = p.getItem(i - 1);
					sibling.insertItem(d);
					p.disconnectChild(0);
					p.connectChild(0, p.disconnectChild(1));

					for (int j = i; j < p.getNumItems(); j++) {
						p.setItem(j - 1, p.getItem(j));
						if (j + 1 <= p.getNumItems()) {
							p.connectChild(j, p.disconnectChild(j + 1));
						}
					}
					p.setItem(p.getNumItems() - 1, null);
					p.setNumItems(p.getNumItems() - 1);

					// Check if parent is null
					if (p.getNumItems() == 0) {
						if (p != root) {
							p = balancetree(p);
						} else {
							root = sibling;
						}
					}
					return p;

				}
			}
		} else if (sibling.getNumItems() > 1) {
			int f = 0;
			if (sibling_side == "r") {
				f = 0;
			} else {
				f = sibling.getNumItems() - 1;
			}

			for (int i = 0; i <= p.getNumItems(); i++) {
				if (p.getChild(i) == sibling && sibling_side == "l") {
					thisNode.getItem(0).dData = p.getItem(i).dData;
					p.getItem(i).dData = sibling.getItem(f).dData;
					sibling.deletenodevalue(sibling.getItem(f).dData);
					return p;
				}

				if (p.getChild(i) == sibling && sibling_side == "r") {
					thisNode.getItem(0).dData = p.getItem(i - 1).dData;
					p.getItem(i - 1).dData = sibling.getItem(f).dData;
					sibling.deletenodevalue(sibling.getItem(f).dData);
					return p;
				}
			}
		}

		return null;
	}

	public Node balancetree(Node currnode) { 
		String sibling_side = "l";
		Node p = currnode.getParent();
		Node sibling = currnode.getsibiling(-1);
		if (sibling == null) {
			sibling_side = "r";
			sibling = p.getChild(1);
		}

		if (sibling.getNumItems() == 1) {
			for (int i = 0; i <= p.getNumItems(); i++) {
				if (p.getChild(i) == sibling && sibling_side == "l") {
					NodeData d = p.getItem(i);
					sibling.insertItem(d);

					sibling.connectChild(sibling.getNumItems(),
							currnode.disconnectChild(0));
					p.disconnectChild(i + 1);
					for (int j = i; j < p.getNumItems(); j++) {
						if (j + 1 < p.getNumItems()) {
							p.setItem(j, p.getItem(j + 1));
							if (j + 2 <= p.getNumItems()) {
								p.connectChild(j + 1, p.disconnectChild(j + 2));
							}
						}
					}
					p.setItem(p.getNumItems() - 1, null);
					p.setNumItems(p.getNumItems() - 1);
					if (p.getNumItems() == 0) {
						if (p != root) {
							p = balancetree(p);
						} else {
							root = sibling;
						}
					}
					return p;
				}

				else if (p.getChild(i) == sibling && sibling_side == "r") {
					NodeData d = p.getItem(i - 1);
					sibling.insertatfront(d);
					sibling.connectChild(0, currnode.disconnectChild(0));
					p.disconnectChild(0);
					p.connectChild(0, p.disconnectChild(1));

					for (int j = i; j < p.getNumItems(); j++) {
						p.setItem(j - 1, p.getItem(j));
						if (j + 1 <= p.getNumItems()) {
							p.connectChild(j, p.disconnectChild(j + 1));
						}
					}
					p.setItem(p.getNumItems() - 1, null);
					p.setNumItems(p.getNumItems() - 1);
					if (p.getNumItems() == 0) {

						if (p != root) {
							p = balancetree(p);
						} else {
							root = sibling;
						}
					}
					return p;
				}

			}

		} else if (sibling.getNumItems() > 1) {
			int f = 0;
			if (sibling_side == "r") {
				f = 0;
			} else {
				f = sibling.getNumItems() - 1;
			}
			for (int i = 0; i <= p.getNumItems(); i++) {
				if (p.getChild(i) == sibling && sibling_side == "l") {
					currnode.setNumItems(currnode.getNumItems()+1);
					currnode.connectChild(1, currnode.disconnectChild(0));
					currnode.connectChild(0,
							sibling.disconnectChild(sibling.getNumItems()));
					currnode.setItem(0, p.getItem(i));
					p.setItem(i, sibling.getItem(f));
					sibling.setItem(sibling.getNumItems() - 1, null);
					sibling.setNumItems(sibling.getNumItems() - 1);
					return p;
				}

				if (p.getChild(i) == sibling && sibling_side == "r") {

					currnode.setNumItems(currnode.getNumItems()+1);
					currnode.setItem(0, p.getItem(i - 1));
					p.setItem(i - 1, sibling.getItem(f));
					currnode.connectChild(1, sibling.disconnectChild(f));
					for (int j = 0; j < sibling.getNumItems(); j++) {
						if (j + 1 < sibling.getNumItems()) {
							sibling.setItem(j, sibling.getItem(j + 1));
						}
						sibling.connectChild(j, sibling.disconnectChild(j + 1));
					}
					sibling.setItem(sibling.getNumItems() - 1, null);
					sibling.setNumItems(sibling.getNumItems() - 1);
				
					return p;
				}
			}
		}
		return null;
	}
	public Node getinordernode(Node thisNode){
		Node c = null;
		if(thisNode.isLeaf()){
			c = thisNode;
		}
		else{
			c = getinordernode(thisNode.getChild(0));
		}
		return c;
	}
}