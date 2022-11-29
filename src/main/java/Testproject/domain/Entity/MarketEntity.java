package Testproject.domain.Entity;

import Testproject.domain.Dto.MarketDto;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "market")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class MarketEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int mno;

    @Column
    private String mname;

    @OneToMany(mappedBy = "marketEntity")
    @Builder.Default
    @ToString.Exclude
    private List<ProductEntity> productEntityList=new ArrayList<>();

    public MarketDto toDto(){
        return MarketDto.builder()
                .mno(this.mno)
                .mname(this.mname)
                .build();
    }



}
