package com.excmul.util.tree;


import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;
import java.util.function.Function;

final class LinkedTreeExplorer<T> implements TreeExplorer<T> {
    @SuppressWarnings({"rawtypes"})
    private static LinkedTreeExplorer instance;

    public static <T> LinkedTreeExplorer<T> getInstance() {
        if (instance == null)
            instance = new LinkedTreeExplorer<>();

        @SuppressWarnings("unchecked")
        LinkedTreeExplorer<T> tInstance = instance;
        return tInstance;
    }

    @Override
    public void traversal(TreeNode<T> rootNode, Consumer<TreeNode<T>> funcCallback) {
        if (rootNode.isLeaf())
            return;

        Queue<TreeNode<T>> queue = new LinkedList<>(rootNode.getChildren());
        while(!queue.isEmpty()) {
            TreeNode<T> iTreeNodeNode = queue.poll();
            funcCallback.accept(iTreeNodeNode);
            if (!iTreeNodeNode.isLeaf()) {
                queue.addAll(iTreeNodeNode.getChildren());
            }
        }
    }

    @Override
    public TreeNode<T> traversal(TreeNode<T> rootNode, Function<TreeNode<T>, Boolean> funcCallback) {
        if (rootNode.isLeaf())
            return null;

        Queue<TreeNode<T>> queue = new LinkedList<>(rootNode.getChildren());
        while(!queue.isEmpty()) {
            TreeNode<T> iTreeNodeNode = queue.poll();
            if (funcCallback.apply(iTreeNodeNode))
                return null;
            if (!iTreeNodeNode.isLeaf()) {
                queue.addAll(iTreeNodeNode.getChildren());
            }
        }
        return null;
    }
}
