package ru.myitschool.ufodestroyer;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import ru.myitschool.ufodestroyer.model.Game;
import ru.myitschool.ufodestroyer.model.GameImpl;

public class MainActivity extends Activity {

    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        game = new GameImpl();
        setContentView(new GameView(game, this));
    }
}
