package ua.advanced.service;

import ua.advanced.domain.Product;
import ua.advanced.shared.AbstractCRUD;

import java.util.Map;

public interface ProductService extends AbstractCRUD<Product> {
    public Map<Integer,Product> readAllMap();
}
