select
	first_film.film_name as first_film_name,
	first_film.session_start as first_film_session_start,
	first_film.duration as first_film_duration,
	min(second_film.session_start) as second_film_session_start,
	min(EXTRACT(EPOCH FROM AGE(second_film.session_start, first_film.endDate))/60) as duration
from (
    select
	    s.id as session,
	    f.name as film_name,
	    s.startDate as session_start,
	    f.duration as duration,
	    s.startDate + f.duration * interval '1 minute' as endDate
    from cinema.films as f
	inner join cinema.sessions as s
		on f.id = s.film_id
	) as first_film
    inner join
    (
	select
	    s.id as session,
	    f.name as film_name,
	    s.startDate as session_start,
	    f.duration as duration
    from cinema.films as f
	    inner join cinema.sessions as s
		    on f.id = s.film_id
    ) as second_film
	on
	    first_film.session_start < second_film.session_start
	    and
	    first_film.session_start::date = second_film.session_start::date
group by
first_film_name,
first_film_session_start,
first_film_duration

having
    min(EXTRACT(EPOCH FROM AGE(second_film.session_start, first_film.endDate))/60) >= 30

order by
	min(EXTRACT(EPOCH FROM AGE(second_film.session_start, first_film.endDate))/60) desc,
	first_film_session_start;






