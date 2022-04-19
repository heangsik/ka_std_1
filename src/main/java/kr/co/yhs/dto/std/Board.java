package kr.co.yhs.dto.std;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class Board {
    int boardNo;
    String title;
    String content;
    String writer;
    LocalDateTime regDate;
}
