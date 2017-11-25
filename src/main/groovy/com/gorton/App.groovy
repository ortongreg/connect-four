package com.gorton

import com.gorton.commandLine.*
import com.gorton.config.*

import static com.gorton.Color.*

class App {

    static Config config = new CommandLineConfig()

    static void main(String[] args){
        UserInterface ui = config.userInterface()

        Board board = new Board()
        board.drop(1, new Piece(1))
        board.drop(1, new Piece(2))
        board.drop(2, new Piece(1))
        board.drop(3, new Piece(2))
        board.drop(3, new Piece(1))

        ui.display("Hello")
        ui.showBoard(board)
        ui.display("World", WHITE)
    }
}