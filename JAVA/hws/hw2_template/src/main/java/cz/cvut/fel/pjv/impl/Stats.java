package cz.cvut.fel.pjv.impl;

import cz.cvut.fel.pjv.StatsInterface;

public class  Stats implements StatsInterface {
    private double[] numbers;
    private int count;

    public Stats(){
        this.numbers = new double[10];
        this.count = 0;
    }

    @Override
    public void addNumber(double number) {

            if (count == 10)
            {
                System.out.println(getFormattedStatistics());
                count = 0;
            };

            numbers[count] = number;
            count++;
    }

    @Override
    public double getAverage() {
        double sum = 0;
       for (int i = 0; i < count; i++) {
           sum+= numbers[i];
       }
       return sum/count;
    }

    @Override
    public double getStandardDeviation() {
       double avg = getAverage();
       double sum = 0;

       for (int i = 0; i < count; i++) {
           sum += (numbers[i]- avg) * (numbers[i] - avg);
       }
            return Math.sqrt(sum/(count));
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public String getFormattedStatistics() {

        return String.format("%2d %.3f %.3f", getCount(), getAverage(), getStandardDeviation());
    }
}
