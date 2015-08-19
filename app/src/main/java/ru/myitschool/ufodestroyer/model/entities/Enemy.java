package ru.myitschool.ufodestroyer.model.entities;

import android.graphics.PointF;

import static java.lang.Math.*;

/**
 * Класс врага
 */
public class Enemy extends MovingGameObject {
    private float rotateSpeed;
    private PointF direction = new PointF();

    /**
     * @return Скорость поворота объекта, в радианах в секунду
     */
    public float getRotateSpeed() {
        return rotateSpeed;
    }

    /**
     * @return Направление, куда движется противник
     */
    public PointF getDirection() {
        return direction;
    }

    /**
     * Задает скорость поворота объекта
     * @param rotateSpeed скорость поворота объекта, в радианах в секунду
     */
    public void setRotateSpeed(float rotateSpeed) {
        this.rotateSpeed = rotateSpeed;
    }

    @Override
    protected void adjustVelocity(float elapsedSeconds) {
        // общая логика:
        // 1. Поворачиваем скорость на угол
        // 2. Размер скорости меняется в зависимости от того, в каком направлении мы движемся
        PointF v = getVelocity();

        // поворот на некоторый угол
        double angle = getRotateSpeed() * elapsedSeconds;
        v.set((float) (v.x * cos(angle) - v.y * sin(angle)),
                (float) (v.x * sin(angle) + v.y * cos(angle)));

        v.set(v.x + direction.x * elapsedSeconds, v.y + direction.y * elapsedSeconds);
    }
}
