package players;

import game.Cost;

public final class Producer extends Player {

    private final String energyType;
    private final int maxDistributors;
    private double priceKW;
    private int energyPerDistributor;

    public Producer(final int id, final String energyType, final int maxDistributors,
                    final double priceKW, final int energyPerDistributor) {
        super(id, 0);
        this.energyType = energyType;
        this.maxDistributors = maxDistributors;
        this.priceKW = priceKW;
        this.energyPerDistributor = energyPerDistributor;
    }

    public boolean hasVacantPlace() {
        if (maxDistributors - countObservers() > 0) {
            return true;
        }

        return false;
    }

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
}
