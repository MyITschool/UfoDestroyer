package ru.myitschool.ufodestroyer.model.entities;

/**
 * Пуля, выпущенная игроком
 */
public class Bullet extends MovingGameObject {
    /**
     * Сила гравитации, в пикселах на секунду в квадрате
     */
    private static final float GRAVITY = 9.8f;

    @Override
    protected void adjustVelocity(float elapsedSeconds) {
        getVelocity().offset(0, - elapsedSeconds * GRAVITY);
    }
}
