package tanks;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;

public class Terrain {
  private String type;
  private List<Tank> tanks;
  private int area;

  public Terrain(String type, int area) {
    this.type = type;
    this.area = area;
    tanks = new ArrayList<>();
  }

  public int getCount() {
    return tanks.size();
  }

  public boolean removeTank(String brand, String model) {
    return tanks.removeIf(t -> t.getBrand().equals(brand) && t.getModel().equals(model));
  }

  public String addTank(Tank tank) {
    BiPredicate<String, String> tankPre = getTanksPredicate();
    if (tankPre.test(tank.getBrand(), tank.getModel())) {
      return "Tank with this brand and model already exists!";
    } else if (tank.getWeight() > 14000 && type.equals("Swamp")) {
      return "This " + tank.getBrand() + " is too heavy for this terrain!";
    } else {
      tanks.add(tank);
      return String.format("Tank %s %s added.", tank.getBrand(), tank.getModel());
    }
  }

  public String getTheMostArmoredTank() {
    var mostArmoredTank = tanks.stream().max(Tank::compareTo).orElse(null);
    return String.format(
        "%s %s is the most armored tank with %dmm. armor thickness.",
        mostArmoredTank.getBrand(), mostArmoredTank.getModel(), mostArmoredTank.getArmor());
  }

  public String getTanksByBarrelCaliberMoreThan(int barrelCaliber) {
    List<Tank> searchByCaliber =
        tanks.stream().filter(t -> t.getBarrelCaliber() > barrelCaliber).toList();
    if (searchByCaliber.isEmpty()) {
      return "There are no tanks with the specified caliber.";
    } else {
      StringBuilder sb = new StringBuilder();
      sb.append("Tanks with caliber more than ").append(barrelCaliber).append("mm:");
      boolean isFirst = false;
      for (var tank : searchByCaliber) {
        sb.append(isFirst ? ", " : "").append(tank.getBrand());
        isFirst = true;
      }
      return sb.toString();
    }
  }

  public String getStatistics() {
    var terrainType = type.toLowerCase();
    if (tanks.isEmpty()) {
      return "There are no tanks in the " + terrainType + ".";
    } else {
      StringBuilder sb = new StringBuilder();
      sb.append("Tanks located in the ").append(terrainType).append(":");
      for (var tank : tanks) {
        sb.append(System.lineSeparator())
            .append("-- ")
            .append(tank.getBrand())
            .append(" ")
            .append(tank.getModel());
      }
      return sb.toString();
    }
  }

  public Tank getTankByBrandAndModel(String brand, String model) {
    return tanks.stream()
        .filter(t -> t.getBrand().equals(brand) && t.getModel().equals(model))
        .findFirst()
        .orElse(null);
  }

  private BiPredicate<String, String> getTanksPredicate() {
    return (brand, model) ->
        tanks.stream().anyMatch(t -> t.getBrand().equals(brand) && t.getModel().equals(model));
  }
}
