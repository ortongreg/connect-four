package com.gorton

import com.gorton.config.Config
import com.gorton.config.UserInterface
import com.gorton.errors.FullColumnException
import com.gorton.errors.InvalidColumnException
import com.gorton.judge.Judge

import static com.gorton.Color.*

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
    }

    Color playGame(){
        board = new Board()
        ui.showBoard(board)
        isPlayerOnesTurn = true
        String play = ui.promptForInput("Would you like to play a new game? [y/n]")
        if( 'y' != play){
            ui.quit()
        }
        playerOne = playerOne ?: ui.promptForInput("Player One, what is your name?")
        playerTwo = playerTwo ?: ui.promptForInput("Player Two, what is your name?")
        gameOn()
    }

    private Color gameOn() {
        Color winner = playMove()
        winner != WHITE? winner : gameOn()
    }

    private Color playMove(){
        ui.showBoard(board)
        String prompt = String.format("%s, Choose a column [1-7]", isPlayerOnesTurn? playerOne: playerTwo)

        try{
            String move = ui.promptForInput(prompt)
            if(move.isInteger()){
                board.drop(new Integer(move), new Piece(isPlayerOnesTurn? RED : BLACK))
                isPlayerOnesTurn = !isPlayerOnesTurn
            }else{
                throw new InvalidColumnException(99)
            }
        }catch (InvalidColumnException e){
            playMove()
        }catch (FullColumnException e){
            ui.display(e.message, RED)
            playMove()
        }
        judge.winner(board)
    }
}
