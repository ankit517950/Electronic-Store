package com.lcwd.electronic.store.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.event.internal.DefaultSaveEventListener;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="categories")
public class Category {
    @Id
    @Column(name = "id")
    private String categoryId;
    @Column(name = "catogory_title",length = 60,nullable = false)
    private String title;
    @Column(name = "catogory_description",length = 500)
    private String description;
    private String coverImage;
    @OneToMany(mappedBy = "category",cascade =CascadeType.ALL,fetch = FetchType.LAZY)
    List<Product> products = new ArrayList<>();
}
