package io.github.oguzhancevik.stockmanagement.model.entity;

import io.github.oguzhancevik.stockmanagement.model.entity.base.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table
@Data
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
