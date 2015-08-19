package ru.myitschool.ufodestroyer.model;

import java.util.List;

import ru.myitschool.ufodestroyer.model.entities.Bullet;
import ru.myitschool.ufodestroyer.model.entities.Enemy;
import ru.myitschool.ufodestroyer.model.entities.GameObject;

/**
 * Интерфейс для игровой модели. Сюда выделено всё, что об игровой модели нужно знать извне.
 *
 * Игровая модель хранит все игровые объекты и отвечает за расчет их новых
 * позиций, столкновений. Предоставляет оповещения о происходящих событиях.
 *
 * Размеры игрового поля должны быть установлены с помощью метода setGameFieldSize до начала
 * самой игры. Нижняя сторона игрового поля совпадает с осью x (направленной вправо), а ось y
 * направлена вверх из середины экрана.
 *
 * <pre>
 *
 *                ↑ (y)
 *                |
 *     -----------+-----------
 *     | Игровое  |   поле   |
 *     |          |          |
 *     |          |          |
 *     |          |          |
 *     -----------+----------|-------→ (x)
 *              (0,0)
 * </pre>
 */
public interface Game {
    /**
     * События, о которых игровая модель оповещает всех заинтересованные стороны
     */
    interface EventListener {
        /**
         * Оповещает о совершении выстрела
         * @param bullet выстрел
         */
        void onBulletFired(Bullet bullet);

        /**
         * Оповещает о появлении врага
         * @param enemy противник
         */
        void onEnemySpawned(Enemy enemy);
    }

    /**
     * Подписывает слушателя на события от игровой модели
     * @param listener слушатель, который будет получать события; не должен быть null
     * @throws NullPointerException если listener равен null
     */
    public void addEventListener(EventListener listener);

    /**
     * Отменяет подписку слушателя на события от игровой модели
     * @param listener слушатель, который более не будет получать события
     */
    public void removeEventListener(EventListener listener);

    /**
     * @return Ширину игрового поля, в пикселях
     */
    float getGameFieldWidth();

    /**
     * @return Высоту игрового поля, в пикселях
     */
    float getGameFieldHeight();

    /**
     * Задает размеры игрового поля, в пикселях
     * @param width ширина
     * @param height высота
     */
    void setGameFieldSize(float width, float height);

    /**
     * @return Список всех игровых объектов; не подлежит изменению
     */
    List<GameObject> getGameObjects();

    /**
     * Рассчитывает новое состояние игры по прошествии указанного времени
     * @param elapsedSeconds количество секунд, которые должны пройти в игровом мире
     */
    public void update(float elapsedSeconds);
}
