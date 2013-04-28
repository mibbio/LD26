package de.mibbiodev.ld26.entity;

/**
 * @author mibbio
 */
public interface Energized {

    void setEnergyLevel(float energyLevel);

    float getEnergyLevel();

    boolean drainEnergy(Energized target, float amount);

    boolean addEnergy(Energized target, float amount);
}
