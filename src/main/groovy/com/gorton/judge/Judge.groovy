package com.gorton.judge

import com.gorton.Board

class Judge{
    /*
    returns -1 for tie
            0 for no winner yet
            1 for RED
            2 for BLACK
     */
    int winner(Board board){
        boolean full = true
        board.columns.each {
            full = full && it.size() == 6
        }
        full? -1:0
    }
}
