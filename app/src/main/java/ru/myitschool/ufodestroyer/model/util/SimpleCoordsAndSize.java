package ru.myitschool.ufodestroyer.model.util;

import android.graphics.PointF;

import ru.myitschool.ufodestroyer.model.entities.HasCoordsAndSize;

/**
 * Простой класс, хранящий координаты и ширину с высоток
 */
public class SimpleCoordsAndSize implements HasCoordsAndSize {
    private PointF coords = new PointF();
    private PointF size = new PointF();

    @Override
    public PointF getCoords() {
        return coords;
    }

    @Override
    public PointF getSize() {
        return size;
    }

    /**
     * Создает новый экземпляр
     * @param x Координата центра по оси x
     * @param y Координата центра по оси y
     * @param width Ширина объекта
     * @param height Высота объекта
     */
    public SimpleCoordsAndSize(float x, float y, float width, float height) {
        this.coords.set(x, y);
        this.size.set(width, height);
    }
}
