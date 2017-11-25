package com.gorton.errors

class InvalidColumnException extends RuntimeException{
    InvalidColumnException(int column){
        super("Invalid column: $column")
    }
}
