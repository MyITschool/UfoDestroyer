package ru.myitschool.ufodestroyer.renderer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import ru.myitschool.ufodestroyer.model.entities.HasCoordsAndSize;

/**
 * Класс, отвечающий за отрисовку врагов
 */
public class EnemyRenderer implements Renderer {
    private final Paint paint = new Paint();
    {
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    public void render(Canvas canvas, HasCoordsAndSize coordsAndSize) {
        float x = coordsAndSize.getCoords().x + (canvas.getWidth() / 2);
        float y = canvas.getHeight() - coordsAndSize.getCoords().y;
        canvas.drawCircle(x, y,
                coordsAndSize.getSize().x / 2, paint);
    }
}
