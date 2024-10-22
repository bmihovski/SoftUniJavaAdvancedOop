package tanks;

public class Tank implements Comparable<Tank> {
  private String brand;
  private String model;
  private int weight;
  private int barrelCaliber;
  private int armor;

  public Tank(String brand, String model, int weight, int barrelCaliber, int armor) {
    this.brand = brand;
    this.model = model;
    this.weight = weight;
    this.barrelCaliber = barrelCaliber;
    this.armor = armor;
  }

  String getBrand() {
    return brand;
  }

  void setBrand(String brand) {
    this.brand = brand;
  }

  String getModel() {
    return model;
  }

  void setModel(String model) {
    this.model = model;
  }

  int getWeight() {
    return weight;
  }

  void setWeight(int weight) {
    this.weight = weight;
  }

  int getBarrelCaliber() {
    return barrelCaliber;
  }

  void setBarrelCaliber(int caliber) {
    barrelCaliber = caliber;
  }

  int getArmor() {
    return armor;
  }

  void setArmor(int armor) {
    this.armor = armor;
  }

  @Override
  public String toString() {
    return String.format(
        "Brand: %s, Model: %s, Weight: %dkg, Barrel caliber: %dmm, Armor: %dmm",
        brand, model, weight, barrelCaliber, armor);
  }

  @Override
  public int compareTo(Tank tank) {
    return Integer.compare(armor, tank.armor);
  }
}
