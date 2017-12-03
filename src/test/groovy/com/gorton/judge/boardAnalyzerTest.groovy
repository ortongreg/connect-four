package com.gorton.judge

import com.gorton.Board
import com.gorton.Color
import com.gorton.Piece
import org.junit.Test

class boardAnalyzerTest {
    BoardAnalyzer boardAnalyzer = new BoardAnalyzer()

    @Test
    void winLines_fullBoard(){
        Board board = new Board()
        Piece p = new Piece(Color.RED)
        (1..7).each{ col ->
            (1..6).each{ board.drop(col, p)}
        }
        List<List<Piece>> winLines = boardAnalyzer.winLines(board)
        assert 25 == winLines.size()
        (0..6).each {assert winLines[it].size() == 6}
        (7..12).each {assert winLines[it].size() == 7}
        assert winLines[13].size() == 4
        assert winLines[14].size() == 5
        assert winLines[15].size() == 6
        assert winLines[16].size() == 6
        assert winLines[17].size() == 5
        assert winLines[18].size() == 4
        assert winLines[19].size() == 6
        assert winLines[20].size() == 5
        assert winLines[21].size() == 4
        assert winLines[22].size() == 6
        assert winLines[23].size() == 5
        assert winLines[24].size() == 4
    }

    @Test
    void winLines_emptyBoard(){
        Board board = new Board()
        List<List<Piece>> winLines = boardAnalyzer.winLines(board)
        assert winLines.isEmpty()
    }

    @Test
    void winLines_piecesButNoPossibleWinLines(){
        Board board = new Board()
        Piece p = new Piece(Color.RED)
        (1..3).each{ col ->
            (1..3).each{ board.drop(col, p) }
        }
        List<List<Piece>> winLines = boardAnalyzer.winLines(board)
        assert winLines.isEmpty()
    }
}
