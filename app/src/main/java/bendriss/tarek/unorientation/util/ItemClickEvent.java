package bendriss.tarek.unorientation.util;

import java.util.Date;

import bendriss.tarek.unorientation.data.source.remote.response.QuizResponse;

public class ItemClickEvent {
    private Date content;
    private String date;
    private String heure;
    private int id;
    private int patientId;
    private QuizResponse quiz;

    public ItemClickEvent(QuizResponse quiz) {
        this.quiz = quiz;
    }

    public ItemClickEvent() {
    }

    public Date getContent() {
        return content;
    }

    public void setContent(Date content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public QuizResponse getQuiz() {
        return quiz;
    }

    public void setQuiz(QuizResponse quiz) {
        this.quiz = quiz;
    }
}
