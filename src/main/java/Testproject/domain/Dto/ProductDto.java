package Testproject.domain.Dto;

import Testproject.domain.Entity.MarketEntity;
import Testproject.domain.Entity.ProductEntity;
import jdk.nashorn.internal.runtime.regexp.joni.ast.StringNode;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductDto {

    private int pno;
    private String pname;
    private int pprice;
    private int mno;

    public ProductEntity toEntity(){
        return ProductEntity.builder()
                .pno(this.pno)
                .pname(this.pname)
                .pprice(this.pprice)
                .build();
    }



}
