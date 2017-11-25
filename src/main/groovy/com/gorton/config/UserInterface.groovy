package com.gorton.config

import com.gorton.Board
import com.gorton.Color

interface UserInterface {
    void display(String msg)
    void display(String msg, Color color)

    void showBoard(Board board)
}