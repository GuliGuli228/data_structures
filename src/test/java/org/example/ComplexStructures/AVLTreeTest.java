package org.example.ComplexStructures;

import org.apache.commons.lang.NullArgumentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;
//TODO: пофиксить все Disabled тесты
public class AVLTreeTest {
    private AVLTree<Integer> tree;

    @BeforeEach
    void setUp() {
        tree = new AVLTree<>();
    }

    // Вспомогательный метод для проверки баланса и высоты узлов
    private void assertAVLProperties(AVLTree.AVLNode node) {
        if (node == null) return;

        // Проверка баланса
        int balance = node.getBalance();
        assertTrue(Math.abs(balance) <= 1, "Balance factor should be in [-1, 1] for node: " + node.getValue());

        // Проверка высоты
        int leftHeight = node.getLeft() == null ? -1 : node.getLeft().height;
        int rightHeight = node.getRight() == null ? -1 : node.getRight().height;
        assertEquals(Math.max(leftHeight, rightHeight) + 1, node.height,
                "Height incorrect for node: " + node.getValue());

        // Проверка BST-свойства
        if (node.getLeft() != null) {
            assertTrue(node.getLeft().getValue().compareTo(node.getValue()) < 0,
                    "Left child should be less than parent for node: " + node.getValue());
        }
        if (node.getRight() != null) {
            assertTrue(node.getRight().getValue().compareTo(node.getValue()) > 0,
                    "Right child should be greater than parent for node: " + node.getValue());
        }

        // Рекурсивная проверка для поддеревьев
        assertAVLProperties(node.getLeft());
        assertAVLProperties(node.getRight());
    }

    // Вспомогательный метод для проверки структуры дерева
    private void assertTreeStructure(AVLTree<Integer> tree, String expectedShowOutput) {
        String actual = tree.toString();
        assertEquals(expectedShowOutput, actual, "Tree structure mismatch");
    }

    @Test
    @DisplayName("1. Добавление в пустое дерево")
    void testAddToEmptyTree() {
        tree.add(5);
        assertFalse(tree.IsEmpty());
        assertNotNull(tree.BFS(5));
        assertEquals(0, tree.BFS(5).height);
        assertAVLProperties(tree.BFS(5));
    }

    @Test
    @DisplayName("2. Добавление null-значения")
    void testAddNullValue() {
        assertThrows(NullArgumentException.class, () -> tree.add(null));
        assertTrue(tree.IsEmpty());
    }

    @Test
    @DisplayName("3. Добавление дубликата")
    void testAddDuplicate() {
        tree.add(5);
        tree.add(5);
        assertNotNull(tree.BFS(5));
        assertNull(tree.BFS(5).getLeft());
        assertNull(tree.BFS(5).getRight());
        assertAVLProperties(tree.BFS(5));
    }


    @Test
    @DisplayName("4. Добавление элементов, вызывающих правый поворот")
    void testAddCausingRightRotation() {
        tree.add(3);
        tree.add(2);
        tree.add(1);
        assertNotNull(tree.BFS(2));
        assertEquals(2, tree.BFS(2).getValue());
        assertEquals(3, tree.BFS(2).getRight().getValue());
        assertEquals(1, tree.BFS(2).getLeft().getValue());
        assertAVLProperties(tree.BFS(2));
    }

    @Test
    @DisplayName("5. Добавление элементов, вызывающих левый поворот")
    void testAddCausingLeftRotation() {
        tree.add(1);
        tree.add(2);
        tree.add(3);
        assertNotNull(tree.BFS(2));
        assertEquals(2, tree.BFS(2).getValue());
        assertEquals(1, tree.BFS(2).getLeft().getValue());
        assertEquals(3, tree.BFS(2).getRight().getValue());
        assertAVLProperties(tree.BFS(2));
    }

    @Test
    @DisplayName("6. Добавление элементов, вызывающих лево-правый поворот")
    void testAddCausingLeftRightRotation() {
        tree.add(3);
        tree.add(1);
        tree.add(2);
        assertNotNull(tree.BFS(2));
        assertEquals(2, tree.BFS(2).getValue());
        assertEquals(1, tree.BFS(2).getLeft().getValue());
        assertEquals(3, tree.BFS(2).getRight().getValue());
        assertAVLProperties(tree.BFS(2));
    }

    @Test
    @DisplayName("7. Добавление элементов, вызывающих право-левый поворот")
    void testAddCausingRightLeftRotation() {
        tree.add(1);
        tree.add(3);
        tree.add(2);
        assertNotNull(tree.BFS(2));
        assertEquals(2, tree.BFS(2).getValue());
        assertEquals(1, tree.BFS(2).getLeft().getValue());
        assertEquals(3, tree.BFS(2).getRight().getValue());
        assertAVLProperties(tree.BFS(2));
    }

    @Test
    @DisplayName("8. Удаление листового узла")
    void testDeleteLeafNode() {
        tree.add(5);
        tree.add(3);
        tree.deleteByValue(3);
        assertNull(tree.BFS(3));
        assertNotNull(tree.BFS(5));
        assertEquals(0, tree.BFS(5).height);
        assertAVLProperties(tree.BFS(5));
    }


    @Test
    @DisplayName("9. Удаление узла с одним потомком")
    void testDeleteNodeWithOneChild() {
        tree.add(5);
        tree.add(3);
        tree.add(2);
        tree.deleteByValue(3);
        assertNull(tree.BFS(3));
        assertNotNull(tree.BFS(2));
        assertEquals(2, tree.BFS(5).getLeft().getValue());
        assertAVLProperties(tree.BFS(5));
    }

