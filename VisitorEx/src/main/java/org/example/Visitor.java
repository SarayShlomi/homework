package org.example;

public interface Visitor {
    void visit(User item);
    void visit(Asset item);
    void visit(Group item);
}