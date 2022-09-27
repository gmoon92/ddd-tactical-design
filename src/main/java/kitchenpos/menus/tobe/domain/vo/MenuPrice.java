package kitchenpos.menus.tobe.domain.vo;

import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import kitchenpos.global.vo.ValueObject;
import kitchenpos.menus.tobe.domain.exception.MinimumMenuPriceException;
import kitchenpos.menus.tobe.domain.exception.NullMenuPriceException;

@Embeddable
public class MenuPrice extends ValueObject {

    @Column(name = "price", nullable = false)
    private BigDecimal value;

    protected MenuPrice() {
    }

    public MenuPrice(BigDecimal value) {
        validate(value);
        this.value = value;
    }

    private void validate(BigDecimal price) {
        if (Objects.isNull(price)) {
            throw new NullMenuPriceException();
        }

        if (price.compareTo(BigDecimal.ZERO) < 0) {
            throw new MinimumMenuPriceException();
        }
    }

    public boolean grateThan(BigDecimal otherPrice) {
        return value.compareTo(otherPrice) > 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MenuPrice menuPrice = (MenuPrice) o;
        return Objects.equals(value, menuPrice.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
