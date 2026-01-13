package utils;

import core.*;
import java.io.*;
import java.util.Random;

/**
 * Experimental runner.
 * Generates random input files and compares Greedy vs Greedy-Decreasing.
 */
public class ExperimentRunner {

    private static final int CAPACITY = 1_000_000;
    private static final int RUNS = 10;

    public static void main(String[] args) throws IOException {

        if (args.length < 2) {
            System.out.println("Usage: java utils.ExperimentRunner <output_dir> <N>");
            return;
        }

        String outputDir = args[0];
        int N = Integer.parseInt(args[1]);

        double avgDisksGreedy = 0;
        double avgDisksGreedyDecreasing = 0;

        Random rnd = new Random();

        for (int run = 1; run <= RUNS; run++) {

            String filename = N + "_" + run + ".txt";
            File file = new File(outputDir + File.separator + filename);

            generateRandomInput(file, N, rnd);

            int[] data = readInput(file);

            // ---------- Greedy ----------
            int disksGreedy = runGreedy(data, false);
            avgDisksGreedy += disksGreedy;

            // ---------- Greedy Decreasing ----------
            int[] sorted = Sort.mergesort(data.clone(), 0, data.length - 1);
            int disksGreedyDec = runGreedy(sorted, true);
            avgDisksGreedyDecreasing += disksGreedyDec;

            System.out.println("Run " + run + " completed");
        }

        System.out.println();
        System.out.println("Average disks (Greedy): " + avgDisksGreedy / RUNS);
        System.out.println("Average disks (Greedy-Decreasing): " + avgDisksGreedyDecreasing / RUNS);
    }

    // ----------------------------------------------------

    private static void generateRandomInput(File file, int N, Random rnd) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (int i = 0; i < N; i++) {
                writer.write(Integer.toString(rnd.nextInt(CAPACITY + 1)));
                writer.newLine();
            }
        }
    }

    private static int[] readInput(File file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));

        int[] data = new int[0];
        String line;

        while ((line = br.readLine()) != null) {
            int value = Integer.parseInt(line);
            if (value > CAPACITY) {
                throw new IllegalArgumentException("Folder size exceeds disk capacity");
            }
            int[] tmp = new int[data.length + 1];
            System.arraycopy(data, 0, tmp, 0, data.length);
            tmp[data.length] = value;
            data = tmp;
        }

        br.close();
        return data;
    }

    private static int runGreedy(int[] input, boolean decreasing) {

        MaxPQ pq = new MaxPQ();
        int diskId = 0;

        for (int size : input) {
            if (pq.size() > 0 && pq.peek().getFreeSpace() >= size) {
                Disk d = pq.getMax();
                d.addFolder(size);
                pq.add(d);
            } else {
                Disk d = new Disk(diskId++);
                d.addFolder(size);
                pq.add(d);
            }
        }

        return pq.size();
    }
}
