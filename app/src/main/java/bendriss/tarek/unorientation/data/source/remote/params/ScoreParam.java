package bendriss.tarek.unorientation.data.source.remote.params;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * this function represents the login's param
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScoreParam {

    @JsonProperty("bac")
    private String bac;
    @JsonProperty("mg")
    private float mg;
    @JsonProperty("m")
    private float m;
    @JsonProperty("sp")
    private float sp;
    @JsonProperty("svt")
    private float svt;
    @JsonProperty("te")
    private float te;
    @JsonProperty("bd")
    private float bd;
    @JsonProperty("a")
    private float a;
    @JsonProperty("ph")
    private float ph;
    @JsonProperty("hg")
    private float hg;
    @JsonProperty("f")
    private float f;
    @JsonProperty("ang")
    private float ang;
    @JsonProperty("ec")
    private float ec;
    @JsonProperty("ge")
    private float ge;
    @JsonProperty("algo")
    private float algo;
    @JsonProperty("tic")
    private float tic;
    @JsonProperty("spsport")
    private float spsport;
    @JsonProperty("ep")
    private float ep;


    @Override
    public String toString() {
        return "ScoreParam{" +
                "bac='" + bac + '\'' +
                ", mg=" + mg +
                ", m=" + m +
                ", sp=" + sp +
                ", svt=" + svt +
                ", te=" + te +
                ", bd=" + bd +
                ", a=" + a +
                ", ph=" + ph +
                ", hg=" + hg +
                ", f=" + f +
                ", ang=" + ang +
                ", ec=" + ec +
                ", ge=" + ge +
                ", algo=" + algo +
                ", tic=" + tic +
                ", spsport=" + spsport +
                ", ep=" + ep +
                '}';
    }

    public ScoreParam() {
    }

    public String getBac() {
        return bac;
    }

    public void setBac(String bac) {
        this.bac = bac;
    }

    public float getMg() {
        return mg;
    }

    public void setMg(float mg) {
        this.mg = mg;
    }

    public float getM() {
        return m;
    }

    public void setM(float m) {
        this.m = m;
    }

    public float getSp() {
        return sp;
    }

    public void setSp(float sp) {
        this.sp = sp;
    }

    public float getSvt() {
        return svt;
    }

    public void setSvt(float svt) {
        this.svt = svt;
    }

    public float getTe() {
        return te;
    }

    public void setTe(float te) {
        this.te = te;
    }

    public float getBd() {
        return bd;
    }

    public void setBd(float bd) {
        this.bd = bd;
    }

    public float getA() {
        return a;
    }

    public void setA(float a) {
        this.a = a;
    }

    public float getPh() {
        return ph;
    }

    public void setPh(float ph) {
        this.ph = ph;
    }

    public float getHg() {
        return hg;
    }

    public void setHg(float hg) {
        this.hg = hg;
    }

    public float getF() {
        return f;
    }

    public void setF(float f) {
        this.f = f;
    }

    public float getAng() {
        return ang;
    }

    public void setAng(float ang) {
        this.ang = ang;
    }

    public float getEc() {
        return ec;
    }

    public void setEc(float ec) {
        this.ec = ec;
    }

    public float getGe() {
        return ge;
    }

    public void setGe(float ge) {
        this.ge = ge;
    }

    public float getAlgo() {
        return algo;
    }

    public void setAlgo(float algo) {
        this.algo = algo;
    }

    public float getTic() {
        return tic;
    }

    public void setTic(float tic) {
        this.tic = tic;
    }

    public float getSpsport() {
        return spsport;
    }

    public void setSpsport(float spsport) {
        this.spsport = spsport;
    }

    public float getEp() {
        return ep;
    }

    public void setEp(float ep) {
        this.ep = ep;
    }
}