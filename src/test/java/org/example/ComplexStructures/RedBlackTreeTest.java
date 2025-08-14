package org.example.ComplexStructures;


import com.sun.source.tree.BinaryTree;
import org.example.AbstracClasses.RBTColors;
import org.example.BasicStructures.BinarySearchTree;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;


public class RedBlackTreeTest {

    private RedBlackTree<Integer> tree;

    @BeforeEach
    void setUp() {
        tree = new RedBlackTree<>();
    }
    @Test
    @DisplayName("Testing RBT after insertion")
    void insertionTest() throws Exception {
       tree.add(1);
       tree.add(2);
       tree.add(3);
       tree.add(4);
       checkRBTProp(tree);
       assertNotNull(tree.BFS(2));
       assertNotNull(tree.DPS(2));
       assertEquals("[ 1, 2, 3, 4 ]", tree.show());
    }
    @Test
    @DisplayName("Testing RBT after delete")
    void deleteTest() throws Exception {
        tree.add(1);
        tree.add(2);
        tree.add(3);
        tree.add(4);
        tree.add(5);

        tree.deleteByValue(5);
        checkRBTProp(tree);
        tree.deleteByValue(3);
        checkRBTProp(tree);

    }



    private boolean checkRBTProp(RedBlackTree<Integer> tree) throws Exception{
        RedBlackTree.RBTNode root = getRoot(tree);

        if (getRBTColors(root) != RBTColors.BLACK) return false;
        if (checkBlackHeight(root) == 0 ) return false;
        if (!checkRedNodesProp(root)) return false;
        return true;
    }
    private RedBlackTree.RBTNode getRoot (RedBlackTree<Integer> tree) throws Exception{
        Field rootField = BinarySearchTree.class.getDeclaredField("root");
        rootField.setAccessible(true);
        return (RedBlackTree.RBTNode) rootField.get(tree);
    }

    private RedBlackTree.RBTNode getLeft (RedBlackTree.RBTNode node) throws Exception{
        Method getLeftMethod = RedBlackTree.RBTNode.class.getDeclaredMethod("getLeft");
        getLeftMethod.setAccessible(true);
        return (RedBlackTree.RBTNode) getLeftMethod.invoke(node);
    }

    private RedBlackTree.RBTNode getRight (RedBlackTree.RBTNode node) throws Exception{
        Method getRightMethod = RedBlackTree.RBTNode.class.getDeclaredMethod("getRight");
        getRightMethod.setAccessible(true);
        return (RedBlackTree.RBTNode) getRightMethod.invoke(node);
    }

    private RBTColors getRBTColors(RedBlackTree<Integer>.RBTNode node) throws Exception{
        Method getColorMethod = RedBlackTree.RBTNode.class.getDeclaredMethod("getColor");
        getColorMethod.setAccessible(true);
        return (RBTColors) getColorMethod.invoke(node);
    }

    private RedBlackTree.RBTNode getParent(RedBlackTree<Integer>.RBTNode node) throws Exception{
        Method getParentMethod = RedBlackTree.RBTNode.class.getDeclaredMethod("getParent");
        getParentMethod.setAccessible(true);
        return (RedBlackTree.RBTNode) getParentMethod.invoke(node);
    }
    private int checkBlackHeight(RedBlackTree<Integer>.RBTNode node) throws Exception{

        if (node instanceof RedBlackTree.NILNode) return 1;
        int LeftBH = checkBlackHeight(getLeft(node));
        if (LeftBH==0) return 0;
        int RightBH = checkBlackHeight(getRight(node));
        if (RightBH==0) return 0;

        if (LeftBH != RightBH) return 0;
        if (getRBTColors(node) == RBTColors.BLACK) return LeftBH +1;
        else return LeftBH;
    }
    private boolean checkRedNodesProp(RedBlackTree.RBTNode node) throws Exception {
        Stack <RedBlackTree.RBTNode> stack = new Stack<>();
        stack.push(node);

        while (!stack.isEmpty()){
            RedBlackTree.RBTNode pop = stack.pop();
            if (getParent(pop) != null){
                if (getRBTColors(pop) == RBTColors.RED && getRBTColors(getParent(pop)) ==  RBTColors.RED) return false;
            }
            if (!(getRight(pop) instanceof RedBlackTree.NILNode)) stack.push(getRight(pop));
            if(!(getLeft(pop) instanceof RedBlackTree.NILNode)) stack.push(getLeft(pop));
        }
        return true;
    }
}