package com.lulu.dp.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: Milo
 * @Date: 2020-06-09 20:31
 */
public class Main {

    public static void main(String[] args) {
        BranchNode root = new BranchNode("root");
        BranchNode chapter1 = new BranchNode("chapter1");
        BranchNode chapter2 = new BranchNode("chapter2");
        Node r1 = new LeafNode("r1");
        Node c11 = new LeafNode("c11");
        Node c22 = new LeafNode("c22");

        BranchNode section21 = new BranchNode("section21");
        Node c211 = new LeafNode("c211");
        Node c212 = new LeafNode("c212");

        root.add(chapter1);
        root.add(chapter2);
        root.add(r1);

        chapter1.add(c11);
        chapter1.add(c22);

        chapter2.add(section21);
        section21.add(c211);
        section21.add(c212);
        tree(root, 0);
    }

    private static void tree(Node b, int depth) {
        for (int i = 0; i < depth; i++) System.out.print("--");
        b.p();
        if (b instanceof BranchNode) {
            for (Node n : ((BranchNode) b).nodeList) {
                tree(n, depth + 1);
            }
        }
    }
}


abstract class Node {
    abstract void p();
}


class LeafNode extends Node {
    String content;

    @Override
    void p() {
        System.out.println(content);
    }

    public LeafNode(String content) {
        this.content = content;
    }
}

class BranchNode extends Node {
    List<Node> nodeList = new ArrayList<>();

    String name;

    @Override
    void p() {
        System.out.println(name);
    }

    public BranchNode(String name) {
        this.name = name;
    }

    public void add(Node node) {
        nodeList.add(node);
    }
}