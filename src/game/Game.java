package game;

import fileio.ConsumerToWrite;
import fileio.DistributorToWrite;
import fileio.ObjectToWrite;
import players.Consumer;
import players.Distributor;
import players.Player;
import players.Producer;

import java.util.ArrayList;
import java.util.List;

public final class Game {

    private int numOfTurns;
    private final List<Consumer> consumers;
    private final List<Distributor> distributors;
    private final List<Producer> producers;
    private final List<MonthUpdate> updates;
    private static int numOfDistributorsInGame;

    public Game(final int numOfTurns, final List<Consumer> consumers,
                final List<Producer> producers, final List<Distributor> distributors,
                final List<MonthUpdate> updates) {
        this.numOfTurns = numOfTurns;
        this.consumers = consumers;
        this.distributors = distributors;
        this.producers = producers;
        this.updates = updates;
        setNumOfDistributorsInGame(distributors.size());
    }

    private void payTaxes(final List<? extends Player> playerList) {
        for (Player player : playerList) {
            if (!player.isBankrupt()) {
                player.payTaxes();
            }
        }
    }

    private void signBestContracts(final List<Consumer> consumersList) {
        for (Consumer consumer : consumersList) {
            if (!consumer.isBankrupt()) {
                consumer.signBestContract(this.distributors);
            }
        }
    }

    private void payPlayers(final List<? extends Player> playerList) {
        for (Player currentPlayer : playerList) {
            if (!currentPlayer.isBankrupt()) {
                currentPlayer.receiveIncome(currentPlayer.calculateIncome());
            }
        }
    }

    private void calculateContractsPrice(final List<Distributor> distributorsList) {
        for (Distributor distributor : distributorsList) {
            if (!distributor.isBankrupt()) {
                distributor.updatePrice();
            }
        }
    }

    private void update(final MonthUpdate month) {
        this.consumers.addAll(month.getNewConsumers());

        for (Cost cost : month.getNewDistributorCosts()) {
            Distributor distributor = getDistributorAfterId(cost.getId());

            if (distributor != null && !distributor.isBankrupt()) {
                distributor.updateCost(cost);
            }
        }
    }

    private Distributor getDistributorAfterId(final int id) {
        for (Distributor distributor : this.distributors) {
            if (distributor.getId() == id) {
                return distributor;
            }
        }

        return null;
    }

    private boolean isGameFinished() {
        for (Distributor distributor : this.distributors) {
            if (!distributor.isBankrupt()) {
                return false;
            }
        }

        return true;
    }

    private List<ConsumerToWrite> createConsumersOut(final List<Consumer> consumersList) {
        List<ConsumerToWrite> out = new ArrayList<>();

        for (Consumer consumer : consumersList) {
            out.add(new ConsumerToWrite(
                    consumer.getId(),
                    consumer.isBankrupt(),
                    consumer.getBudget()));
        }

        return out;
    }

    private void removeBankruptContracts(final List<Consumer> consumersList) {
        for (Consumer consumer : consumersList) {
            if (consumer.isBankrupt()) {
                consumer.getCurrentDistributor().removeContract(consumer.getCurrentContract());
            }
        }
    }

    private List<DistributorToWrite> createDistributorsOut(
            final List<Distributor> distributorsList) {

        List<DistributorToWrite> out = new ArrayList<>();

        for (Distributor distributor : distributorsList) {
            out.add(new DistributorToWrite(
                    distributor.getId(),
                    distributor.getBudget(),
                    distributor.isBankrupt(),
                    distributor.getContracts()));
        }

        return out;
    }

    private void playRound() {
        payPlayers(this.consumers);
        // TODO choose producers
        calculateContractsPrice(this.distributors);
        signBestContracts(this.consumers);
        payTaxes(this.consumers);
        payTaxes(this.distributors);
        removeBankruptContracts(this.consumers);
    }

    /**
     * Method that starts the game
     * @return the resulted state of the game when finished
     */
    public ObjectToWrite play() {
        playRound();

        for (int i = 0; i < numOfTurns; i++) {
            if (isGameFinished()) {
                break;
            }

            update(this.updates.get(i));
            playRound();
        }

        List<ConsumerToWrite> consumersOut = createConsumersOut(this.consumers);
        List<DistributorToWrite> distributorsOut = createDistributorsOut(this.distributors);

        return new ObjectToWrite(consumersOut, distributorsOut);
    }

    public static void setNumOfDistributorsInGame(final int numOfDistributorsInGame) {
        Game.numOfDistributorsInGame = numOfDistributorsInGame;
    }

    /**
     * Decrements the number of distributors remained in the game
     */
    public static void decreaseNumOfDistributorsInGame() {
        Game.numOfDistributorsInGame--;
    }

    public static int getNumOfDistributorsInGame() {
        return numOfDistributorsInGame;
    }
}
