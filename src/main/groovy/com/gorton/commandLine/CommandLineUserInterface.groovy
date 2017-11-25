package com.gorton.commandLine

import com.gorton.config.UserInterface

class CommandLineUserInterface implements UserInterface{
    @Override
    void display(String msg){
        println msg
    }
}