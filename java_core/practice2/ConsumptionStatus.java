package practice2;

public enum ConsumptionStatus
{
    SELL_WELL("Sold well"),
    HARD_TO_SELL("Hard to sell"),
    SLOW_SALE("Slow sale"),
    NOT_EVALUATED("Not evaluated");

    private final String description;

    ConsumptionStatus(String description)
    {
        this.description = description;
    }

    @Override
    public String toString()
    {
        return description;
    }
}
