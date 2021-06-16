package com.excmul.util.tree;

import lombok.*;

import java.util.*;
import java.util.function.Function;

public final class LinkedTreeNode<T> implements TreeNode<T> {
    private final AbstractTreeExplorer<T> treeExplorerDelegator;

    @Getter private final T instance;

    @Setter(AccessLevel.PRIVATE)
    @Getter private TreeNode<T> parent;

    @Setter(AccessLevel.PRIVATE)
    @Getter private List<TreeNode<T>> children;

    @Setter(AccessLevel.PRIVATE)
    @Getter private int level;

    public LinkedTreeNode(T instance) {
        // New Root Node
        this.instance = instance;
        this.level = 1;
        treeExplorerDelegator = new AbstractTreeExplorerDelegator<>(LinkedTreeExplorer.getInstance());
    }

    public LinkedTreeNode(List<T> instances, Function<T, T> funcGetParent) {
        this(null);
        connectAll(instances, funcGetParent);
    }

    public LinkedTreeNode(T instance, List<T> instances, Function<T, T> funcGetParent) {
        this(instance);
        connectAll(instances, funcGetParent);
    }

    private void connectAll(List<T> instanceList, Function<T, T> funcGetParent) {
        Map<T, TreeNode<T>> shortcut = new HashMap<>();
        Set<T> checked = new HashSet<>();

        Queue<T> instances = new LinkedList<>(instanceList);
        while(!instances.isEmpty()) {
            T iInstance = instances.poll();
            T iParentInstance = funcGetParent.apply(iInstance);

            TreeNode<T> newTreeNode;
            TreeNode<T> iParentTreeNode =
                    iParentInstance == null ? this : shortcut.get(iParentInstance); // 부모 객체가 없다면 함수를 호출한 Node와 연결

            if (iParentTreeNode == null && !checked.contains(iInstance)) { // 등록된 부모 객체가 없다면 대기열 재등록, 1회 제한
                instances.add(iInstance);
                checked.add(iInstance);
                continue;
            }
            newTreeNode = new LinkedTreeNode<>(iInstance);
            newTreeNode.connectNode(iParentTreeNode);

            shortcut.put(iInstance, newTreeNode);
        }
    }

    @Override
    public void connectNode(TreeNode<T> value) {
        LinkedTreeNode<T> parentNode = (LinkedTreeNode<T>) value;
        if (parentNode.getChildren() == null)
            parentNode.setChildren(new ArrayList<>());
        parentNode.getChildren().add(this);

        this.setParent(parentNode);
        this.setLevel(parentNode.getLevel() + 1);
    }

    @Override
    public boolean isRoot() {
        return getParent() == null;
    }

    @Override
    public boolean isLeaf() {
        return getChildren() == null || getChildren().size() == 0;
    }

    @Override
    public List<TreeNode<T>> toList() {
        return treeExplorerDelegator.toList(this);
    }

    @Override
    public TreeNode<T> findByInstance(T instance) {
        return treeExplorerDelegator.findByInstance(this, instance);
    }
}
