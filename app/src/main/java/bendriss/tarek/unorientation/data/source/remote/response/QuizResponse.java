package bendriss.tarek.unorientation.data.source.remote.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuizResponse {

    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("answersSize")
    private int answersSize;
    @JsonProperty("question")
    private String question;
    @JsonProperty("answers")
    private List<String> answers;
    @JsonProperty("final")
    private Boolean isFinal;

    @Override
    public String toString() {
        return "QuizResponse{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", answersSize=" + answersSize +
                ", question='" + question + '\'' +
                ", answers=" + answers +
                '}';
    }

    public QuizResponse() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAnswersSize() {
        return answersSize;
    }

    public void setAnswersSize(int answersSize) {
        this.answersSize = answersSize;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public Boolean getFinal() {
        return isFinal;
    }

    public void setFinal(Boolean aFinal) {
        isFinal = aFinal;
    }
}
