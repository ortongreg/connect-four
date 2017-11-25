package com.gorton

import com.gorton.commandLine.*
import com.gorton.config.*

class App {

    static Config config = new CommandLineConfig()

    static void main(String[] args){
        UserInterface ui = config.userInterface()

        ui.display("Hello")
    }
}