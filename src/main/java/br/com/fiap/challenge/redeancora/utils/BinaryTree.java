package br.com.fiap.challenge.redeancora.utils;

import br.com.fiap.challenge.redeancora.model.Product;

import java.util.ArrayList;
import java.util.List;

public class BinaryTree {
    private NodeTree root;

    public void insert(Product product) {
        root = insertRecursive(root, product);
    }

    private NodeTree insertRecursive(NodeTree node, Product product) {
        if (node == null) {
            return new NodeTree(product);
        }

        if (product.getName().compareToIgnoreCase(node.data.getName()) < 0) {
            node.left = insertRecursive(node.left, product);
        } else {
            node.right = insertRecursive(node.right, product);
        }

        return node;
    }

    public List<Product> autocomplete(String prefix) {
        List<Product> results = new ArrayList<>();
        searchWithPrefix(root, prefix.toLowerCase(), results);
        return results.size() > 5 ? results.subList(0, 5) : results;
    }

    private void searchWithPrefix(NodeTree node, String prefix, List<Product> results) {
        if (node == null || results.size() >= 5) return;

        String nameLower = node.data.getName().toLowerCase();

        if (nameLower.startsWith(prefix)) {
            results.add(node.data);
        }

        // Percorre a subárvore da esquerda somente se o prefixo for menor que o nome atual
        if (prefix.compareTo(nameLower) <= 0) {
            searchWithPrefix(node.left, prefix, results);
        }

        // Percorre a subárvore da direita sempre que necessário
        if (prefix.compareTo(nameLower) >= 0) {
            searchWithPrefix(node.right, prefix, results);
        }
    }
}
