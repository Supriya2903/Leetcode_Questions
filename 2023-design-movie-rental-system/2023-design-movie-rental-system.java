import java.util.*;

class MovieRentingSystem {
    private Map<Integer, TreeSet<Pair>> available;
    private TreeSet<Triple> rented;
    private Map<String, Integer> priceMap;

    public MovieRentingSystem(int n, int[][] entries) {
        available = new HashMap<>();
        rented = new TreeSet<>();
        priceMap = new HashMap<>();

        for (int[] e : entries) {
            int shop = e[0], movie = e[1], price = e[2];
            priceMap.put(key(shop, movie), price);
            available.computeIfAbsent(movie, k -> new TreeSet<>())
                     .add(new Pair(price, shop));
        }
    }

    public List<Integer> search(int movie) {
        List<Integer> res = new ArrayList<>();
        if (!available.containsKey(movie)) return res;
        Iterator<Pair> it = available.get(movie).iterator();
        for (int i = 0; i < 5 && it.hasNext(); i++) {
            res.add(it.next().shop);
        }
        return res;
    }

    public void rent(int shop, int movie) {
        int price = priceMap.get(key(shop, movie));
        Pair p = new Pair(price, shop);
        available.get(movie).remove(p);
        rented.add(new Triple(price, shop, movie));
    }

    public void drop(int shop, int movie) {
        int price = priceMap.get(key(shop, movie));
        rented.remove(new Triple(price, shop, movie));
        available.computeIfAbsent(movie, k -> new TreeSet<>())
                 .add(new Pair(price, shop));
    }

    public List<List<Integer>> report() {
        List<List<Integer>> res = new ArrayList<>();
        Iterator<Triple> it = rented.iterator();
        for (int i = 0; i < 5 && it.hasNext(); i++) {
            Triple t = it.next();
            res.add(Arrays.asList(t.shop, t.movie));
        }
        return res;
    }

    private String key(int shop, int movie) {
        return shop + "#" + movie;
    }

    // Helper classes
    private static class Pair implements Comparable<Pair> {
        int price, shop;
        Pair(int p, int s) { price = p; shop = s; }
        public int compareTo(Pair o) {
            if (price != o.price) return price - o.price;
            return shop - o.shop;
        }
        public boolean equals(Object o) {
            if (!(o instanceof Pair)) return false;
            Pair p = (Pair) o;
            return price == p.price && shop == p.shop;
        }
        public int hashCode() { return Objects.hash(price, shop); }
    }

    private static class Triple implements Comparable<Triple> {
        int price, shop, movie;
        Triple(int p, int s, int m) { price = p; shop = s; movie = m; }
        public int compareTo(Triple o) {
            if (price != o.price) return price - o.price;
            if (shop != o.shop) return shop - o.shop;
            return movie - o.movie;
        }
        public boolean equals(Object o) {
            if (!(o instanceof Triple)) return false;
            Triple t = (Triple) o;
            return price == t.price && shop == t.shop && movie == t.movie;
        }
        public int hashCode() { return Objects.hash(price, shop, movie); }
    }
}


/**
 * Your MovieRentingSystem object will be instantiated and called as such:
 * MovieRentingSystem obj = new MovieRentingSystem(n, entries);
 * List<Integer> param_1 = obj.search(movie);
 * obj.rent(shop,movie);
 * obj.drop(shop,movie);
 * List<List<Integer>> param_4 = obj.report();
 */