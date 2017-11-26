package com.gorton.commandLine

import com.gorton.Board
import com.gorton.Color
import com.gorton.Piece
import com.gorton.config.UserInterface

import static com.gorton.Color.*

class CommandLineUserInterface implements UserInterface{

    ConsoleWriter console = new ConsoleWriter()

    private static final String ROW_KEYS =  " 1  2  3  4  5  6  7"
    private static final String RESET = "\u001B[0m"
    private static final Map<Color, String> COLOR_CODES
    static {
        COLOR_CODES = new EnumMap<Color, String>(Color.class)
        COLOR_CODES.put(WHITE, "")
        COLOR_CODES.put(BLUE, "\u001B[34m")
        COLOR_CODES.put(RED, "\u001B[31m")
        COLOR_CODES.put(YELLOW, "\u001B[33m")
    }
    @Override
    void display(String msg){
        console.println(msg)
    }

    @Override
    void display(String msg, Color color){
        console.println("${COLOR_CODES[color]}$msg$RESET")
    }

    @Override
    void showBoard(Board board){
        console.println(ROW_KEYS)
        (5..0).each {
            console.println(row(board.row(it)))
        }
    }

    private String row(List<Piece> row){
        String result = ""
        row.each {result += slot(it)}
        result
    }

    private String slot(Piece piece){
        Color color = WHITE
        String token = " "
        if( piece != null ){
            color = piece.player == 1? RED: YELLOW
            token = piece.player == 1? "X": "O"
        }
        String pieceString = "${COLOR_CODES[color]}$token"
        "${COLOR_CODES[BLUE]}[$pieceString${COLOR_CODES[BLUE]}]$RESET"
    }
}