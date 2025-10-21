package com.fontys.marketplace_backend.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    @Id
    @GeneratedValue
    private Integer id;

    private Integer sellerUserId;

    private String sellerDisplayName;

    private String title;

    private String description;

    private Double price;

    // @Enumerated(EnumType.STRING)
    // private Category category;
    //
    // @Enumerated(EnumType.STRING)
    // private Location location;
}
