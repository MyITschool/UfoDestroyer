package ru.myitschool.ufodestroyer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;

import ru.myitschool.ufodestroyer.model.Game;
import ru.myitschool.ufodestroyer.renderer.GameRenderer;

/**
 * Поток, отвечающий за отрисовку игры
 */
public class GameThread extends Thread {
    private SurfaceHolder surfaceHolder;
    private volatile boolean running = true;
    private long lastTime = System.currentTimeMillis();
    private long lastFpsTime = System.currentTimeMillis();
    private int fpsCounter = 0;
    private int lastFps = 0;

    private Game game;

    public GameThread(Game game, SurfaceHolder surfaceHolder, float width, float height) {
        this.surfaceHolder = surfaceHolder;
        this.game = game;
        game.setGameFieldSize(width, height);
    }

    /**
     * Обновляет размеры поверхности
     * @param width ширина в пикселях
     * @param height высота в пикселях
     */
    public void updateSize(float width, float height) {
        // чтобы ширина и высота менялись атомарно и не в середине расчетов/отрисовки
        synchronized (this) {
            game.setGameFieldSize(width, height);
        }
    }

    /**
     * Устанавливает флаг остановки для этого потока. Как только окончится очередной игровой цикл,
     * поток завершит свою работу
     */
    public void requestStop() {
        running = false;
    }

    @Override
    public void run() {
        try {
            GameRenderer renderer = new GameRenderer(game);
            try {
                while (running) {
                    long currentTime = System.currentTimeMillis();
                    float elapsedSeconds = (currentTime - lastTime) / (1000.0f);
                    lastTime = currentTime;

                    // чтобы ширина и высота менялись атомарно и не в середине расчетов/отрисовки
                    synchronized (this) {
                        game.update(elapsedSeconds);

                        if (!running)
                            break;

                        Canvas canvas = surfaceHolder.lockCanvas();
                        if (canvas != null) {
                            try {
                                renderer.render(canvas);
                                Paint paint = new Paint();
                                paint.setColor(Color.BLACK);
                                paint.setTextAlign(Paint.Align.LEFT);
                                paint.setTextSize(40f);
                                canvas.drawText("Fps: " + lastFps, 0, 50, paint);
                            } finally {
                                surfaceHolder.unlockCanvasAndPost(canvas);
                            }
                        }
                    }

                    fpsCounter++;
                    if (currentTime - lastFpsTime > 1000) {
                        lastFpsTime = currentTime;
                        lastFps = fpsCounter;
                        fpsCounter = 0;
                    }
                }
            } finally {
                renderer.close();
            }
        }
        catch (Exception e) {
            Log.e("GameThread", "Exception occured in GameThread", e);
        } catch (Error e) {
            Log.e("GameThread", "Error occured in GameThread", e);
        }
    }
}
