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
            whoWonR(pieces, 1):
            null
    }

    private Color whoWonR(List<Piece> pieces, int streak){
        Color winner
        if( streak==4){
            winner = pieces.head().color
        }else if( !pieces.tail().isEmpty()){
            Piece nextHead = pieces.tail().head()
            if(pieces.head().color == nextHead.color){
                streak++
            }else{
                streak = 1
            }
            winner = whoWonR(pieces.tail(), streak)
        }
        winner
    }
}
