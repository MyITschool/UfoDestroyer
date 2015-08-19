package ru.myitschool.ufodestroyer.model.entities;

import android.graphics.PointF;

/**
 * Класс-предок для большинства игровых объектов, таких как пули и противники.
 * Хранит свои координаты на поле и размеры. Может хранить вспомогательный объект
 * для упрощения рендеринга
 */
public class GameObject implements HasCoordsAndSize {
    private PointF coords = new PointF();
    private PointF size = new PointF();
    private Object renderingHelper = null;

    /**
     * @return Координаты центра объекта
     */
    public PointF getCoords() {
        return coords;
    }

    /**
     * @return Размеры объекта (ширина и высота)
     */
    public PointF getSize() {
        return size;
    }

    /**
     * @return Вспомогательный объект для хранения дополнительных данных для отрисовки.
     * Например, в нем можно хранить текущую фазу анимации или другие вспомогательные данные,
     * которые нужно связать с игровым объектом, но которое не нужны для собственно расчета
     */
    public Object getRenderingHelper() {
        return renderingHelper;
    }

    /**
     * Задает вспомогательный объект для хранения дополнительных данных для отрисовки.
     * Например, в нем можно хранить текущую фазу анимации или другие вспомогательные данные,
     * которые нужно связать с игровым объектом, но которое не нужны для собственно расчета
     * @param renderingHelper объект, который можно будет получить с помощью getRenderingHelper()
     */
    public void setRenderingHelper(Object renderingHelper) {
        this.renderingHelper = renderingHelper;
    }
}
