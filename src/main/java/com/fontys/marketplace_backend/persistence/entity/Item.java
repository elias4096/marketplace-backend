package com.fontys.marketplace_backend.persistence.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

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

    @Lob
    @Column(length = 512)
    private String description;

    private Double price;

    private String category;

    private String quality;

    private String location;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
