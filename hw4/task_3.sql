select
	*
from
    (select
	    films.name as film_name,
	    count(*) as count_audience,
	    count(distinct session.id) as count_session,
	    count(*)/count(distinct session.id) as audience_per_session,
	    sum(session.price) as billing,
	    0 as in_order

    from cinema.sold_tickets as tickets
	    join cinema.sessions as session
		    on tickets.session_id = session.id
		    join cinema.films as films
			    on session.film_id = films.id
    group by
	    film_name

    union

    select
	    'ИТОГО' as film_name,
	    count(*) as count_audience,
	    count(distinct session.id) as count_session,
	    count(*)/count(distinct session.id) as audience_per_session,
	    sum(session.price) as billing,
	    1 as in_order

    from cinema.sold_tickets as tickets
	    join cinema.sessions as session
		    on tickets.session_id = session.id
		    join cinema.films as films
			    on session.film_id = films.id) as big_query
order by
	big_query.in_order,
	big_query.billing desc;
