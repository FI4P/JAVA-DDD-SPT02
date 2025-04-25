package br.com.fiap.challenge.redeancora;

import br.com.fiap.challenge.redeancora.model.Product;
import br.com.fiap.challenge.redeancora.utils.BinaryTree;
import br.com.fiap.challenge.redeancora.utils.Heap;
import br.com.fiap.challenge.redeancora.utils.Utils;
import br.com.fiap.challenge.redeancora.view.SignUp;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args ) throws SQLException {
            SwingUtilities.invokeLater(SignUp::new);



//        //Algoritimos de python - Exemplos com utilização
//        //1 -
//        Product[] products = new Product[] {
//                new Product("1", "Pastilha de Freio", "Pastilha de freio de alta qualidade", "MarcaX", "Carro", "BP123", 75.50),
//                new Product("2", "Filtro de Óleo", "Filtro de óleo durável", "MarcaY", "Carro", "OF456", 15.25),
//                new Product("3", "Filtro de Ar", "Filtro de ar premium", "MarcaZ", "Carro", "AF789", 25.00),
//                new Product("4", "Vela de Ignição", "Vela de ignição durável", "MarcaA", "Carro", "SP012", 5.75)
//        };
//
//        // Ordenando em ordem crescente por preço
//        Utils.quickSort(products, 0, products.length - 1, true);
//        System.out.println("Produtos ordenados por preço (crescente):");
//        for (Product p : products) {
//            System.out.println(p.getName() + " - R$" + p.getPrice());
//        }
//        System.out.println("========================================================");
//
//        // Ordenando em ordem crescente por preço
//        Utils.quickSort(products, 0, products.length - 1, false);
//        System.out.println("Produtos ordenados por preço (descrescente):");
//        for (Product p : products) {
//            System.out.println(p.getName() + " - R$" + p.getPrice());
//        }

//        //2 - Heaps ( K-maiores e K-menores)
//        List<Product> products = new ArrayList<>();
//        products.add(new Product("1", "Pastilha de Freio", "Pastilha de freio de alta qualidade", "MarcaX", "Carro", "BP123", 75.50));
//        products.add(new Product("2", "Filtro de Óleo", "Filtro de óleo durável", "MarcaY", "Carro", "OF456", 15.25));
//        products.add(new Product("3", "Filtro de Ar", "Filtro de ar premium", "MarcaZ", "Carro", "AF789", 25.00));
//        products.add(new Product("4", "Vela de Ignição", "Vela de ignição durável", "MarcaA", "Carro", "SP012", 5.75));
//        products.add(new Product("5", "Roda", "Roda", "MarcaA", "Carro", "SP012", 45.30));
//
//
//        int K = 3;
//        Heap heap = new Heap();
//        for (Product p : products) {
//            heap.insert(p);
//        }
//
//
//        List<Product> kMinors = heap.getKMin(products, K);
//        System.out.println("Os " + K + " produtos mais baratos:");
//        for (Product p : kMinors) {
//            System.out.println(p.getName() + " - R$ " + p.getPrice());
//        }
//
//        List<Product> kMajors = heap.getKMax(K);
//        System.out.println("Os " + K + " produtos mais caros:");
//        for (Product p : kMajors) {
//            System.out.println(p.getName() + " - R$ " + p.getPrice());
//        }

        //3
//        BinaryTree binaryTree = new BinaryTree();
//
//        for (Product product : products) {
//            binaryTree.insert(product);
//        }
//
//        List<Product> suggestions = binaryTree.autocomplete("f");
//
//        System.out.println("Sugestões de autocompletar para 'a':");
//        for (Product p : suggestions) {
//            System.out.println(p.getName());
//        }





    }
}