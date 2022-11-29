package Testproject.domain.Dto;

import Testproject.domain.Entity.MarketEntity;
import lombok.*;
import org.yaml.snakeyaml.error.Mark;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MarketDto {

    private int mno;
    private String mname;

    public MarketEntity toEntity(){
        return MarketEntity.builder()
                .mno(this.mno)
                .mname(this.mname)
                .build();
    }


}
