package ua.advanced.domain;

import java.util.Date;
import java.util.Objects;

public class Bucket {
    private Integer id;
    private Integer userId;
    private Integer productId;
    private Date purchaseDate;
    private Integer quantity;

    public Bucket(Integer id, Integer userId, Integer productId, Date purchaseDate, Integer quantity) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.purchaseDate = purchaseDate;
        this.quantity = quantity;
    }

    public Bucket(Integer userId, Integer productId, Date purchaseDate, Integer quantity) {
        this.userId = userId;
        this.productId = productId;
        this.purchaseDate = purchaseDate;
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bucket bucket = (Bucket) o;
        return Objects.equals(id, bucket.id) &&
                Objects.equals(userId, bucket.userId) &&
                Objects.equals(productId, bucket.productId) &&
                Objects.equals(purchaseDate, bucket.purchaseDate) &&
                Objects.equals(quantity, bucket.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, productId, purchaseDate, quantity);
    }

    @Override
    public String toString() {
        return "Bucket{" +
                "id=" + id +
                ", userId=" + userId +
                ", productId=" + productId +
                ", purchaseDate=" + purchaseDate +
                ", quantity=" + quantity +
                '}';
    }
}
