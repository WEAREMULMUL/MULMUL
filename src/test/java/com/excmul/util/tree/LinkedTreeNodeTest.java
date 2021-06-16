package com.excmul.util.tree;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.test.util.AssertionErrors.assertTrue;

@SpringBootTest
class LinkedTreeNodeTest {
    private static TreeNode<MockHashTreeInstance> treeNode;

    @BeforeAll
    private static void createNode() {
        List<MockHashTreeInstance> list = new ArrayList<>();
        MockHashTreeInstance rootInstance = new MockHashTreeInstance("ROOT");

        MockHashTreeInstance childInstance1 = new MockHashTreeInstance("LEVEL 1 = 1", rootInstance);
        MockHashTreeInstance childInstance1_1 = new MockHashTreeInstance("LEVEL 2 = 1-1", childInstance1);
        MockHashTreeInstance childInstance1_2 = new MockHashTreeInstance("LEVEL 2 = 1-2", childInstance1);
        MockHashTreeInstance childInstance1_3 = new MockHashTreeInstance("LEVEL 2 = 1-3", childInstance1);

        MockHashTreeInstance childInstance1_3_1 = new MockHashTreeInstance("LEVEL 3 - 1-3-1", childInstance1_1);
        MockHashTreeInstance childInstance1_3_1_1 = new MockHashTreeInstance("LEVEL 4 - 1-3-1-1", childInstance1_3_1);

        MockHashTreeInstance childInstance2 = new MockHashTreeInstance("LEVEL 1 - 2", rootInstance);
        MockHashTreeInstance childInstance2_1 = new MockHashTreeInstance("LEVEL 2 - 2-1", childInstance2);
        MockHashTreeInstance childInstance2_2 = new MockHashTreeInstance("LEVEL 2 - 2-2", childInstance2);
        MockHashTreeInstance childInstance2_3 = new MockHashTreeInstance("LEVEL 2 - 2-3", childInstance2);

        list.add(rootInstance);

        list.add(childInstance1);
        list.add(childInstance1_1);
        list.add(childInstance1_2);
        list.add(childInstance1_3);

        list.add(childInstance2);
        list.add(childInstance2_1);
        list.add(childInstance2_2);
        list.add(childInstance2_3);

        list.add(childInstance1_3_1);
        list.add(childInstance1_3_1_1);

        treeNode = new LinkedTreeNode<>(rootInstance, list, MockHashTreeInstance::getParentInstance);
    }

    @Test
    @DisplayName("Tree 레벨 테스트")
    public void connectTest() {
        int compareLevel = 1;
        while(!treeNode.isLeaf()) {
            assertTrue("Root 자식 불일치", treeNode.getLevel() == compareLevel++);
            if (!treeNode.isLeaf())
                treeNode = treeNode.getChildren().get(0);
        }
    }

    @Test
    public void view() {
        treeNode.getChildren().get(0);
    }
}