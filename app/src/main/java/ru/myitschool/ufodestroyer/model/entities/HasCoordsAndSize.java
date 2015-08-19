package ru.myitschool.ufodestroyer.model.entities;

import android.graphics.PointF;

/**
 * Общий интерфейс для объектов, у которых есть положение и размер
 */
public interface HasCoordsAndSize {
    /**
     * @return Координаты центра объекта
     */
    public PointF getCoords();

    /**
     * @return Размеры объекта (ширина и высота)
     */
    public PointF getSize();
}
