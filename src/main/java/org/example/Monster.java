package org.example;

public class Monster extends Creature{

    //Добавить боевой клич для монстров! (вместо реплики атаки при вызове attack())!!

    public void attack(Player target){
        System.out.println("For the Horde!");
        this.attackCalculation(target);
    }

    public void attack(Monster target){
        System.out.println("FRIENDLY FIRE!!");
    }

    public Monster(byte attack, byte defence, int maxHealth, int minDamage, int maxDamage) {
        super(attack, defence, maxHealth, minDamage, maxDamage);
        setName("monster");
    }

}
