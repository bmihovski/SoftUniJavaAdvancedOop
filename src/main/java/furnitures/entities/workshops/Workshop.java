package furnitures.entities.workshops;

import furnitures.entities.wood.Wood;

public interface Workshop {

    int getWoodQuantity();
    int getProducedFurnitureCount();
    int getWoodQuantityReduceFactor();
    void addWood(Wood wood);
    void produce();
}
