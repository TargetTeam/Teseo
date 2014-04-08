package unalcol.agents.homework;

import java.util.Formatter;

public class SparseMatrix {
	private int rows, cols;
	Node start;
	public SparseMatrix(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		start = new Node(0, 0, null);
		Node mover = start, newNode;
		for(int i = 1; i < this.cols; i ++) {
			newNode = new Node(0, i, null);
			newNode.setNext(start);
			mover.setNext(newNode);
			mover = mover.getNext();
		}
		
		mover = start;
		for(int i = 1; i < this.rows; i ++) {
			newNode = new Node(i, 0, null);
			newNode.setDown(start);
			mover.setDown(newNode);
			mover = mover.getDown();
		}
	}
	
	public Node retriveData(int i, int j) {
		Node mover = start;
		if(!checkLimits(i, j))
			return null;
		while(mover.getCol() != j)
			mover = mover.getNext();
		do {
			mover = mover.getDown();
		} while(mover.getRow() != i && mover.getRow() != 0);
		
		if(mover.getRow() == i && mover.getCol() == j)
			return (Node) mover.getData();
		return null;
	}
	
	public boolean checkLimits(int row, int col) {
		if(row < this.rows && rows > 0 && col < this.cols && cols > 1)
			return true;
		return false;
	}
	
	public boolean add(int i, int j, Node data) {
		Node mover, newNode;
		mover = start;
		if(!checkLimits(i, j))
			return false;
		
		while(mover.getRow() != i) {
			mover = mover.getDown();
		}
		
		while(mover.getNext().getCol() < j && mover.getNext().getCol() != 0) {
			mover = mover.getNext();
		}
		
		if(mover.getCol() == j) {
			mover.setData(data);
			return true;
		}
		
		if(mover.getNext().getCol() == j) {
			mover.getNext().setData(data);
			return true;
		}
		
		newNode = new Node(i, j, data);
		newNode.setNext(mover.getNext());
		mover.setNext(newNode);
		
		mover = start;
		while(mover.getCol() != j)
			mover = mover.getNext();
		
		while(mover.getDown().getRow() < i && mover.getDown().getRow() != 0) {
			mover = mover.getDown();
		}
		
		newNode.setDown(mover.getDown());
		mover.setDown(newNode);
	
		return true;
	}
	
	public int getRows() {
		return this.rows;
	}
	
	public int getCols() {
		return this.cols;
	}
	
	public Node remove(int i, int j) {
		if(!checkLimits(i, j))
			return null;
		if(retriveData(i, j) == null)
			return null;
		Node mover = start;
		if(i == 0 || j == 0) {
			Node temp = retriveData(i, j);
			add(i, j, null);
			return temp;
		}
		while(mover.getRow() != i)
			mover = mover.getDown();
		
		while(mover.getNext().getCol() != j)
			mover = mover.getNext();
		
		Node temp = mover.getNext();
		mover.setNext(temp.getNext());
		mover = start;
		while(mover.getCol() != j)
			mover = mover.getNext();
		
		while(mover.getDown().getRow() != i)
			mover = mover.getDown();
		
		mover.setDown(temp.getDown());
		
		
		return (Node) temp.getData();
	}
	
	public String toString() {
		String[][] str = new String[rows][cols];
		for(int i = 0; i < str.length; i ++) {
			for(int j = 0; j < str[i].length; j ++) {
				if(retriveData(i, j) != null)
					str[i][j] = retriveData(i, j).toString();
				else
					str[i][j] = new Formatter().format("%4d", 0).toString();
			}
		}
		
		String strDone = "";
		for(int i = 0; i < str.length; i ++) {
			for(int j = 0; j < str[i].length; j ++) {
				strDone += str[i][j];
			}
			strDone +="\n";
		}
		return strDone;
	}
	
}
