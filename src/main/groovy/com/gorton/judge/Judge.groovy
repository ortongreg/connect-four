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
        int count = 0
        Color check
        for (Piece it: pieces) {
            if( check == null){
                count++
                check = it.color
            }else if(check == it.color){
                count ++
                check = it.color
            }else{
                count = 1
                check = it.color
            }
            if(count > 3){
                return check
            }
        }
        null
    }
}
