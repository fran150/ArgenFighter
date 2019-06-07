package ar.com.pachisoft.argenfighter.world.characters;

import ar.com.pachisoft.argenfighter.gfx.*;
import ar.com.pachisoft.argenfighter.input.KeyHandler;
import ar.com.pachisoft.argenfighter.main.GameObject;
import ar.com.pachisoft.argenfighter.main.Renderable;
import ar.com.pachisoft.argenfighter.main.TimeSteppable;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;

public class Trump extends GameObject implements Renderable, TimeSteppable {

    SpriteSheet sprites;

    Animation walkUpAnimation;
    Animation walkDownAnimation;
    Animation walkLeftAnimation;
    Animation walkRightAnimation;

    int walkingDirection = 0;

    int x = 100;
    int y = 100;

    public Trump() throws IOException {
        super("Donald Trump");

        Texture texture = new Texture("trump_run");

        sprites = new SpriteSheet(texture, 100, SpriteFlippingOptions.FLIP_NONE);

        ArrayList<AnimationStep> steps;

        double duration = 0.1;

        // Down
        steps = new ArrayList<>();
        steps.add(new AnimationStep(sprites.getSprite(0), duration));
        steps.add(new AnimationStep(sprites.getSprite(1), duration));
        steps.add(new AnimationStep(sprites.getSprite(2), duration));
        steps.add(new AnimationStep(sprites.getSprite(3), duration));
        steps.add(new AnimationStep(sprites.getSprite(4), duration));
        steps.add(new AnimationStep(sprites.getSprite(5), duration));
        walkDownAnimation = new Animation(steps);

        // Right
        steps = new ArrayList<>();
        steps.add(new AnimationStep(sprites.getSprite(6), duration));
        steps.add(new AnimationStep(sprites.getSprite(7), duration));
        steps.add(new AnimationStep(sprites.getSprite(8), duration));
        steps.add(new AnimationStep(sprites.getSprite(9), duration));
        steps.add(new AnimationStep(sprites.getSprite(10), duration));
        steps.add(new AnimationStep(sprites.getSprite(11), duration));
        walkRightAnimation = new Animation(steps);

        // Up
        steps = new ArrayList<>();
        steps.add(new AnimationStep(sprites.getSprite(12), duration));
        steps.add(new AnimationStep(sprites.getSprite(13), duration));
        steps.add(new AnimationStep(sprites.getSprite(14), duration));
        steps.add(new AnimationStep(sprites.getSprite(15), duration));
        steps.add(new AnimationStep(sprites.getSprite(16), duration));
        steps.add(new AnimationStep(sprites.getSprite(17), duration));
        walkUpAnimation = new Animation(steps);

        // Left
        steps = new ArrayList<>();
        steps.add(new AnimationStep(sprites.getSprite(18), duration));
        steps.add(new AnimationStep(sprites.getSprite(19), duration));
        steps.add(new AnimationStep(sprites.getSprite(20), duration));
        steps.add(new AnimationStep(sprites.getSprite(21), duration));
        steps.add(new AnimationStep(sprites.getSprite(22), duration));
        steps.add(new AnimationStep(sprites.getSprite(23), duration));
        walkLeftAnimation = new Animation(steps);
    }

    @Override
    public void render(Graphics2D graphics) {
        switch (walkingDirection) {
            case 0:
                walkDownAnimation.render(graphics, x, y);
                break;
            case 1:
                walkRightAnimation.render(graphics, x, y);
                break;
            case 2:
                walkUpAnimation.render(graphics, x, y);
                break;
            case 3:
                walkLeftAnimation.render(graphics, x, y);
                break;
        }
    }

    @Override
    public void timestep() {
        if (KeyHandler.isDown(KeyEvent.VK_S)) {
            if (walkingDirection == 0) {
                walkDownAnimation.timestep();
            } else {
                walkDownAnimation.start();
            }
            walkingDirection = 0;
            y++;
        }

        if (KeyHandler.isDown(KeyEvent.VK_D)) {
            if (walkingDirection == 1) {
                walkRightAnimation.timestep();
            } else {
                walkRightAnimation.start();
            }
            walkingDirection = 1;
            x++;
        }

        if (KeyHandler.isDown(KeyEvent.VK_W)) {
            if (walkingDirection == 2) {
                walkUpAnimation.timestep();
            } else {
                walkUpAnimation.start();
            }
            walkingDirection = 2;
            y--;
        }

        if (KeyHandler.isDown(KeyEvent.VK_A)) {
            if (walkingDirection == 3) {
                walkLeftAnimation.timestep();
            } else {
                walkLeftAnimation.start();
            }
            walkingDirection = 3;
            x--;
        }
    }
}
