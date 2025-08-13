package cz.cvut.fel.pjv.impl;

import cz.cvut.fel.pjv.TextIO;

public class Lab02 {
    public void main(String[] args)
    {
        Stats stats = new Stats();
        TextIO textIO = new TextIO();
        int cnt = 0;
    while (true){
        String line = textIO.getLine();
        cnt ++;
        if (line == "") {
            if (stats.getCount() > 0)
                System.out.println(stats.getFormattedStatistics());
            System.err.println("End of input detected!");
            break;
        }
        if (!TextIO.isDouble(line)&& !TextIO.isInteger(line))
        {
            System.err.println("A number has not been parsed from line " + cnt);
        }
        else{
            stats.addNumber(Double.parseDouble(line));
        }
    }
    }
}