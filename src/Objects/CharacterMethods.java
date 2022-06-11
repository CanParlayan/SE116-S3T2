package Objects;

public interface CharacterMethods {
    void Pick(Item item);
    void Throw(Item item);
    void Wield(Weapon weapon);
    void Wear(Armor armor);
    boolean HasSpaceInInventory(Item item);
    int CalculateDamage();
    void CheckDead();
    void DisplayStats();

}
