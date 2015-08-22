package ru.myitschool.ufodestroyer.model.entities;

import android.graphics.PointF;

import static java.lang.Math.*;

/**
 * Класс врага
 */
public class Enemy extends MovingGameObject {
    private static final float TIME_BETWEEN_DIRECTION_CHANGES = 1.3f;
    private float timeSinceLastDirectionChange = 0;

    @Override
    protected void adjustVelocity(float elapsedSeconds) {
        timeSinceLastDirectionChange += elapsedSeconds;
        if (timeSinceLastDirectionChange > TIME_BETWEEN_DIRECTION_CHANGES) {
            timeSinceLastDirectionChange -= TIME_BETWEEN_DIRECTION_CHANGES;

            PointF v = getVelocity();
            v.set(v.x, -v.y);
        }
    }
}
