package com.epam.ik.service;

import com.epam.ik.entity.CoffeeType;
import com.epam.ik.entity.Machine;
import com.epam.ik.entity.MachineState;
import com.epam.ik.entity.ResultState;

import java.util.Scanner;

public class Menu {
    private static final Machine MACHINE = new Machine(4000, 5400, 1200, 90, 550);
    private static ResultState result;

    public static void runMachine(){
        Scanner scanner = new Scanner(System.in);
        boolean timeToStop = false;
        while (!timeToStop) {
            System.out.print("Write action (buy, fill, take, remaining, exit): ");
            String action = scanner.next();
            switch (action) {
                case "remaining" -> checkingRemainingBalance();
                case "exit" -> timeToStop = true;
                case "fill" -> fillingRemainingBalance(scanner);
                case "buy" -> makingPurchase();
                default -> {
                    MACHINE.manage(MachineState.TAKE_MONEY.name());
                    result = MACHINE.manage("");
                }
            }
            System.out.println();
        }
    }

    private static void makingPurchase() {
        {
            System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
            Scanner scanner = new Scanner(System.in);
            String choice = scanner.next();
            choosingDrinkWhenBuying(choice);

            switch (result) {
                case NO_WATER -> System.out.println("Sorry, not enough water!");
                case NO_BEANS -> System.out.println("Sorry, not enough coffee beans!");
                case NO_CUPS -> System.out.println("Sorry, not enough disposable cups!");
                case NO_MILK -> System.out.println("Sorry, not enough milk!");
                case COFFEE_SOLD -> System.out.println("I have enough resources, making you a coffee!");
                default -> System.out.println("Unexpected response: " + result.name());
            }
        }
    }

    private static void choosingDrinkWhenBuying(String choice) {
        switch (choice) {
            case "1" -> {
                MACHINE.manage(MachineState.BUY.name());
                result = MACHINE.manage(CoffeeType.ESPRESSO.name());
            }
            case "2" -> {
                MACHINE.manage(MachineState.BUY.name());
                result = MACHINE.manage(CoffeeType.LATTE.name());
            }
            case "3" -> {
                MACHINE.manage(MachineState.BUY.name());
                result = MACHINE.manage(CoffeeType.CAPPUCCINO.name());
            }
            default -> {
                System.out.println("Make the right choice");
                makingPurchase();
            }
        }
    }

    private static void fillingRemainingBalance(Scanner scanner) {
        System.out.print("Write how many ml of water do you want to add:");
        MACHINE.manage(MachineState.FILL_WATER.name());
        MACHINE.manage(scanner.next());
        System.out.print("Write how many ml of milk do you want to add:");
        MACHINE.manage(MachineState.FILL_MILK.name());
        MACHINE.manage(scanner.next());
        System.out.print("Write how many grams of coffee beans do you want to add:");
        MACHINE.manage(MachineState.FILL_BEANS.name());
        MACHINE.manage(scanner.next());
        System.out.print("Write how many disposable cups of coffee do you want to add:");
        MACHINE.manage(MachineState.FILL_CUPS.name());
        MACHINE.manage(scanner.next());
    }

    private static void checkingRemainingBalance() {
        System.out.println("The coffee machine has:");
        System.out.println(MACHINE.water + " of water");
        System.out.println(MACHINE.milk + " of milk");
        System.out.println(MACHINE.beans + " of coffee beans");
        System.out.println(MACHINE.cups + " of disposable cups");
        System.out.println("$" + MACHINE.money + " of money");
    }
}
