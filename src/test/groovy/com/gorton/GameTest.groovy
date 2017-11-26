package com.gorton

import com.gorton.config.Config
import com.gorton.config.UserInterface
import com.gorton.errors.FullColumnException
import com.gorton.judge.Judge
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InOrder
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

import static com.gorton.Color.*
import static org.mockito.Mockito.*

@RunWith(MockitoJUnitRunner.class)
class GameTest {

    public static final String NEW_GAME = "Would you like to play a new game? [y/n]"
    public static final String PLAYER_ONE_PROMPT = "Player One, what is your name?"
    public static final String PLAYER_TWO_PROMPT = "Player Two, what is your name?"
    public static final String PLAYER_ONE = "P One"
    public static final String PLAYER_TWO = "Twoster"
    Game game
    @Mock Config config
    @Mock UserInterface ui
    @Mock Judge judge

    @Before
    void setUp(){
        when(config.userInterface()).thenReturn(ui)

        when(ui.promptForInput(NEW_GAME)).thenReturn("Y")

        when(ui.promptForInput(PLAYER_ONE_PROMPT)).thenReturn(PLAYER_ONE)
        when(ui.promptForInput(PLAYER_TWO_PROMPT)).thenReturn(PLAYER_TWO)
        when(ui.promptForInput("$PLAYER_ONE, Choose a column [1-7]")).thenReturn("1")
        when(ui.promptForInput("$PLAYER_TWO, Choose a column [1-7]")).thenReturn("2")

        when(judge.winner(any(Board))).thenReturn(0,1)
    }

    @Test
    void startNewGame_lowerCase(){
        when(ui.promptForInput(NEW_GAME)).thenReturn("y")

        game = new Game(config, judge)

        verify(ui).promptForInput(NEW_GAME)
        verify(ui).promptForInput(PLAYER_ONE_PROMPT)
    }

    @Test
    void startNewGame_upperCase(){
        when(ui.promptForInput(NEW_GAME)).thenReturn("Y")

        game = new Game(config, judge)

        verify(ui).promptForInput(NEW_GAME)
        verify(ui).promptForInput(PLAYER_ONE_PROMPT)
    }

    @Test
    void startNewGame_notY(){
        when(ui.promptForInput(NEW_GAME)).thenReturn("n")
        game = new Game(config, judge)
        verify(ui).quit()
    }

    @Test
    void initGame(){
        when(ui.promptForInput(NEW_GAME)).thenReturn("Y")
        game = new Game(config, judge)
        assert PLAYER_ONE == game.playerOne
        assert PLAYER_TWO == game.playerTwo
        assert game.isPlayerOnesTurn
    }

    @Test
    void goPlayerTwo(){
        when(ui.promptForInput(NEW_GAME)).thenReturn("Y")
        when(ui.promptForInput("$PLAYER_ONE, Choose a column [1-7]")).thenReturn("1")
        when(ui.promptForInput("$PLAYER_TWO, Choose a column [1-7]")).thenReturn("7")
        game = new Game(config, judge)
        game.gameOn()

        List<Piece> row = game.board.row(0)
        assert 7 == row.size()
        assert row[0] != null
        (1..5).each { assert row[it] == null}
        assert row[6] != null

        InOrder inOrder = inOrder(ui)
        inOrder.verify(ui).showBoard(any(Board))
        inOrder.verify(ui).promptForInput("$PLAYER_ONE, Choose a column [1-7]")
        inOrder.verify(ui).showBoard(any(Board))
        inOrder.verify(ui).promptForInput("$PLAYER_TWO, Choose a column [1-7]")
    }

    @Test
    void invalidMove_0(){
        when(ui.promptForInput(NEW_GAME)).thenReturn("Y")
        when(ui.promptForInput("$PLAYER_ONE, Choose a column [1-7]")).thenReturn("0", "1")
        game = new Game(config, judge)
        game.gameOn()
        verify(ui, times(2)).promptForInput("$PLAYER_ONE, Choose a column [1-7]")
    }

    @Test
    void tieGame(){
        when(judge.winner(any(Board))).thenReturn(-1,1)
        game = new Game(config, judge)
        int winner = game.gameOn()
        assert 1 == winner
        verify(ui, times(2)).promptForInput(NEW_GAME)
        verify(ui, times(3)).showBoard(any(Board))
        verify(ui).display("It's a tie!")
    }

    @Test
    void invalidMove_8(){
        when(ui.promptForInput(NEW_GAME)).thenReturn("Y")
        when(ui.promptForInput("$PLAYER_ONE, Choose a column [1-7]")).thenReturn("8", "1")
        game = new Game(config, judge)
        game.gameOn()
        verify(ui, times(2)).promptForInput("$PLAYER_ONE, Choose a column [1-7]")
    }

    @Test
    void invalidMove_NotAnumber(){
        when(ui.promptForInput(NEW_GAME)).thenReturn("Y")
        when(ui.promptForInput("$PLAYER_ONE, Choose a column [1-7]")).thenReturn("FOO", "1")
        game = new Game(config, judge)
        game.gameOn()
        verify(ui, times(2)).promptForInput("$PLAYER_ONE, Choose a column [1-7]")
    }

    @Test
    void invalidMove_ColumnFull(){
        when(ui.promptForInput(NEW_GAME)).thenReturn("Y")
        when(ui.promptForInput("$PLAYER_ONE, Choose a column [1-7]"))
                .thenThrow(new FullColumnException(7))
                .thenReturn("1")
        game = new Game(config, judge)
        game.gameOn()
        verify(ui, times(1)).display("Column 7 is full", RED)
        verify(ui, times(2)).promptForInput("$PLAYER_ONE, Choose a column [1-7]")
    }

}
