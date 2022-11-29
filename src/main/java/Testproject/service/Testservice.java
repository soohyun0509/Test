package Testproject.service;

import Testproject.domain.Dto.MarketDto;
import Testproject.domain.Dto.PageVo;
import Testproject.domain.Dto.ProductDto;
import Testproject.domain.Entity.MarketEntity;
import Testproject.domain.Entity.MarketRepository;
import Testproject.domain.Entity.ProductEntity;
import Testproject.domain.Entity.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    // 매장 리스트 출력
    public PageVo productlist(int mno, int page ){

        PageVo pageDto = new PageVo();
        // 페이징 처리된 객체 생성
        Page<ProductEntity> productEntities = null;
        // 페이징 설정 시작 0부터
        Pageable pageable = PageRequest.of( page-1, 5, Sort.by( Sort.Direction.DESC, "mno"));
        List<ProductDto> productDtos = new ArrayList<>();

        if( mno == 0 ){
            productEntities = productRepository.findAll( pageable ); // 전체출력
        }else {
            productEntities = productRepository.findbyMno( mno, pageable ); // 카테고리 선택
        }

        // 표시할 페이징 번호 버튼 수
        int btncount = 5; // 페이지에 표시할 개수
        int startbtn = ( page/btncount) * btncount; // 시작 번호 버튼
        int endbtn = startbtn + btncount-1; // 마지막 번호 버튼
        if( endbtn > productEntities.getTotalPages() ) { // 마지막 페이지가 크면
            endbtn = productEntities.getTotalPages(); // 같게 만들어주기
        }

        for( ProductEntity e : productEntities ){
            productDtos.add( e.toDto() );
        }
        pageDto.setList( productDtos );
        pageDto.setStartbtn( startbtn );
        pageDto.setEndbtn( endbtn );

        return pageDto;
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


    // 매장별 일별, 월별 매출 합계 출력
    public int printtotal(int mno , String cdate){
        List<ProductEntity> entities=productRepository.findbyCdate(mno, cdate);
        System.out.println(entities.toString() +"확인");

        int daytotal=0;
        for(ProductEntity e : entities){
            daytotal+=e.getPprice();
        }
        return daytotal;


    }


}
