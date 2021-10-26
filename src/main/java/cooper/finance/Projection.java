package cooper.finance;

import java.util.ArrayList;

public class Projection {
    public int rate;
    public int years;
    public double principal;
    public static ArrayList<Double> growthValues;

    public Projection() {
        //this.principal = principal;
        //this.rate = rate;
        //this.years = years;
        this.growthValues = new ArrayList<>();
    }

    public static ArrayList<Double> getProjection() {
        return growthValues;
    }
}
