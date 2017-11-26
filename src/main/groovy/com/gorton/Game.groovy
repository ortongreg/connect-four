package com.gorton

import com.gorton.config.Config
import com.gorton.config.UserInterface

class Game {
    private UserInterface ui
    String playerOne
    String playerTwo
    boolean isPlayerOnesTurn = true
    Board board

    Game(Config config){
        ui = config.userInterface()
        initGame()
    }

    void initGame(){
        String play = ui.promptForInput("Would you like to play a new game? [y/n]")
        if( 'y' != play){
            ui.quit()
        }

        playerOne = ui.promptForInput("Player One, what is your name?")
        playerTwo = ui.promptForInput("Player Two, what is your name?")
        board = new Board()
        gameOn()
    }

    void gameOn() {
        playMove()
        playMove()
    }

    void playMove(){
        ui.showBoard(board)
        String prompt = String.format("%s, Choose a column [1-7]", isPlayerOnesTurn? playerOne: playerTwo)
        String move = ui.promptForInput(prompt)
        isPlayerOnesTurn = !isPlayerOnesTurn

    }
}
