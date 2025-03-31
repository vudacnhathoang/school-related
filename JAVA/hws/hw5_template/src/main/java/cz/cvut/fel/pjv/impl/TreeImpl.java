package cz.cvut.fel.pjv.impl;

import cz.cvut.fel.pjv.Node;
import cz.cvut.fel.pjv.Tree;

public class TreeImpl implements Tree {

    Node root;

    public TreeImpl(){
        this.root = null;
    }


    public void insertrecursive(Node parent, int start, int end, int[] values, boolean isRight){
        if (start >end)
            return;
        int index = (start + end + 1)/2;
        Node newNode = new NodeImpl(values[index]);

        if (isRight) {
            ((NodeImpl) parent).setRight(newNode);
        }
        else {
            ((NodeImpl) parent).setLeft(newNode);
        }

        insertrecursive(newNode, start, index -1, values, false);
        insertrecursive(newNode, index + 1, end, values, true);


    }

    @Override
    public void setTree(int[] values) {
        Node current;
        current = this.root;
        if (values.length == 0) {
            this.root = null;
            return;
        }
        int index = values.length/ 2 ;
        this.root = new NodeImpl(values[index]);

        insertrecursive(this.root, 0, index -1, values, false);
        insertrecursive(this.root, index + 1, values.length -1, values, true);




    }
    @Override
    public Node getRoot() {
        return this.root;
    }


    private void stringrecursive(Node current, int cnt, StringBuilder res){

        if (current == null) return;

        for (int i = 0; i < cnt; i++)
            res.append(" ");

//        System.out.println(current.getValue());
        res.append("- " + current.getValue() + "\n");
        stringrecursive(current.getLeft(), cnt + 1, res);
        stringrecursive(current.getRight(), cnt + 1, res);

    }

    @Override
    public String toString( ) {
        int cnt = 0;
        StringBuilder res = new StringBuilder("");
        Node current= this.root;
        if (current == null)
            return "";

        res.append( "- " + current.getValue() + "\n");

        stringrecursive(current.getLeft(), cnt + 1, res);
        stringrecursive(current.getRight(), cnt + 1, res);
        return res.toString();
    }

}
