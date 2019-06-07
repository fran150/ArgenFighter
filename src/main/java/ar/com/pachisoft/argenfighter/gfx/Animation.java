package ar.com.pachisoft.argenfighter.gfx;

import ar.com.pachisoft.argenfighter.main.GameConsts;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public final class Animation {
    private final List<AnimationStep> steps;
    private int currentIndex;
    private double currentAnimationTime;
    private AnimationStep currentStep;

    public Animation(List<AnimationStep> steps) {
        this.steps = new ArrayList<>(steps);
        start();
    }

    public void start() {
        currentIndex = 0;
        currentAnimationTime = 0;
        currentStep = steps.get(0);
    }

    public void next() {
        currentIndex++;

        if (currentIndex >= steps.size()) {
            currentIndex = 0;
        }

        currentStep = steps.get(currentIndex);
    }

    public void timestep() {
        currentAnimationTime += GameConsts.FRAME_TIME;

        double duration = currentStep.getDuration();

        if (currentAnimationTime >= duration) {
            next();
            currentAnimationTime -= duration;
        }
    }

    public void render(Graphics2D graphics, int x, int y) {
        Sprite currentSprite = currentStep.getSprite();
        graphics.drawImage(currentSprite.getImage(), x, y, null);
    }
}
