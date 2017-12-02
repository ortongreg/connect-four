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
    Color winner(Board board){
        boolean full = true
        board.columns.each {
            full = full && it.size() == 6
        }
        full? BLUE:WHITE
    }

    Color whoWon(List<Piece> pieces) {
        whoWonR(pieces, 0)
    }

    private Color whoWonR(List<Piece> pieces, int streak){
        if(pieces.isEmpty() || pieces.tail().isEmpty()) return BLUE

        boolean nextMatchesHead = pieces.head().color == pieces.tail().head().color

        if(nextMatchesHead){
            streak++
        }else{
            streak = 0
        }

        streak == 3? pieces.head().color : whoWonR(pieces.tail(), streak)

    }
}
