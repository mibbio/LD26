package de.mibbiodev.ld26.tile;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Disposable;
import de.mibbiodev.ld26.LD26Game;
import de.mibbiodev.ld26.Tickable;
import de.mibbiodev.ld26.entity.Energized;

import java.util.*;

/**
 * @author mibbio
 */
public class WireStrip implements Tickable, Energized, Disposable {

    private Color color;
    private float energyLevel;
    private Door door;
    private List<Wire> wires;

    public WireStrip(Color color, float initialEnergy) {
        this.color = color;
        this.energyLevel = initialEnergy;
        door = null;
        wires = new ArrayList<Wire>();
    }

    public Color getColor() {
        return color;
    }

    private void addWire(Wire wire) {
        wires.add(wire);
    }

    public Door getDoor() {
        return door;
    }

    public void setDoor(Door door) {
        this.door = door;
    }

    @Override
    public float getEnergyLevel() {
        return energyLevel;
    }

    @Override
    public void setEnergyLevel(float energyLevel) {
        this.energyLevel = energyLevel;
        for (Wire wire : wires) {
            wire.setEnergyLevel(energyLevel);
        }
    }

    @Override
    public boolean drainEnergy(Energized target, float amount) {
        if (this.getEnergyLevel() > 0.95f) return false;

        float targetEnergy = target.getEnergyLevel();
        float myEnergy = this.getEnergyLevel();

        if (targetEnergy > amount && (1-myEnergy) > amount) {
            targetEnergy -= amount;
            myEnergy += amount;
            target.setEnergyLevel(targetEnergy);
            this.setEnergyLevel(myEnergy);
            return true;
        }
        return false;
    }

    @Override
    public boolean addEnergy(Energized target, float amount) {
        return target.drainEnergy(this, amount);
    }

    public void draw(SpriteBatch batch) {
        for (Wire wire : wires) {
            batch.draw(wire.getTexture(null), wire.getBounds().x, wire.getBounds().y);
        }
        if (door != null) batch.draw(door.getTexture(null), door.getBounds().x, door.getBounds().y);
    }

    public boolean overlaps(Rectangle rect) {
        for (Wire wire : wires) {
            if (wire.getBounds().overlaps(rect)) return true;
        }
        return false;
    }

    @Override
    public void tick(float tickTime) {
        for (Wire wire : wires) {
            wire.tick(tickTime);
        }

        if (energyLevel >= 0.95f) door.unlock();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        WireStrip tempStrip = (WireStrip) obj;
        return getColor() == tempStrip.getColor();
    }

    public static List<WireStrip> load(Pixmap wireImage) {
        // separating different WireStrips by color
        Color color = new Color();
        Map<Color, List<Wire>> wireMap = new HashMap<Color, List<Wire>>();
        for (byte x = 0; x < wireImage.getWidth(); x++) {
            for (byte y = 0; y < wireImage.getHeight(); y++) {
                int pixel = wireImage.getPixel(x, LD26Game.ROOM_SIZE - 1 - y);
                Color.rgba8888ToColor(color, pixel);

                if (color.a > 0) {
                    Color keyColor = color.cpy();
                    keyColor.a = 1;
                    if (wireMap.containsKey(keyColor)) {
                        wireMap.get(keyColor).add(new Wire(x, y, color.cpy()));
                    } else {
                        List<Wire> wireList = new ArrayList<Wire>();
                        wireList.add(new Wire(x, y, color.cpy()));
                        wireMap.put(keyColor, wireList);
                    }
                }
            }
        }

        List<WireStrip> finalList = new ArrayList<WireStrip>();
        for (Color stripColor : wireMap.keySet()) {
            WireStrip strip = new WireStrip(stripColor, 0.2f);
            for (Wire wire : wireMap.get(stripColor)) {
                strip.addWire(wire);
            }
            finalList.add(strip);
        }

        return finalList;
    }

    @Override
    public void dispose() {
        for (Wire wire : wires) {
            wire.dispose();
        }
        wires = new ArrayList<Wire>();
        door.dispose();
    }
}
