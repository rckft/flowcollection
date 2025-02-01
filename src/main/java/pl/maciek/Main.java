package pl.maciek;

import pl.maciek.map.MyBinarySearchTreeMap;

import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

//        var javaMap = new TreeMap<Integer, String>();

//        javaMap.put(null, "a");



        var map = new MyBinarySearchTreeMap<Integer, String>();

        //lvl 0
        map.put(82, "a");

        //lvl 1
        map.put(15, "a");
        map.put(93, "a");

        //lvl 2
        map.put(67, "a");
        map.put(86, "a");

        //lvl 3
        map.put(30, "a");
        map.put(72, "a");

        //lvl 4
        map.put(17, "a");
        map.put(66, "a");
        map.put(80, "a");

        //lvl 5
        map.put(29, "a");
        map.put(54, "a");
        map.put(75, "a");
        map.put(81, "a");

        //lvl 6
        map.put(51, "a");
        map.put(57, "a");
        map.put(76, "a");

        //lvl 7
        map.put(47, "a");
        map.put(53, "a");
        map.put(55, "a");
        map.put(58, "a");
        map.put(79, "a");

        //lvl 8
        map.put(52, "a");

//        System.out.println(map);

        map.remove(67);

//        System.out.println(map);

    }
}