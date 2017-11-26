package com.gorton

class Judge{
    int winner(Board board){
        boolean full = true
        board.columns.each {
            full = full && it.size() == 6
        }
        full? -1:0
    }
}
