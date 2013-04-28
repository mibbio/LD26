package de.mibbiodev.ld26;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Disposable;

import java.util.HashMap;
import java.util.Map;

/**
 * @author mibbio
 */
public class SoundManager implements Tickable, Disposable {

    private Map<String, Sound> soundMap;
    private Map<String, Float> soundsPlaying;
    private float alltimeTick = 0;

    public SoundManager() {
        soundMap = new HashMap<String, Sound>();
        soundsPlaying = new HashMap<String, Float>();
    }

    public void play(String soundName, float volume, float pitch, Class owner) {
        StringBuilder playName = new StringBuilder()
                .append(owner.getClass().getSimpleName())
                .append("_")
                .append(soundName);
        Float startTime = soundsPlaying.get(playName.toString());

        if (soundMap.containsKey(soundName)) {
            if (startTime == null || (alltimeTick - startTime) > 1f) {
                soundMap.get(soundName).play(volume, pitch, 0);
                soundsPlaying.put(playName.toString(), alltimeTick);
            }
        } else {
            Sound sound = Gdx.audio.newSound(Gdx.files.internal("data/sounds/" + soundName + ".ogg"));
            soundMap.put(soundName, sound);
            soundsPlaying.put(playName.toString(), alltimeTick);
            sound.play(volume, pitch, 0);
        }
    }

    @Override
    public void dispose() {
        for (Sound sound : soundMap.values()) {
            sound.dispose();
        }
    }

    @Override
    public void tick(float tickTime) {
        alltimeTick += tickTime;
    }
}
