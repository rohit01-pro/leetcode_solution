# Write your MySQL query statement below
SELECT
name
From
SalesPerson
Where
sales_id Not In (
    select 
    Sales_id
    From
    orders
    Where
    com_id = (Select com_id From Company Where name = 'Red')
);