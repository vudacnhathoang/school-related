package cz.cvut.fel.pjv.impl;

import cz.cvut.fel.pjv.Node;

public class NodeImpl implements Node {

    public Node left;
    public Node right;
    int value;



    // TODO: implement this class
    public NodeImpl(int value){
        this.value = value;
        this.left = null;
        this.right = null;
    }


    @Override
    public Node getLeft() {
        return this.left;
    }

    @Override
    public Node getRight() {
        return this.right;
    }

    @Override
    public int getValue() {
        return this.value;
    }

    public void setLeft(Node left) {
        this.left = left;
    }
    public  void setRight(Node right) {
        this.right = right;
    }

}
