package zhth.bom.management.bom.domianextend;

import java.io.Serializable;
import java.util.List;

public class Page<T>  implements Serializable{
    private  static final Long serializableID=-1L;
    // 每页多少条记录
    private Integer pageSize;

    //当前第几页数据
     private Integer currentPage;

    // 一共多少页记录
    private Integer totalPage;

    private List<T> list;   //每页的数据

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
