package ua.advanced.dao;
import ua.advanced.domain.Bucket;
import ua.advanced.dto.UserOrder;
import ua.advanced.shared.AbstractCRUD;

import java.util.List;

public interface BucketDao extends AbstractCRUD<Bucket> {
    List<UserOrder> readAllByUser(int userId);
    Bucket readAllByUserProduct(int user_id, int product_id);
}
