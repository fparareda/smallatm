package org.fakebank.model;

public class User {

    private String id;
    private String name;
    private String surname;

    public static final class UserBuilder {
        private String id;
        private String name;
        private String surname;

        private UserBuilder() {
        }

        public static UserBuilder anUser() {
            return new UserBuilder();
        }

        public UserBuilder withId(String id) {
            this.id = id;
            return this;
        }

        public UserBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder withSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public User build() {
            User user = new User();
            user.id = this.id;
            user.name = this.name;
            user.surname = this.surname;
            return user;
        }
    }
}
