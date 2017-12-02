package com.gorton.judge

import com.gorton.Board
import com.gorton.Piece

class BoardAnalyzer {


    /*
   5 X X X X X X X
   4 X X X X X X X
   3 T X X X X X X
   2 X T X X X X X
   1 X X T X X X X
   0 X X X T X X X
     0 1 2 3 4 5 6
     */

    List<List<Piece>> winLines(Board board){
        List<List<Piece>> result = []
        result.addAll(board.columns)
        (0..5).each {result.add(board.row(it))}
        (3..5).each {result.add(doDiagonal(board, it, 0))}
        result.add(doDiagonal(board, 5, 2))
        result.add(doDiagonal(board, 5, 3))
//        result.add([board.getAt(6, 1), board.getAt(5,2), board.getAt(4,3), board.getAt(3,4), board.getAt(2,5)])
        result
    }

    private List<Piece> doDiagonal(Board board, int startRow, int startCol){
        List<Piece> result = []
        int c = startCol
        int r = startRow
        while(r >= 0 && c <= 6){
            println "get at r:$r c:$c"
            result.add(board.getAt(r, c))
            r--
            c++
        }
        return result
    }
}
