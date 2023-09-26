package org.example;


public abstract class Creature {

    private String name;

    final static byte MIN_ATTACK = 1;
    final static byte MAX_ATTACK = 30;

    // Целое число от 1 до 30
    private byte attack;

    final static byte MIN_DEFENCE = 1;
    final static byte MAX_DEFENCE = 30;

    // Целое число от 1 до 30
    private byte defence;

    // Натуральное число от 0 до N.
    private int maxHealth;

    private int health;

    private int minDamage;

    private int maxDamage;

    private boolean isDead = false;

    private byte attackModifier;

    public Creature(byte attack, byte defence, int maxHealth, int minDamage, int maxDamage){
        setAttack(attack);
        setDefence(defence);

        setMaxHealth(maxHealth);
        setHealth(maxHealth);

        setMinDamage(minDamage);
        setMaxDamage(maxDamage);

        setAttackModifier(calculateAttackModifier(this.attack, this.defence));

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte getAttack() {
        return attack;
    }

    public void setAttack(byte attack){
        if (attack < MIN_ATTACK){
            throw new IllegalArgumentException("Creature's attack has to be at least 1.");
        } else if (attack > MAX_ATTACK){
            throw new IllegalArgumentException("Creature's attack has to be less or equal to 30.");
        }
        this.attack = attack;
    }

    public byte getDefence() {
        return attack;
    }

    public void setDefence(byte defence){
        if (defence < MIN_DEFENCE){
            throw new IllegalArgumentException("Creature's defence has to be at least 1.");
        } else if (defence > MAX_DEFENCE){
            throw new IllegalArgumentException("Creature's defence has to be less or equal to 30.");
        }
        this.defence = defence;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        if(maxHealth < 0){
            throw new IllegalArgumentException("Creature's health can't be negative!");
        } else {
            this.maxHealth = maxHealth;
        }
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        if(health < 0){
            throw new IllegalArgumentException("Creature's health can't be negative!");
        } else if (health > maxHealth){
            throw new IllegalArgumentException("Creature's health can't be greater than creature's maximum health.");
        } else {
            this.health = health;
        }
    }

    public int getMinDamage() {
        return minDamage;
    }

    public void setMinDamage(int minDamage) {
        if(minDamage < 1){
            // Будем считать, что наши существа не совсем беспомощные, и бьют хотя бы на 1 единицу урона.
            throw new IllegalArgumentException("Creature's damage can't be less than 1.");
        } else{
            this.minDamage = minDamage;
        }
    }

    public int getMaxDamage() {
        return maxDamage;
    }

    public void setMaxDamage(int maxDamage) {
        if( maxDamage < 1){
            throw new IllegalArgumentException("Creature's damage can't be less than 1.");
        } else {
            this.maxDamage = maxDamage;
        }
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public byte getAttackModifier() {
        return attackModifier;
    }

    public void setAttackModifier(byte attackModifier) {
        if(attackModifier < 0){
            throw new IllegalArgumentException("Attack modifier can't be less than 0");
        } else {
            this.attackModifier = attackModifier;
        }
    }

    // Вынесем посдчет модификатора атаки в отдельную функцию для лучшей читаемости.
    byte calculateAttackModifier(byte attack, byte defence){
        return (byte)(attack - defence + 1);
    }

    //Механика подсчетов атаки у всех существ одинакова, поэтому подсчеты оставляем в родительском классе.
    void attackCalculation(Creature target){
        // Рассчитываем модификатор атаки как разность атаки и защиты существа + 1

        System.out.println(getName().toUpperCase() + " attacks " + target.getName() + ".");
        byte temp = attackModifier;

        //Бросаем N кубиков, где N = attackModifier, минимум 1 бросок
        do{
            System.out.println("Rolling dice...");
            int min = 1;
            int max = 6;
            int randNum = min + (int)(Math.random() * max);
            System.out.println("Dice is " + randNum + "!");
            temp--;
            if(randNum == 5 || randNum == 6){
                // Наносим цели урон, если на кубике выпало 5 или 6 и прекращаем бросать кубики
                int targetDamage = (int) (Math.random() * ((getMaxDamage() + 1) - getMinDamage()) + getMinDamage());
                if(targetDamage >= target.getHealth()){
                    target.setHealth(0);
                    target.setDead(true);
                    System.out.println(target.getName().toUpperCase() + " is dead.");
                } else {
                    target.setHealth(target.getHealth() - targetDamage);
                }
                System.out.println(getName().toUpperCase() + " deals " + target.getName() + " " + targetDamage + " damage.");
                break;
            }
        } while(temp != 0);
        System.out.println();
    }

    //А атаку переопределим, чтобы игроки и монстры могли иметь свой боевой клич!
    abstract void attack(Player target);
    abstract void attack(Monster target);
}
