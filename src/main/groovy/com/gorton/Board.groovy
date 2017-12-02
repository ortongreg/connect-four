package com.gorton

import com.gorton.errors.FullColumnException
import com.gorton.errors.InvalidColumnException

class Board {
    List<List<Piece>> columns = []

    Board(){
        (0..6).each {
            columns.add([])
        }
    }

    void drop(int column, Piece piece) {
        if( column < 1 || column > 7) throw new InvalidColumnException(column)
        List<Piece> col = columns[column-1]
        if( col.size() == 6) throw new FullColumnException(column)
        col.add(piece)
    }

    List<Piece> row(int row) {
        List<Piece> res = []
        (0..6).each {
            res.add(columns[it][row])
        }
        res
    }

    Piece getAt(int col, int row){
        if( columns[col].size() > row){
            return columns[col][row]
        }
        null
    }
}
