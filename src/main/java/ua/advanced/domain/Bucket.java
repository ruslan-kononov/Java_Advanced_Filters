package ua.advanced.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "bucket")
public class Bucket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;
    @Column(name = "purchase_date")
    private Date purchaseDate;
    @Column(name = "quantity")
    private Integer quantity;

    public Bucket(){}

    public Bucket(User user, Product product, Date purchaseDate, Integer quantity) {
        this.user = user;
        this.product = product;
        this.purchaseDate = purchaseDate;
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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
                Objects.equals(user, bucket.user) &&
                Objects.equals(product, bucket.product) &&
                Objects.equals(purchaseDate, bucket.purchaseDate) &&
                Objects.equals(quantity, bucket.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, product, purchaseDate, quantity);
    }

    @Override
    public String toString() {
        return "Bucket{" +
                "id=" + id +
                ", user=" + user +
                ", product=" + product +
                ", purchaseDate=" + purchaseDate +
                ", quantity=" + quantity +
                '}';
    }
}
