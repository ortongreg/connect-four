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
    private static final String WHITE_BG = "\u001B[47m"
    private static final Map<Color, String> COLOR_CODES
    static {
        COLOR_CODES = new EnumMap<Color, String>(Color.class)
        COLOR_CODES.put(WHITE, "")
        COLOR_CODES.put(BLUE, "\u001B[34m")
        COLOR_CODES.put(RED, "\u001B[31m")
        COLOR_CODES.put(BLACK, "\u001B[30m")
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
        console.println('')
        console.println(ROW_KEYS)
        (5..0).each {
            console.println(row(board.row(it)))
        }
    }

    String promptForInput(String prompt) {
        return console.input("$prompt: ")
    }

    void quit() {
        console.quit()
    }

    private String row(List<Piece> row){
        String result = WHITE_BG
        row.each {result += slot(it)}
        result += RESET
        result
    }

    private String slot(Piece piece){
        Color color = WHITE
        String token = " "
        if( piece != null ){
            color = piece.player == 1? RED: BLACK
            token = piece.player == 1? "X": "O"
        }
        String pieceString = "${COLOR_CODES[color]}$token"
        "${COLOR_CODES[BLUE]}[$pieceString${COLOR_CODES[BLUE]}]"
    }
}