package ar.com.pachisoft.argenfighter.gfx.exceptions;

import ar.com.pachisoft.argenfighter.main.GameConsts;

public class AnimationStepTooShortException extends RuntimeException {
    private double requestedDuration;
    private double timeFrame;

    public AnimationStepTooShortException(double requestedDuration) {
        super("The animation duration must be longer than a physics frame time");
        this.requestedDuration = requestedDuration;
        this.timeFrame = GameConsts.FRAME_TIME;
    }
}
