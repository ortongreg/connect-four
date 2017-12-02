package com.gorton.judge

import com.gorton.Board
import com.gorton.Color
import com.gorton.Piece
import org.junit.Test

class boardAnalyzerTest {
    @Test
    void winLines_fullBoard(){
        Board board = new Board()
        Piece p = new Piece(Color.RED)
        (1..7).each{ col ->
            (1..6).each{ board.drop(col, p)}
        }
        List<List<Piece>> winLines = new BoardAnalyzer().winLines(board)
        assert 18== winLines.size()
        (0..6).each {assert winLines[it].size() == 6}
        (7..12).each {assert winLines[it].size() == 7}
        assert winLines[13].size() == 4
        assert winLines[14].size() == 5
        assert winLines[15].size() == 6
        assert winLines[16].size() == 5
        assert winLines[17].size() == 4

        /*
       5 X X X X X X X
       4 X X X X X X X
       3 T X X X X X X
       2 X T X X X X X
       1 X X T X X X X
       0 X X X T X X X
         0 1 2 3 4 5 6
         */
    }
}
