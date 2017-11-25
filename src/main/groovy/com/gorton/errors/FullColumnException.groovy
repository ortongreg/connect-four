package com.gorton.errors

class FullColumnException extends RuntimeException {
    FullColumnException(int column){
        super("Column $column is full")
    }
}
