package core;

public class Sort {

    private static int min(int a, int b) {
        return Math.min(a, b);
    }

    // Bottom-up merge sort (descending order)
    public static int[] mergesort(int[] a, int p, int r) {
        if (r <= p) {
            return a;
        }

        int[] aux = new int[a.length];

        for (int m = 1; m <= r - p; m = m + m) {
            for (int i = p; i <= r - m; i += m + m) {
                merge(a, aux, i, i + m - 1, min(i + m + m - 1, r));
            }
        }
        return a;
    }

    // Merge step producing DESCENDING order
    private static void merge(int[] a, int[] aux, int p, int m, int r) {
        int i, j;

        for (i = m + 1; i > p; i--) {
            aux[i - 1] = a[i - 1];
        }
        for (j = m; j < r; j++) {
            aux[j + 1] = a[r + m - j];
        }

        for (int k = p; k <= r; k++) {
            if (aux[j] > aux[i]) {
                a[k] = aux[j--];
            } else {
                a[k] = aux[i++];
            }
        }
    }
}
