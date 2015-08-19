package ru.myitschool.ufodestroyer.model.util;

import android.graphics.RectF;

import ru.myitschool.ufodestroyer.model.entities.HasCoordsAndSize;

/**
 * Вспомогательные методы
 */
public class Tools {

    /**
     * Проверяет, пересекаются ли два объекта, заданных прямоугольниками
     * @param o1 Первый объект
     * @param o2 Второй объект
     * @return true, если объекты пересекаются и false в противном случае
     */
    public static boolean intersects(HasCoordsAndSize o1, HasCoordsAndSize o2) {
        RectF rect1 = new RectF(o1.getCoords().x - (o1.getSize().x / 2),
                o1.getCoords().y - (o1.getSize().y / 2),
                o1.getCoords().x + (o1.getSize().x / 2),
                o1.getCoords().y + (o1.getSize().y / 2));

        RectF rect2 = new RectF(o2.getCoords().x - (o2.getSize().x / 2),
                o2.getCoords().y - (o2.getSize().y / 2),
                o2.getCoords().x + (o2.getSize().x / 2),
                o2.getCoords().y + (o2.getSize().y / 2));

        return RectF.intersects(rect1, rect2);
    }
}
