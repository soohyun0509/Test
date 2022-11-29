package Testproject.controller;

import Testproject.domain.Dto.ProductDto;
import Testproject.domain.Dto.MarketDto;
import Testproject.service.Testservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    public List<ProductDto> productlist(){
        return testservice.productlist();
    }


}
