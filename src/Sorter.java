import java.util.*;

public class Sorter {

        // Quick sort by distance
        public static void quickSortByDistance(List<Place> arr) {
            if (arr == null || arr.size() <= 1) return;
            quickSortByDistance(arr, 0, arr.size() - 1);
        }

        private static void quickSortByDistance(List<Place> arr, int low, int high) {
            if (low < high) {
                int p = partition(arr, low, high);
                quickSortByDistance(arr, low, p - 1);
                quickSortByDistance(arr, p + 1, high);
            }
        }

        private static int partition(List<Place> arr, int low, int high) {
            double pivot = arr.get(high).getDistanceFromHotel();
            int i = low - 1;
            for (int j = low; j < high; j++) {
                if (arr.get(j).getDistanceFromHotel() <= pivot) {
                    i++;
                    Place tmp = arr.get(i); arr.set(i, arr.get(j)); arr.set(j, tmp);
                }
            }
            Place tmp = arr.get(i + 1); arr.set(i + 1, arr.get(high)); arr.set(high, tmp);
            return i + 1;
        }

        // Merge sort by price
        public static List<Place> mergeSortByPrice(List<Place> input) {
            if (input.size() <= 1) return new ArrayList<>(input);
            int mid = input.size() / 2;
            List<Place> left = mergeSortByPrice(input.subList(0, mid));
            List<Place> right = mergeSortByPrice(input.subList(mid, input.size()));
            return merge(left, right);
        }

        private static List<Place> merge(List<Place> left, List<Place> right) {
            List<Place> res = new ArrayList<>();
            int i = 0, j = 0;
            while (i < left.size() && j < right.size()) {
                if (left.get(i).getPrice() <= right.get(j).getPrice()) res.add(left.get(i++));
                else res.add(right.get(j++));
            }
            while (i < left.size()) res.add(left.get(i++));
            while (j < right.size()) res.add(right.get(j++));
            return res;
        }
}
