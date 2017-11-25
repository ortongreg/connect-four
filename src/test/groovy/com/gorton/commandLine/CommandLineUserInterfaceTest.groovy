package com.gorton.commandLine

import com.gorton.Board
import com.gorton.Color
import com.gorton.Piece
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

import static com.gorton.Color.*
import static org.mockito.Mockito.*

@RunWith(MockitoJUnitRunner.class)
class CommandLineUserInterfaceTest {

    static final String TEST = "Test"
    @Mock
    ConsoleWriter mockConsole

    CommandLineUserInterface ui

    @Before
    void setUp(){
        ui = new CommandLineUserInterface()
        ui.console = mockConsole
    }

    @Test
    void display(){
        ui.display(TEST)
        verify(mockConsole).println(TEST)
    }

    @Test
    void display_color(){
        ui.display(TEST, BLUE)
        String expected = "\u001B[34m$TEST\u001B[0m"
        verify(mockConsole).println(expected)
    }
}
