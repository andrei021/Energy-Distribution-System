package players;

import game.Contract;
import java.util.List;

public final class Consumer extends Player {

    private final int monthlyIncome;
    private Distributor debtDistributor;
    private Distributor currentDistributor;
    private Contract currentContract;
    private boolean hasDebt;
    private int debt;

    public Consumer(final int id, final int budget,
                    final int monthlyIncome) {
        super(id, budget);
        this.monthlyIncome = monthlyIncome;
        this.debtDistributor = null;
        this.currentDistributor = null;
        this.currentContract = null;
        this.hasDebt = false;
        this.debt = 0;
    }

    @Override
    public void payTaxes() {
        if (this.isBankrupt()) {
            return;
        }

        if (hasDebt) {
            if (!this.debtDistributor.isBankrupt()) {
                this.debt = (int) Math.round(Math.floor(1.2 * this.debt));

                if (this.getBudget() >= this.debt + this.currentContract.getPrice()) {
                    this.debtDistributor.receiveIncome(this.debt);
                    this.withdrawAmount(this.debt);
                    this.hasDebt = false;
                } else {
                    this.setBankrupt(true);
                    this.currentContract.decreaseNumOfMonths();
                    return;
                }
            } else {
                this.hasDebt = false;
            }
        }

        if (this.getBudget() >= this.currentContract.getPrice()) {
            this.currentDistributor.receiveIncome(this.currentContract.getPrice());
            this.withdrawAmount(this.currentContract.getPrice());
        } else {
            this.debtDistributor = this.currentDistributor;
            this.debt = this.currentContract.getPrice();
            this.hasDebt = true;
        }

        this.currentContract.decreaseNumOfMonths();
    }

    @Override
    public int calculateIncome() {
        return this.monthlyIncome;
    }

    /**
     * Searches for the best contract this player can get from all the
     * available distributors and signs it
     */
    public void signBestContract(final List<Distributor> distributors) {
        if (this.currentContract == null
                || (!this.isBankrupt() && this.currentContract.getRemainedContractMonths() == 0)) {

            int price = Integer.MAX_VALUE;
            Distributor bestDistributor = null;

            for (Distributor distributor : distributors) {
                if (!distributor.isBankrupt()) {
                    if (distributor.getPrice() < price) {
                        bestDistributor = distributor;
                        price = distributor.getPrice();
                    }
                }
            }

            if (bestDistributor == null) {
                return;
            }

            if (this.currentDistributor != null) {
                this.currentDistributor.removeContract(this.currentContract);
            }

            this.currentContract = new Contract(
                    this.getId(),
                    bestDistributor.getPrice(),
                    bestDistributor.getContractLength());

            bestDistributor.addContract(this.currentContract);
            this.currentDistributor = bestDistributor;
        }
    }

    public int getMonthlyIncome() {
        return monthlyIncome;
    }

    public Distributor getCurrentDistributor() {
        return currentDistributor;
    }

    public Contract getCurrentContract() {
        return currentContract;
    }
}
