package com.gorton.judge

import com.gorton.Board
import com.gorton.Color
import com.gorton.Piece

import static com.gorton.Color.*

class Judge{
    /*
    returns BLUE for tie
            WHITE for no winner yet
            RED for RED
            BLACK for BLACK
     */
    BoardAnalyzer boardAnalyzer = new BoardAnalyzer()

    Color winner(Board board){
        boolean full = true
        board.columns.each {
            full = full && it.size() == 6
        }
        if(full) return BLUE

        Color winner = WHITE
        for(List<Piece> winLine: boardAnalyzer.winLines(board)){
            winner = whoWon(winLine)
            if(winner != WHITE) break
        }
        winner

    }

    Color whoWon(List<Piece> pieces) {
        whoWonR(pieces, 0)
    }

    private Color whoWonR(List<Piece> pieces, int streak){
        if(pieces.isEmpty() || pieces.tail().isEmpty()) return WHITE

        boolean nextMatchesHead = pieces.head() != null && pieces.tail().head() != null &&
                pieces.head().color == pieces.tail().head().color

        if(nextMatchesHead){
            streak++
        }else{
            streak = 0
        }

        streak == 3? pieces.head().color : whoWonR(pieces.tail(), streak)

    }
}
