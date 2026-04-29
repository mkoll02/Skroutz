package org.example;

public class Functions {

    private FunctionsHelper helper;

    public Functions(FunctionsHelper helper) {
        this.helper = helper;
    }

    public void function1(SkroutzManager manager) {
        helper.handleInsert(manager);
    }

    public void function2(SkroutzManager manager) {
        helper.handleUpdateStock(manager);
    }
}