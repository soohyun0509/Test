package Testproject.domain.Dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class PageVo { // 읽기전용 : 출력만

    
    private int startbtn;
    private int endbtn;
    List<ProductDto> list;



}
