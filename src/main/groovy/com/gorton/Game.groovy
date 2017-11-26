package com.gorton

import com.gorton.config.Config
import com.gorton.config.UserInterface

import static com.gorton.Color.WHITE


class Game {
    private UserInterface ui
    Game(Config config){
        ui = config.userInterface()
        ui.display("Connect Four!")
    }

    void gameOn() {
        Board board = new Board()
        board.drop(1, new Piece(1))
        board.drop(1, new Piece(2))
        board.drop(2, new Piece(1))
        board.drop(3, new Piece(2))
        board.drop(3, new Piece(1))

        ui.showBoard(board)

    }
}
