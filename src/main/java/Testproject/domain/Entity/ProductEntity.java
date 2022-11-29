package Testproject.domain.Entity;

import Testproject.domain.Dto.ProductDto;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "product")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class ProductEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pno;

    @Column
    private String pname;

    @Column
    private int pprice;

    @ManyToOne
    @JoinColumn(name = "mno")
    @ToString.Exclude
    private MarketEntity marketEntity;

    public ProductDto toDto(){
        return ProductDto.builder()
                .pno(this.pno)
                .pname(this.pname)
                .pprice(this.pprice)
                .mname(this.getMarketEntity().getMname())
                .build();
    }



}
