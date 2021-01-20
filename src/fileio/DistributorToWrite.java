package fileio;

import game.Contract;

import java.util.List;

public final class DistributorToWrite {

    private final int id;
    private final int energyNeededKW;
    private final int contractCost;
    private final int budget;
    private final String producerStrategy;
    private final boolean isBankrupt;
    private final List<Contract> contracts;

    public DistributorToWrite(final int id, final int energyNeededKW, final int contractCost,
                              final int budget, final String producerStrategy,
                              final boolean isBankrupt, final List<Contract> contracts) {
        this.id = id;
        this.energyNeededKW = energyNeededKW;
        this.contractCost = contractCost;
        this.budget = budget;
        this.producerStrategy = producerStrategy;
        this.isBankrupt = isBankrupt;
        this.contracts = contracts;
    }

    public int getId() {
        return id;
    }

    public int getBudget() {
        return budget;
    }

    public boolean getIsBankrupt() {
        return isBankrupt;
    }

    public List<Contract> getContracts() {
        return contracts;
    }

    public int getEnergyNeededKW() {
        return energyNeededKW;
    }

    public int getContractCost() {
        return contractCost;
    }

    public String getProducerStrategy() {
        return producerStrategy;
    }
}
