package roundrobinjava;

import java.util.Scanner;

/**
 *
 * @author S52500, S51356, S50947, S52014
 */
public class RoundRobinJava {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Get input from user
        System.out.print("Number of processes: ");
        int numberOfProcesses = sc.nextInt();
        System.out.print("Quantum: ");
        // Currently works with quantum = 1, other values are buggy
        int quantum = sc.nextInt();

        // Creates an empty array of processes to be filled in later
        Process[] processes = new Process[numberOfProcesses];

        // Initialize total time taken for all processes to be executed
        int totalTime = 0;

        // Get arrival times and burst times from user, loop through each
        // process
        for (int i = 0; i < numberOfProcesses; i++) {
            System.out.print("Arrival time for P" + i + " (lowest 0): ");
            int arrivalTime = sc.nextInt();
            System.out.print("Burst time for P" + i + " (lowest 0) : ");
            int burstTime = sc.nextInt();

            // Sets the ith process based on user input
            processes[i] = new Process(burstTime, arrivalTime);

            totalTime += processes[i].getBurstTime();
        }

        sc.close();

        // Displays initial data
        System.out.println("Process Num\t| Arrival\t| Burst");
        for (int i = 0; i < numberOfProcesses; i++) {
            System.out.println(displayProcessDetails(i, processes[i].getArrivalTime(), processes[i].getRmBurstTime()));
        }
        System.out.println();

        // Store waiting and turnaround times
        int[] waitingTimes = new int[numberOfProcesses];
        int[] turnaroundTimes = new int[numberOfProcesses];

        // CPU begins executing processes
        int time = 0;
        while (time < totalTime) {
            // loop through each process, check arrival and burst
            for (int num = 0; num < processes.length; num++) {
                // Check to see if the current process has arrived
                if (processes[num].getArrivalTime() <= time) {
                    // Check that it still has remaining burst time
                    if (processes[num].getRmBurstTime() >= quantum) {
                        // Check if the process is executed for the first time
                        // (burst time isn't decreased yet)
                        if (processes[num].getRmBurstTime() == processes[num].getBurstTime()) {
                            // If yes, store it
                            processes[num].setTimeArrivedInQueue(time);
                        }
                        // Add process to timeline
                        printProcess(num);

                        // Decrease current process's burst time by quantum
                        processes[num].decreaseBurstTime(quantum);

                        if (processes[num].getRmBurstTime() == 0) {
                            // No burst left, set completion time
                            processes[num].setFinishedTime(time);
                            // calculate waiting and turnaround time
                            turnaroundTimes[num] = processes[num].getTurnaroundTime();
                            waitingTimes[num] = processes[num].getWaitingTime();
                        }
                    }
                    // 1 burst finished, move on
                    time += quantum;
                }
            }
        }

        // Display output
        System.out.println("\n\nTotal time: " + totalTime + "s\n");

        int totalWaiting = 0, totalTurnaround = 0;
        // Display turnaround and waiting times
        System.out.println("Process Num\t| Arrival\t| Burst \t| Waiting time \t| Turnaround time");
        for (int i = 0; i < numberOfProcesses; i++) {
            System.out.println(displayProcessResults(i, processes[i].getArrivalTime(), processes[i].getBurstTime(),
                    waitingTimes[i], turnaroundTimes[i]));

            // Also sum up the times, to calculate average
            totalWaiting += waitingTimes[i];
            totalTurnaround += turnaroundTimes[i];
        }

        // Calculate and show the averages
        double avgWaitingTime = (double) totalWaiting / numberOfProcesses;
        double avgTurnaroundTime = (double) totalTurnaround / numberOfProcesses;
        System.out.println();
        System.out.printf("Average waiting time: %.2fs\n", avgWaitingTime);
        System.out.printf("Average turnaround time: %.2fs\n", avgTurnaroundTime);
    }

    /**
     * Plot the process to the ordered timeline
     */
    private static void printProcess(int processIndex) {
        System.out.print("P" + processIndex + " - ");
    }

    /**
     * Display a process's details in table format
     *
     * @param i       the process number
     * @param arrival arrival time of the process
     * @param burst   burst time of the process
     * @return process details as a String
     */
    private static String displayProcessDetails(int i, int arrival, int burst) {
        return "P" + i + "\t\t| " + arrival + "\t\t| " + burst;
    }

    /**
     * Display a process's waiting and turnaround time in a table format
     *
     * @param i          the process number
     * @param arrival    arrival time of the process
     * @param burst      burst time of the process
     * @param waiting    waiting time of the process
     * @param turnaround turnaround time
     * @return process details as a String
     */
    private static String displayProcessResults(int i, int arrival, int burst, int waiting, int turnaround) {
        return displayProcessDetails(i, arrival, burst) + "\t\t| " + waiting + "\t\t| " + turnaround;
    }
}
