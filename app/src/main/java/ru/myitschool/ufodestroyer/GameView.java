package ru.myitschool.ufodestroyer;

import android.content.Context;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import ru.myitschool.ufodestroyer.model.Game;

/**
 * View отвечающий за отрисовку игры
 */
public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private Game game;

    public GameView(Game game, Context context) {
        super(context);
        Log.d("GameView", "Game view created");
        getHolder().addCallback(this);
        this.game = game;
    }

    private GameThread gameThread = null;

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.d("GameView", "Surface created");
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.d("GameView", "Surface changed");
        if (gameThread == null) {
            gameThread = new GameThread(getContext(), game, holder, width, height);
            gameThread.start();
        }
        else
            gameThread.updateSize(width, height);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.d("GameView", "Surface destroyed");
        boolean retry = true;
        // завершаем работу потока
        gameThread.requestStop();
        while (retry) {
            try {
                gameThread.join();
                retry = false;
            } catch (InterruptedException e) {
                // если ожидание почему-то прервалось - будем ждать снова
            }
        }
        gameThread = null;
        Log.d("GameView", "Game thread joined");
    }
}
