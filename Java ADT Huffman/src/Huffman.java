import java.util.HashSet;
import java.util.Set;

public class Huffman {

	private HuffmanData[] leafEntries;
	private final static int SIZE = 50;
	private PriorityQueueInterface<BinaryTreeInterface<HuffmanData>> pq;
	private BinaryTreeInterface<HuffmanData> huffmanTree;

	public Huffman() {
		leafEntries = new HuffmanData[SIZE];
		pq = new PriorityQueue<BinaryTreeInterface<HuffmanData>>();
		huffmanTree = new BinaryTree<HuffmanData>();
	}

	public void setFrequencies() {
	// Create test data and store as an array of HuffData
		leafEntries[0] = new HuffmanData(5000, 'a');
		leafEntries[1] = new HuffmanData(2000, 'b');
		leafEntries[2] = new HuffmanData(10000, 'c');
		leafEntries[3] = new HuffmanData(8000, 'd');
		leafEntries[4] = new HuffmanData(22000, 'e');
		leafEntries[5] = new HuffmanData(49000, 'f');
		leafEntries[6] = new HuffmanData(4000, 'g');
	}

	public void setPriorityQueue() {
	// Copy test data from array LeafEntries of HuffData
	// into a PriorityQueue of HuffmanTree
		for (int i = 0; i < 7; i++) {
			if (leafEntries[i].getFrequency() > 0)
				pq.add(new BinaryTree<HuffmanData>(leafEntries[i]));
		}
	}

	public void createHuffmanTree() {
	// Process PriorityQueue pq until there is a complete HuffmanTree
		while (pq.getSize() > 1) {
			BinaryTree<HuffmanData> first = (BinaryTree<HuffmanData>) pq.removeMin();
			BinaryTree<HuffmanData> second = (BinaryTree<HuffmanData>) pq.removeMin();
			// to ensure leftChild < rightChild
			BinaryTree<HuffmanData> leftChild, rightChild;

			if (first.compareTo(second) < 0 ) {
				leftChild = first;
				rightChild = second;
			} else {
				leftChild = second;
				rightChild = first;
			}

			int freq = leftChild.getRootData().getFrequency() + rightChild.getRootData().getFrequency();
			BinaryTree<HuffmanData> newRoot =
					new BinaryTree<>(
						new HuffmanData(freq),
						leftChild,
						rightChild
					);
			pq.add(newRoot);
		}
		huffmanTree = pq.getMin();
	}

	public void printCode() {
		String tracker = "";
		printCodeProcedure(tracker, huffmanTree);
	}

	private void printCodeProcedure(String code, BinaryTreeInterface<HuffmanData> tree) {
	// Print out a complete HuffmanTree
		if (tree != null) {
			StringBuilder sb = new StringBuilder();
			if (tree.isEmpty())
				System.out.println("Tree is Empty");
			else {
				HuffmanData data = tree.getRootData();
				if (data.getSymbol() != '\u0000') {
					sb.append(data.getSymbol())
							.append(": ")
							.append(code);
					System.out.println(sb);
				}
				//left
				printCodeProcedure(code.concat("0"), tree.getLeftSubtree());
				//right
				printCodeProcedure(code.concat("1"), tree.getRightSubtree());
			}
		}
	}

	public static void main(String[] args) {

	}
}
