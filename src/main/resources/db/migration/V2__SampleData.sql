-- Meal plans
INSERT INTO meal_plan (
    meal_plan_id,
    meal_plan_duration,
    meal_plan_price
) VALUES
      (1, '3 months', 199.99),
      (2, '6 months', 399.99);

-- Student
INSERT INTO student (student_id, student_name, student_phone_number, student_email, student_has_meal_plan)
VALUES
    (1, 'John Doe', '+1234567890', 'john.doe@example.com', 1),
    (2, 'Jane Smith', '+9876543210', 'jane.smith@example.com', 2);

-- Student Allergies
INSERT INTO student_allergies (student_id, student_allergies)
VALUES
    (1, 'PEANUT'),
    (1, 'MILK'),
    (2, 'WHEAT');

-- Student Food Preferences
INSERT INTO student_food_preferences (student_id, student_food_preferences)
VALUES
    (1, 'VEGETARIAN'),
    (2, 'VEGAN');


-- Dining Halls
INSERT INTO dining_hall (
    dining_hall_name,
    dining_hall_address,
    dining_hall_opening_time,
    dining_hall_closing_time,
    dining_hall_phone_number,
    dining_hall_email
) VALUES
      ('Ohouse', '123 University Ave', '07:00', '20:00', '111-111-1111', 'ohouse@example.com'),
      ('Bolton', '456 College St', '06:30', '21:00', '222-222-2222', 'bolton@example.com'),
      ('Snelling', '789 Main St', '06:00', '22:00', '333-333-3333', 'snelling@example.com');

-- Set the next value for the _user table
SELECT setval('user_sequence', (SELECT MAX(user_id) FROM _user));

-- Set the next value for the meal_plan table
SELECT setval('meal_plan_sequence', (SELECT MAX(meal_plan_id) FROM meal_plan));

-- Set the next value for the student table
SELECT setval('student_sequence', (SELECT MAX(student_id) FROM student));