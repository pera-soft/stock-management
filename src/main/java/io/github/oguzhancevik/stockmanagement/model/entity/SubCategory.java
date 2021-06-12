package io.github.oguzhancevik.stockmanagement.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.github.oguzhancevik.stockmanagement.model.entity.base.BaseEntity;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
@Data
public class SubCategory extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @JsonIgnore
    @OneToMany(mappedBy = "subCategory")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Product> products = new HashSet<>();

}