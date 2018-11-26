/**
 * Created by Andrey on 12.03.2017.
 * Доделать контейнер SimpleArray<T> добавить методы add, update, delete, get(int index);
 *
 * Сделать интерфейс Store<T extends Base> где Base
 * Это абстрактный класс для моделей c методами String getId(); void setId(String id).
 * 1. Сделать два класса User, и Role которые наследуют данный класс.
 * 2. Сделать два хранилища UserStore и RoleStore. Внутри для хранения данных использовать SimpleArray.
 * 3. Методы добавить, обновить, удалить.
 * 4. Помните. про инкапсуляцию. В методах dao нельзя использовать методы c index.
 */
package ru.apetrov.generic;