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
        !pieces.isEmpty()?
            whoWonR(pieces.head(), pieces.tail(), 1):
            null
    }

    private Color whoWonR(Piece check, List<Piece> pieces, int streak){
        Color winner
        if( streak==4){
            winner = check.color
        }else if( !pieces.isEmpty()){
            if(pieces.head().color == check.color){
                streak++
            }else{
                streak = 1
            }
            winner = whoWonR(pieces.head(), pieces.tail(), streak)
        }
        winner
    }
}
