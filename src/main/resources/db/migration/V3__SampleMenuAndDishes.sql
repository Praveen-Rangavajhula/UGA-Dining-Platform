-- Menus for Ohouse
INSERT INTO menu (menu_name, dining_hall_name)
VALUES
    ('Ohouse Breakfast Menu', 'Ohouse'),
    ('Ohouse Dinner Menu', 'Ohouse');

-- Menus for Bolton
INSERT INTO menu (menu_name, dining_hall_name)
VALUES
    ('Bolton Lunch Menu', 'Bolton'),
    ('Bolton Dinner Menu', 'Bolton');

-- Menus for Snelling
INSERT INTO menu (menu_name, dining_hall_name)
VALUES
    ('Snelling Breakfast Menu', 'Snelling'),
    ('Snelling Lunch Menu', 'Snelling'),
    ('Snelling Dinner Menu', 'Snelling');

-- Dishes
INSERT INTO dish (dish_name, dish_ingredients, dish_type)
VALUES
    ('Pancakes', 'Flour, Eggs, Milk', 'VEGETARIAN'),
    ('Bacon and Eggs', 'Bacon, Eggs', 'PESCATARIAN'),
    ('Vegetarian Pizza', 'Dough, Tomato Sauce, Cheese, Vegetables', 'VEGETARIAN'),
    ('Grilled Chicken', 'Chicken Breast, Olive Oil, Herbs', 'HALAL'),
    ('Caesar Salad', 'Romaine Lettuce, Caesar Dressing, Croutons', 'VEGETARIAN'),
    ('Spaghetti Bolognese', 'Spaghetti, Bolognese Sauce, Ground Beef', 'KOSHER'),
    ('Vegan Burger', 'Plant-Based Patty, Vegan Bun, Lettuce, Tomato', 'VEGAN'),
    ('Fish Tacos', 'Fish, Tortillas, Cabbage Slaw, Lime', 'PESCATARIAN'),
    ('Cheese Plate', 'Assorted Cheeses, Crackers, Grapes', 'VEGETARIAN'),
    ('Chocolate Cake', 'Chocolate, Flour, Sugar, Eggs', 'DAIRY_FREE'),
    ('Hummus with Pita', 'Chickpeas, Tahini, Olive Oil', 'VEGAN'),
    ('Caprese Salad', 'Tomatoes, Mozzarella, Basil, Balsamic Glaze', 'VEGETARIAN'),
    ('Shrimp Scampi', 'Shrimp, Garlic, Butter, White Wine', 'PESCATARIAN'),
    ('Gluten-Free Brownies', 'Almond Flour, Cocoa Powder, Eggs, Sugar', 'GLUTEN_FREE'),
    ('Quinoa Salad', 'Quinoa, Cucumber, Cherry Tomatoes, Feta', 'VEGETARIAN'),
    ('Halal Chicken Curry', 'Chicken, Curry Sauce, Rice', 'HALAL'),
    ('Vegan Chocolate Mousse', 'Avocado, Cocoa Powder, Maple Syrup', 'VEGAN'),
    ('Kosher Beef Brisket', 'Beef Brisket, Potatoes, Carrots', 'KOSHER'),
    ('Peanut Butter Smoothie', 'Peanut Butter, Banana, Almond Milk', 'NUT_FREE'),
    ('Vegetable Stir-Fry', 'Mixed Vegetables, Tofu, Soy Sauce', 'VEGAN'),
    ('Gluten-Free Pizza', 'Gluten-Free Pizza Crust, Tomato Sauce, Cheese', 'GLUTEN_FREE'),
    ('Dairy-Free Ice Cream', 'Coconut Milk, Vanilla, Sugar', 'DAIRY_FREE'),
    ('Halal Lamb Kebabs', 'Lamb, Marinade, Skewers', 'HALAL'),
    ('Vegan Caesar Salad', 'Romaine Lettuce, Vegan Caesar Dressing, Croutons', 'VEGAN'),
    ('Kosher Matzo Ball Soup', 'Matzo Balls, Chicken Broth, Vegetables', 'KOSHER'),
    ('Pescatarian Sushi Rolls', 'Sushi Rice, Nori, Fish, Vegetables', 'PESCATARIAN'),
    ('Gluten-Free Pasta with Pesto', 'Gluten-Free Pasta, Pesto Sauce', 'GLUTEN_FREE'),
    ('Dairy-Free Coconut Yogurt Parfait', 'Coconut Yogurt, Granola, Berries', 'DAIRY_FREE'),
    ('Halal Grilled Lamb Chops', 'Lamb Chops, Marinade, Herbs', 'HALAL'),
    ('Vegan Pad Thai', 'Rice Noodles, Tofu, Bean Sprouts', 'VEGAN'),
    ('Kosher Potato Latkes', 'Potatoes, Onion, Matzo Meal', 'KOSHER'),
    ('Pescatarian Grilled Salmon', 'Salmon, Lemon, Dill', 'PESCATARIAN'),
    ('Gluten-Free Chocolate Chip Cookies', 'Almond Flour, Chocolate Chips', 'GLUTEN_FREE'),
    ('Dairy-Free Avocado Lime Cheesecake', 'Avocado, Lime, Coconut Cream', 'DAIRY_FREE');
-- Add more dishes as needed. These are just for convenience

-- Menu-Dish associations
-- Distribute dishes evenly across the three dining halls
INSERT INTO menu_dish_list (menu_name, dish_name)
SELECT
    'Ohouse Breakfast Menu' AS menu_name, dish_name
FROM
    dish
ORDER BY
    RANDOM()
LIMIT 10;

INSERT INTO menu_dish_list (menu_name, dish_name)
SELECT
    'Ohouse Dinner Menu' AS menu_name, dish_name
FROM
    dish
ORDER BY
    RANDOM()
LIMIT 10;

INSERT INTO menu_dish_list (menu_name, dish_name)
SELECT
    'Bolton Lunch Menu' AS menu_name, dish_name
FROM
    dish
ORDER BY
    RANDOM()
LIMIT 10;

INSERT INTO menu_dish_list (menu_name, dish_name)
SELECT
    'Bolton Dinner Menu' AS menu_name, dish_name
FROM
    dish
ORDER BY
    RANDOM()
LIMIT 10;

INSERT INTO menu_dish_list (menu_name, dish_name)
SELECT
    'Snelling Breakfast Menu' AS menu_name, dish_name
FROM
    dish
ORDER BY
    RANDOM()
LIMIT 10;

INSERT INTO menu_dish_list (menu_name, dish_name)
SELECT
    'Snelling Lunch Menu' AS menu_name, dish_name
FROM
    dish
ORDER BY
    RANDOM()
LIMIT 10;

INSERT INTO menu_dish_list (menu_name, dish_name)
SELECT
    'Snelling Dinner Menu' AS menu_name, dish_name
FROM
    dish
ORDER BY
    RANDOM()
LIMIT 10;