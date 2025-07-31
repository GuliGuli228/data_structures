package org.example.BasicStructures;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BinarySearchTreeTest {

    private BinarySearchTree<Integer> tree;

    // Подготовка: создаем новое дерево перед каждым тестом
    @BeforeEach
    @DisplayName("Initialize a new empty tree")
    void setUp() {
        tree = new BinarySearchTree<>();
    }

    // Тесты для метода IsEmpty
    @Test
    @DisplayName("Test empty tree returns true")
    void testIsEmptyOnEmptyTree() {
        // Проверяем, что новое дерево пустое
        assertTrue(tree.IsEmpty(), "New tree should be empty");
    }

    @Test
    @DisplayName("Test non-empty tree returns false")
    void testIsEmptyOnNonEmptyTree() {
        // Добавляем один узел
        tree.add(10);
        assertFalse(tree.IsEmpty(), "Tree with a node should not be empty");
    }

    // Тесты для метода add
    @Test
    @DisplayName("Add node to an empty tree")
    void testAddToEmptyTree() {
        // Добавляем узел в пустое дерево
        tree.add(10);
        assertFalse(tree.IsEmpty(), "Tree should not be empty after adding a node");
        assertEquals("[ 10 ]", tree.show(), "Root should be 10");
    }

    @Test
    @DisplayName("Add multiple nodes to the tree")
    void testAddMultipleNodes() {
        // Добавляем несколько узлов и проверяем порядок
        tree.add(10);
        tree.add(5);
        tree.add(15);
        assertEquals("[ 5 10 15 ]", tree.show(), "Nodes should be in order: 5, 10, 15");
    }

    @Test
    @DisplayName("Test ignoring duplicate node addition")
    void testAddDuplicate() {
        // Добавляем одно и то же значение дважды
        tree.add(10);
        tree.add(10);
        assertEquals("[ 10 ]", tree.show(), "Duplicate should not be added");
    }

    @Test
    @DisplayName("Test throwing exception when adding null")
    void testAddNullValue() {
        // Проверяем поведение при попытке добавить null
        assertThrows(NullPointerException.class, () -> tree.add(null), "Adding null should throw an exception");
    }

    // Тесты для метода deleteByValue
    @Test
    @DisplayName("Delete a leaf node")
    void testDeleteLeafNode() {
        // Создаем дерево: 10 -> 5, 15, удаляем лист 5
        tree.add(10);
        tree.add(5);
        tree.add(15);
        tree.deleteByValue(5);
        assertEquals("[ 10 15 ]", tree.show(), "After deleting leaf 5, tree should contain 10, 15");
    }

    @Test
    @DisplayName("Delete a node with one child")
    void testDeleteNodeWithOneChild() {
        // Создаем дерево: 10 -> 5 -> 3, удаляем узел 5 с одним ребенком
        tree.add(10);
        tree.add(5);
        tree.add(3);
        tree.deleteByValue(5);
        assertEquals("[ 3 10 ]", tree.show(), "After deleting node 5, tree should contain 3, 10");
    }

    @Test
    @DisplayName("Delete a node with two children")
    void testDeleteNodeWithTwoChildren() {
        // Создаем дерево: 10 -> 5, 15 -> 12, 20, удаляем узел 15
        tree.add(10);
        tree.add(15);
        tree.add(12);
        tree.add(20);
        tree.deleteByValue(15);
        assertEquals("[ 10 12 20 ]", tree.show(), "After deleting node 15, tree should contain 10, 12, 20");
    }

    @Test
    @DisplayName("Test deleting a non-existent value")
    void testDeleteNonExistentValue() {
        // Пытаемся удалить значение, которого нет
        tree.add(10);
        tree.deleteByValue(20);
        assertEquals("[ 10 ]", tree.show(), "Tree should not change when deleting a non-existent value");
    }

    // Тесты для метода show
    @Test
    @DisplayName("Test output of an empty tree")
    void testShowEmptyTree() {
        // Проверяем вывод для пустого дерева
        assertEquals("[ ]", tree.show(), "Empty tree should return [ ]");
    }

    @Test
    @DisplayName("Test output of a tree with one node")
    void testShowSingleNode() {
        // Проверяем вывод для дерева с одним узлом
        tree.add(10);
        assertEquals("[ 10 ]", tree.show(), "Tree with one node should return [ 10 ]");
    }

    @Test
    @DisplayName("Test output of a tree with multiple nodes")
    void testShowMultipleNodes() {
        // Проверяем вывод для дерева с несколькими узлами
        tree.add(10);
        tree.add(5);
        tree.add(15);
        assertEquals("[ 5 10 15 ]", tree.show(), "Tree should return nodes in order: 5, 10, 15");
    }

    // Тесты для метода update
    @Test
    @DisplayName("Update an existing value")
    void testUpdateExistingValue() {
        // Обновляем значение узла
        tree.add(10);
        tree.update(10, 20);
        assertEquals("[ 20 ]", tree.show(), "Value 10 should be updated to 20");
    }

    @Test
    @DisplayName("Test updating a non-existent value")
    void testUpdateNonExistentValue() {
        // Пытаемся обновить несуществующее значение
        tree.add(10);
        tree.update(20, 30);
        assertEquals("[ 10 ]", tree.show(), "Tree should not change when updating a non-existent value");
    }

    @Test
    @DisplayName("Test throwing exception when updating with null")
    void testUpdateWithNull() {
        // Проверяем поведение при попытке обновления с null
        tree.add(10);
        assertThrows(NullPointerException.class, () -> tree.update(null, 20), "Updating with null should throw an exception");
    }

    // Тесты для метода BFS
    @Test
    @DisplayName("Test BFS search in an empty tree")
    void testBFSOnEmptyTree() {
        // Поиск в пустом дереве
        assertNull(tree.BFS(10), "BFS search in an empty tree should return null");
    }

    @Test
    @DisplayName("Test BFS search for an existing value")
    void testBFSExistingValue() {
        // Поиск существующего значения
        tree.add(10);
        tree.add(5);
        BinarySearchTree<Integer>.BinaryNode node = tree.BFS(5);
        assertNotNull(node, "Node with value 5 should be found");
        assertEquals(5, node.getValue(), "Found node should have value 5");
    }

    @Test
    @DisplayName("Test BFS search for a non-existent value")
    void testBFSNonExistentValue() {
        // Поиск несуществующего значения
        tree.add(10);
        BinarySearchTree<Integer>.BinaryNode node = tree.BFS(20);
        assertNotNull(node, "BFS should return the last checked node");
        assertNotEquals(20, node.getValue(), "Value 20 should not be found");
    }

    // Тесты для метода DPS
    @Test
    @DisplayName("Test DPS search in an empty tree")
    void testDPSOnEmptyTree() {
        // Поиск в пустом дереве
        assertNull(tree.DPS(10), "DPS search in an empty tree should return null");
    }

    @Test
    @DisplayName("Test DPS search for an existing value")
    void testDPSExistingValue() {
        // Поиск существующего значения
        tree.add(10);
        tree.add(5);
        BinarySearchTree<Integer>.BinaryNode node = tree.DPS(5);
        assertNotNull(node, "Node with value 5 should be found");
        assertEquals(5, node.getValue(), "Found node should have value 5");
    }

    @Test
    @DisplayName("Test DPS search for a non-existent value")
    void testDPSNonExistentValue() {
        // Поиск несуществующего значения
        tree.add(10);
        BinarySearchTree<Integer>.BinaryNode node = tree.DPS(20);
        assertNull(node, "DPS search for a non-existent value should return null");
    }
}