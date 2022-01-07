# Cinema_Project

## Create classes: Time, Movie, Seance, Schedule, Cinema.

### In enum Days:

- prescribe the days of the week;

### In Time:

- fields int min, int hour;

- provide limits for their values (for min 0..60, for hour 0..24);

### In Movie:

- fields String title, Time duration;

### In Seance:

- Movie movie, Time startTime, Time endTime fields;

- the constructor must receive value parameters for the first two fields, the third field must be calculated (startTime + movie.duration);

### In Schedule:

- field Set <Seance> seances = new TreeSet <> ();

- methods: addSeance (Seance), removeSeance (Seance);

### In Cinema:

- fields:

TreeMap <Days, Schedule> schedules,

ArrayList <Movie> moviesLibrary = new ArrayList <> ();

Time open, Time close; - take into account the opening and closing times when forming sessions!

- methods:

addMovie (Movie, Time ... time) - adds the movie to the moviesLibrary;

addSeance (Seance, String) - adds a movie session to the schedules on a specific day, which is set by the String parameter;

removeMovie (Movie) - completely removes the movie from the movie library and schedule, if planned;

removeSeance (Seance, String) - deletes a specific movie session on a specific day, which is set by the String parameter.

### In the Main class: - create a Cinema object; - implement a menu in which all Cinema functionality is performed.
