package com.excmul.util.tree;

import lombok.*;

public class MockHashTreeInstance {
    private final String instance;

    private MockHashTreeInstance parentInstance;

    public MockHashTreeInstance(String instance) {
        this.instance = instance;
    }

    public MockHashTreeInstance(String instance, MockHashTreeInstance parentInstance) {
        this.instance = instance;
        this.parentInstance = parentInstance;
    }

    public MockHashTreeInstance getParentInstance() {
        return parentInstance;
    }

    @Override
    public String toString() {
        return instance;
    }
}
