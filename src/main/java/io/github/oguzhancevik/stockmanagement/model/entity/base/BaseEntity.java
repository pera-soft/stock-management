package io.github.oguzhancevik.stockmanagement.model.entity.base;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

import static io.github.oguzhancevik.stockmanagement.util.Constants.ENTITY.SEQUENCE_GENERATOR;
import static io.github.oguzhancevik.stockmanagement.util.Constants.ENTITY.SEQUENCE_NAME;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_GENERATOR)
    @SequenceGenerator(name = SEQUENCE_GENERATOR, sequenceName = SEQUENCE_NAME, allocationSize = 1)
    private Long id;

    @Column(unique = true, nullable = false, length = 100)
    private String name;

    @CreatedDate
    private Date createdDate;

    @LastModifiedDate
    private Date updatedDate;

}
