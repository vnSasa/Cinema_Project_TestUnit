package _1._Unit;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;
import java.util.Map.Entry;

public class Main {

	static Cinema cinema;

	@SuppressWarnings("resource")
	public static void main(String[] args) {

		String menuString;
		Scanner sc;
		sc = new Scanner(System.in);
		cinema = initializeCinema();
		Menu();
		Menu2();

		while (true) {
			/* checking of menuString from the user */
			sc = new Scanner(System.in);
			InputMenuString inputMenuString = new InputMenuString(sc.next().toLowerCase());
			menuString = inputMenuString.getString();

			if (inputMenuString.flag) {
				switch (menuString) {
				case "1": {
					addMovieFromConsole();
					break;
				}
				case "2": {
					removeMovieFromConsole();
					break;
				}
				case "3": {
					addSeanceFromConsole();
					break;
				}
				case "4": {
					removeSeanceFromConsole();
					break;
				}

				case "5": {
					String day = getDayFromConsole();
					showScheduleByDay(day);
					break;
				}
				case "6": {
					showMovieLibrary();
					break;
				}
				case "7": {
					System.out.println(cinema.toString());
					break;
				}
				case "menu": {
					Menu();
					break;
				}
				case "q": {
					System.out.println("Exit application... Good buy!");
					System.exit(0);
					sc.close();
				}

				}
				Menu2();
			}
		}

	}

	public static void Menu() {
		System.out.println("| -----------------------Menu-----------------------------|");
		System.out.println("| Input 1 to add movie to the movie library               |");
		System.out.println("| Input 2 to remove movie from the movie library          |");
		System.out.println("| Input 3 to add seance to the schedule of the day        |");
		System.out.println("| Input 4 to remove seance from the schedule of the day   |");
		System.out.println("| Input 5 to show schedule of the day                     |");
		System.out.println("| Input 6 to show movie library                           |");
		System.out.println("| Input 7 to show info about Cinema                       |");
	}

	public static void Menu2() {
		System.out.println("[menu] - show all Menu again");
		System.out.println("[q] - quit the program");
	}

	@SuppressWarnings("resource")
	public static Cinema initializeCinema() {
		Time openTime = Time.timeFromString("06:00");
		Time closeTime = Time.timeFromString("00:00");
		Scanner sc1;
		sc1 = new Scanner(System.in);
		System.out.println("Please, input open time of the Cinema (hh:mm)");
		if (sc1.hasNext())
			openTime = Time.timeFromString(sc1.next());
		System.out.println("Please, input close time of the Cinema (hh:mm)");
		if (sc1.hasNext())
			closeTime = Time.timeFromString(sc1.next());
		System.out.println("Well done! New Cinema was created! Next chose the menu...\n ATTENTION! If you want to add Seance, you must first add the movie to the library");
		return new Cinema(openTime, closeTime);
	}

	public static void addMovieFromConsole() {

		Scanner sc1 = new Scanner(System.in);
		System.out.println("Input Title of the Movie to add:");
		String title = "";
		if (sc1.hasNext())
			title = sc1.next();
		System.out.println("Input duration of the Movie to add(hh:mm):");
		Time duration = Time.timeFromString("0:00");
		if (sc1.hasNext())
			duration = Time.timeFromString(sc1.next());

		Movie movie = new Movie(title, duration);
		cinema.getMoviesLibrary().add(movie);
		System.out.println("The movie added: " + movie.toString());
	}

	public static void removeMovieFromConsole() {
		Scanner sc2 = new Scanner(System.in);
		System.out.println("Input Title of the Movie to remove:");
		String title = "";
		if (sc2.hasNext())
			title = sc2.next();
		Movie movie;
		Optional<Movie> movieOpt = findMovie(title);
		if (movieOpt.isPresent()) {
			movie = movieOpt.get();
			cinema.removeMovie(movie);
			System.out.println(movie.toString() + " is removed from the movie library");
			cinema.removeAllSeancesByMovie(title);

		} else
			System.out.println("There is some problem...");
	}

