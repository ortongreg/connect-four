package com.gorton

import com.gorton.errors.FullColumnException
import com.gorton.errors.InvalidColumnException
import org.junit.Before
import org.junit.Test


class BoardTest {
    Board board

    @Before
    void setUp(){
        board = new Board()
    }

    @Test
    void boardEmpty(){
        assert board.columns.size() == 7
        board.columns.each{
            assert it.isEmpty()
        }
    }

    @Test(expected = InvalidColumnException.class)
    void drop_Zero_invalid(){
        board.drop(0, new Piece(1))
    }

    @Test(expected = InvalidColumnException.class)
    void drop_eight_invalid(){
        board.drop(8, new Piece(1))
    }

    @Test
    void drop_firstColumn(){
        Piece expected = new Piece(1)
        board.drop(1, expected)

        assert expected == board.columns[0][0]
    }

    @Test
    void drop_seventhColumn(){
        Piece expected = new Piece(1)
        board.drop(7, expected)

        assert expected == board.columns[6][0]
    }

    @Test
    void drop_sixSameColumn_allowed(){
        (1..6).each {board.drop(1, new Piece(1))}
        assert 6 == board.columns[0].size()
    }

    @Test(expected = FullColumnException.class)
    void drop_sevenSameColumn_NotAllowed(){
        (1..7).each {board.drop(1, new Piece(1))}
    }

    @Test
    void row_bottomRow_full(){
        (1..7).each {board.drop(it, new Piece(1))}

        List<Piece> row0 = board.row(0)
        assert 7 == row0.size()
        (0..6).each{ assert row0[it] != null}
    }

    @Test
    void row_secondRow_empty(){
        (1..7).each {board.drop(it, new Piece(1))}

        List<Piece> row1 = board.row(1)
        assert 7 == row1.size()
        (0..6).each{ assert row1[it] == null}
    }

    @Test
    void row_secondRow_partialFull(){
        (1..7).each {board.drop(it, new Piece(1))}
        board.drop(4, new Piece(2))

        List<Piece> row0 = board.row(0)
        assert 7 == row0.size()
        (0..6).each{ assert row0[it] != null}

        List<Piece> row1 = board.row(1)
        assert 7 == row1.size()
        assert row1[0] == null
        assert row1[1] == null
        assert row1[2] == null
        assert row1[3] != null
        assert row1[4] == null
        assert row1[5] == null
        assert row1[6] == null
    }
}
