# Write your MySQL query statement below
SELECT
    class
From
    Courses
Group by
    class
    Having
    Count(student) >= 5;