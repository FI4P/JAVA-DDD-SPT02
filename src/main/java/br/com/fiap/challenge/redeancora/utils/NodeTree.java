package br.com.fiap.challenge.redeancora.utils;

import br.com.fiap.challenge.redeancora.model.Product;

public class NodeTree {

    public Product data;
    public NodeTree left;
    public NodeTree right;

    public NodeTree(Product data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

}
