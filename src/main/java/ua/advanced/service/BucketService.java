package ua.advanced.service;

import ua.advanced.domain.Bucket;
import ua.advanced.dto.UserOrder;
import ua.advanced.shared.AbstractCRUD;

import java.util.List;

public interface BucketService extends AbstractCRUD<Bucket> {
    List<UserOrder> readAllByUser(int userId);
    Bucket readAllByUserProduct(int user_id, int product_id);
}
