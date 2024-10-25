import java.util.Comparator;

public class PrivateEnterpriseComparators {
    public static Comparator<PrivateEnterprise> byName() {
        return Comparator.comparing(PrivateEnterprise::getName);
    }

    public static Comparator<PrivateEnterprise> byOwner() {
        return Comparator.comparing(PrivateEnterprise::getOwner);
    }

    public static Comparator<PrivateEnterprise> byActivityType() {
        return Comparator.comparing(PrivateEnterprise::getActivityType);
    }

    public static Comparator<PrivateEnterprise> byTaxAmount() {
        return Comparator.comparingDouble(PrivateEnterprise::getTaxAmount);
    }

    public static Comparator<PrivateEnterprise> byAnnualIncome() {
        return Comparator.comparingDouble(PrivateEnterprise::getAnnualIncome);
    }

    public static Comparator<PrivateEnterprise> byMultipleCriteria() {
        return byName().thenComparing(byOwner()).thenComparing(byAnnualIncome());
    }
}