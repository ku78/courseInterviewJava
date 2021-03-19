select
	case
		when session.startDate::time >= '09:00:00' and session.startDate::time < '15:00:00'
		then 'с 9 до 15'
		when session.startDate::time >= '15:00:00' and session.startDate::time < '18:00:00'
		then 'с 15 до 18'
		when session.startDate::time >= '18:00:00' and session.startDate::time < '21:00:00'
		then 'с 18 до 21'
		when session.startDate::time >= '21:00:00' and session.startDate::time < '24:00:00'
		then 'с 21 до 00:00'
		else
		'error'
	end as session_startDate,
	case
		when session.startDate::time >= '09:00:00' and session.startDate::time < '15:00:00'
		then 0
		when session.startDate::time >= '15:00:00' and session.startDate::time < '18:00:00'
		then 1
		when session.startDate::time >= '18:00:00' and session.startDate::time < '21:00:00'
		then 2
		when session.startDate::time >= '21:00:00' and session.startDate::time < '24:00:00'
		then 3
		else
		4
	end as session_order,
	count(*) as count_audience,
	sum(session.price) as billing
from cinema.sold_tickets as tickets
	join cinema.sessions as session
	on tickets.session_id = session.id

group by
	session_startDate,
	session_order
order by
	session_order


