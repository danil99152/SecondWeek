package com.company;

import com.interfaces.Calculation;

import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String address = "//home//danilkomyshev//IdeaProjects//SecondWeek//src//com//company//Input.txt";
        File f = new File(address);
        LinkedList<String> st = new LinkedList<>();

        try {
            FileReader fr = new FileReader(f);
            Scanner scan = new Scanner(fr);

            while (scan.hasNext()) {
                st.add(scan.next());
            }
            scan.close();
        } catch (FileNotFoundException e) {
            System.err.println(e);
        }

        Calculation score = new Score(address, st);
    }
}