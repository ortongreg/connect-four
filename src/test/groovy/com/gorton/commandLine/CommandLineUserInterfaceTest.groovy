package com.gorton.commandLine

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
    static final String INPUT = "INPUT"
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

    @Test
    void promptForInput(){
        when(mockConsole.input("$TEST: ")).thenReturn(INPUT)
        assert INPUT == ui.promptForInput(TEST)
    }

    @Test
    void quit(){
        ui.quit()
        verify(mockConsole).quit()
    }
}
