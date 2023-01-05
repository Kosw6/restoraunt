package com.example.restaurant.wishlist.service;

import com.example.restaurant.naver.NaverClient;
import com.example.restaurant.naver.dto.SearchImageReq;
import com.example.restaurant.naver.dto.SearchLocalReq;
import com.example.restaurant.wishlist.dto.WishListDto;
import com.example.restaurant.wishlist.entity.WishListEntity;
import com.example.restaurant.wishlist.repository.WishListRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor//final붙은 필드 자동 생성자 주입
public class WishListService {

    private final NaverClient naverClient;
    private final WishListRepository wishListRepository;

    public WishListDto search(String query){
        // 지역검색
        var searchLocalReq = new SearchLocalReq();
        searchLocalReq.setQuery(query);
        var searchLocalRes = naverClient.searchLocal(searchLocalReq);

        if(searchLocalRes.getTotal() > 0){
            var localItem = searchLocalRes.getItems().stream().findFirst().get();
            var imageQuery = localItem.getTitle().replaceAll("<[^>]*>","");
            var searchImageReq = new SearchImageReq();
            searchImageReq.setQuery(imageQuery);

            // 이미지 검색
            var searchImageRes = naverClient.searchImage(searchImageReq);

            if(searchImageRes.getTotal() > 0){
                var imageItem = searchImageRes.getItems().stream().findFirst().get();

                // 결과를 리턴
                var result = new WishListDto();
                result.setTitle(localItem.getTitle());
                result.setCategory(localItem.getCategory());
                result.setAddress(localItem.getAddress());
                result.setRoadAddress(localItem.getRoadAddress());
                result.setHomePageLink(localItem.getLink());
                result.setImageLink(imageItem.getLink());
                return result;
            }
        }

        return new WishListDto();
    }

    public WishListDto add(WishListDto wishListDto) { //search에서 WishListDto로 받은 값을 entity로 변환후 저장
        var entity = dtoToEntity(wishListDto);
        var saveEntity = wishListRepository.save(entity);
        System.out.println("add succesess");
//        System.out.println("dtoToEntity : "+saveEntity);
        var result = entityToDto(saveEntity);
//        System.out.println("entityToDto : "+ result);
        return result;
    }


    private WishListEntity dtoToEntity(WishListDto wishListDto){
        var entity = new WishListEntity();
//        entity.setIndex(wishListDto.getId());
        entity.setTitle(wishListDto.getTitle());
        entity.setCategory(wishListDto.getCategory());
        entity.setAddress(wishListDto.getAddress());
        entity.setRoadAddress(wishListDto.getRoadAddress());
        entity.setHomePageLink(wishListDto.getHomePageLink());
        entity.setImageLink(wishListDto.getImageLink());
        entity.setVisit(wishListDto.isVisit());
        entity.setVisitCount(wishListDto.getVisitCount());
        entity.setLastVisitDate(wishListDto.getLastVisitDate());
        return entity;
    }

    private WishListDto entityToDto(WishListEntity wishListEntity){
        var dto = new WishListDto();
        dto.setId(wishListEntity.getId());
        dto.setTitle(wishListEntity.getTitle());
        dto.setCategory(wishListEntity.getCategory());
        dto.setAddress(wishListEntity.getAddress());
        dto.setRoadAddress(wishListEntity.getRoadAddress());
        dto.setHomePageLink(wishListEntity.getHomePageLink());
        dto.setImageLink(wishListEntity.getImageLink());
        dto.setVisit(wishListEntity.isVisit());
        dto.setVisitCount(wishListEntity.getVisitCount());
        dto.setLastVisitDate(wishListEntity.getLastVisitDate());
        return dto;
    }

    public List<WishListDto> findAll() {
        return wishListRepository.findAll()
                .stream()
                .map(it -> entityToDto(it))
                .collect(Collectors.toList());
    }

    public void delete(Long index) {
        wishListRepository.deleteById(index);
    }

    public void addVisit(Long index){ //진단 데이터베이스가 저장이 안됨 findAll은 잘 되는데....MySQL적용 시켜보자
        if(wishListRepository.findById(index).isPresent()){
         var wish = wishListRepository.findById(index).get();
//         System.out.println("before : "+wishListRepository.findById(index));
         wish.setVisit(true);
         wish.setVisitCount(wish.getVisitCount()+1);
         wishListRepository.save(wish);
//         System.out.println("after : "+wishListRepository.findById(index));
//         wishListRepository.findAll().forEach(System.out::println);
//         wishListRepository.findAll().forEach(System.out::println);
//         System.out.println("count" + wish.getVisitCount());
        }
    }
}
