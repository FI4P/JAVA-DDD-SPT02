package br.com.fiap.challenge.redeancora.utils;

import br.com.fiap.challenge.redeancora.model.Product;

import java.util.List;
import java.util.UUID;

public class Utils {

    public static String generateUUID() {
        return UUID.randomUUID().toString();
    }

    public static void quickSort(Product[] products, int low, int high, boolean crescente) {
        if (low < high) {
            int pivotIndex = partition(products, low, high, crescente);
            quickSort(products, low, pivotIndex - 1, crescente);
            quickSort(products, pivotIndex + 1, high, crescente);
        }
    }

    private static int partition(Product[] products, int low, int high, boolean crescente) {
        Product pivot = products[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            boolean condicao = crescente
                    ? products[j].getPrice() <= pivot.getPrice()
                    : products[j].getPrice() >= pivot.getPrice();
            if (condicao) {
                i++;
                swap(products, i, j);
            }
        }
        swap(products, i + 1, high);
        return i + 1;
    }

    private static void swap(Product[] products, int i, int j) {
        Product temp = products[i];
        products[i] = products[j];
        products[j] = temp;
    }
}
