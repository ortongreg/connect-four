package com.gorton.commandLine

import com.gorton.Board
import com.gorton.Color
import com.gorton.Piece
import com.gorton.config.UserInterface

import static com.gorton.Color.*

class CommandLineUserInterface implements UserInterface{

    ConsoleWriter console = new ConsoleWriter()

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
        console.println("HELLO")
    }

    private String slot(Piece piece){
        Color color = piece.player == 1? RED: YELLOW
        String token = piece.player == 1? "X": "O"
        String pieceString = "${COLOR_CODES[color]}$token"
        "${COLOR_CODES[BLUE]}[$pieceString${COLOR_CODES[BLUE]}]$RESET"
    }
}