package cz.cvut.fel.pjv;

import cz.cvut.fel.pjv.impl.TreeImpl;
import cz.cvut.fel.pjv.impl.NodeImpl;

public class Main {

   public void printtree(Node root, int cnt){
       cnt++;
       if (root == null)
              return;
       for(int i = 0; i < cnt; i++)
              System.out.print(" ");

         System.out.println(root.getValue());
         printtree(root.getLeft(),cnt);
         printtree(root.getRight(),cnt);
   }


    public static void main(String[] args) {
        int[] numbers= {1,2,3,4};
        Main main = new Main();
        Tree tree = new TreeImpl();
        tree.setTree(numbers);
        Node nd = tree.getRoot();
        String res = tree.toString();

     //   System.out.println(nd.getValue());
      //  System.out.println(res);

        main.printtree(nd, 0);
    }



}
