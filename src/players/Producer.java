package players;

import java.util.ArrayList;
import java.util.List;

public final class Producer extends Player {

    private final String energyType;
    private final int maxDistributors;
    private double priceKW;
    private int energyPerDistributors;
    private List<Distributor> distributors;

    public Producer(final int id, final String energyType, final int maxDistributors,
                    final double priceKW, final int energyPerDistributors) {
        super(id, 0);
        this.energyType = energyType;
        this.maxDistributors = maxDistributors;
        this.priceKW = priceKW;
        this.energyPerDistributors = energyPerDistributors;
        this.distributors = new ArrayList<>();
    }

    @Override
    public void payTaxes() {
    }

    @Override
    public int calculateIncome() {
        return 0;
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

    public int getEnergyPerDistributors() {
        return energyPerDistributors;
    }

    public void setEnergyPerDistributors(final int energyPerDistributors) {
        this.energyPerDistributors = energyPerDistributors;
    }
}
