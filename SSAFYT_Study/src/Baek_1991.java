import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//트리순회

//node 클래스
class Node{
	char data;
	Node left;
	Node right;
	
	Node(char data){
		this.data = data;
	}
}

//tree 클래스
class Tree{
	Node root;
	
	//node를 만든다
	public void createNode(char data, char leftchild, char rightchild) {
		//root 노드가 없다면 노드 추가, right left child도 있으면 추가
		if(root == null) {
			root = new Node(data);
			
			if(leftchild != '.')
				root.left = new Node(leftchild);
			
			if(rightchild != '.')
				root.right = new Node(rightchild);
		}
		else {
			//있다면 searchNode 수행
			searchNode(root, data, leftchild, rightchild);
		}
	}
	
	//어디로 들어가야할지 찾음
	public void searchNode(Node node, char data, char leftchild, char rightchild) {
		if(node == null)
			return;
		
		//node를 찾았다면 값 입력
		else if(node.data == data) {
			if(leftchild != '.')
				node.left = new Node(leftchild);
			if(rightchild != '.')
				node.right = new Node(rightchild);
		}
		//아닐경우
		else {
			//왼쪽 탐색
			searchNode(node.left, data, leftchild, rightchild);
			//오른쪽 탐색
			searchNode(node.right, data, leftchild, rightchild);
		}
	}
	//전위
	public void preOrder(Node node) {
		System.out.print(node.data);
		if(node.left != null) preOrder(node.left);
		if(node.right != null) preOrder(node.right);
	}
	
	//중위
	public void inOrder(Node node) {
		if(node.left != null) inOrder(node.left);
		System.out.print(node.data);
		if(node.right != null) inOrder(node.right);
	}
	
	//후위
	public void postOrder(Node node) {
		if(node.left != null) postOrder(node.left);
		if(node.right != null) postOrder(node.right);
		System.out.print(node.data);
	}
}

public class Baek_1991 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		Tree tree = new Tree();
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			char c1 = st.nextToken().charAt(0);
			char c2 = st.nextToken().charAt(0);
			char c3 = st.nextToken().charAt(0);
			tree.createNode(c1, c2, c3);
		}
		
		tree.preOrder(tree.root);
		System.out.println();
		
		tree.inOrder(tree.root);
		System.out.println();
		
		tree.postOrder(tree.root);
	}	
}

