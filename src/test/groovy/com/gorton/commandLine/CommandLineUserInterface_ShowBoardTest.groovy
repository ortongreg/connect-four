package com.gorton.commandLine

import com.gorton.Board
import com.gorton.Piece
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InOrder
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

import static org.mockito.Mockito.*

@RunWith(MockitoJUnitRunner.class)
class CommandLineUserInterface_ShowBoardTest {

    static final String BLUE = "\u001B[34m"
    static final String RESET = "\u001B[0m"

    CommandLineUserInterface ui
    @Mock ConsoleWriter console
    Board board

    @Before
    void setUp(){
        ui = new CommandLineUserInterface()
        ui.console = console
        board = new Board()
    }

    @Test
    void showBoard_fullBoard(){
        int count = 0
        (0..5).each {row ->
            (1..7).each{ col ->
                board.drop(col, new Piece(count%2 +1))
                count++
            }
        }

        ui.showBoard(board)

        InOrder inOrder = inOrder(console)
        String firstRow = exSlot(new Piece(1)) +
                exSlot(new Piece(2)) +
                exSlot(new Piece(1)) +
                exSlot(new Piece(2)) +
                exSlot(new Piece(1)) +
                exSlot(new Piece(2))+
                exSlot(new Piece(1))
        String secondRow = exSlot(new Piece(2)) +
                exSlot(new Piece(1)) +
                exSlot(new Piece(2)) +
                exSlot(new Piece(1)) +
                exSlot(new Piece(2)) +
                exSlot(new Piece(1)) +
                exSlot(new Piece(2))
        inOrder.verify(console).println(secondRow)
        inOrder.verify(console).println(firstRow)
        inOrder.verify(console).println(secondRow)
        inOrder.verify(console).println(firstRow)
        inOrder.verify(console).println(secondRow)
        inOrder.verify(console).println(firstRow)
    }

    @Test
    void showBoard_emptyBoard(){
        ui.showBoard(board)
        String emptyRow = ""
        (1..7).each {emptyRow +=
                "$BLUE[ $BLUE]$RESET"}
        verify(console, times(6)).println(emptyRow)
    }

    String exSlot(Piece piece){
        String colorCode = piece.player == 1 ? "\u001B[31m" : "\u001B[33m"
        String token = " "
        if(piece.player == 1){
            token = "X"
        }else if(piece.player == 2){
            token = "O"
        }
        "$BLUE[$colorCode$token$BLUE]$RESET"
    }

}