package Lab_2_1_3;

import Collections.MyArrayList;
import Collections.MyLinkedList;
import Collections.MyStack;
import Lab_2_1_3.AVLTree.TreeAVL;

public class Main {



    public static void main(String[] args) {

        String input = "(5452(6(3(4)(5)))(7(2)(5)))";
        int curIndChar = 0;
        char curChar;
        int sizeInput = input.length();
        MyStack<Node> stackNode = new MyLinkedList<>();
        MyStack<Character> stackBrack = new MyLinkedList<>();
        BinTree<Integer> tree = null;


        while (curIndChar < sizeInput) {
            curChar = input.charAt(curIndChar);
            if (curChar == '('){
                stackBrack.push(curChar);
            }

            else if (curChar == ')'){
                stackBrack.pop();
                stackNode.pop();
            }

            else{
                //input num
                char nextChar = input.charAt(curIndChar + 1);
                int num = 0;
                while (nextChar >= '0' && nextChar <= '9') {
                    num *= 10;
                    num += (curChar - '0');
                    curChar = nextChar;
                    curIndChar++;
                    nextChar = input.charAt(curIndChar + 1);
                }
                num *= 10;
                num += (curChar - '0');
                Node curNode = new Node(num);
                if (stackNode.isEmpty()){
                    stackNode.push(curNode);
                    tree = new BinTree<>(curNode);
                }
                else{
                    Node last = stackNode.peek();
                    if (last.left == null) last.left = curNode;
                    else if (last.right == null) last.right = curNode;
                    else{
                        System.out.println("It's not bin Tree");
                    }
                    stackNode.push(curNode);

                }

            }
            curIndChar++;
            //debag.viewStack(stackNode);


        }

        //TreeAVL<Integer> treeAVL = new TreeAVL( (Integer)tree.getRoot().data);
        TreeAVL<Integer> treeAVL = new TreeAVL();
        MyArrayList<Integer> treeArray = tree.getTree();


        for (int i = 0; i < treeArray.getSize(); i++){
            treeAVL.add(treeArray.get(i));
        }


        System.out.println(treeArray.toString());
        System.out.println(treeAVL.contLevelOrder().toString());
        System.out.println(treeAVL.contPreOrder().toString());


    }
}
