package com.gorton

import com.gorton.commandLine.*
import com.gorton.config.*

import static com.gorton.Color.*

class App {

    static Config config = new CommandLineConfig()

    static void main(String[] args){
        UserInterface ui = config.userInterface()

        ui.display("Hello")
        ui.display("[][][][][][][]", BLUE)
        ui.display("X X X X X", RED)
        ui.display("O O O O O", YELLOW)
        ui.display("World", WHITE)
    }
}