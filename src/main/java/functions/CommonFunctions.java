package functions;

import functions.UrlNotFoundException;
import functions.UrlPram;

import java.util.*;

public class CommonFunctions {

    protected String getParamValue(List<UrlPram> params, String key) { // 두 기능클래스에서 분리했음

        for (UrlPram param : params) {

            if (param.getKey().equals(key)) {
                return param.getValue();
            }
        }
        return null;
    }


    public static class ParsedUrl {
        private String action;
        private List<UrlPram> params; //리턴될 params의 타입이 UrlPram 이어야 추후 문제가 없음을 확인

        public ParsedUrl(String action, List<UrlPram> params) {
            this.action = action;
            this.params = params;
        }

        public String getAction() {
            return action;
        }

        public List<UrlPram> getParams() {
            return params;
        }

    }


    public static ParsedUrl parseUrl(String url) throws UrlNotFoundException { // 두 기능클래스에서 분리했음

    String[] blocks = url.split("\\?",2); // 파라미터 분리
    String[] path = blocks[0].split("/"); // 경로 분리

            if ( path.length < 3 ){ // 정상적인 URL인지 검사

        throw new UrlNotFoundException("URL이 올바르지 않습니다.");

    }

    String actions = path[2];

    List<UrlPram> params = new ArrayList<>();

    if (blocks.length > 1) {

        String[] paramSet = blocks[1].split("&");

        for (String set : paramSet) {

            String[] keyNum = set.split("=");

            if (keyNum.length == 2) {

                String key = keyNum[0];
                String value = keyNum[1];

                UrlPram isExist = null;

                for (UrlPram param : params) {
                    if (param.getKey().equals(key)) {
                        isExist = param;
                        break;
                    }
                }

                if (isExist != null) {
                    isExist.setValue(value);
                } else {
                    params.add(new UrlPram(key, value));
                }

            }
        }
    }
            return new ParsedUrl(actions,params);
    }
}