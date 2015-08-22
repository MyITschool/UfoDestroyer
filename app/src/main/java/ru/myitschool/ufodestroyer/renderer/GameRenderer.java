package ru.myitschool.ufodestroyer.renderer;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

import ru.myitschool.ufodestroyer.R;
import ru.myitschool.ufodestroyer.model.Game;
import ru.myitschool.ufodestroyer.model.entities.Bullet;
import ru.myitschool.ufodestroyer.model.entities.Enemy;
import ru.myitschool.ufodestroyer.model.entities.GameObject;

/**
 * Класс, который отвечает за отрисовку игры
 */
public class GameRenderer implements Game.EventListener {
    private Game game;
    private Resources resources;
    private Bitmap background;
    private EnemyRenderer enemyRenderer;

    /**
     * Создает новый экземпляр рендерера
     * @param game игровая модель
     */
    public GameRenderer(Game game, Resources resources) {
        this.game = game;
        this.resources = resources;
        game.addEventListener(this);
    }

    /**
     * Вызывается, чтобы сообщить о размерах игрового поля
     * @param width ширина в пикселях
     * @param height высота в пикселях
     */
    public void setGameFieldSize(int width, int height) {
        // загрузка ресурсов и их масштабирование
        // задний фон
        Bitmap originalBackground = BitmapFactory.decodeResource(resources, R.drawable.background);
        Point destSize = RendererTools.calcFitOutroSize(
                new Point(originalBackground.getWidth(), originalBackground.getHeight()),
                new Point(width, height));
        background = Bitmap.createScaledBitmap(originalBackground, destSize.x, destSize.y, true);

        // противник
        Bitmap ufo = BitmapFactory.decodeResource(resources, R.drawable.ufo);
        enemyRenderer = new EnemyRenderer(ufo, width, height);

        // TODO: остальные ресурсы
    }

    public void close() throws Exception {
        game.removeEventListener(this);
    }

    private final Paint paint = new Paint();
    {
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
    }

    /**
     * Рисует текущее состояние игры
     * @param canvas канва для рисования на ней текущего состояния игры
     */
    public void render(Canvas canvas) {
        canvas.drawBitmap(background, (canvas.getWidth() - background.getWidth()) / 2,
                (canvas.getHeight() - background.getHeight()) / 2, paint);

        for (GameObject object: game.getGameObjects()) {
            if (object.getRenderingHelper() != null) {
                Renderer renderer = (Renderer)object.getRenderingHelper();
                renderer.render(canvas, object);
            }
        }
    }

    @Override
    public void onBulletFired(Bullet bullet) {

    }

    @Override
    public void onEnemySpawned(Enemy enemy) {
        enemy.setRenderingHelper(enemyRenderer);
    }
}
