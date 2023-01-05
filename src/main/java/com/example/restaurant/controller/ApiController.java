package com.example.restaurant.controller;

import com.example.restaurant.wishlist.dto.WishListDto;
import com.example.restaurant.wishlist.repository.WishListRepository;
import com.example.restaurant.wishlist.service.WishListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/restaurant")
@RequiredArgsConstructor
public class ApiController {

    private final WishListService wishListService;

    @Autowired
    private final WishListRepository wishListRepository;

    @GetMapping("/search")
    public WishListDto search(@RequestParam String query){
        return wishListService.search(query);
    }

    @PostMapping("")
    public WishListDto add(@RequestBody WishListDto wishListDto){
        log.info("{}", wishListDto);
        return wishListService.add(wishListDto);
    }

    @GetMapping("/all")
    public List<WishListDto> findAll(){
        return wishListService.findAll();
    }

    @DeleteMapping("/{index}")
    public void delete(@PathVariable int index){
        System.out.println(index);
        Long Lindex = Long.valueOf(index);
        System.out.println("delete : "+wishListRepository.findById(Lindex));
        wishListService.delete(Lindex);
    }

    @PostMapping("/{index}")
    public void addVisit(@PathVariable int index){
        System.out.println(index);
        Long Lindex = Long.valueOf(index);
        System.out.println("addVisit : "+wishListRepository.findById(Lindex));
        wishListService.addVisit(Lindex);
    }

}