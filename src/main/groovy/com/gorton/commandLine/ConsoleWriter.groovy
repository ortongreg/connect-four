package com.gorton.commandLine

class ConsoleWriter {

    void println(String msg){
        System.out.println(msg)
    }
    void print(String msg){
        System.out.print(msg)
    }
    String input(String msg){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in))
        print msg
        br.readLine()
    }

    void quit() {
        System.exit(0)
    }
}
