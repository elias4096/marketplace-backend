package com.fontys.backend.repository;

import com.fontys.backend.model.Item;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {
    @NotNull
    Iterable<Item> findAll();

    @NotNull
    Iterable<Item> findAllBySellerUserId(long userId);
}
