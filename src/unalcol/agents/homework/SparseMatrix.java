package unalcol.agents.homework;

import java.util.Formatter;

public class SparseMatrix {

    Node start;

    public SparseMatrix(Data data) {
        start = new Node(0, 0, data);
    }

    public Data retriveData(int i, int j) {
        Node mover = start;
        if(j<0){
            while (mover.getCol() != j) {
                mover = mover.getPrev();
            }
        }else{
            while (mover.getCol() != j) {
                mover = mover.getNext();
            }
        }
        if(i<0){
            do {
                mover = mover.getUp();
            } while (mover.getRow() != i && mover.getRow() != 0);
        }else{
             do {
                mover = mover.getDown();
            } while (mover.getRow() != i && mover.getRow() != 0);
        }

        if (mover.getRow() == i && mover.getCol() == j) {
            return (Data) mover.getData();
        }
        return null;
    }

    public boolean add(int i, int j, Data data) {
        Node mover, newNode;
        mover = start;
        if(i<0){
            while (mover.getRow() != i) {
                mover = mover.getUp();
            }
        }else{
            while (mover.getRow() != i) {
                mover = mover.getDown();
            }
        }
        if(j<0){
            while (mover.getPrev().getCol() < j && mover.getPrev().getCol() != 0) {
                mover = mover.getPrev();
            }
        }else{
            while (mover.getNext().getCol() < j && mover.getNext().getCol() != 0) {
                mover = mover.getNext();
            }
        }

        if (mover.getCol() == j) {
            mover.setData(data);
            return true;
        }
        if (j<0 && mover.getPrev().getCol() == j) {
            mover.getPrev().setData(data);
            return true;
        }else if (j>=0 && mover.getNext().getCol() == j) {
            mover.getNext().setData(data);
            return true;
        }
        
        if(j<0){            
            newNode = new Node(i, j, data);
            newNode.setPrev(mover.getPrev());
            mover.setPrev(newNode);
        }else{            
            newNode = new Node(i, j, data);
            newNode.setNext(mover.getNext());
            mover.setNext(newNode);
        }

        mover = start;
        if(j<0){ 
            while (mover.getCol() != j) {
                mover = mover.getPrev();
            }
        }else{
            while (mover.getCol() != j) {
                mover = mover.getNext();
            }
        }
        if(i<0){ 
            while (mover.getUp().getRow() < i && mover.getUp().getRow() != 0) {
                mover = mover.getUp();
            }
            newNode.setUp(mover.getDown());
            mover.setUp(newNode);
        }else{
            while (mover.getDown().getRow() < i && mover.getDown().getRow() != 0) {
                mover = mover.getDown();
            }
            newNode.setDown(mover.getDown());
            mover.setDown(newNode);
        }
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
        Node temp;
        if(i<0){
            while (mover.getRow() != i) {
                mover = mover.getUp();
            }
        }else{
            while (mover.getRow() != i) {
                mover = mover.getDown();
            }
        }
        if(j<0){
            while (mover.getPrev().getCol() != j) {
                mover = mover.getPrev();
            }
            temp = mover.getPrev();
        }else{
            while (mover.getNext().getCol() != j) {
                mover = mover.getNext();
            }
            temp = mover.getNext();            
        }
        mover = start;
        if(j<0){
            while (mover.getCol() != j) {
                mover = mover.getPrev();
            }
        }else{
            while (mover.getCol() != j) {
                mover = mover.getNext();
            }
        }
        if(i<0){
            while (mover.getUp().getRow() != i) {
                mover = mover.getUp();
            }
            mover.setUp(temp.getUp());
        }else{
            while (mover.getDown().getRow() != i) {
                mover = mover.getDown();
            }
            mover.setDown(temp.getDown());
        }

        return (Data) temp.getData();
    }

}
