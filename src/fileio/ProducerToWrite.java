package fileio;

import game.MonthStats;

import java.util.List;

public final class ProducerToWrite {

    private final int id;
    private final int maxDistributors;
    private final double priceKW;
    private final String energyType;
    private final int energyPerDistributor;
    private final List<MonthStats> monthlyStats;

    public ProducerToWrite(int id, int maxDistributors, double priceKW, String energyType,
                           int energyPerDistributor, List<MonthStats> monthlyStats) {
        this.id = id;
        this.maxDistributors = maxDistributors;
        this.priceKW = priceKW;
        this.energyType = energyType;
        this.energyPerDistributor = energyPerDistributor;
        this.monthlyStats = monthlyStats;
    }

    public int getId() {
        return id;
    }

    public int getMaxDistributors() {
        return maxDistributors;
    }

    public double getPriceKW() {
        return priceKW;
    }

    public String getEnergyType() {
        return energyType;
    }

    public int getEnergyPerDistributor() {
        return energyPerDistributor;
    }

    public List<MonthStats> getMonthlyStats() {
        return monthlyStats;
    }
}
