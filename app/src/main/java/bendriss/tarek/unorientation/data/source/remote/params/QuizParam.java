package bendriss.tarek.unorientation.data.source.remote.params;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;



@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuizParam {

    @JsonProperty("finalQuiz")
    private String finalQuiz;
    @JsonProperty("name")
    private String name;
    @JsonProperty("userId")
    private int userId;


    public QuizParam() {
    }


    public QuizParam(String finalQuiz, String name) {
        this.finalQuiz = finalQuiz;
        this.name = name;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFinalQuiz() {
        return finalQuiz;
    }

    public void setFinalQuiz(String finalQuiz) {
        this.finalQuiz = finalQuiz;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "QuizParam{" +
                "finalQuiz='" + finalQuiz + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}