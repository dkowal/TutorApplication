package pl.dkowal.model;

public class Lesson {

    private int id;
    private String date;
    private String topic;
    private String content;
    private int studId;
    private String lessonName;
    private boolean exam;

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

    public void setDate(String date) {
        this.date = date;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setStudId(int studId) {
        this.studId = studId;
    }

    public boolean isExam() {
        return exam;
    }

    public void setExam(boolean exam) {
        this.exam = exam;
    }

    @Override
    public String toString() {return lessonName + " [" + this.date.substring(0,10) + "]"; }
}
