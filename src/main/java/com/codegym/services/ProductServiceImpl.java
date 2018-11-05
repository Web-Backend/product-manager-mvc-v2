package com.codegym.services;

import com.codegym.models.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductServiceImpl implements ProductService {
    private static Map<Integer, Product> products;

    static {
        products = new HashMap<>();
        products.put(1, new Product(1, "dat 09", 9.9, "full service"));
        products.put(2, new Product(2, "hau mon", 13.9, "best ..."));
        products.put(3, new Product(3, "son nh", 99.9, "khong biet gi het"));
        products.put(4, new Product(4, "tien sat thu", 19.9, "cam ki, thi hoa"));
        products.put(5, new Product(5, "truong con", 19.9, "chem gio facebook"));
        products.put(6, new Product(6, "tuan anh mat thon", 0.0, "khong tu tin"));
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    @Override
    public void save(Product product) {
        products.put(product.getId(), product);
    }

    @Override
    public Product findById(int id) {
        return products.get(id);
    }

    @Override
    public void update(int id, Product product) {
        products.put(id, product);
    }

    @Override
    public void remove(int id) {
        products.remove(id);
    }
}
