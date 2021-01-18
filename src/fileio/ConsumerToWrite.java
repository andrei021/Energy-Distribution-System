package fileio;

public final class ConsumerToWrite {

    private final int id;
    private final boolean isBankrupt;
    private final int budget;

    public ConsumerToWrite(final int id, final boolean isBankrupt,
                           final int budget) {
        this.id = id;
        this.isBankrupt = isBankrupt;
        this.budget = budget;
    }

    public int getId() {
        return id;
    }

    public boolean getIsBankrupt() {
        return isBankrupt;
    }

    public int getBudget() {
        return budget;
    }
}
