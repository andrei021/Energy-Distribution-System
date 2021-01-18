package players;

public abstract class Player {

    private final int id;
    private boolean isBankrupt;
    private int budget;

    public Player(final int id, final int budget) {
        this.id = id;
        this.budget = budget;
        this.isBankrupt = false;
    }

    /**
     * Class that extends from this class must implement its special way
     * of paying taxes
     */
    public abstract void payTaxes();

    /**
     * Calculates a player's income
     */
    public abstract int calculateIncome();

    /**
     * Adds an income to the player's budget
     */
    public final void receiveIncome(final int income) {
        if (!isBankrupt) {
            this.budget += income;
        }
    }

    /**
     * Performs an withdrawal of given amount on this player's budget if it's
     * still in the game
     */
    public final void withdrawAmount(final int amount) {
        if (!isBankrupt) {
            this.budget -= amount;
        }
    }

    public final int getId() {
        return id;
    }

    public final int getBudget() {
        return budget;
    }

    public final boolean isBankrupt() {
        return isBankrupt;
    }

    /**
     * Sets the state of the player
     */
    public final void setBankrupt(final boolean bankrupt) {
        isBankrupt = bankrupt;
    }
}
