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
    
    public static final int OPEN = 0;
    public static final int VISITED = 1;
    public static final int CLOSED = 2;
    
    private int x;
    private int y;
    private int[] paths = {OPEN,OPEN,OPEN,OPEN};
    private String key;
    
    public Node(int n_x, int n_y){
        this.x = n_x;
        this.y = n_y;
        this.key = x+","+y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getPaths(int path) {
        return paths[path];
    }

    public String getKey() {
        return key;
    }
    
    public void changePaths(int path, int state){
        paths[path] = state;
    }
}
