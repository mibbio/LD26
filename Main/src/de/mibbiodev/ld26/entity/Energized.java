package de.mibbiodev.ld26.entity;

/**
 * @author mibbio
 */
public interface Energized {

    void setEnergyLevel(float energyLevel);

    float getEnergyLevel();

    void drainEnergy(Energized target, float amount);

    void addEnergy(Energized target, float amount);
}
