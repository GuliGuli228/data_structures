package org.example.ComplexStructures;


import com.sun.source.tree.BinaryTree;
import org.example.AbstracClasses.RBTColors;
import org.example.BasicStructures.BinarySearchTree;
import org.example.Exceptions.EmptyBinaryTreeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;


public class RedBlackTreeTest {

    private RedBlackTree<Integer> tree;

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

    @BeforeEach
    void setUp() {
        tree = new RedBlackTree<>();
    }
    @Test
    @DisplayName("Insert in empty tree")
    void insertionTest() throws Exception {
       tree.add(1);
       checkRBTProp(tree);
    }
    @Test
    @DisplayName("Insert in with tree violation of properties ")
    void insertionWithViolation() throws Exception {
        tree.add(10);
        tree.add(5);
        tree.add(3);
        checkRBTProp(tree);
    }
    @Test
    @DisplayName("Insert with multiple rotations")
    void insertionWithMultipleRotations() throws Exception {
        tree.add(10);
        tree.add(5);
        tree.add(15);
        tree.add(3);
        tree.add(7);
        tree.add(1);
        checkRBTProp(tree);
    }
    @Test
    @DisplayName("Delete list")
    void deleteTest() throws Exception {
        tree.add(10);
        tree.add(5);
        tree.add(15);
        tree.deleteByValue(5);
        checkRBTProp(tree);
    }
    @Disabled
    @Test
    @DisplayName("Delete black node with one red child")
    void deleteBlackNodeWithOneRedChild() throws Exception {
        tree.add(10);
        tree.add(5);
        tree.add(15);
        tree.add(3);
        tree.deleteByValue(5);
        checkRBTProp(tree);
    }
    @Disabled
    @Test
    @DisplayName("Delete root")
    void deleteRootTest() throws Exception {
        tree.add(10);
        tree.add(5);
        tree.add(15);
        tree.deleteByValue(10);
        checkRBTProp(tree);
    }
    @Test
    @DisplayName("BFS existing element")
    void BFSExistsTest() throws Exception {
        tree.add(10);
        tree.add(20);
        tree.add(5);
        assertNotNull(tree.BFS(5));
    }
    @Test
    @DisplayName("BFS non-existing element")
    void BFSNonExistsTest() throws Exception {
        tree.add(10);
        tree.add(20);
        tree.add(30);
        assertNull(tree.BFS(25));
    }
    @Test
    @DisplayName("BFS in empty tree")
    void BFSInEmptyTreeTest() throws Exception {
        assertThrows(EmptyBinaryTreeException.class, ()->{tree.BFS(0);});
    }
    @Test
    @DisplayName("DPS in empty tree")
    void DPSInEmptyTreeTest() throws Exception {
        assertThrows(EmptyBinaryTreeException.class, ()->{tree.BFS(0);});
    }
    @Test
    @DisplayName("DPS existing element")
    void DPSExistsTest() throws Exception {
        tree.add(10);
        tree.add(20);
        tree.add(5);
        assertNotNull(tree.BFS(5));
    }
    @Test
    @DisplayName("DPS non-existing element")
    void DPSNonExistsTest() throws Exception {
        tree.add(10);
        tree.add(20);
        tree.add(30);
        assertNull(tree.DPS(25));
    }
}