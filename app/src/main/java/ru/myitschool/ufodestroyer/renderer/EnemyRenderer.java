package ru.myitschool.ufodestroyer.renderer;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;

import ru.myitschool.ufodestroyer.model.Game;
import ru.myitschool.ufodestroyer.model.entities.HasCoordsAndSize;
import ru.myitschool.ufodestroyer.model.util.Tools;

import static ru.myitschool.ufodestroyer.model.util.Tools.gamePointToCanvasPoint;

/**
 * Класс, отвечающий за отрисовку врагов
 */
public class EnemyRenderer implements Renderer {
    private final Bitmap bitmap;
    private final float pixelWidth;
    private final float pixelHeight;

    public EnemyRenderer(Bitmap bitmap, float width, float height) {
        pixelWidth = Math.round(Tools.gameSizeToCanvasSize(Game.ENEMY_WIDTH, width, height));
        pixelHeight = Math.round(Tools.gameSizeToCanvasSize(Game.ENEMY_HEIGHT, width, height));

        this.bitmap = Bitmap.createScaledBitmap(bitmap, Math.round(pixelWidth), Math.round(pixelHeight), true);
    }

    private final Paint paint = new Paint();
    {
        paint.setStyle(Paint.Style.FILL);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
    }

    @Override
    public void render(Canvas canvas, HasCoordsAndSize coordsAndSize) {
        PointF point = gamePointToCanvasPoint(coordsAndSize.getCoords(),
                canvas.getWidth(), canvas.getHeight());

        canvas.drawBitmap(bitmap, point.x - pixelWidth / 2f, point.y - pixelHeight / 2f, paint);
    }
}
