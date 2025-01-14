package pl.maciek.map;

public class Main {

    public static void main(String[] args) {

//        System.out.println(index("key1", 10));
//        System.out.println(index("key2", 10));
//        System.out.println(index("key3", 10));
//        System.out.println(index("key4", 10));
//        System.out.println(index("key5", 10));
//        System.out.println(index("key6", 10));
//        System.out.println(index("key7", 10));
//        System.out.println(index("key8", 10));
//        System.out.println(index("key9", 10));
//        System.out.println(index("key10", 10));

        System.out.println(index(new Object(), 10));
        System.out.println(index(new Object(), 10));
    }

    private static int index(Object key, int capacity) {
        int hash = key == null ? 0 : Math.abs(key.hashCode());
        return hash % capacity; // capacity to rozmiar tablicy
    }
}
