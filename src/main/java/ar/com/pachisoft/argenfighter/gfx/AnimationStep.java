package ar.com.pachisoft.argenfighter.gfx;

import ar.com.pachisoft.argenfighter.gfx.exceptions.AnimationStepTooShortException;
import ar.com.pachisoft.argenfighter.main.GameConsts;
import ar.com.pachisoft.argenfighter.world.levels.ReadonlyLevelCoordinates;
import org.jetbrains.annotations.NotNull;

public final class AnimationStep {
    private Sprite sprite;
    private double duration;
    private boolean hitOtherCharacter;
    private ReadonlyLevelCoordinates hitCoordinates;

    public AnimationStep(@NotNull Sprite sprite, double duration, boolean hitOtherCharacter, ReadonlyLevelCoordinates hitCoordinates) {
        if (duration < GameConsts.FRAME_TIME) {
            throw new AnimationStepTooShortException(duration);
        }

        this.sprite = sprite;
        this.duration = duration;
        this.hitOtherCharacter = hitOtherCharacter;
        this.hitCoordinates = hitCoordinates;
    }

    public AnimationStep(Sprite sprite, double duration) {
        this(sprite, duration, false, null);
    }

    public Sprite getSprite() {
        return sprite;
    }

    public double getDuration() {
        return duration;
    }

    public boolean isHitOtherCharacter() {
        return hitOtherCharacter;
    }

    public ReadonlyLevelCoordinates getHitCoordinates() {
        return hitCoordinates;
    }
}
