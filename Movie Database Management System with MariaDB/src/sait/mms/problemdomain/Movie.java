package sait.mms.problemdomain;
/**
@author: Madhu Madhavan
**/

public class Movie {
	private String duration;
	private String title;
	private String year;
	
	public Movie(String duration, String title, String year) {
		super();
		this.duration = duration;
		this.title = title;
		this.year = year;
	}

	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return "Movie [duration=" + duration + ", title=" + title + ", year=" + year + "]";
	}

}
