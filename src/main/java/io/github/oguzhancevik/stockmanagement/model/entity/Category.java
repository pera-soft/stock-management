package io.github.oguzhancevik.stockmanagement.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.github.oguzhancevik.stockmanagement.model.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table
@Data
@NoArgsConstructor
public class Category extends BaseEntity {

    public Category(String name) {
        super.setName(name);
    }

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<SubCategory> subCategories = new HashSet<>();

}
