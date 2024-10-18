package functions;

public class UrlPram {

    private String key;
    private String value;

    public UrlPram(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) { //2-1에서 추가
        this.value = value;
    }

}
