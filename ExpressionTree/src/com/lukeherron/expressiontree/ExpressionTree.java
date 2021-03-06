package com.lukeherron.expressiontree;

import com.lukeherron.expressiontree.iterator.IteratorFactory;
import com.lukeherron.expressiontree.node.ComponentNode;
import com.lukeherron.expressiontree.visitor.Visitor;
import com.lukeherron.expressiontree.visitor.Visitable;

import java.util.Iterator;

public abstract class ExpressionTree implements Visitable {

    protected ComponentNode root = null;
    protected IteratorFactory treeIteratorFactory = new IteratorFactory();
    private String treeEvaluationResult;

    /**
     * ExpressionTree constructor takes a Node pointer that contains all the nodes in the expression tree
     *
     * @param root The root component node
     */
    public ExpressionTree(ComponentNode root) {
        this.root = root;
    }

    public abstract ExpressionTree left();

    public abstract ExpressionTree right();

    public abstract String type();

    public boolean isNull() {
        boolean temp;
        synchronized (this) {
            temp = root == null;
        }

        return temp;
    }

    public ComponentNode getRoot() {
        ComponentNode temp;
        synchronized (this) {
            temp = this.root;
        }

        return temp;
    }

    public Iterator<ExpressionTree> makeIterator(String traversalOrderRequest) {
       Iterator<ExpressionTree> temp;
        synchronized (this) {
            temp = treeIteratorFactory.makeIterator(this, traversalOrderRequest);
        }

        return temp;
    }

    public void setTreeEvaluationResult(String result) {
        this.treeEvaluationResult = result;
    }

    public String getTreeEvaluationResult() {
        return this.treeEvaluationResult;
    }

    @Override
    public void accept(Visitor visitor) {
        synchronized (this) {
            root.accept(visitor);
        }
    }
}
