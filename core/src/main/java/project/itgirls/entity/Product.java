package project.itgirls.entity;
import java.util.Objects;

public class Product {
    private Long id;
    private String name;
    private Double price;
    private Long brandId;

    public Product() {
    }

    public Product(Long id, String name, Double price, Long brandId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.brandId = brandId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) &&
                Objects.equals(name, product.name) &&
                Objects.equals(price, product.price) &&
                Objects.equals(brandId, product.brandId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, brandId);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", brandId=" + brandId +
                '}';
    }
}
