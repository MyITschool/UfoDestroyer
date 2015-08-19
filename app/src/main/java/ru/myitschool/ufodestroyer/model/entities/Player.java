package ru.myitschool.ufodestroyer.model.entities;

/**
 * Игровой объект игрока - пушка
 */
public class Player extends GameObject {
    private float angle;

    /**
     * @return Угол поворота пушки против часовой стрелки в радианах. Если пушка смотрит вправо,
     *   возвращает ноль. Если пушка смотрит влево, возвращает пи.
     */
    public float getAngle() {
        return angle;
    }

    /**
     * Задает угол поворота пушки.
     * @param angle Угол поворота пушки против часовой стрелки в радианах. Если пушка смотрит
     *              вправо, должен быть равен нулю. Если пушка смотрит влево, должен быть равен Pi.
     */
    public void setAngle(float angle) {
        while (angle < 0)
            angle += Math.PI;
        while (angle > Math.PI)
            angle -= Math.PI;
        this.angle = angle;
    }
}
