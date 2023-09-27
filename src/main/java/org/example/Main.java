package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Player player = new Player("Paladin", (byte) 4, (byte) 2, 50, 2, 10); // В этом случае модификатор атаки равен 3
        Monster monster = new Monster("Zombie", (byte) 3, (byte) 2, 40, 1, 4); // В этом случае модификатор атаки равен 2

        System.out.println("You are fighting " + monster.getName());
        Scanner scanner = new Scanner(System.in);
        while(!monster.isDead()){
            System.out.println(player.getName().toUpperCase() + "'s HP = " + player.getHealth());
            System.out.println(monster.getName().toUpperCase() + "'s HP = " + monster.getHealth());
            System.out.println("1 - attack, 2 - heal " + player.getHealAmount() +  " HP.");
            byte turn = scanner.nextByte();
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