package org.vivek.algo2.wk1;

public class Schedule {

    private Integer weight;
    private Integer length;
    private Integer diff;
    private Double ratio;

    public Schedule(final String[] args) {
        if (args.length != 2)
            throw new RuntimeException("Input array size should be only 2");

        this.weight = Integer.valueOf(args[0]);
        this.length = Integer.valueOf(args[1]);
        this.diff = this.weight - this.length;
        this.ratio = (double) this.weight / this.length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Schedule schedule = (Schedule) o;

        if (!length.equals(schedule.length)) return false;
        if (!weight.equals(schedule.weight)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = weight.hashCode();
        result = 31 * result + length.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "weight=" + weight +
                ", length=" + length +
                ", diff=" + diff +
                ", ratio=" + ratio +
                '}';
    }

    public Integer getWeight() {
        return weight;
    }

    public Integer getLength() {
        return length;
    }
}
