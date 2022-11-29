package Testproject.service;

import Testproject.domain.Dto.ProductDto;
import Testproject.domain.Entity.MarketEntity;
import Testproject.domain.Entity.MarketRepository;
import Testproject.domain.Entity.ProductEntity;
import Testproject.domain.Entity.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


}
