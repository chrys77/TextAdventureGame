import java.util.Random;
import java.util.Scanner;

public class Main {

    //System objects
    static Scanner sc = new Scanner(System.in);
    static Random rand = new Random();

    //Game variables
    static String[] enemies = {"Skeleton", "Zombie", "Barbarian", "Assassin", "Undead", "Goblin", "Dwarf", "Troll", "Mercenary", "Wizard", "Orc"};
    static int maxEnemyHealth = 75;
    static int enemyAttackDamage = 25;
    static int numEnemies = 0;

    //Player variables
    static int heroHealth = 100;
    static int atackDamage = 50;
    static int numHeathPotions = 3;
    static int healthPotionHealAmount = 30;
    static int healthPotionDropChance = 50; //percentage

    public static void main(String[] args) {

        System.out.println("Welcome to the Dungeon! You are a warrior!");

        //GAME
        outerloop: while (true) {
            System.out.println("---------------------------------------------------------------");

            int enemyHealth = rand.nextInt(maxEnemyHealth);
            String enemy = enemies[rand.nextInt(enemies.length)];
            System.out.println("\t# " + enemy + " has appeared! #\n");

            while (enemyHealth > 0) {
                System.out.println("\tYour HP: " + heroHealth);
                System.out.println("\t" + enemy + "'s HP: " + enemyHealth);
                System.out.println("\n\tWhat would you like to do?");
                System.out.println("\t1. Attack");
                System.out.println("\t2. Drink health potion");
                System.out.println("\t3. Run!");
                System.out.print("\tChoose an option: ");

                String input = sc.nextLine();
                if (input.equals("1")) {
                    int damageDealt = rand.nextInt(atackDamage);
                    enemyHealth -= damageDealt;
                    System.out.println("\n\t> You strike the " + enemy + " for " + damageDealt + " damage.");
                    enemyStrike();

                } else if (input.equals("2")) {
                    if (numHeathPotions > 0) {
                        heroHealth += healthPotionHealAmount;
                        numHeathPotions--;
                        System.out.println("\n\t> You drink a health potion, healing yourself for " + healthPotionHealAmount + ". "
                                + "\n\t> You now have " + heroHealth + "HP."
                                + "\n\t> You have " + numHeathPotions + " health potions left.\n");
                        enemyStrike();
                    } else {
                        System.out.println("\n\t> You have no health potions left! Defeat enemies for a chance to get one!\n");
                    }

                } else if (input.equals("3")) {
                    enemyStrike();
                    System.out.println("\n\tYou run away from the " + enemy + "!");
                    continue outerloop;

                } else {
                    System.out.println("\n\tInvalid command!\n");
                }

                if (heroHealth < 1) {
                    System.out.println("\n\t> You have taken too much damage, you are too weak to go on!");
                    break;
                }
            }

            if (heroHealth < 1) {
                System.out.println("\t> You limp out of the dungeon, weak from battle.");
                break;
            }

            numEnemies++;
            System.out.println("---------------------------------------------------------------");
            System.out.println(" # " + enemy + " was defeated! # ");
            System.out.println(" # You have " + heroHealth + "HP left. #");

            if (rand.nextInt(100) > healthPotionDropChance) {
                numHeathPotions++;
                System.out.println(" # The " + enemy + " dropped a health potion! #");
                System.out.println(" # You now have " + numHeathPotions + " health potions. #");
            }

            System.out.println("---------------------------------------------------------------");
            System.out.println("\tWhat would you like to do now?");
            System.out.println("\t1. Continue fighting");
            System.out.println("\t2. Exit dungeon");
            System.out.print("\tChoose an option: ");

            String input = sc.nextLine();

            while(!input.equals("1") && ! input.equals("2")) {
                System.out.println("\t> Invalid command!");
                input = sc.nextLine();
            }

            if (input.equals("1")) {
                System.out.println("\n\t> You continue on your adventure!");
            } else if (input.equals("2")) {
                System.out.println("\n\t> You exit the dungeon, successful from adventures!");
                break;
            }
        }

        System.out.println("\t> You have defeated " + numEnemies + " enemies");

        System.out.println("\n\t#######################");
        System.out.println("\t# THANKS FOR PLAYING! #");
        System.out.println("\t#######################");

    }

    public static void enemyStrike() {
            int damageTaken = rand.nextInt(enemyAttackDamage);
            heroHealth -= damageTaken;
            System.out.println("\t> You receive " + damageTaken + " damage in retaliation!");

    }
}