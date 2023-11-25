CREATE SEQUENCE IF NOT EXISTS _user_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS mean_plan_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS staff_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS student_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE _user
(
    id        BIGINT NOT NULL,
    firstname VARCHAR(255),
    lastname  VARCHAR(255),
    email     VARCHAR(255),
    password  VARCHAR(255),
    role      VARCHAR(255),
    CONSTRAINT pk__user PRIMARY KEY (id)
);

CREATE TABLE dining_hall
(
    dining_hall_name         VARCHAR(255) NOT NULL,
    dining_hall_address      VARCHAR(255),
    dining_hall_opening_time time WITHOUT TIME ZONE,
    dining_hall_closing_time time WITHOUT TIME ZONE,
    dining_hall_phone_number VARCHAR(255),
    dining_hall_email        VARCHAR(255),
    CONSTRAINT pk_dining_hall PRIMARY KEY (dining_hall_name)
);

CREATE TABLE dish
(
    dish_name        VARCHAR(255) NOT NULL,
    dish_ingredients VARCHAR(255),
    dish_type        VARCHAR(255),
    CONSTRAINT pk_dish PRIMARY KEY (dish_name)
);

CREATE TABLE mean_plan
(
    meal_plan_id       BIGINT NOT NULL,
    meal_plan_duration VARCHAR(255),
    meal_plan_price    DECIMAL,
    CONSTRAINT pk_mean_plan PRIMARY KEY (meal_plan_id)
);

CREATE TABLE menu
(
    menu_name VARCHAR(255) NOT NULL,
    CONSTRAINT pk_menu PRIMARY KEY (menu_name)
);

CREATE TABLE menu_dish_list
(
    dish_name VARCHAR(255) NOT NULL,
    menu_name VARCHAR(255) NOT NULL
);

CREATE TABLE staff
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
    CONSTRAINT pk_staff PRIMARY KEY (staff_id)
);

CREATE TABLE student
(
    student_id            BIGINT NOT NULL,
    student_name          VARCHAR(255),
    student_phone_number  VARCHAR(255),
    student_email         VARCHAR(255),
    student_has_meal_plan BIGINT,
    CONSTRAINT pk_student PRIMARY KEY (student_id)
);

CREATE TABLE student_allergies
(
    student_id        BIGINT NOT NULL,
    student_allergies VARCHAR(255)
);

CREATE TABLE student_food_preferences
(
    student_id               BIGINT NOT NULL,
    student_food_preferences VARCHAR(255)
);

ALTER TABLE staff
    ADD CONSTRAINT FK_STAFF_ON_WORKS_IN_DINING_HALL FOREIGN KEY (works_in_dining_hall) REFERENCES dining_hall (dining_hall_name);

ALTER TABLE student
    ADD CONSTRAINT FK_STUDENT_ON_STUDENT_HAS_MEAL_PLAN FOREIGN KEY (student_has_meal_plan) REFERENCES mean_plan (meal_plan_id);

ALTER TABLE menu_dish_list
    ADD CONSTRAINT fk_mendislis_on_dish FOREIGN KEY (dish_name) REFERENCES dish (dish_name);

ALTER TABLE menu_dish_list
    ADD CONSTRAINT fk_mendislis_on_menu FOREIGN KEY (menu_name) REFERENCES menu (menu_name);

ALTER TABLE student_allergies
    ADD CONSTRAINT fk_student_allergies_on_student FOREIGN KEY (student_id) REFERENCES student (student_id);

ALTER TABLE student_food_preferences
    ADD CONSTRAINT fk_student_food_preferences_on_student FOREIGN KEY (student_id) REFERENCES student (student_id);