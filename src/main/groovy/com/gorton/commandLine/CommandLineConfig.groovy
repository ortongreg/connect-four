package com.gorton.commandLine

import com.gorton.config.Config
import com.gorton.config.UserInterface

class CommandLineConfig implements Config {
    @Override
    UserInterface userInterface(){ new CommandLineUserInterface() }
}