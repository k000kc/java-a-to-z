/**
 * Created by Andrey on 04.07.2017.
 *
 * 1. Сделать класс многопоточный счетчик Count#int incremant().
 * 2. Подключить библиотеку jcip-annotations
 * https://mvnrepository.com/artifact/net.jcip/jcip-annotations/1.0
 * Для этого добавить в блок dependencies следующий код.
 * <dependency>
 *     <groupId>net.jcip</groupId>
 *     <artifactId>jcip-annotations</artifactId>
 *     <version>1.0</version>
 * </dependency>
 * 3. В заголовке класса указать аннотацию @ThreadSafe
 * 4. Для поля состояния использовать аннотацию @GuardedBy
 * 5. В аннотации GuardedBy - указать объект монитора.
 */
package ru.apetrov;