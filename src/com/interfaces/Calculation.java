package com.interfaces;

import java.util.LinkedList;

public interface Calculation {

    double processOperator(LinkedList<String> st);

    double plus(LinkedList<Double> result, LinkedList<String> st);

    boolean isOperator(String character);
}