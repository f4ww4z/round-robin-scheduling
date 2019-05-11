package roundrobinjava;

public class Process {

    private final int burstTime;
    private int remainingBurstTime, arrivalTime, timeArrivedInQueue, finishedTime;

    public Process(int burstTime, int arrivalTime) {
        this.burstTime = burstTime;
        this.remainingBurstTime = burstTime;
        this.arrivalTime = arrivalTime;
        this.finishedTime = 0;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public int getRmBurstTime() {
        return remainingBurstTime;
    }

    public void setRmBurstTime(int burstTime) {
        this.remainingBurstTime = burstTime;
    }

    public void decreaseBurstTime(int quantum) {
        this.remainingBurstTime -= quantum;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setTimeArrivedInQueue(int t) {
        timeArrivedInQueue = t;
    }

    public void setFinishedTime(int finishedTime) {
        // only set the completion time if it's not already been set
        if (this.finishedTime == 0) {
            this.finishedTime = finishedTime;
        }
    }

    public int getTurnaroundTime() {
        // Using the formula
        return (int) Math.abs(finishedTime - timeArrivedInQueue);
    }

    public int getWaitingTime() {
        // Using the formula
        return getTurnaroundTime() - burstTime;
    }
}