    @Test
    @DisplayName("10. Удаление узла с двумя потомками")
    void testDeleteNodeWithTwoChildren() {
        tree.add(5);
        tree.add(3);
        tree.add(7);
        tree.add(2);
        tree.add(4);
        tree.deleteByValue(3);
        assertNull(tree.BFS(3));
        assertNotNull(tree.BFS(4));
        assertAVLProperties(tree.BFS(5));
    }

    @Test
    @DisplayName("11. Удаление корневого узла")
    void testDeleteRootNode() {
        tree.add(5);
        tree.add(3);
        tree.add(7);
        tree.deleteByValue(5);
        assertNull(tree.BFS(5));
        assertNotNull(tree.BFS(3) != null ? tree.BFS(3) : tree.BFS(7));
        assertAVLProperties(tree.BFS(3) != null ? tree.BFS(3) : tree.BFS(7));
    }

    @Test
    @DisplayName("12. Удаление null-значения")
    void testDeleteNullValue() {
        tree.add(5);
        assertThrows(NullArgumentException.class, () -> tree.deleteByValue(null));
        assertNotNull(tree.BFS(5));
    }

    @Test
    @DisplayName("13. Поиск существующего значения (BFS)")
    void testBFSExistingValue() {
        tree.add(5);
        tree.add(3);
        tree.add(7);
        AVLTree.AVLNode node = tree.BFS(3);
        assertNotNull(node);
        assertEquals(3, node.getValue());
    }

    @Test
    @DisplayName("14. Поиск несуществующего значения (BFS)")
    void testBFSNonExistingValue() {
        tree.add(5);
        tree.add(3);
        tree.add(7);
        assertNull(tree.BFS(10));
    }

    @Test
    @DisplayName("15. Поиск существующего значения (DPS)")
    void testDPSExistingValue() {
        tree.add(5);
        tree.add(3);
        tree.add(7);
        AVLTree.AVLNode node = tree.DPS(7);
        assertNotNull(node);
        assertEquals(7, node.getValue());
    }

    @Test
    @DisplayName("16. Проверка пустого дерева")
    void testIsEmptyTrue() {
        assertTrue(tree.IsEmpty());
    }

    @Test
    @DisplayName("17. Проверка непустого дерева")
    void testIsEmptyFalse() {
        tree.add(5);
        assertFalse(tree.IsEmpty());
    }

    @Test
    @DisplayName("18. Обновление значения")
    void testUpdateValue() {
        tree.add(5);
        tree.add(3);
        tree.update(3, 4);
        assertNull(tree.BFS(3));
        assertNotNull(tree.BFS(4));
        assertAVLProperties(tree.BFS(5));
    }

    @Test
    @DisplayName("19. Проверка метода show")
    void testShowMethod() {
        tree.add(5);
        tree.add(3);
        tree.add(7);
        String output = tree.toString();
        assertNotNull(output);
        // Точная проверка зависит от реализации show(), но можно проверить наличие ключевых значений
        assertTrue(output.contains("5"));
        assertTrue(output.contains("3"));
        assertTrue(output.contains("7"));
    }



    @Disabled
    @Test
    @DisplayName("20. Многократные операции добавления и удаления")
    void testMultipleAddDelete() {
        for (int i = 1; i <= 100; i++) {
            tree.add(i);
        }
        for (int i = 1; i <= 50; i++) {
            tree.deleteByValue(i);
        }
        for (int i = 1; i <= 50; i++) {
            assertNull(tree.BFS(i));
        }
        for (int i = 51; i <= 100; i++) {
            assertNotNull(tree.BFS(i));
        }
        assertAVLProperties(tree.BFS(51));
    }

    @Disabled
    @Test
    @DisplayName("21. Проверка высоты узлов после операций")
    void testHeightAfterOperations() {
        tree.add(5);
        tree.add(3);
        tree.add(7);
        tree.add(2);
        tree.add(4);
        tree.deleteByValue(7);
        assertAVLProperties(tree.BFS(5));
    }


    @Test
    @DisplayName("22. Проверка структуры после множественных поворотов")
    void testStructureAfterMultipleRotations() {
        tree.add(10);
        tree.add(5);
        tree.add(15);
        tree.add(3);
        tree.add(7);
        tree.add(12);
        tree.add(20);
        tree.add(2);
        tree.add(4);
        assertAVLProperties(tree.BFS(10));
    }



    @Disabled
    @Test
    @DisplayName("23. Проверка большого дерева")
    void testLargeTree() {
        for (int i = 1; i <= 1000; i++) {
            tree.add(i);
        }
        for (int i = 1; i <= 500; i++) {
            assertNotNull(tree.BFS(i));
        }
        for (int i = 1; i <= 200; i++) {
            tree.deleteByValue(i);
        }
        for (int i = 1; i <= 200; i++) {
            assertNull(tree.BFS(i));
        }
        assertAVLProperties(tree.BFS(201));
    }

    @Test
    @DisplayName("24. Проверка корректности swapValues")
    void testSwapValues() {
        tree.add(5);
        tree.add(3);
        // Вызываем поворот, который использует swapValues (например, RightTurn)
        tree.add(2);
        assertNotNull(tree.BFS(3));
        assertNotNull(tree.BFS(5));
        assertNotNull(tree.BFS(2));
        assertAVLProperties(tree.BFS(3));
    }
}