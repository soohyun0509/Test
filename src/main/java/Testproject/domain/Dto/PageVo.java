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

    private int totalpage; // 전체 페이지
    private int startbtn;  // 시작 버튼
    private int endbtn;    // 끝 버튼
    List<ProductDto> list;



}
