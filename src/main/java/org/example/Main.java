package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Player player = new Player((byte) 4, (byte) 2, 25, 10, 20); // in this case attack modifier equals 3
        Monster monster = new Monster((byte) 3, (byte) 2, 30, 1, 5); // in this case attack modifier equals 2
        System.out.println("Your opponent today is " + monster.getName());
        Scanner scanner = new Scanner(System.in);
        while(!monster.isDead()){
            System.out.println(player.getName().toUpperCase() + "'s HP = " + player.getHealth());
            System.out.println(monster.getName().toUpperCase() + "'s HP = " + monster.getHealth());
            System.out.println("1 - attack, 2 - heal  " + player.getHealAmount() +  " HP.");
            int turn = scanner.nextInt();
            if(turn == 1){
                player.attack(monster);
                if(monster.isDead()){
                    System.out.println(monster.getName().toUpperCase() + " is dead! Congratulations!");
                    break;
                }
            } else if(turn == 2){
                player.heal();
            } else{
                System.out.println("Incorrect choice.");
            }

            monster.attack(player);
            if(player.isDead()){
                System.out.println(player.getName().toUpperCase() + " is dead. Try again. :(");
                break;
            }
        }
    }
}