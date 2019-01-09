package ssm.core.domain.result;

import java.util.List;

public class JqGridResult {

    private int page;
    private long records;
    private List<Object> data;
    private long total;

    public JqGridResult() {
        super();
    }

    public JqGridResult(int page, long records, long total, List<Object> data) {
        super();
        this.page = page;
        this.records = records;
        this.data = data;
        this.total = total;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public long getRecords() {
        return records;
    }

    public void setRecords(long records) {
        this.records = records;
    }

    public List<Object> getData() {
        return data;
    }

    public void setData(List<Object> data) {
        this.data = data;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

}
