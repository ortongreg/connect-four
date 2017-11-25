package com.gorton

import com.gorton.commandLine.*
import com.gorton.config.*

import static com.gorton.Color.*

class App {

    static Config config = new CommandLineConfig()

    static void main(String[] args){
        UserInterface ui = config.userInterface()

        Board board = new Board()
        board.pieces.add(new Piece(1))
        board.pieces.add(new Piece(2))
        board.pieces.add(new Piece(1))
        board.pieces.add(new Piece(2))
        board.pieces.add(new Piece(1))
        board.pieces.add(new Piece(2))

        ui.display("Hello")
        ui.showBoard(board)
        ui.display("World", WHITE)
    }
}