	public static void addSeanceFromConsole() {
		Movie movie = getMovieFromConsole();
		String day = getDayFromConsole();
		Seance seance = getCheckedSeanceFromConsole(movie);

		cinema.addSeance(seance, day);
	}

	public static void removeSeanceFromConsole() {

		String day = getDayFromConsole();
		Scanner sc3 = new Scanner(System.in);
		System.out.println("Input start time of the seance you want to remove");
		Time startTime = Time.timeFromString("0:00");
		if (sc3.hasNext())
			startTime = Time.timeFromString(sc3.next());
		Optional<Seance> findSeance = findSeanceByTime(startTime);
		if (findSeance.isPresent()) {
			cinema.removeSeanceByDay(findSeance.get(), day);
		} else
			System.out.println("There is not seance in this day and with this start time, try again");
	}

	public static void showMovieLibrary() {

		ArrayList<Movie> moviesLibrary = cinema.getMoviesLibrary();
		System.out.println("Movie library:");
		if (!moviesLibrary.isEmpty()) {
			moviesLibrary.stream().forEach(System.out::println);
		} else {
			System.out.println("Movie library is empty yet...");
		}

	}

	public static void showScheduleByDay(String day) {

		for (Entry<Days, Schedule> scheduleEntry : cinema.schedules.entrySet()) {
			Days key = scheduleEntry.getKey();
			if (key.toString().equalsIgnoreCase(day)) {
				Schedule value = scheduleEntry.getValue();
				value.getSeances().stream().forEach(System.out::println);

			}

		}
	}

	private static Movie getMovieFromConsole() {
		Optional<Movie> movieOpt;
		while (true) {
			Scanner sc3 = new Scanner(System.in);
			System.out.println("Input Title of the Movie from movie library");
			String title = "";
			if (sc3.hasNext())
				title = sc3.next();
			movieOpt = findMovie(title);
			if (movieOpt.isPresent()) {
				System.out.println(movieOpt.get().toString() + " is in the movie library, next...");
				break;
			} else
				System.out.println("There is no movie in the library\nAdd the film to the library");
				addMovieFromConsole();
		}
		return movieOpt.get();
	}

	private static String getDayFromConsole() {
		String dayString = "";
		String stringOut = "";
		Scanner sc3 = new Scanner(System.in);
		while (true) {
			System.out.println("Input day of seanse");
			if (sc3.hasNext())
				dayString = sc3.next();
			for (Days day : Days.values()) {
				if (day.toString().equalsIgnoreCase(dayString))
					stringOut = day.toString();
			}
			if (stringOut.isEmpty()) {
				System.out.println("Wrong day name, try again...");
			} else {
				System.out.println("Day " + stringOut + " choosen, next...");
				break;
			}
		}
		return stringOut;
	}

	private static Seance getCheckedSeanceFromConsole(Movie movie) {
		Time startTime = Time.timeFromString("0:00");
		Scanner sc3 = new Scanner(System.in);
		Seance seance;
		while (true) {
			System.out.println("Input start time of the new seance(hh:mm):");
			if (sc3.hasNext())
				startTime = Time.timeFromString(sc3.next());
			Seance seanceTemp = new Seance(movie, startTime);
			if (cinema.checkSeanceBounds(seanceTemp)) {
				seance = seanceTemp;
				break;
			}
		}
		return seance;
	}

	private static Optional<Movie> findMovie(String title) {
		for (Movie movie : cinema.getMoviesLibrary()) {
			if (movie.getTitle().equalsIgnoreCase(title)) {
				return Optional.of(movie);
			}
		}
		return Optional.empty();
	}

	private static Optional<Seance> findSeanceByTime(Time startTime) {
		for (Schedule schedule : cinema.getSchedules().values()) {
			for (Seance seance : schedule.getSeances()) {
				if (seance.getStartTime().equals(startTime))
					return Optional.of(seance);
			}
		}
		return Optional.empty();
	}

}
