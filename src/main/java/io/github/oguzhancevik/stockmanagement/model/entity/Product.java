package io.github.oguzhancevik.stockmanagement.model.entity;

import io.github.oguzhancevik.stockmanagement.model.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table
@Data
@Where(clause = "stock_amount > 0")
@NoArgsConstructor
public class Product extends BaseEntity {

    public Product(String name, BigDecimal price, Long stockAmount) {
        super.setName(name);
        this.price = price;
        this.stockAmount = stockAmount;
    }

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private Long stockAmount;

    @ManyToOne
    @JoinColumn(name = "sub_category_id", nullable = false)
    private SubCategory subCategory;

}
