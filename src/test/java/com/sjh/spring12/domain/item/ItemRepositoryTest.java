package com.sjh.spring12.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {

   ItemRepository itemRepository = new ItemRepository();

   @AfterEach
    void afterEach() {
       itemRepository.clearStore();
   }

   @Test
    void save() {
       //given
       Item item = new Item("itemA", 10000, 10);

       //when
       Item savedItem = itemRepository.save(item);

       //then
       Item findItem = itemRepository.findById(savedItem.getId());
       assertThat(savedItem).isEqualTo(findItem);
   }

    @Test
    void findAll() {
        //given
       Item item1 = new Item("itemA", 10000, 10);
       Item item2 = new Item("itemA", 10000, 10);
       itemRepository.save(item1);
       itemRepository.save(item2);
        //when
       List<Item> result = itemRepository.findAll();

       //then
       assertThat(result.size()).isEqualTo(2);
       assertThat(result).contains(item1);
       assertThat(result).contains(item2);
    }

    @Test
    void updateItem() {
        //given
       Item item1 = new Item("itemA", 10000, 10);
       Item savedItem = itemRepository.save(item1);
       Long itemId = savedItem.getId();
       //when
       Item item2 = new Item("itemB", 10000, 10);
       itemRepository.update(itemId, item2);
        //then
       Item findItem = itemRepository.findById(itemId);

       assertThat(findItem.getItemName()).isEqualTo(item2.getItemName());
       assertThat(findItem.getPrice()).isEqualTo(item2.getPrice());
       assertThat(findItem.getQuantity()).isEqualTo(item2.getQuantity());
    }
//
//    @Test
//    void test() {
//        //given
//
//        //when
//
//        //then
//    }
}