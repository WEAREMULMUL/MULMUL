package com.excmul.util.tree;


import java.util.List;
public interface TreeNode<T> {
    T getInstance();
    TreeNode<T> getParent();
    List<TreeNode<T>> getChildren();

    void connectNode(TreeNode<T> parentNode);
    TreeNode<T> findByInstance(T instance);
    List<TreeNode<T>> toList();

    int getLevel();
    boolean isRoot();
    boolean isLeaf();
}
