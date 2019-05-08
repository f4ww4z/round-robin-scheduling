package roundrobinjava;

import java.util.Scanner;

/**
 *
 * @author S52500, S51356, S50947
 */
public class RoundRobinJava {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Get input from user
        System.out.print("Number of processes: ");
        int numberOfProcesses = sc.nextInt();
        System.out.print("Quantum: ");
        int quantum = sc.nextInt();

        Process[] processes = new Process[numberOfProcesses];

        // Critical time, and total burst time
        int totalTime = 0;

        for (int i = 0; i < numberOfProcesses; i++) {
            System.out.print("Arrival time for P" + i + " (lowest 0): ");
            int arrivalTime = sc.nextInt();
            System.out.print("Burst time for P" + i + " (lowest 0) : ");
            int burstTime = sc.nextInt();

            processes[i] = new Process(burstTime, arrivalTime);

            totalTime += processes[i].getBurstTime();
        }

        sc.close();

        System.out.println("Process Num\t| Arrival\t| Burst");
        for (int i = 0; i < numberOfProcesses; i++) {
            System.out.println(printInLine(i, processes[i].getArrivalTime(),
                    processes[i].getBurstTime()));
        }

        // process begins
        for (int time = 0; time < totalTime; time += quantum) {
            // loop through each process, check arrival and burst
            for (int num = 0; num < processes.length; num++) {

                // Check to see if the current process has arrived
                // and that it still has remaining burst time
                if (processes[num].getArrivalTime() <= time
                        && processes[num].getBurstTime() > 0) {
                    printProcess(num);

                    // Decrease current process's burst time by quantum
                    processes[num].decreaseBurstTime(quantum);
                }   
            }
        }

        System.out.println("\nTotal time: " + totalTime + "s");
    }

    private static void printProcess(int processIndex) {
        System.out.print("P" + processIndex + " - ");
    }

    private static String printInLine(int i, int arrival, int burst) {
        return "P" + i + "\t\t| " + arrival + "\t\t| " + burst;
    }

}
