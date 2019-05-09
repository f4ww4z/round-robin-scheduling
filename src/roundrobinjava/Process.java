package roundrobinjava;

public class Process {

    private final int burstTime;
    private int remainingBurstTime, arrivalTime, timeArrivedInQueue, finishedTime;

    public Process(int burstTime, int arrivalTime) {
        this.burstTime = burstTime;
        this.remainingBurstTime = burstTime;
        this.arrivalTime = arrivalTime;
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
        this.finishedTime = finishedTime;
    }

    public int getWaitingTime() {
        return getTurnaroundTime() - burstTime;
    }

    public int getTurnaroundTime() {
        return (int) Math.abs(finishedTime - timeArrivedInQueue);
    }
}
