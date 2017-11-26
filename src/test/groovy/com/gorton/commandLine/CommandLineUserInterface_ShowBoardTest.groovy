package com.gorton.commandLine

import com.gorton.Board
import com.gorton.Color
import com.gorton.Piece
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InOrder
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

import static com.gorton.Color.*
import static org.mockito.Mockito.*

@RunWith(MockitoJUnitRunner.class)
class CommandLineUserInterface_ShowBoardTest {

    static final String ROW_KEYS =  " 1  2  3  4  5  6  7"
    static final String BLUE = "\u001B[34m"
    static final String RESET = "\u001B[0m"
    static final String WHITE_BG = "\u001B[47m"

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
                Color color = count%2 == 0? RED : BLACK
                board.drop(col, new Piece(color))
                count++
            }
        }

        ui.showBoard(board)

        InOrder inOrder = inOrder(console)
        String firstRow = WHITE_BG +
                exSlot(new Piece(RED)) +
                exSlot(new Piece(BLACK)) +
                exSlot(new Piece(RED)) +
                exSlot(new Piece(BLACK)) +
                exSlot(new Piece(RED)) +
                exSlot(new Piece(BLACK)) +
                exSlot(new Piece(RED)) +
                RESET
        String secondRow =  WHITE_BG +
                exSlot(new Piece(BLACK)) +
                exSlot(new Piece(RED)) +
                exSlot(new Piece(BLACK)) +
                exSlot(new Piece(RED)) +
                exSlot(new Piece(BLACK)) +
                exSlot(new Piece(RED)) +
                exSlot(new Piece(BLACK)) +
                RESET
        inOrder.verify(console).println('')
        inOrder.verify(console).println(ROW_KEYS)
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
        String emptyRow = WHITE_BG
        (1..7).each {emptyRow +=
                "$BLUE[ $BLUE]"}
        emptyRow += RESET
        verify(console).println('')
        verify(console).println(ROW_KEYS)
        verify(console, times(6)).println(emptyRow)
    }

    String exSlot(Piece piece){
        String colorCode = piece.color == RED ? "\u001B[31m" : "\u001B[30m"
        String token = " "
        if(piece.color == RED){
            token = "X"
        }else if(piece.color == BLACK){
            token = "O"
        }
        "$BLUE[$colorCode$token$BLUE]"
    }

}
