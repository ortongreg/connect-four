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
        if( column < 1 || column > 6) throw new InvalidColumnException(column)
        List<Piece> col = columns[column-1]
        if( col.size() == 6) throw new FullColumnException(column)
        col.add(piece)
    }
}
