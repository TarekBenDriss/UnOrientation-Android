package bendriss.tarek.unorientation.data.source.remote.params;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScrapParam {

    @JsonProperty("keyword")
    private String keyword;

    @Override
    public String toString() {
        return "ScrapParam{" +
                "keyword='" + keyword + '\'' +
                '}';
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public ScrapParam() {
    }

    public ScrapParam(String keyword) {
        this.keyword = keyword;
    }
}


