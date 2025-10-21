package com.fontys.marketplace_backend.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fontys.marketplace_backend.persistence.entity.Item;

@Repository
public interface ItemRepository extends CrudRepository<Item, Integer> {
    Iterable<Item> findAll();

    Iterable<Item> findAllBySellerUserId(Integer userId);
}
