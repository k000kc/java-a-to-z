select p.name, c.name from person as p 
inner join company as c on p.company_id = c.id and c.id <> 5; 
