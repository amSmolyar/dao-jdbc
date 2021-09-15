select *
from orders o
         inner join customers c on o.customer_id = c.id;


select product_name
from orders o
         inner join customers c on o.customer_id = c.id
where (name = :name)
group by o.product_name;