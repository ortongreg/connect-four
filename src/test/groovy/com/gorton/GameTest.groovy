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

    Game game
    @Mock Config config
    @Mock UserInterface ui

    @Before
    void setUp(){
        when(config.userInterface()).thenReturn(ui)
        game = new Game(config)
    }

    @Test
    void startGame(){
        verify(ui).display("Connect Four!")
    }
}
