
SELECT n.name, m.maxcount FROM 

(SELECT namecount.name, namecount.count FROM 
(SELECT c.name, count(p.company_id) AS count FROM company AS c 
INNER JOIN person AS p 
ON c.id = p.company_id GROUP BY c.name) AS namecount) AS n 

INNER JOIN 

(SELECT max(numbercount.count)AS maxcount FROM 
(SELECT count(p.company_id) AS count FROM company AS c
INNER JOIN person AS p
ON c.id = p.company_id GROUP BY c.name) AS numbercount) AS m

ON n.count = m.maxcount;