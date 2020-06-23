package ua.advanced.service;

import ua.advanced.domain.Bucket;
import ua.advanced.shared.AbstractCRUD;

import java.util.List;

public interface BucketService extends AbstractCRUD<Bucket> {
    List<Bucket> readAllByUser(int userId);
    List<Bucket> readAllByUserProduct(int user_id, int product_id);
}
