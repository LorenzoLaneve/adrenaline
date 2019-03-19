package it.polimi.deib.newdem.adrenaline.common.mechs;

public interface DamageBoard {

    Player getPlayer();

    int getDeaths();

    void dealDamageTo(Player p, int damage);

    void dealMarksTo(Player p, int marks);

    int getTotalDamage();

    int getTotalMarks();

    Player getDamager(int cell);
}
