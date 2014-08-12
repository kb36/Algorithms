import java.util.Queue;
import java.util.LinkedList;
import java.util.HashMap;

/*Time Complexity: O(n)*/
/*Space Complexity: O(n)*/
/* Desc:

Given inorder and level-order traversals of a Binary Tree, construct the Binary Tree. Following is an example to illustrate the problem.
BinaryTree

Input: Two arrays that represent Inorder and level order traversals of a Binary Tree
in[]    = {4, 8, 10, 12, 14, 20, 22};
level[] = {20, 8, 22, 4, 12, 10, 14};

Output: Construct the tree represented by the two arrays. For the above two arrays, the constructed tree is shown in the diagram on right side
source: http://www.geeksforgeeks.org/construct-tree-inorder-level-order-traversals/
*/
public class buildTreeFromInorderAndLevelOrder {
	
	public int currentIndex = 0;
	public HashMap<Integer, Integer> inOrderHashMap = new HashMap<Integer, Integer>();
	
	/*
	 * Node structure
     */
	public class Node<T> {
		public Node<T> left;
		public Node<T> right;
		public T value;
		public Node(T value, Node<T> left, Node<T> right) {
			this.value = value;
			this.left = left;
			this.right = right;
		}
	}

	/*
	 * build tree and return
	 */
	public void build(int[] inOrder, int[] levelOrder) {
		inOrderHashMap.clear();
		//add to HashMap
		for(int i = 0; i < inOrder.length; i++)
			inOrderHashMap.put(inOrder[i], i);

		//reset currentIndex;
		currentIndex = 0;
		if(inOrder == null || levelOrder == null) {
			System.out.println("Empty traversals");
			return;
		}
		
		if(inOrder.length != levelOrder.length || inOrder.length == 0) {
			System.out.println("Incorrect lengths or empty arrays");
			return;
		}
	
	   Node<Integer> root = build(0, inOrder.length-1, levelOrder, currentIndex);
	   printLevelOrder(root);
	   System.out.println("Inorder: ");
	   printInOrder(root);
	   System.out.println();
	   System.out.println("Preorder: ");
	   printPreOrder(root);
	   System.out.println();
	   System.out.println("Postorder: ");
	   printPostOrder(root);
	   System.out.println();

	}

	/**
	 * print level order traversal
     */
	public void printLevelOrder(Node<Integer> current) {
		Queue<Node<Integer>> q = new LinkedList<Node<Integer>>();
		q.add(current);
		Node<Integer> temp;
		System.out.println("Level order");
		while((temp = q.poll()) != null) {
		  System.out.print(temp.value+" ");
		  if(temp.left != null)
		  	q.add(temp.left);
		  if(temp.right != null)
		  	q.add(temp.right);
		}
		System.out.println();
	}

	/**
     * Inorder traversal
     */
	public void printInOrder(Node<Integer> current) {
		if(current == null)
        	return;
		printInOrder(current.left);
		System.out.print(current.value+ " ");
        printInOrder(current.right);
	}
	
	/**
     * Preorder traversal
     */
	public void printPreOrder(Node<Integer> current) {
		if(current == null)
        	return;
		System.out.print(current.value+ " ");
		printPreOrder(current.left);
        printPreOrder(current.right);
	}
	
	/**
     * Postorder traversal
     */
	public void printPostOrder(Node<Integer> current) {
		if(current == null)
        	return;
		printPostOrder(current.left);
        printPostOrder(current.right);
		System.out.print(current.value+ " ");
	}

	/**
	 * build tree from inorder and levelOrder traversal
     */
	private Node<Integer> build(int inBegin, int inEnd, int[] levelOrder, int lCurrent) {
		Queue<Node<Integer>> tree = new LinkedList<Node<Integer>>();
		Queue<Integer> minIndices = new LinkedList<Integer>();
		Queue<Integer> maxIndices = new LinkedList<Integer>();

		Node<Integer> root = new Node<Integer>(levelOrder[lCurrent++], null, null);
	    minIndices.add(inBegin);
		maxIndices.add(inEnd);
		tree.add(root);
		while(lCurrent < levelOrder.length) {
			Node<Integer> temp = tree.remove();
            Integer min = minIndices.remove();
			Integer max = maxIndices.remove();
			int index = findInorderIndex(temp.value);
			if(index == -1)
				System.out.println("Error: values not matching");
			if((index-min) > 0) {
				//left
				Node<Integer> pNode = new Node<Integer>(levelOrder[lCurrent++], null, null);
				temp.left = pNode;
                minIndices.add(min);
				maxIndices.add(index-1);
				tree.add(pNode);
			} 
			if((max-index) > 0) {
				//right
				Node<Integer> pNode = new Node<Integer>(levelOrder[lCurrent++], null, null);
				temp.right = pNode;
				minIndices.add(index+1);
				maxIndices.add(max);
				tree.add(pNode);
			}		
		}
		return root;
	}

	//speedup-Add hashMap
	private Integer findInorderIndex(int value) {
        return inOrderHashMap.get(value);
	}

	/*
	 * main method
	 */
	public static void main(String[] args) {
		int[] inOrder = {7, 6, 5, 4, 3, 2, 1};
		int[] levelOrder = {1, 2, 3, 4, 5, 6, 7};
		buildTreeFromInorderAndLevelOrder b = new buildTreeFromInorderAndLevelOrder();
		b.build(inOrder, levelOrder);
		int[] inOrder2 = {4, 2, 5, 1, 6, 3, 7};
		b.build(inOrder2, levelOrder);
		int[] inOrder3 = {4, 8, 10, 12, 14, 20, 22};
		int[] levelOrder3 = {20, 8, 22, 4, 12, 10, 14};
		b.build(inOrder3, levelOrder3);
		return;
	}
}
