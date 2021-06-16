package com.excmul.util.tree;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

abstract class AbstractTreeExplorer<T> implements TreeExplorer<T> {
    private List<TreeNode<T>> allChildren;
    private final TreeExplorer<T> treeExplorer;

    public AbstractTreeExplorer(TreeExplorer<T> treeExplorer) {
        this.treeExplorer = treeExplorer;
    }

    public void traversal(TreeNode<T> rootNode, Consumer<TreeNode<T>> funcCallback) {
        treeExplorer.traversal(rootNode, funcCallback);
    }

    public TreeNode<T> traversal(TreeNode<T> rootNode, Function<TreeNode<T>, Boolean> funcCallback) {
        return treeExplorer.traversal(rootNode, funcCallback);
    }

    public List<TreeNode<T>> toList(TreeNode<T> rootNode) {
        allChildren = new LinkedList<>();
        this.traversal(rootNode, (Consumer<TreeNode<T>>) iTreeNode -> allChildren.add(iTreeNode));
        return allChildren;
    }

    public TreeNode<T> findByInstance(TreeNode<T> rootNode, T instance) {
        return this.traversal(rootNode, (Function<TreeNode<T>, Boolean>) iTreeNode -> (iTreeNode).getInstance().equals(instance));
    }
}
