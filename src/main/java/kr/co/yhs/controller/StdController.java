package kr.co.yhs.controller;

import kr.co.yhs.dto.std.Board;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Slf4j
@Controller
@RestController
public class StdController {
    @GetMapping("/ajaxHome")
    public String ajaxHome() {
        return "ajaxHome";
    }

    @GetMapping("boards/{boardNo}")
    public ResponseEntity<Board> read(@PathVariable("boardNo") int boardNo) {
        log.info("read board");

        Board board = Board.builder().boardNo(10).title("board title std").content("board content std").writer("board writer").regDate(LocalDateTime.now()).build();

        return new ResponseEntity<Board>(board, HttpStatus.OK);
    }
}
