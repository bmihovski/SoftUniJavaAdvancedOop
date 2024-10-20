package surfers;

public class Surfer implements Comparable<Surfer> {
  private String name;
  private int age;
  private int experience;
  private Boolean ownsASurfBoard;
  private int money;

  public Surfer(String name, int age, int experience, Boolean ownsASurfBoard, int money) {
    this.name = name;
    this.age = age;
    this.experience = experience;
    this.ownsASurfBoard = ownsASurfBoard;
    this.money = money;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public int getExperience() {
    return experience;
  }

  public void setExperience(int experience) {
    this.experience = experience;
  }

  public Boolean getOwnsASurfBoard() {
    return ownsASurfBoard;
  }

  public void setOwnsASurfBoard(Boolean ownsASurfboard) {
    this.ownsASurfBoard = ownsASurfboard;
  }

  public int getMoney() {
    return money;
  }

  public void setMoney(int money) {
    this.money = money;
  }

  @Override
  public String toString() {
    return String.format(
        "Surfer %s is %d years old and " + "has %d years surfing experience.",
        name, age, experience);
  }

  @Override
  public int hashCode() {
    return name.hashCode() + age + experience + money;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (other == null) {
      return false;
    }
    if (!(other instanceof Surfer)) {
      return false;
    }
    Surfer otherSurfer = (Surfer) other;
    return this.name.equals(otherSurfer.getName())
        && this.age == otherSurfer.getAge()
        && this.experience == otherSurfer.getExperience()
        && this.ownsASurfBoard == otherSurfer.getOwnsASurfBoard()
        && this.money == otherSurfer.getMoney();
  }

  @Override
  public int compareTo(Surfer o) {
    if (this.experience > o.getExperience()) {
      return 1;
    } else if (this.experience < o.getExperience()) {
      return -1;
    } else {
      return 0;
    }
  }
}
