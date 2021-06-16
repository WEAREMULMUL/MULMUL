package com.excmul.util.tree;

import java.util.function.Consumer;
import java.util.function.Function;

interface TreeExplorer<T> {
    // 중단 없이 순회(조회 전용)
    void traversal(TreeNode<T> rootNode, Consumer<TreeNode<T>> funcCallback);

    // callback 함수가 true를 반환 한다면 순회 중단
    // 반환 값은 중단 시점의 Node
    TreeNode<T> traversal(TreeNode<T> rootNode, Function<TreeNode<T>, Boolean> funcCallback);
}
