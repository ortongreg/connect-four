package com.gorton

import com.gorton.judge.Judge
import org.junit.Test

import static com.gorton.Color.*


class Judge_whoWonTest {

    Judge judge = new Judge()

    @Test
    void whoWon_noInput(){
        List<Piece> winLines = []
        Color winner = judge.whoWon(winLines)
        assert BLUE == winner
    }

    @Test
    void whoWon_hasInput_noWinner(){
        List<Piece> winLines = [new Piece(RED), new Piece(BLACK)]
        Color winner = judge.whoWon(winLines)
        assert BLUE == winner
    }

    @Test
    void whoWon_winner(){
        List<Piece> winLines = [new Piece(RED),new Piece(RED),new Piece(RED),new Piece(RED)]
        Color winner = judge.whoWon(winLines)
        assert RED == winner
    }

    @Test
    void whoWon_doesntStartWithTheWinner(){
        List<Piece> winLines = [new Piece(BLACK), new Piece(BLACK), new Piece(BLACK),
                                new Piece(RED),new Piece(RED),new Piece(RED),new Piece(RED)]
        Color winner = judge.whoWon(winLines)
        assert RED == winner
    }

    @Test
    void whoWon_resetCount(){
        List<Piece> winLines = [new Piece(BLACK), new Piece(BLACK), new Piece(BLACK),
                                new Piece(RED),new Piece(RED)]
        Color winner = judge.whoWon(winLines)
        assert BLUE == winner
    }

    @Test
    void whoWon_stopOnWinner(){
        List<Piece> winLines = [new Piece(RED),new Piece(RED),new Piece(RED),new Piece(RED), new Piece(BLACK)]
        Color winner = judge.whoWon(winLines)
        assert RED == winner
    }

}
