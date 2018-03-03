package ru.apetrov.model;

import java.sql.Timestamp;

/**
 * user.
 */
public class User {

        /**
         * User name.
         */
        private String name;

        /**
         * User login.
         */
        private String login;

        /**
         * User E-mail.
         */
        private String email;

        /**
         * User create date.
         */
        private Timestamp createDate;

        /**
         * Constructor.
         * @param login login
         * @param name name
         * @param email e-mail
         * @param createDate create date.
         */
        public User(String login, String name, String email, Timestamp createDate) {
            this.login = login;
            this.name = name;
            this.email = email;
            this.createDate = createDate;
        }

        /**
         * Getter.
         * @return name.
         */
        public String getName() {
            return name;
        }

        /**
         * Getter.
         * @return login.
         */
        public String getLogin() {
            return login;
        }

        /**
         * Getter.
         * @return e-mail.
         */
        public String getEmail() {
            return email;
        }

        /**
         * Getter.
         * @return create date.
         */
        public Timestamp getCreateDate() {
            return createDate;
        }

        @Override
        public String toString() {
            return String.format("User: login - %s, name - %s, email - %s, create date - %s", login, name, email, createDate);
        }
}
