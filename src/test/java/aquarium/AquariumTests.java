package aquarium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

public class AquariumTests {
  // TODO: TEST ALL THE FUNCTIONALITY OF THE PROVIDED CLASS Aquarium
  private Aquarium testAquarium;

  @Before
  public void setUp() {
    testAquarium = new Aquarium("TestTank", 100);
  }

  @Test
  public void testAquariumConstructor() {
    Aquarium tank = new Aquarium("test", 50);
    assertEquals("test", tank.getName());
    assertEquals(50, tank.getCapacity());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAquariumNoCapacity_thenException() {
    var tank = new Aquarium("test", 0);
    tank.add(new Fish("Nemo"));
  }

  @Test
  public void testAddFish() {
    Fish f = new Fish("aao");
    testAquarium.add(f);
    assertEquals(1, testAquarium.getCount());
  }

  @Test
  public void testGetCapacity() {
    assertEquals(100, testAquarium.getCapacity());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddNullFish_thenException() {
    new Aquarium("nest", -1);
  }

  @Test(expected = NullPointerException.class)
  public void testSetEmptyName_thenException() {
    new Aquarium("", 10);
  }

  @Test
  public void testRemoveFish() {
    testAquarium.add(new Fish("eae"));
    assertEquals(1, testAquarium.getCount());
    testAquarium.remove("eae");
    assertEquals(0, testAquarium.getCount());
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenRemoveNotExistingFish_thenException() {
    testAquarium.remove("nonexistent");
  }

  @Test
  public void testSellFish() {
    testAquarium.add(new Fish("eaea"));
    Fish sold = testAquarium.sellFish("eaea");
    assertEquals("eaea", sold.getName());
    assertFalse(sold.isAvailable());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNotExistingFishCanNotBeSold() {
    testAquarium.sellFish("eaea");
  }
}
