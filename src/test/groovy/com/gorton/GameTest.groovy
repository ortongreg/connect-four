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
    public static final String CHOOSE_P_ONE = "$PLAYER_ONE, Choose a column [1-7]"
    public static final String CHOOSE_P_TWO = "$PLAYER_TWO, Choose a column [1-7]"
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
        when(ui.promptForInput(CHOOSE_P_ONE)).thenReturn("1")
        when(ui.promptForInput(CHOOSE_P_TWO)).thenReturn("2")

        when(judge.winner(any(Board))).thenReturn(0,1)
    }

    @Test
    void startNewGame_lowerCase(){
        when(ui.promptForInput(NEW_GAME)).thenReturn("y")

        game = new Game(config, judge)
        game.playGame()

        verify(ui).promptForInput(CHOOSE_P_ONE)
    }

    @Test
    void startNewGame_upperCase(){
        when(ui.promptForInput(NEW_GAME)).thenReturn("Y")
        when(judge.winner(any(Board))).thenReturn(1)

        game = new Game(config, judge)
        game.playGame()

        verify(ui).promptForInput(CHOOSE_P_ONE)
    }

    @Test
    void startNewGame_notY(){
        when(ui.promptForInput(NEW_GAME)).thenReturn("n")
        game = new Game(config, judge)
        game.playGame()
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
        when(ui.promptForInput(CHOOSE_P_ONE)).thenReturn("1")
        when(ui.promptForInput(CHOOSE_P_TWO)).thenReturn("7")
        game = new Game(config, judge)
        game.playGame()

        List<Piece> row = game.board.row(0)
        assert 7 == row.size()
        assert row[0] != null
        (1..5).each { assert row[it] == null}
        assert row[6] != null

        InOrder inOrder = inOrder(ui)
        inOrder.verify(ui).showBoard(any(Board))
        inOrder.verify(ui).promptForInput(CHOOSE_P_ONE)
        inOrder.verify(ui).showBoard(any(Board))
        inOrder.verify(ui).promptForInput(CHOOSE_P_TWO)
    }

    @Test
    void invalidMove_0(){
        when(ui.promptForInput(NEW_GAME)).thenReturn("Y")
        when(ui.promptForInput(CHOOSE_P_ONE)).thenReturn("0", "1")
        game = new Game(config, judge)
        game.playGame()
        verify(ui, times(2)).promptForInput(CHOOSE_P_ONE)
    }

    @Test
    void tieGame(){
        when(judge.winner(any(Board))).thenReturn(-1)
        game = new Game(config, judge)
        int winner = game.playGame()
        assert -1 == winner
    }

    @Test
    void invalidMove_8(){
        when(ui.promptForInput(NEW_GAME)).thenReturn("Y")
        when(ui.promptForInput(CHOOSE_P_ONE)).thenReturn("8", "1")
        game = new Game(config, judge)
        game.playGame()
        verify(ui, times(2)).promptForInput(CHOOSE_P_ONE)
    }

    @Test
    void invalidMove_NotAnumber(){
        when(ui.promptForInput(NEW_GAME)).thenReturn("Y")
        when(ui.promptForInput(CHOOSE_P_ONE)).thenReturn("FOO", "1")
        game = new Game(config, judge)
        game.playGame()
        verify(ui, times(2)).promptForInput(CHOOSE_P_ONE)
    }

    @Test
    void invalidMove_ColumnFull(){
        when(ui.promptForInput(NEW_GAME)).thenReturn("Y")
        when(ui.promptForInput(CHOOSE_P_ONE))
                .thenThrow(new FullColumnException(7))
                .thenReturn("1")
        game = new Game(config, judge)
        game.playGame()
        verify(ui, times(1)).display("Column 7 is full", RED)
        verify(ui, times(2)).promptForInput(CHOOSE_P_ONE)
    }

    @Test
    void secondGame_doNotPromptForNames(){
        when(ui.promptForInput(NEW_GAME)).thenReturn("Y")
        when(judge.winner(any(Board))).thenReturn(0,1)

        game = new Game(config, judge)
        game.playGame()
        game.playGame()
        verify(ui, times(1)).promptForInput(PLAYER_ONE_PROMPT)
        verify(ui, times(1)).promptForInput(PLAYER_TWO_PROMPT)
    }

    @Test
    void secondGame_playerOneStarts(){
        when(ui.promptForInput(NEW_GAME)).thenReturn("Y")
        when(judge.winner(any(Board))).thenReturn(0,0,2)
        game = new Game(config, judge)
        game.playGame()
        game.playGame()
        verify(ui, times(3)).promptForInput(CHOOSE_P_ONE)
        verify(ui, times(1)).promptForInput(CHOOSE_P_TWO)
    }

}
