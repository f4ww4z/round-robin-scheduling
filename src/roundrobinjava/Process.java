package roundrobinjava;

public class Process {

    private int burstTime, arrivalTime;

    public Process(int burstTime, int arrivalTime) {
        this.burstTime = burstTime;
        this.arrivalTime = arrivalTime;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public void setBurstTime(int burstTime) {
        this.burstTime = burstTime;
    }
    
    public void decreaseBurstTime(int quantum) {
        this.burstTime -= quantum;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
}
