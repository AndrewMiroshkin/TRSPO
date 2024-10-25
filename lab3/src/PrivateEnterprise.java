public class PrivateEnterprise {
    private String name;
    private String owner;
    private String activityType;
    private double taxAmount;
    private double annualIncome;

    public PrivateEnterprise(String name, String owner, String activityType, double taxAmount, double annualIncome) {
        this.name = name;
        this.owner = owner;
        this.activityType = activityType;
        this.taxAmount = taxAmount;
        this.annualIncome = annualIncome;
    }

    public String getName() { return name; }
    public String getOwner() { return owner; }
    public String getActivityType() { return activityType; }
    public double getTaxAmount() { return taxAmount; }
    public double getAnnualIncome() { return annualIncome; }

    public void setName(String name) { this.name = name; }
    public void setOwner(String owner) { this.owner = owner; }
    public void setActivityType(String activityType) { this.activityType = activityType; }
    public void setTaxAmount(double taxAmount) { this.taxAmount = taxAmount; }
    public void setAnnualIncome(double annualIncome) { this.annualIncome = annualIncome; }

    @Override
    public String toString() {
        return "PrivateEnterprise{" +
                "name='" + name + '\'' +
                ", owner='" + owner + '\'' +
                ", activityType='" + activityType + '\'' +
                ", taxAmount=" + taxAmount +
                ", annualIncome=" + annualIncome +
                '}';
    }
}