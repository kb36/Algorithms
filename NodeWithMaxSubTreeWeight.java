import java.util.Queue;
import java.util.LinkedList;

/**
 * Given a binary tree which can contain negative values, return a node with maximum
 * sub tree weight. Sub tree weight includes the current node.
 * e.g.        4
 *            / \
 *           1   0
 *          / \   \
 *        -1  -2   -3
 *        /    \    \
 *       4     5     9
 *      / \   / \   / \
 *     3  2  -6 7  -10 -11
 */

public class NodeWithMaxSubTreeWeight {
    /**
     * Tree Node class
     */
    public static class Node<T> {
        public Node<T> left;
        public Node<T> right;
        public T value;
        
        /**
         * constructor of tree node
         */
        public Node(T value, Node<T> left, Node<T> right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
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
     * wrapper class for returning multiple values
     * assuming node structure not to be modified.
     */ 
    public static class NodeWrapper<T> {
        Node<T> node;
        long weight;
        long maxWeight;
        public NodeWrapper(Node<T> node, long weight, long maxWeight) {
            this.node = node;
            this.weight = weight;
            this.maxWeight = maxWeight;
        }   
    }


    /**
     * Returns Node with max subtree weight
     */
    public NodeWrapper<Integer> findNodeWithMaxSubTreeWeight(Node<Integer> current) {
        //post order
        NodeWrapper<Integer> c = new NodeWrapper<Integer>(current, 0, 0);
        if(current == null) {
            return c;
        }
        
        NodeWrapper<Integer> left = findNodeWithMaxSubTreeWeight(current.left);
        NodeWrapper<Integer> right = findNodeWithMaxSubTreeWeight(current.right);
        long currentWeight = current.value + left.weight + right.weight;

        c.weight = currentWeight;

        if(left.weight > right.weight) {
            c.maxWeight = left.weight;
            c.node = left.node;
        }
        else {
            c.maxWeight = right.weight;
            c.node = right.node;
        }
        
        if(c.weight >= c.maxWeight) {
            c.maxWeight = c.weight;
            c.node = current;
        }
        return c;
        
    }

        
    /**
     * main method
     */
    public static void main(String[] args) {
        Node<Integer> n14 = new Node<Integer>(1, null, null);
        Node<Integer> root = new Node<Integer>(-4, null, null);
        Node<Integer> n1 = new Node<Integer>(-1, null, null);
        Node<Integer> n2 = new Node<Integer>(-2, null, null);
        Node<Integer> n3 = new Node<Integer>(-3, null, null);
        Node<Integer> n4 = new Node<Integer>(4, null, null);
        Node<Integer> n5 = new Node<Integer>(5, null, null);
        Node<Integer> n6 = new Node<Integer>(9, null, null);

        Node<Integer> n7 = new Node<Integer>(3, null, null);
        Node<Integer> n8 = new Node<Integer>(2, null, null);
        Node<Integer> n9 = new Node<Integer>(-6, null, null);
        Node<Integer> n10 = new Node<Integer>(7, null, null);
        Node<Integer> n11 = new Node<Integer>(-10, null, null);
        Node<Integer> n12 = new Node<Integer>(-11, null, null);
        
        Node<Integer> n13 = new Node<Integer>(0, null, null);
        root.left = n14;
        root.right = n13;
        
        n14.left = n1;
        n14.right = n2;
        
        n1.left = n4;
        n4.left = n7;
        n4.right = n8;

        n2.right = n5;
        n5.left = n9;
        n5.right = n10;

        n13.right = n3;
        n3.right = n6;
        
        n6.left = n11;
        n6.right = n12;
        
        NodeWithMaxSubTreeWeight node = new NodeWithMaxSubTreeWeight();
        node.printLevelOrder(root);
        NodeWrapper<Integer> mWeightNode= node.findNodeWithMaxSubTreeWeight(root);
        if(mWeightNode != null)
        System.out.println("Max weight node: "+ (mWeightNode.node != null ? mWeightNode.node.value : null) + " max weight: "+ mWeightNode.maxWeight);
    }
}
