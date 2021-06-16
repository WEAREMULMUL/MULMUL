package com.excmul.util.tree;


public class AbstractTreeExplorerDelegator<T> extends AbstractTreeExplorer<T> {
    public AbstractTreeExplorerDelegator(TreeExplorer<T> treeExplorer) {
        super(treeExplorer);
    }
}
