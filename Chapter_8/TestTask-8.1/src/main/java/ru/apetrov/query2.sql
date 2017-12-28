select c.name, p.company_id from company as c
inner join person as p on c.id = p.company_id and p.company_id 
in (select max(p.company_id) from company as c 
inner join person as p on c.id = p.company_id and p.company_id 
in (select count(p.company_id) from company as c
inner join person as p
on c.id = p.company_id group by c.name));