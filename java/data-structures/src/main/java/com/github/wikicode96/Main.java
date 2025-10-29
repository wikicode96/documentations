package com.github.wikicode96;

import java.util.ArrayList;
import java.util.LinkedList;

import static com.github.wikicode96.list.ListBenchmark.*;

public class Main {
    public static void main(String[] args) {

        int n = 1_000_000;
        int deleteCount = 1_000;

        System.out.println(add(new ArrayList<>(), n) + " ms" + "\t -> Arraylist añadiendo.");
        System.out.println(add(new LinkedList(), n) + " ms" + "\t -> LinkedList añadiendo.");

        System.out.println(deleteByIndex(new ArrayList<>(), n) + " ms" + "\t -> Arraylist eliminando por indice.");
        System.out.println(deleteByIndex(new LinkedList<>(), n) + " ms" + "\t -> LinkedList eliminando por indice.");

        System.out.println(deleteByReference(new ArrayList<>(), n) + " ms" + "\t -> Arraylist eliminando por referencia.");
        System.out.println(deleteByReference(new LinkedList<>(), n) + " ms" + "\t -> LinkedList eliminando por referencia.");

        System.out.println(deleteConsecutiveByIterator(new ArrayList<>(), n, deleteCount) + " ms" + "\t -> Arraylist eliminando por iteracion.");
        System.out.println(deleteConsecutiveByIterator(new LinkedList<>(), n, deleteCount) + " ms" + "\t -> LinkedList eliminando por iteracion.");
    }
}