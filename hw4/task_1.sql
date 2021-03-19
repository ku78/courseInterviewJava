select
	first_film.film_name as first_film_name,
	first_film.session_start as first_film_session_start,
	first_film.duration as first_film_duration,
	second_film.film_name as second_film_name,
	second_film.session_start as second_film_session_start,
	second_film.duration as second_film_duration
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
	    first_film.session_start <= second_film.session_start
	    and first_film.endDate > second_film.session_start
	    and first_film.session <> second_film.session
order by
    first_film.session_start;




