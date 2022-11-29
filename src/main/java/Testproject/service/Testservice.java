package Testproject.service;

import Testproject.domain.Dto.MarketDto;
import Testproject.domain.Dto.ProductDto;
import Testproject.domain.Entity.MarketEntity;
import Testproject.domain.Entity.MarketRepository;
import Testproject.domain.Entity.ProductEntity;
import Testproject.domain.Entity.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yaml.snakeyaml.error.Mark;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class Testservice {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MarketRepository marketRepository;

    @Transactional
    public boolean productup(ProductDto productDto){

        Optional<MarketEntity> optional=marketRepository.findById(productDto.getMno());
        if(optional.isPresent()){
            MarketEntity marketEntity=optional.get();
            // 일단 레코드생성하고 나중에 연관관계 넣기
            ProductEntity productEntity= productRepository.save(productDto.toEntity());
            if(productEntity.getPno()!=0){ // 제대로 등록됐으면
                productEntity.setMarketEntity(marketEntity);
                marketEntity.getProductEntityList().add(productEntity);
                return true;
            }

        }
        return false;
    }

    // 마켓 등록
    @Transactional
    public boolean setmarket(MarketDto marketDto){
        MarketEntity marketEntity = marketRepository.save( marketDto.toEntity() );
        System.out.println("서비스 마켓 엔티티: " + marketEntity);
        if( marketEntity.getMno() != 0 ){ // 번호가 0이아니면 등록
            return true;
        }else {
            return false;
        }
    }

    // 매출 출력
    public List<ProductDto> productlist(int mno){
        List<ProductEntity> productEntities = productRepository.findbyMno(mno);
        List<ProductDto> productDtos = new ArrayList<>();



        for( ProductEntity e : productEntities ){
            productDtos.add( e.toDto() );
        }
        return productDtos;
    }


    // 매장리스트 출력
    public List<MarketDto> getmarket(){
        List<MarketEntity> entities=marketRepository.findAll();

        List<MarketDto> marketDtoList=new ArrayList<>();
        for(MarketEntity entity : entities){
            marketDtoList.add(entity.toDto());
        }
        return marketDtoList;
    }




}
