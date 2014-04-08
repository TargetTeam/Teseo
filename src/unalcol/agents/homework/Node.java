/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.agents.homework;

/**
 *
 * @author windows
 */
public class Node {

    private int x;
    private int y;
    private Object data;
    private Node down, next;

    public Node(int n_x, int n_y, Object data) {
        this.x = n_x;
        this.y = n_y;
        this.data = data;
        down = this;
        next = this;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setDown(Node down) {
        this.down = down;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node getDown() {
        return this.down;
    }

    public Node getNext() {
        return this.next;
    }

    public int getCol() {
        return this.y;
    }

    public int getRow() {
        return this.x;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
