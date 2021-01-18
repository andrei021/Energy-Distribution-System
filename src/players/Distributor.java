package players;

import game.Contract;
import game.Cost;
import game.Game;

import java.util.ArrayList;
import java.util.List;

public final class Distributor extends Player {

    private int price;
    private Cost cost;
    private List<Contract> contracts;
    private int contractLength;
    private final int energyNeededKW;
    private final String producerStrategy;
    private boolean hasToChooseProducer;

    public Distributor(final int id, final int budget, final int contractLength,
                       final Cost cost, final int energyNeededKW, final String producerStrategy) {
        super(id, budget);
        this.price = 0;
        this.cost = cost;
        this.contractLength = contractLength;
        this.contracts = new ArrayList<>();
        this.energyNeededKW = energyNeededKW;
        this.producerStrategy = producerStrategy;
        this.hasToChooseProducer = true;
    }

    private int calculateProfit() {
        return (int) Math.round(Math.floor(0.2 * this.cost.getProductionCost()));
    }

    @Override
    public void payTaxes() {
        this.withdrawAmount(
                this.cost.getInfrastructureCost()
                + this.cost.getProductionCost() * this.contracts.size());

        if (this.getBudget() < 0) {
            this.setBankrupt(true);
            Game.decreaseNumOfDistributorsInGame();

            if (Game.getNumOfDistributorsInGame() == 0) {
                return;
            }

            for (Contract contract : this.contracts) {
                contract.setRemainedContractMonths(0);
            }

            this.contracts.clear();
        }
    }

    @Override
    public int calculateIncome() {
        return 0;
    }

    /**
     * Updates the price the company has to offer
     */
    public void updatePrice() {
        if (this.isBankrupt()) {
            return;
        }

        // TODO calculate production cost

        if (this.contracts.isEmpty()) {
            this.price = this.cost.getTotalCost() + calculateProfit();
            return;
        }

        this.price = (int) Math.round(Math.floor(
                (double) this.cost.getInfrastructureCost() / this.contracts.size())
                + this.cost.getProductionCost()
                + calculateProfit());
    }

    /**
     * Updates the costs in case an update occurs during the game
     */
    public void updateCost(final Cost newCost) {
        this.cost = newCost;
    }

    /**
     * Adds a given contract to contracts list
     */
    public void addContract(final Contract newContract) {
        this.contracts.add(newContract);
    }

    /**
     * Removes the contract given as param from the list if it exists
     */
    public void removeContract(final Contract contract) {
        int index = this.contracts.indexOf(contract);

        if (index != -1) {
            this.contracts.remove(index);
        }
    }

    public int getPrice() {
        return price;
    }

    public List<Contract> getContracts() {
        return contracts;
    }

    public int getContractLength() {
        return contractLength;
    }

    public int getEnergyNeededKW() {
        return energyNeededKW;
    }

    public String getProducerStrategy() {
        return producerStrategy;
    }
}
