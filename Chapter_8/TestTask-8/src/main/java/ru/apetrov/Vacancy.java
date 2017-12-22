package ru.apetrov;

import java.sql.Timestamp;

/**
 * Created by Andrey on 18.12.2017.
 */
public class Vacancy {

    /**
     * вакансия.
     */
    private String name;

    /**
     * автор разместивший вакансию.
     */
    private String author;

    /**
     * дата публикации вакансии.
     */
    private Timestamp createDate;

    /**
     * Конструктор.
     * @param name вакансия.
     * @param author автор разместивший вакансию.
     * @param createDate дата публикации вакансии.
     */
    public Vacancy(String name, String author, Timestamp createDate) {
        this.name = name;
        this.author = author;
        this.createDate = createDate;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vacancy vacancy = (Vacancy) o;

        if (name != null ? !name.equals(vacancy.name) : vacancy.name != null) return false;
        if (author != null ? !author.equals(vacancy.author) : vacancy.author != null) return false;
        return createDate != null ? createDate.equals(vacancy.createDate) : vacancy.createDate == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        return result;
    }
}
