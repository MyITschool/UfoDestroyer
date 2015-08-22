package ru.myitschool.ufodestroyer.model.entities;

import android.graphics.PointF;

/**
 * Движущийся игровой объект. Хранит также свою скорость, помимо координат и размеров и имеет метод,
 * отвечающий за перерасчет скорости
 */
public abstract class MovingGameObject extends GameObject {
    private PointF velocity = new PointF();

    /**
     * @return Скорость объекта
     */
    public PointF getVelocity() {
        return velocity;
    }

    /**
     * Этот метод должен быть перекрыт в наследниках и может изменять скорость объекта. Цель этого
     * метода - задать значения скорости на основании какого-то закона, по которому перемещается
     * этот объект
     * @param elapsedSeconds сколько секунд прошло с прошлого вызова
     */
    protected abstract void adjustVelocity(float elapsedSeconds);

    /**
     * Расчитывает новую позицию объекта
     * @param elapsedSeconds сколько секунд прошло с прошлого вызова
     */
    public void update(float elapsedSeconds) {
        adjustVelocity(elapsedSeconds);
        getCoords().offset(velocity.x * elapsedSeconds, velocity.y * elapsedSeconds);
    }
}
