package com.example.restaurant.wishlist.repository;

import com.example.restaurant.wishlist.dto.WishListDto;
import com.example.restaurant.wishlist.entity.WishListEntity;
import com.example.restaurant.wishlist.service.WishListService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class WishListRepositoryTest {

    @Autowired
    private WishListRepository wishListRepository;
    @Autowired
    private WishListService wishListService;

    private WishListDto create(){
        var wishList = new WishListDto();
        wishList.setTitle("title");
        wishList.setCategory("category");
        wishList.setAddress("address");
        wishList.setRoadAddress("readAddress");
        wishList.setHomePageLink("");
        wishList.setImageLink("");
        wishList.setVisit(false);
        wishList.setVisitCount(0);
        wishList.setLastVisitDate(null);

        return wishList;
    }
//
//    @Test
//    public void saveTest(){
//        var wishListEntity = create();
//        var expected = wishListRepository.save(wishListEntity);
//
//        Assertions.assertNotNull(expected);
//        Assertions.assertEquals(1, expected.getIndex());
//    }
//
//    @Test
//    public void updateTest(){
//        var wishListEntity = create();
//        var expected = wishListRepository.save(wishListEntity);
//
//        expected.setTitle("update test");
//        var saveEntity = wishListRepository.save(expected);
//
//        Assertions.assertEquals("update test", saveEntity.getTitle());
//        Assertions.assertEquals(1, wishListRepository.findAll().size());
//    }
//
//    @Test
//    public void findByIdTest(){
//        var wishListEntity = create();
//        wishListRepository.save(wishListEntity);
//
//        var expected = wishListRepository.findById(1);
//
//        Assertions.assertEquals(true, expected.isPresent());
//        Assertions.assertEquals(1, expected.get().getIndex());
//    }
//
//    @Test
//    public void deleteTest(){
//        var wishListEntity = create();
//        wishListRepository.save(wishListEntity);
//
//        wishListRepository.deleteById(1);
//
//        int count = wishListRepository.findAll().size();
//
//        Assertions.assertEquals(0, count);
//    }
//
//    @Test
//    public void listAllTest(){
//        var wishListEntity1 = create();
//        wishListRepository.save(wishListEntity1);
//
//        var wishListEntity2 = create();
//        wishListRepository.save(wishListEntity2);
//
//        int count = wishListRepository.findAll().size();
//        Assertions.assertEquals(2, count);
//    }

    @Test
    public void findall(){
        var wishListDto1 = create();
        var wishListDto2 = create();
        wishListService.add(wishListDto1);
        wishListRepository.findAll().forEach(System.out::println);
        wishListService.add(wishListDto1);
        wishListService.add(wishListDto2);
        wishListRepository.findAll().forEach(System.out::println);
    }
    @Test
    public void addTest(){
        var wishListDto1 = create();
        var wishListDto2 = create();
        wishListService.add(wishListDto1);
        wishListRepository.findAll().forEach(System.out::println);
        wishListService.add(wishListDto1);
        wishListService.add(wishListDto2);
        wishListRepository.findAll().forEach(System.out::println);
    }
    @Test
    public void deleteTest(){
        var wishListDto1 = create();
        var wishListDto2 = create();
        wishListService.add(wishListDto1);
        wishListRepository.findAll().forEach(System.out::println);
        wishListService.add(wishListDto1);
        wishListService.add(wishListDto2);
        wishListRepository.findAll().forEach(System.out::println);
        wishListService.delete(1L);
        wishListRepository.findAll().forEach(System.out::println);
    }
    @Test
    public void addVisitTest(){
        var wishListDto1 = create();
        var wishListDto2 = create();
        wishListService.add(wishListDto1);
        wishListService.add(wishListDto2);
        wishListService.addVisit(1L);
//        wishListService.addVisit(1L);
//        wishListService.addVisit(2L);
        wishListRepository.findAll().forEach(System.out::println);
    }
}