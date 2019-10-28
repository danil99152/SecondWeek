package com.company;

import com.interfaces.Calculation;

import java.io.*;
import java.util.LinkedList;

class Score implements Calculation {
    private String address;

    Score(String address, LinkedList<String> st) {
        this.address = address;
        result(st);
    }

    private void result(LinkedList<String> st) {
        LinkedList<String> brackets = new LinkedList<>();
        if (!st.contains("(") && !st.contains(")"))
            setInOut(address, processOperator(st));
        else {
            for (var i = 0; i < st.size(); i++){
                if (st.get(i).equals("(")){
                    while (!st.get(i).equals(")")){
                        st.remove(i);
                        if (!st.get(i).equals(")"))
                            brackets.add(st.get(i));
                        else {
                            st.remove(i);
                            break;
                        }
                    }
                    st.add(i, String.valueOf(processOperator(brackets)));
                    brackets.clear();
                }
            }
            setInOut(address, processOperator(st));
        }
    }

    public double processOperator(LinkedList<String> st) {
        LinkedList<Double> result = new LinkedList<>();
        if (!st.contains("*") && !st.contains("/")) {
            return plus(result, st);
        }
        else {
            for (var i = 0; i < st.size(); i++) {
                if (isOperator(st.get(i))) {
                    switch (st.get(i)) {
                        case "*":
                            st.add(i-1, String.valueOf(Double.parseDouble(st.get(i-1)) * Double.parseDouble(st.get(i+1))));
                            st.remove(i);
                            st.remove(i);
                            i = 0;
                            break;
                        case "/":
                            st.add(i-1, String.valueOf(Double.parseDouble(st.get(i-1)) / Double.parseDouble(st.get(i+1))));
                            st.remove(i);
                            st.remove(i);
                            i = 0;
                            break;
                    }
                }
            }
            return plus(result, st);
        }
    }

    public double plus(LinkedList<Double> result, LinkedList<String> st) {
        result.add(0, Double.parseDouble(st.get(0)));
        for (var i = 0; i < st.size(); i++) {
            if (isOperator(st.get(i))) {
                switch (st.get(i)) {
                    case "+":
                        result.add(0, result.get(0) + Double.parseDouble(st.get(i + 1)));
                        break;
                    case "-":
                        result.add(0, result.get(0) - Double.parseDouble(st.get(i + 1)));
                        break;
                }
            }
        }
        return  result.get(0);
    }

    public boolean isOperator(String character) {
        return (character.equals("+")) || (character.equals("*")) || (character.equals("-")) || (character.equals("/"));
    }

    private void setInOut(String fileName, double result) {
        try {
            PrintWriter pw = new PrintWriter(
                    new BufferedWriter(
                            new FileWriter(
                                    new File(fileName), true
                            )
                    )
            );
            pw.println("\n" + result);
            pw.close();
            System.out.println("Wrote!");
        } catch (IOException e) {
            System.err.println("Error: " + e);
            System.exit(1);
        }
    }
}
