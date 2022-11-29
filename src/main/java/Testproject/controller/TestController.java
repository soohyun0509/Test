package Testproject.controller;

import Testproject.domain.Dto.PageVo;
import Testproject.domain.Dto.ProductDto;
import Testproject.domain.Dto.MarketDto;
import Testproject.service.Testservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TestController {

    @Autowired
    private Testservice testservice;

    @GetMapping("/test")
    public Resource test(){return new ClassPathResource("templates/test.html");
    }

    @PostMapping("/productup")
    public boolean productup(@RequestBody ProductDto productDto){

       return testservice.productup(productDto);
    }

    @PostMapping("/setmarket")
    public boolean setmarket(@RequestBody MarketDto marketDto){
        boolean result = testservice.setmarket( marketDto );
        return result;
    }

    @GetMapping("/productlist")
    public PageVo productlist(@RequestParam("mno") int mno, @RequestParam("page") int page){
       return testservice.productlist(mno, page);

    }

    // 매장리스트 출력
    @GetMapping("/getmarket")
    public List<MarketDto> getmarket(){
        return testservice.getmarket();
    }

    // 매장별 매출 합계 출력
    @GetMapping("/printtotal")
    public int printtotal(@RequestParam("mno") int mno, @RequestParam("cdate") String cdate){
        return testservice.printtotal(mno, cdate);
    }




}
