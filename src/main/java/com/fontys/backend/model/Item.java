package com.fontys.backend.model;

import jakarta.persistence.*;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Item {
    @Id @GeneratedValue
    private long id;

    private long sellerUserId;

    private String sellerDisplayName;

    private String title;

    private String description;

    private double price;

    // Todo: /api/categories & /api/locations

//    @Enumerated(EnumType.STRING)
//    private Category category;
//
//    @Enumerated(EnumType.STRING)
//    private Location location;

    // Todo: store images.
}
