package com.gorton

import com.gorton.config.Config
import com.gorton.config.UserInterface

class Game {
    private UserInterface ui
    String playerOne
    String playerTwo

    Game(Config config){
        ui = config.userInterface()
        String play = ui.promptForInput("Would you like to play a new game? [y/n]")
        if( 'y' != play){
            ui.quit()
        }
        initGame()
    }

    void initGame(){
        playerOne = ui.promptForInput("Player One, what is your name?")
        playerTwo = ui.promptForInput("Player Two, what is your name?")
    }

    void gameOn() {
        Board board = new Board()
        board.drop(1, new Piece(1))
        board.drop(1, new Piece(2))
        board.drop(2, new Piece(1))
        board.drop(3, new Piece(2))
        board.drop(3, new Piece(1))

        ui.showBoard(board)
        String i = ui.promptForInput("HELLO")
        ui.println("val = $i")

    }
}
