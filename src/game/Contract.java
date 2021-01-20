package game;

public final class Contract {

    private int consumerId;
    private int price;
    private int remainedContractMonths;

    public Contract(final int consumerId, final int price,
                    final int remainedContractMonths) {
        this.consumerId = consumerId;
        this.price = price;
        this.remainedContractMonths = remainedContractMonths;
    }

    public int getConsumerId() {
        return consumerId;
    }

    public int getPrice() {
        return price;
    }

    public int getRemainedContractMonths() {
        return remainedContractMonths;
    }

    /**
     * Decrements the number of remained contract months whenever it's called
     */
    public void decreaseNumOfMonths() {
        this.remainedContractMonths--;
    }

    public void setRemainedContractMonths(final int remainedContractMonths) {
        this.remainedContractMonths = remainedContractMonths;
    }

    @Override
    public String toString() {
        return "Contract{"
                + "consumerId=" + consumerId
                + ", price=" + price
                + ", remainedContractMonths="
                + remainedContractMonths + '}'
                + "\n";
    }
}
