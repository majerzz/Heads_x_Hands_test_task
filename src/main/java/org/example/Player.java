package org.example;

public class Player extends Creature {

    private int healAmount;
    final static byte CURRENT_LIMIT = 4;
    private byte limit;

    public Player(String name, byte attack, byte defence, int maxHealth, int minDamage, int maxDamage) {
        super(name, attack, defence, maxHealth, minDamage, maxDamage);
        setLimit(CURRENT_LIMIT);
        // Учитывая, что здоровье - только натуральные числа, если maximumHealth игрока - 1, хилиться он не сможет.
        setHealAmount(calculateHealAmount());

    }

    public int getHealAmount() {
        return healAmount;
    }

    public void setHealAmount(int healAmount) {
        this.healAmount = healAmount;
    }

    public byte getLimit() {
        return limit;
    }

    public void setLimit(byte limit) {
        if(limit < 0){
            throw new IllegalArgumentException("limit can't be a negative number!");
        } else{
            this.limit = limit;
        }
    }


    public void attack(Monster target){
        System.out.println("For the Alliance!");
        this.attackCalculation(target);
    }

    // Ошибки случаются.
    public void attack(Player target){
        System.out.println("FRIENDLY FIRE!!");
    }

    int calculateHealAmount(){
        return(Math.round((float) getMaxHealth() * 30 / 100));
    }

    public void heal(){
        if(limit != 0) {
            if (this.getHealth() == getMaxHealth()) {
                System.out.println("Player already at maximum HP.\n");
            } else if (!this.isDead() && (getHealth() + healAmount) < this.getMaxHealth()) {
                this.setHealth(this.getHealth() + healAmount);
                System.out.println(healAmount + " HP healed. " + "Current HP -  " + getHealth() + " HP.\n");
                limit--;
            } else {
                setHealth(getMaxHealth());
                System.out.println("Healed to maximum HP! Current HP - " + getHealth() + " HP.\n");
                limit--;
            }

        } else {
            System.out.println(getName().toUpperCase() + " is out of heals!\n");
        }
    }
}
