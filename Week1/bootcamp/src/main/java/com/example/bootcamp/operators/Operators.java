package com.example.bootcamp.operators;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Operators {

    public static List<Integer> map(List<Integer> list){
        return list.stream().map(number -> number*number).collect(Collectors.toList());
    }

    public static List<String> concat(List<String> first,List<String> second){
//        we can use also addAll method but I prefer Streams
        return Stream.concat(first.stream(),second.stream()).collect(Collectors.toList());
    }

    public static List<Integer> remove(List<Integer> list,List<Integer> remove){
        return list.stream().filter(i -> !remove.contains(i)).collect(Collectors.toList());
    }

    public static List<Integer> distinct(List<Integer> list){
        return list.stream().distinct().collect(Collectors.toList());
    }

    public static List<Integer> slice(List<Integer> list,int fromIndex, int toIndex){
        return list.subList(fromIndex,toIndex);
    }

    public static List<Integer> filter(List<Integer> list,int filter){
        return list.stream().filter(number -> number == filter).collect(Collectors.toList());
    }

    public static List<String> flatMap(List<String> list){
        return list.stream().flatMap(str -> Stream.of(str.split(" "))).collect(Collectors.toList());
    }

    public static List<Integer> flatten(List<List<Integer>> list){
        return list.stream().flatMap(Collection::stream).collect(Collectors.toList());
    }

    public static List<String> reduce(List<String> list){
        return Arrays.asList(list.stream().reduce("",(str, element) -> str + element));
    }

    public static List<Integer> sort(List<Integer> list){
        return list.stream().sorted().collect(Collectors.toList());
    }

    public static List<Integer> union(List<Integer> first,List<Integer> second){
        return Stream.concat(first.stream(),second.stream()).distinct().collect(Collectors.toList());
    }

    public static Map<String,Long> groupBy(List<Book> list){
        return list.stream().collect(Collectors.groupingBy(Book::getName,Collectors.counting()));
    }

}
