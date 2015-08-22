package ru.myitschool.ufodestroyer.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import ru.myitschool.ufodestroyer.model.entities.Enemy;
import ru.myitschool.ufodestroyer.model.entities.GameObject;
import ru.myitschool.ufodestroyer.model.entities.HasCoordsAndSize;
import ru.myitschool.ufodestroyer.model.entities.MovingGameObject;
import ru.myitschool.ufodestroyer.model.entities.Player;
import ru.myitschool.ufodestroyer.model.util.SimpleCoordsAndSize;
import ru.myitschool.ufodestroyer.model.util.Tools;

/**
 * Класс, реализующий логику игры. Для подробного поисания см {@link Game}
 */
public class GameImpl implements Game {
    /**
     * Максимальное время, которое может пройти между обновлениями игровой модели;
     * Необходимо для того, чтобы быть уверенным, что проверка коллизий произойдет
     * верно
     */
    private static final float MAX_SECONDS_BETWEEN_UPDATES = 0.1f;

    /**
     * Время между появлением врагов, в секундах
     */
    private static final float SECONDS_BETWEEN_ENEMY_SPAWNS = 2.0f;

    private Player player = new Player();
    {
        player.getSize().set(10.0f, 10.0f);
    }


    private List<GameObject> gameObjects = new ArrayList<>();

    {
        gameObjects.add(player);
    }

    private List<EventListener> listeners = new ArrayList<>();

    private float gameFieldHeight;
    private final float gameFieldWidth = 20;

    @Override
    public void setFieldWidthHeightRatio(float width, float height) {
        gameFieldHeight = 20 * height / width;
    }

    @Override
    public void addEventListener(EventListener listener) {
        if (listener == null)
            throw new NullPointerException("Listener should never be null");
        listeners.add(listener);
    }

    @Override
    public void removeEventListener(EventListener listener) {
        listeners.remove(listener);
    }

    @Override
    public List<GameObject> getGameObjects() {
        return Collections.unmodifiableList(gameObjects);
    }

    @Override
    public void update(float elapsedSeconds) {
        while (elapsedSeconds > 0) {
            float tick;
            if (elapsedSeconds > MAX_SECONDS_BETWEEN_UPDATES)
                tick = MAX_SECONDS_BETWEEN_UPDATES;
            else
                tick = elapsedSeconds;

            calcPositions(tick);
            deleteOffMapObjects();
            spawnEnemies(tick);
            elapsedSeconds -= tick;
        }
    }

    /**
     * Удаляет объекты, которые находятся за пределами игрового поля
     */
    private void deleteOffMapObjects() {
        HasCoordsAndSize gameField = new SimpleCoordsAndSize(0, gameFieldHeight / 2, gameFieldWidth, gameFieldHeight);

        Iterator<GameObject> objectIterator = gameObjects.iterator();
        while (objectIterator.hasNext()) {
            GameObject object = objectIterator.next();
            if (!Tools.intersects(object, gameField))
                objectIterator.remove();
        }
    }

    /**
     * Обновляет позиции движущихся объектов
     * @param elapsedSeconds количество секунд, которые прошли в игровом мире
     */
    private void calcPositions(float elapsedSeconds) {
        for (GameObject object: gameObjects)
            if (object instanceof MovingGameObject)
                ((MovingGameObject) object).update(elapsedSeconds);
    }

    /**
     * Время с момента появления последнего противника
     */
    private float timeSinceLastSpawn = 0;

    /**
     * Создает новых врагов, если необходимо
     * @param elapsedSeconds количество секунд, которые прошли в игровом мире
     */
    private void spawnEnemies(float elapsedSeconds) {
        timeSinceLastSpawn += elapsedSeconds;
        if (timeSinceLastSpawn > SECONDS_BETWEEN_ENEMY_SPAWNS) {
            timeSinceLastSpawn -= SECONDS_BETWEEN_ENEMY_SPAWNS;
            spawnEnemy();
        }
    }

    private static final Random random = new Random();

    /**
     * Создает нового случайного противника
     */
    private void spawnEnemy() {
        Enemy enemy = new Enemy();
        float direction = 1f;
        if (random.nextBoolean())
            direction = -1f;
        enemy.getCoords().set(direction * gameFieldWidth / 2,
                (gameFieldHeight / 4) + random.nextFloat() * (gameFieldHeight / 2));
        enemy.getSize().set(ENEMY_WIDTH, ENEMY_HEIGHT);

        float dirY = (random.nextFloat() - 0.5f) * 3f;
        enemy.getVelocity().set( - direction * 3f, dirY * 0.5f);

        for (EventListener listener: listeners)
            listener.onEnemySpawned(enemy);

        gameObjects.add(enemy);
    }
}
