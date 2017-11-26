package com.gorton

import com.gorton.config.Config
import com.gorton.config.UserInterface
import com.gorton.errors.InvalidColumnException

class Game {
    private UserInterface ui
    String playerOne
    String playerTwo
    boolean isPlayerOnesTurn = true
    Board board
    Judge judge

    Game(Config config){
        this(config, new Judge())
    }

    protected Game(Config config, Judge judge){
        ui = config.userInterface()
        this.judge = judge
        initGame()
    }

    void initGame(){
        board = new Board()
        String play = ui.promptForInput("Would you like to play a new game? [y/n]")
        if( 'y' != play){
            ui.quit()
        }

        playerOne = ui.promptForInput("Player One, what is your name?")
        playerTwo = ui.promptForInput("Player Two, what is your name?")
    }

    int gameOn() {
        int winner = playMove()
        winner > 0? winner : gameOn()
    }

    int playMove(){
        ui.showBoard(board)
        String prompt = String.format("%s, Choose a column [1-7]", isPlayerOnesTurn? playerOne: playerTwo)

        try{
            String move = ui.promptForInput(prompt)
            if(move.isInteger()){
                board.drop(new Integer(move), new Piece(isPlayerOnesTurn? 1:2))
                isPlayerOnesTurn = !isPlayerOnesTurn
            }else{
                throw new InvalidColumnException(99)
            }
        }catch (InvalidColumnException e){
            playMove()
        }
        judge.winner(board)
    }
}
