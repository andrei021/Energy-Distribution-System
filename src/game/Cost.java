package game;

public final class Cost {

    private final int id;
    private int infrastructureCost;
    private int productionCost;

    public Cost(final int id, final int infrastructureCost) {
        this.id = id;
        this.infrastructureCost = infrastructureCost;
    }

    public int getInfrastructureCost() {
        return infrastructureCost;
    }

    public int getTotalCost() {
        return this.infrastructureCost + this.productionCost;
    }

    public void setInfrastructureCost(final int infrastructureCost) {
        this.infrastructureCost = infrastructureCost;
    }

    public int getId() {
        return id;
    }

    public int getProductionCost() {
        return productionCost;
    }

    public void setProductionCost(int productionCost) {
        this.productionCost = productionCost;
    }

    public void addToProductionCost(int amount) {
        this.productionCost += amount;
    }
}
