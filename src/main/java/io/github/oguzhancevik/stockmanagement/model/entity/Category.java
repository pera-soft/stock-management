package io.github.oguzhancevik.stockmanagement.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.github.oguzhancevik.stockmanagement.model.entity.base.BaseEntity;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
@Data
public class Category extends BaseEntity {

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<SubCategory> subCategories = new HashSet<>();

}
