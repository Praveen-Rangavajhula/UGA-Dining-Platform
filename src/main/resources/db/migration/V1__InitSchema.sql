-- Drop sequences if they exist
DROP SEQUENCE IF EXISTS user_sequence;
DROP SEQUENCE IF EXISTS meal_plan_sequence;
DROP SEQUENCE IF EXISTS staff_sequence;
DROP SEQUENCE IF EXISTS student_sequence;

-- Create sequences
CREATE SEQUENCE IF NOT EXISTS user_sequence START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS meal_plan_sequence START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS staff_sequence START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS student_sequence START WITH 1 INCREMENT BY 1;

-- Drop tables if they exist
DROP TABLE IF EXISTS student_food_preferences;
DROP TABLE IF EXISTS student_allergies;
DROP TABLE IF EXISTS menu_dish_list;
DROP TABLE IF EXISTS student;
DROP TABLE IF EXISTS staff;
DROP TABLE IF EXISTS menu;
DROP TABLE IF EXISTS meal_plan;
DROP TABLE IF EXISTS dish;
DROP TABLE IF EXISTS dining_hall;
DROP TABLE IF EXISTS _user;

-- Create tables
CREATE TABLE IF NOT EXISTS _user
(
    user_id   BIGINT NOT NULL,
    firstname VARCHAR(255),
    lastname  VARCHAR(255),
    email     VARCHAR(255),
    password  VARCHAR(255),
    role      VARCHAR(255),
    CONSTRAINT pk__user PRIMARY KEY (user_id)
);

CREATE TABLE IF NOT EXISTS dining_hall
(
    dining_hall_name         VARCHAR(255) NOT NULL,
    dining_hall_address      VARCHAR(255),
    dining_hall_opening_time time WITHOUT TIME ZONE,
    dining_hall_closing_time time WITHOUT TIME ZONE,
    dining_hall_phone_number VARCHAR(255),
    dining_hall_email        VARCHAR(255),
    CONSTRAINT pk_dining_hall PRIMARY KEY (dining_hall_name)
);

CREATE TABLE IF NOT EXISTS dish
(
    dish_name        VARCHAR(255) NOT NULL,
    dish_ingredients VARCHAR(255),
    dish_type        VARCHAR(255),
    CONSTRAINT pk_dish PRIMARY KEY (dish_name)
);

CREATE TABLE IF NOT EXISTS meal_plan
(
    meal_plan_id       BIGINT NOT NULL,
    meal_plan_duration VARCHAR(255),
    meal_plan_price    DECIMAL,
    CONSTRAINT pk_meal_plan PRIMARY KEY (meal_plan_id)
);

CREATE TABLE IF NOT EXISTS menu
(
    menu_name VARCHAR(255) NOT NULL,
    dining_hall_name VARCHAR(255),
    CONSTRAINT pk_menu PRIMARY KEY (menu_name)
);

CREATE TABLE IF NOT EXISTS menu_dish_list
(
    dish_name VARCHAR(255) NOT NULL,
    menu_name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS staff
(
    staff_id             BIGINT NOT NULL,
    staff_name           VARCHAR(255),
    staff_designation    VARCHAR(255),
    staff_phone_number   VARCHAR(255),
    staff_email          VARCHAR(255),
    staff_salary         DECIMAL,
    work_start_time      time WITHOUT TIME ZONE,
    work_end_time        time WITHOUT TIME ZONE,
    works_in_dining_hall VARCHAR(255),
    CONSTRAINT pk_staff PRIMARY KEY (staff_id),
    CONSTRAINT FK_STAFF_ON_WORKS_IN_DINING_HALL FOREIGN KEY (works_in_dining_hall) REFERENCES dining_hall (dining_hall_name)
);

CREATE TABLE IF NOT EXISTS student
(
    student_id            BIGINT NOT NULL,
    student_name          VARCHAR(255),
    student_phone_number  VARCHAR(255),
    student_email         VARCHAR(255),
    student_has_meal_plan BIGINT,
    CONSTRAINT pk_student PRIMARY KEY (student_id),
    CONSTRAINT FK_STUDENT_ON_STUDENT_HAS_MEAL_PLAN FOREIGN KEY (student_has_meal_plan) REFERENCES meal_plan (meal_plan_id)
);

CREATE TABLE IF NOT EXISTS student_allergies
(
    student_id        BIGINT NOT NULL,
    student_allergies VARCHAR(255),
    CONSTRAINT fk_student_allergies_on_student FOREIGN KEY (student_id) REFERENCES student (student_id)
);

CREATE TABLE IF NOT EXISTS student_food_preferences
(
    student_id               BIGINT NOT NULL,
    student_food_preferences VARCHAR(255),
    CONSTRAINT fk_student_food_preferences_on_student FOREIGN KEY (student_id) REFERENCES student (student_id)
);

ALTER TABLE menu_dish_list
    ADD CONSTRAINT fk_mendislis_on_dish FOREIGN KEY (dish_name) REFERENCES dish (dish_name);

ALTER TABLE menu_dish_list
    ADD CONSTRAINT fk_mendislis_on_menu FOREIGN KEY (menu_name) REFERENCES menu (menu_name);
