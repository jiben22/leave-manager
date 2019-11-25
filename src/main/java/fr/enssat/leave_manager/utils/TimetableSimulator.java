package fr.enssat.leave_manager.utils;

import java.time.LocalDateTime;
import java.util.Random;

public class TimetableSimulator {
    private static double[] frequencies = {1.0,2.0,3.0,4.0,5.0,6.0,7.0,8.0,9.0,10.0,11.0,12.0,13.0,14.0,15.0,16.0,17.0,18.0,19.0,20.0,21.0,22.0,23.0,24.0,25.0,26.0,27.0,28.0,29.0,30.0};
    private static int maxVal = 100;
    private static int mapsize = 366;
    private static Random random = new Random();

    public static boolean isAvailable(String eid, LocalDateTime start_date, LocalDateTime end_date) {
        //EMPLOYEE-157314099170606-1920
        String[] parts = eid.split("-");
        long id = Long.valueOf(parts[1]) *1000 + Long.valueOf(parts[2]);
        random.setSeed(id); // to always have sale timetable per employee

        int start = start_date.toLocalDate().getDayOfYear(); // from 1 to 366
        int end = end_date.toLocalDate().getDayOfYear();
        return inner(start, end);
    }

    private static boolean inner(int start, int end) {
        double[] timetable = sumOfNoises();

        for (int day=start ; day<=end ; day++)
            if (Math.abs(timetable[day-1]) >= maxVal)
                return false;
        return true;
    }

    private static double[] sumOfNoises() {
        double[] amplitudes = frequencies;
        double[][] noises = new double[frequencies.length][mapsize];
        for (int i=0 ; i<frequencies.length ; i++)
            noises[i] = noise(frequencies[i]);

        return weightedSum(amplitudes, noises);
    }

    private static double[] weightedSum(double[] amplitudes, double[][] noises) {
        double[] output = new double[mapsize];
        for (int i=0 ; i<mapsize ; i++) output[i] = 0.0;

        for (int k=0 ; k<noises.length ; k++)
            for (int x=0; x<mapsize ; x++)
                output[x] += amplitudes[k]
                        * noises[k][x];

        return output;
    }

    private static double[] noise(double freq) {
        double phase = Math.PI*2 *2 *random.nextDouble();
        double[] noise = new double[mapsize];
        for (int i=0 ; i<mapsize ; i++)
            noise[i] = Math.sin(Math.PI *2 * freq*i/mapsize + phase);
        return noise;
    }
}
