package ssm.core.domain.result;

public class JsonResult {

    private int code;
    private String message;
    private Object data;

    public JsonResult() {
        super();
    }

    public JsonResult(int code, String message) {
        super();
        this.code = code;
        this.message = message;

    }

    public JsonResult(int code, String message, Object data) {
        super();
        this.code = code;
        this.message = message;
        this.data = data;

    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
