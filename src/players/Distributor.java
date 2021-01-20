package players;

import game.Contract;
import game.Cost;
import game.Game;
import strategies.EnergyStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public final class Distributor extends Player implements Observer {

    private int price;
    private Cost cost;
    private List<Contract> contracts;
    private int contractLength;
    private final int energyNeededKW;
    private int currentEnergy;
    private final String producerStrategy;
    private boolean hasToChooseProducers;
    private List<Producer> producers;

    public Distributor(final int id, final int budget, final int contractLength,
                       final Cost cost, final int energyNeededKW, final String producerStrategy) {
        super(id, budget);
        this.price = 0;
        this.cost = cost;
        this.cost.setProductionCost(0);
        this.contractLength = contractLength;
        this.contracts = new ArrayList<>();
        this.energyNeededKW = energyNeededKW;
        this.currentEnergy = 0;
        this.producerStrategy = producerStrategy;
        this.hasToChooseProducers = true;
        this.producers = new ArrayList<>();
    }

    private int calculateProfit() {
        return (int) Math.round(Math.floor(0.2 * this.cost.getProductionCost()));
    }

    /**
     * Method finds proper producers for this distributor based on his
     * strategy
     */
    public void chooseProducers(final EnergyStrategy strategy) {
        if (strategy.getName().equals(this.producerStrategy)) {
            strategy.chooseProducers(this);
        }
    }

    /**
     * Adds a producer to this distributor's list of producers
     * Takes into account the enery that the producer provides
     */
    public void addProducer(final Producer producer) {
        this.currentEnergy += producer.getEnergyPerDistributor();
        this.producers.add(producer);
        producer.addDistributor(this);
    }

    /**
     * Checks if this distributor needs more energy or not
     */
    public boolean hasEnoughEnergy() {
        if (currentEnergy >= energyNeededKW) {
            return true;
        }

        return false;
    }

    @Override
    public void update(Observable o, Object arg) {
        this.hasToChooseProducers = true;
    }

    /**
     * Removes all producers from this distributor's list after one of them
     * changed its energyPerDistributor
     */
    public void clearProducers() {
        for (Producer producer : this.producers) {
            producer.removeDistributor(this);
        }

        this.producers.clear();
        this.cost.setProductionCost(0);
        this.currentEnergy = 0;
        this.hasToChooseProducers = false;
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

            for (Producer producer : this.producers) {
                producer.removeDistributor(this);
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

        updatePorudctionCost();

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
     * Calculates the production cost with current data
     */
    public void updatePorudctionCost() {
        double sum = 0.0;
        for (Producer producer : this.producers) {
            sum += producer.getEnergyPerDistributor() * producer.getPriceKW();
        }
        this.cost.setProductionCost((int) Math.round(Math.floor(sum / 10)));
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

    public String getStrategy() {
        return producerStrategy;
    }

    public int getCurrentEnergy() {
        return currentEnergy;
    }

    /**
     * @return true if this distributor has to choose new producers
     */
    public boolean hasToChooseProducers() {
        return hasToChooseProducers;
    }

    public void setHasToChooseProducers(boolean hasToChooseProducers) {
        this.hasToChooseProducers = hasToChooseProducers;
    }

    @Override
    public String toString() {
        return "distributor id: " + this.getId()
                + "\n energyNeededKW:" + this.energyNeededKW
                + "\n contractCost: " + this.price
                + "\n budget: " + this.getBudget()
                + "\n producerStrategy: " + this.producerStrategy
                + "\n isBankrupt: " + this.isBankrupt()
                + "\n contracts: " + this.contracts;
    }
}
