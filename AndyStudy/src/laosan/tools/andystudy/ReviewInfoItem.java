package laosan.tools.andystudy;

import android.text.StaticLayout;

public class ReviewInfoItem {
	private static final String TABLENAME = "reviewinfo";
	
	private static final String lessonTag = "lesson";
	private static final String StartTag = "startDate";
	private static final String lastTag = "lastDate";
	private static final String reviewTag = "reviewTimes";
	
	private int _id;
	private String lessonString;
	private String startDateString;
	private String lastDateString;
	private int reviewTimes;
	private String statusString;
	
	private boolean finishMark;
	/**
	 * @return the lessonTag
	 */
	public static String getLessonTag() {
		return lessonTag;
	}

	/**
	 * @return the startTag
	 */
	public static String getStartTag() {
		return StartTag;
	}

	/**
	 * @return the lastTag
	 */
	public static String getLastTag() {
		return lastTag;
	}

	/**
	 * @return the reviewTag
	 */
	public static String getReviewTag() {
		return reviewTag;
	}
	
	/**
	 * @return the lessonString
	 */
	public String getLessonString() {
		return lessonString;
	}

	/**
	 * @param lessonString the lessonString to set
	 */
	public void setLessonString(String lessonString) {
		this.lessonString = lessonString;
	}

	/**
	 * @return the startDateString
	 */
	public String getStartDateString() {
		return startDateString;
	}

	/**
	 * @param startDateString the startDateString to set
	 */
	public void setStartDateString(String startDateString) {
		this.startDateString = startDateString;
	}

	/**
	 * @return the lastDateString
	 */
	public String getLastDateString() {
		return lastDateString;
	}

	/**
	 * @param lastDateString the lastDateString to set
	 */
	public void setLastDateString(String lastDateString) {
		this.lastDateString = lastDateString;
	}

	/**
	 * @return the reviewTimes
	 */
	public int getReviewTimes() {
		return reviewTimes;
	}

	/**
	 * @param reviewTimes the reviewTimes to set
	 */
	public void setReviewTimes(int reviewTimes) {
		this.reviewTimes = reviewTimes;
	}

	/**
	 * @return the _id
	 */
	public int get_id() {
		return _id;
	}

	/**
	 * @param _id the _id to set
	 */
	public void set_id(int _id) {
		this._id = _id;
	}

	/**
	 * @return the tablename
	 */
	public static String getTablename() {
		return TABLENAME;
	}

	/**
	 * @return the finishMark
	 */
	public boolean isFinishMark() {
		return finishMark;
	}

	/**
	 * @param finishMark the finishMark to set
	 */
	public void setFinishMark(boolean finishMark) {
		this.finishMark = finishMark;
	}
	
}
