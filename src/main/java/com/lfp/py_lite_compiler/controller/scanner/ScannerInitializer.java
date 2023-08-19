package com.lfp.py_lite_compiler.controller.scanner;

/**
 *The purpose of this class is to create an instance of type Scanner.
  This receives the source code and regardless of the form in which it initially comes,
  it converts it to a character array that will later be transferred to the Scanner
  so that it can analyze it as a character stream.
 */
public class ScannerInitializer {
    public Scanner getScannerFromContent(String content){
        return new Scanner(content.toCharArray());
    }

    public Scanner getScannerFromFile(String path){
        //pending implementation
        return new Scanner(new char[1]);
    }
}
