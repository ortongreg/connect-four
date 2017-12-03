package com.gorton.judge

import com.gorton.Board
import com.gorton.Piece

class BoardAnalyzer {

    List<List<Piece>> winLines(Board board){

        List<List<Piece>> result = []
        result.addAll(board.columns)
        (0..5).each {result.add(board.row(it))}
        (3..4).each{result.add(doDiagonalDown(board, it, 0))}
        (0..3).each{result.add(doDiagonalDown(board, 5, it))}
        (0..2).each{result.add(doDiagonalUp(board, it, 0))}
        (1..3).each{result.add(doDiagonalUp(board, 0, it))}

        result.findAll{hasAtLeastFourPieces(it)}
    }

    private boolean hasAtLeastFourPieces(List<Piece> pieces){
        pieces.findAll{it != null}.size() > 3
    }

    private List<Piece> doDiagonalDown(Board board, int startRow, int startCol){
        List<Piece> result = []
        int c = startCol
        int r = startRow
        while(r >= 0 && c <= 6){
            result.add(board.getAt(c,r))
            r--
            c++
        }
        return result
    }

    private List<Piece> doDiagonalUp(Board board, int startRow, int startCol){
        List<Piece> result = []
        int c = startCol
        int r = startRow
        while(r <= 5 && c <= 6){
            result.add(board.getAt(c,r))
            r++
            c++
        }
        return result
    }
}
