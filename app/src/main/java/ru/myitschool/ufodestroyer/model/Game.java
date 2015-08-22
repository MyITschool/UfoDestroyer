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
 * Ширина игрового поля равна 20 единицам (константа {@link #FIELD_WIDTH}). Высота игрового поля зависит
 * от соотношения сторон устройства и должна быть установлена путем вызова метода
 * {@link #setFieldWidthHeightRatio} до начала самой игры. Нижняя сторона игрового поля совпадает
 * с осью x (направленной вправо), а ось y направлена вверх из середины игрового поля.
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
 * (-10,0)      (0,0)     (10,0)
 * </pre>
 */
public interface Game {
    public static final float FIELD_WIDTH = 20f;

    public static final float ENEMY_WIDTH = 2f;
    public static final float ENEMY_HEIGHT = 0.52532f;



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
     * Задает соотношение сторон игрового поля. Числа width и height должны относится также,
     * как относятся по размеру стороны игрового поля для правильного расчета игры
     * @param width ширина поля, в любых единицах измерения (одинаковых для width и height)
     * @param height высота поля, в любых единицах измерения (одинаковых для width и height)
     */
    void setFieldWidthHeightRatio(float width, float height);

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
