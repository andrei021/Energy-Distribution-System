package game;

import java.util.ArrayList;
import java.util.List;

public class MonthStats {

    private int month;
    private List<Integer> distributorsIds;

    public MonthStats(int month) {
        this.month = month;
        this.distributorsIds = new ArrayList<>();
    }

    public void addDistributorId(int id) {
        this.distributorsIds.add(id);
    }

    public int getMonth() {
        return month;
    }

    public List<Integer> getDistributorsIds() {
        return distributorsIds;
    }

    @Override
    public String toString() {
        return "MonthStats{" +
                "month=" + month +
                ", distributorsIds=" + distributorsIds +
                '}' + "\n";
    }
}
