package ru.myitschool.ufodestroyer.renderer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.Log;

import ru.myitschool.ufodestroyer.model.Game;
import ru.myitschool.ufodestroyer.model.entities.Bullet;
import ru.myitschool.ufodestroyer.model.entities.Enemy;
import ru.myitschool.ufodestroyer.model.entities.GameObject;

/**
 * Класс, который отвечает за отрисовку игры
 */
public class GameRenderer implements Game.EventListener, AutoCloseable {
    private Game game;

    /**
     * Создает новый экземпляр рендерера
     * @param game игровая модель
     */
    public GameRenderer(Game game) {
        this.game = game;
        game.addEventListener(this);
    }

    @Override
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
        canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), paint);

        for (GameObject object: game.getGameObjects()) {
            if (object.getRenderingHelper() != null) {
                Renderer renderer = (Renderer)object.getRenderingHelper();
                renderer.render(canvas, object);
            }
        }
    }

    private final EnemyRenderer enemyRenderer = new EnemyRenderer();

    @Override
    public void onBulletFired(Bullet bullet) {

    }

    @Override
    public void onEnemySpawned(Enemy enemy) {
        enemy.setRenderingHelper(enemyRenderer);
    }
}
