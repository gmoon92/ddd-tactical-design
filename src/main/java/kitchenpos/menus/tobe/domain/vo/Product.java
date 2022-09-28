package kitchenpos.menus.tobe.domain.vo;

import java.util.Objects;
import java.util.UUID;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import kitchenpos.global.vo.Name;
import kitchenpos.global.vo.Price;
import kitchenpos.global.vo.ValueObject;

@Embeddable
public class Product extends ValueObject {

    @Column(
            name = "product_id",
            columnDefinition = "binary(16)",
            nullable = false
    )
    private UUID id;

    @Embedded
    @AttributeOverride(
            name = "value",
            column = @Column(name = "product_name", nullable = false)
    )
    private Name name;

    @Embedded
    @AttributeOverride(
            name = "value",
            column = @Column(name = "product_price", nullable = false)
    )
    private Price price;

    protected Product() {
    }

    public Product(UUID productId) {
        this(productId, null);
    }

    public Product(UUID productId, Price productPrice) {
        this(productId, null, productPrice);
    }

    public Product(UUID id, Name name, Price price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public UUID getId() {
        return id;
    }

    public Name getName() {
        return name;
    }

    public Price getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
