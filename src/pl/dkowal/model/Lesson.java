package pl.dkowal.model;

public class Lesson {

    private int id;
    private String date;
    private String topic;
    private String content;
    private int studId;
    private String lessonName;

    public Lesson(String date, String topic, String content, int studId) {
        this.date = date;
        this.topic = topic;
        this.content = content;
        this.studId = studId;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {this.id = id; }

    public String getDate() {
        return date;
    }

    public String getTopic() {
        return topic;
    }

    public String getContent() {
        return content;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) { this.lessonName = lessonName; }

    public int getStudId() {
        return studId;
    }

    @Override
    public String toString() {return lessonName; }
}
