package com.gorton

import com.gorton.commandLine.*
import com.gorton.config.*


class App {

    static Config config = new CommandLineConfig()

    static void main(String[] args){
        Game game = new Game(config)
        while(true){
            Color winner = game.playGame()
            config.userInterface().showBoard(game.board)
            config.userInterface().display("GAME OVER")
            String message = winner == Color.BLUE ? "Tie game!" : "${winner.name()} has won the Game!"
            config.userInterface().display(message)
        }
    }
}