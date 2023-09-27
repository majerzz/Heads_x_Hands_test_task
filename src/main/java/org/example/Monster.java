package org.example;

public class Monster extends Creature{

    public void attack(Player target){
        System.out.println("For the Horde!");
        this.attackCalculation(target);
    }

    public void attack(Monster target){
        System.out.println("FRIENDLY FIRE!!");
    }

    public Monster(String name, byte attack, byte defence, int maxHealth, int minDamage, int maxDamage) {
        super(name, attack, defence, maxHealth, minDamage, maxDamage);
    }

}
