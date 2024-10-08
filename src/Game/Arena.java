package Game;

import Fighter.Fighter;
import Items.Armor;
import Items.Consumable;
import Items.Weapon;

import java.util.ArrayList;
import java.util.Random;

public class Arena {
    final private ArrayList<Weapon> weapons = Setup.loadWeapons();
    final private ArrayList<Armor> armors = Setup.loadArmors();
    final private ArrayList<Consumable> consumables = Setup.loadConsumables();
    Random random = new Random();

    //Chans att plocka upp ett bättre vapen var tredje runda + bättre armor för den med minst liv...
    public void fight(Fighter fighter1, Fighter fighter2) throws InterruptedException {
        armFighters(fighter1, fighter2);
        armorFighters(fighter1, fighter2);
        int round = 1;
        while (fighter1.isAlive() && fighter2.isAlive()) {
            System.out.println("\n*****---------*****");
            System.out.println("Round: " + round);
            System.out.println();
            if (round % 3 == 0) {
                armFighters(fighter1, fighter2);
                if (fighter1.getHealth() > fighter2.getHealth()) {
                    fighter2.pickUpArmor(getRandomArmor());
                } else if (fighter1.getHealth() < fighter2.getHealth()) {
                    fighter1.pickUpArmor(getRandomArmor());
                }
            }


            if (fighter1.isAlive() && fighter2.isAlive()) {
                Thread.sleep(2000);
                fighter1.attack(fighter2);
            }

            if (fighter1.isAlive() && fighter2.isAlive()) {
                Thread.sleep(2000);
                fighter2.attack(fighter1);
            }
            round++;
        }
    }

    private Armor getRandomArmor() {
        return armors.get(random.nextInt(armors.size()));
    }

    private void armFighters(Fighter fighter1, Fighter fighter2) {
        fighter1.pickUpWeapon(getRandomWeapon());
        fighter2.pickUpWeapon(getRandomWeapon());
    }

    private Weapon getRandomWeapon() {
        return weapons.get(random.nextInt(weapons.size()));
    }

    private void armorFighters(Fighter fighter1, Fighter fighter2) {
        fighter1.pickUpArmor(getRandomArmor());
        fighter2.pickUpArmor(getRandomArmor());
    }


}
