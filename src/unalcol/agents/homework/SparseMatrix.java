package unalcol.agents.homework;

import java.util.Formatter;

public class SparseMatrix {

    Node start;

    public SparseMatrix(Data data) {
        start = new Node(0, 0, data);
    }

    public Data retriveData(int i, int j) {
        Node mover = start;
        while (mover.getCol() != j) {
            mover = mover.getNext();
        }
        do {
            mover = mover.getDown();
        } while (mover.getRow() != i && mover.getRow() != 0);

        if (mover.getRow() == i && mover.getCol() == j) {
            return (Data) mover.getData();
        }
        return null;
    }

    public boolean add(int i, int j, Data data) {
        Node mover, newNode;
        mover = start;
        while (mover.getRow() != i) {
            mover = mover.getDown();
        }

        while (mover.getNext().getCol() < j && mover.getNext().getCol() != 0) {
            mover = mover.getNext();
        }

        if (mover.getCol() == j) {
            mover.setData(data);
            return true;
        }

        if (mover.getNext().getCol() == j) {
            mover.getNext().setData(data);
            return true;
        }

        newNode = new Node(i, j, data);
        newNode.setNext(mover.getNext());
        mover.setNext(newNode);

        mover = start;
        while (mover.getCol() != j) {
            mover = mover.getNext();
        }

        while (mover.getDown().getRow() < i && mover.getDown().getRow() != 0) {
            mover = mover.getDown();
        }

        newNode.setDown(mover.getDown());
        mover.setDown(newNode);

        return true;
    }

    public Data remove(int i, int j) {
        if (retriveData(i, j) == null) {
            return null;
        }
        Node mover = start;
        if (i == 0 || j == 0) {
            Data temp = retriveData(i, j);
            add(i, j, null);
            return temp;
        }
        while (mover.getRow() != i) {
            mover = mover.getDown();
        }

        while (mover.getNext().getCol() != j) {
            mover = mover.getNext();
        }

        Node temp = mover.getNext();
        mover.setNext(temp.getNext());
        mover = start;
        while (mover.getCol() != j) {
            mover = mover.getNext();
        }

        while (mover.getDown().getRow() != i) {
            mover = mover.getDown();
        }

        mover.setDown(temp.getDown());


        return (Data) temp.getData();
    }

}
