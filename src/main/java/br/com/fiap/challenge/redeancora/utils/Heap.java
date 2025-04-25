package br.com.fiap.challenge.redeancora.utils;

import br.com.fiap.challenge.redeancora.model.Product;

import java.util.ArrayList;
import java.util.List;

public class Heap {
    private List<Product> items;

    public Heap() {
        items = new ArrayList<>();
        items.add(null);
    }

    public void insert(Product item) {
        items.add(item);
        int i = items.size() - 1;

        while (i > 1) {
            int pai = i / 2;

            if (items.get(i).getPrice() > items.get(pai).getPrice()) {
                // troca os elementos
                Product temp = items.get(i);
                items.set(i, items.get(pai));
                items.set(pai, temp);
                i = pai;
            } else {
                break;
            }
        }
    }

    public Product remove() {
        if (items.size() <= 1) {
            return null;
        }

        Product raiz = items.get(1);
        items.set(1, items.get(items.size() - 1));
        items.remove(items.size() - 1);

        int i = 1;
        while (2 * i < items.size()) {
            int c = 2 * i;

            if (c + 1 < items.size() && items.get(c + 1).getPrice() > items.get(c).getPrice()) {
                c += 1;
            }

            if (items.get(i).getPrice() >= items.get(c).getPrice()) {
                break;
            }

            Product temp = items.get(i);
            items.set(i, items.get(c));
            items.set(c, temp);
            i = c;
        }

        return raiz;
    }

    public List<Product> getKMax(int k) {
        List<Product> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            Product max = remove();
            if (max != null) {
                result.add(max);
            } else {
                break;
            }
        }
        return result;
    }

    public List<Product> getKMin(List<Product> products, int k) {
        Heap heap = new Heap();

        for (Product product : products) {
            if (heap.items.size() <= k) {
                heap.insert(product);
            } else if (product.getPrice() < heap.items.get(1).getPrice()) {
                heap.remove();
                heap.insert(product);
            }
        }

        return heap.getKMax(k); // pega os k menores da maxHeap
    }

    @Override
    public String toString() {
        return items.toString();
    }
}
