package players;

import game.Cost;
import game.MonthStats;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Producer extends Player {

    private final String energyType;
    private final int maxDistributors;
    private double priceKW;
    private int energyPerDistributor;
    private List<MonthStats> monthlyStats;
    private List<Distributor> distributors;

    public Producer(final int id, final String energyType, final int maxDistributors,
                    final double priceKW, final int energyPerDistributor) {
        super(id, 0);
        this.energyType = energyType;
        this.maxDistributors = maxDistributors;
        this.priceKW = priceKW;
        this.energyPerDistributor = energyPerDistributor;
        this.monthlyStats = new ArrayList<>();
        this.distributors = new ArrayList<>();
    }

    /**
     * Adds a new month that corresponds to current round of the game to
     * monthlyStats
     */
    public void addNewMonth() {
        this.monthlyStats.add(new MonthStats(this.monthlyStats.size() + 1));
    }

    /**
     * Adds a distributor to the list and marks it as an observer
     */
    public void addDistributor(Distributor distributor) {
        this.addObserver(distributor);
        this.distributors.add(distributor);
    }

    /**
     * Removes the distributor from its lists (distributors and observers)
     */
    public void removeDistributor(Distributor distributor) {
        this.deleteObserver(distributor);
        this.distributors.remove(distributor);
    }

    /**
     * Adds to the month that has just finished the distributors whom this
     * producer supplied
     */
    public void makeMonthStats() {
        MonthStats currentMonth = this.monthlyStats.get(this.monthlyStats.size() - 1);

        for (Distributor distributor : this.distributors) {
            currentMonth.addDistributorId(distributor.getId());
        }

        Collections.sort(currentMonth.getDistributorsIds());
    }

    /**
     * @return true if this producer has at least one place vacant in order to
     * take in another distributor
     */
    public boolean hasVacantPlace() {
        if (maxDistributors - countObservers() > 0) {
            return true;
        }

        return false;
    }

    /**
     * @return true if this producer is GREEN
     */
    public boolean isGreen() {
        if (energyType.equals("COAL") || energyType.equals("NUCLEAR")) {
            return false;
        }

        return true;
    }

    @Override
    public void payTaxes() {
    }

    @Override
    public int calculateIncome() {
        return 0;
    }

    /**
     * Updates this producer's energyPerDistributor
     */
    public void updateCost(final Cost newCost) {
        this.energyPerDistributor = newCost.getInfrastructureCost();
        setChanged();
        notifyObservers();
    }

    public String getEnergyType() {
        return energyType;
    }

    public int getMaxDistributors() {
        return maxDistributors;
    }

    public double getPriceKW() {
        return priceKW;
    }

    public int getEnergyPerDistributor() {
        return energyPerDistributor;
    }

    public void setEnergyPerDistributor(final int energyPerDistributor) {
        this.energyPerDistributor = energyPerDistributor;
    }

    public List<MonthStats> getMonthlyStats() {
        return monthlyStats;
    }

    @Override
    public String toString() {
        return "producer id: " + super.getId()
                + "\n maxDistributors: " + this.maxDistributors
                + "\n priceKW: " + this.priceKW
                + "\n energyType: " + this.energyType
                + "\n energyPerDistributor: " + this.energyPerDistributor
                + "\n monthlyStats: " + this.monthlyStats;
    }
}
