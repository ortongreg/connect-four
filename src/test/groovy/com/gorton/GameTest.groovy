package com.gorton

import com.gorton.config.Config
import com.gorton.config.UserInterface
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

import static org.mockito.Mockito.*

@RunWith(MockitoJUnitRunner.class)
class GameTest {

    public static final String NEW_GAME = "Would you like to play a new game? [y/n]"
    public static final String PLAYER_ONE = "Player One, what is your name?"
    Game game
    @Mock Config config
    @Mock UserInterface ui

    @Before
    void setUp(){
        when(config.userInterface()).thenReturn(ui)
    }

    @Test
    void startNewGame_lowerCase(){
        when(ui.promptForInput(NEW_GAME)).thenReturn("y")

        game = new Game(config)

        verify(ui).promptForInput(NEW_GAME)
        verify(ui).promptForInput(PLAYER_ONE)
    }

    @Test
    void startNewGame_upperCase(){
        when(ui.promptForInput(NEW_GAME)).thenReturn("Y")

        game = new Game(config)

        verify(ui).promptForInput(NEW_GAME)
        verify(ui).promptForInput(PLAYER_ONE)
    }

    @Test
    void startNewGame_notY(){
        when(ui.promptForInput(NEW_GAME)).thenReturn("n")
        game = new Game(config)
        verify(ui).quit()
    }
}
