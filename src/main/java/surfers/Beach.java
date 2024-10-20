package surfers;

import java.util.ArrayList;
import java.util.List;

public class Beach {
  private String name;
  private int surfboardsForRent;
  private List<Surfer> surfers;

  public Beach(String name, int surfboardsForRent) {
    this.name = name;
    this.surfboardsForRent = surfboardsForRent;
    surfers = new ArrayList<>();
  }

  public String addSurfer(Surfer surfer) {
    String addSurferMsg = "";
    if (surfer.getOwnsASurfBoard().equals(false)) {
      if (surfboardsForRent == 0) {
        addSurferMsg = "There are no free surfboards.";
      } else if (surfer.getMoney() < 50) {
        addSurferMsg = surfer.getName() + " has not enough money to rent a surfboard.";
      }
    }
    this.surfers.add(surfer);
    addSurferMsg = "Surfer " + surfer.getName() + " added.";
    return addSurferMsg;
  }

  public boolean removeSurfer(String name) {
    return surfers.removeIf(s -> s.getName().equals(name));
  }

  public String getMostExperiencedSurfer() {
    if (surfers.isEmpty()) {
      return "There are no surfers.";
    }
    var topSurfer = surfers.stream().max(Surfer::compareTo).get();
    return String.format(
        "%s is most experienced " + "surfer with %d years experience.",
        topSurfer.getName(), topSurfer.getExperience());
  }

  public Surfer getSurfer(String name) {
    return surfers.stream().filter(s -> s.getName().equals(name)).findFirst().orElse(null);
  }

  public int getCount() {
    return surfers.size();
  }

  public String getSurfersWithPersonalSurfboards() {
    if (surfers.isEmpty()) {
      return "There are no surfers.";
    }
    var messageBuilder = new StringBuilder();
    List<Surfer> surfersWithBoard = surfers.stream().filter(Surfer::getOwnsASurfBoard).toList();
    messageBuilder.append("Surfers who have their own surfboards: ");
    boolean isFirst = true;
    for (var surfer : surfersWithBoard) {
      if (!isFirst) {
        messageBuilder.append(", ");
      }
      messageBuilder.append(surfer.getName());
      isFirst = false;
    }
    messageBuilder.append(System.lineSeparator());
    return messageBuilder.toString();
  }

  public String getReport() {
    if (surfers.isEmpty()) {
      return "There are no surfers on " + name + " beach.";
    }
    var messageBuilder = new StringBuilder();
    messageBuilder.append("Beach " + name + " was visited by the following surfers:");
    messageBuilder.append(System.lineSeparator());
    int counter = 1;
    for (var surfer : surfers) {
      if (surfer.getExperience() == 0) {
        messageBuilder.append(
            String.format("%d. %s with no experience.", counter++, surfer.getName()));
        messageBuilder.append(System.lineSeparator());
      } else {
        messageBuilder.append(
            String.format(
                "%d. %s with %d years experience.",
                counter++, surfer.getName(), surfer.getExperience()));
        messageBuilder.append(System.lineSeparator());
      }
    }
    return messageBuilder.toString();
  }
}
