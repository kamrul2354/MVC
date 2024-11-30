DROP TABLE IF EXISTS course;

CREATE TABLE course (
    id INT AUTO_INCREMENT PRIMARY KEY,
    courseNumber VARCHAR(20) NOT NULL,
    courseTitle VARCHAR(50) NOT NULL,
    courseCredit INT
);
