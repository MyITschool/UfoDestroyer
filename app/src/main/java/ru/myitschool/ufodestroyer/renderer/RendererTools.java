package ru.myitschool.ufodestroyer.renderer;

import android.graphics.Point;
import android.graphics.PointF;

import ru.myitschool.ufodestroyer.model.Game;

/**
 * Вспомогательные вычисления для рендерера
 */
public class RendererTools {
    /**
     * Расчитывает новые размеры прямоугольника с сохранением соотношения сторон таким образом,
     * чтобы он помещался (был вписан) в другой прямоугольник.
     * @param whatToFit прямоугольник, который нужно вписать. x - ширина, y - высота
     * @param fitArea прямоугольник, в который нужно вписать. x - ширина, y - высота
     * @return прямоугольник, который помещается в fitArea (таким образом, результат совпадает с
     *         fitArea или по ширине, или по высоте, или по тому и другому) и имеет то же
     *         соотношение сторон, что и whatToFit. x - ширина, y - высота
     * @throws IllegalArgumentException если ширина или высота любого из параметров равна нулю
     */
    public static Point calcFitIntoSize(Point whatToFit, Point fitArea) {
        if (whatToFit.x == 0 || whatToFit.y == 0 || fitArea.x == 0 || fitArea.y == 0)
            throw new IllegalArgumentException("Degenerate rectangles not allowed");

        double scaleX = ((double)fitArea.x) / whatToFit.x;
        double scaleY = ((double)fitArea.y) / whatToFit.y;
        if (scaleX < scaleY) {
            return new Point(fitArea.x, (int) Math.round(scaleX * whatToFit.y));
        } else {
            return new Point((int) Math.round(scaleY * whatToFit.x), fitArea.y);
        }
    }

    /**
     * Расчитывает новые размеры прямоугольника с сохранением соотношения сторон таким образом,
     * чтобы он вмещал в себя другой прямоугольник (был описан вокруг него).
     * @param whatToFit прямоугольник, вокруг которого нужно описать другой. x - ширина, y - высота
     * @param fitArea прямоугольник, который должен вмещаться в whatToFit. x - ширина, y - высота
     * @return прямоугольник, в который помещается fitArea (таким образом, результат совпадает с
     *         fitArea или по ширине, или по высоте, или по тому и другому) и имеет то же
     *         соотношение сторон, что и whatToFit. x - ширина, y - высота
     * @throws IllegalArgumentException если ширина или высота любого из параметров равна нулю
     */
    public static Point calcFitOutroSize(Point whatToFit, Point fitArea) {
        if (whatToFit.x == 0 || whatToFit.y == 0 || fitArea.x == 0 || fitArea.y == 0)
            throw new IllegalArgumentException("Degenerate rectangles not allowed");

        double scaleX = ((double)fitArea.x) / whatToFit.x;
        double scaleY = ((double)fitArea.y) / whatToFit.y;
        if (scaleX > scaleY) {
            return new Point(fitArea.x, (int) Math.round(scaleX * whatToFit.y));
        } else {
            return new Point((int) Math.round(scaleY * whatToFit.x), fitArea.y);
        }
    }

    /**
     * Вычисляет координаты точки для рисования, зная координаты в игровом мире (см. {@link ru.myitschool.ufodestroyer.model.Game}
     * @param point Координаты точки в игровой модели
     * @param width ширина канвы для рисования
     * @param height высота канвы для рисования
     * @return координаты точки на канве, которая соответствует точке point в игровой модели
     */
    public static PointF gamePointToCanvasPoint(PointF point, float width, float height) {
        float scalingFactor = width / Game.FIELD_WIDTH;
        float x = (point.x / (Game.FIELD_WIDTH / 2f)) * (width / 2f) + (width / 2f);
        float y = height - scalingFactor * point.y;
        return new PointF(x, y);
    }

    /**
     * Вычисляет расстояние или размер чего-либо на экране в пикселях, зная размеры в игровом мире
     * (см. {@link ru.myitschool.ufodestroyer.model.Game}
     * @param size расстояние или размер чего-либо в игровом мире
     * @param width ширина канвы для рисования
     * @param height высота канвы для рисования
     * @return расстояние в пикселях, соответствующее расстоянию size в игровом мире
     */
    public static float gameSizeToCanvasSize(float size, float width, float height) {
        float scalingFactor = width / Game.FIELD_WIDTH;
        return scalingFactor * size;
    }


}
