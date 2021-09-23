package entity;

public class PageCommodity {
    private int curPage;
    private int pageSize;
    private int totalPage;
    private int totalCount;
    private int recNum;
    private String key;
    private String ifm;
    private String ifm1;

    public PageCommodity(int curPage, int pageSize, int totalPage, int totalCount,
                    int recNum, String key, String ifm, String ifm1) {
        super();
        this.curPage = curPage;
        this.pageSize = pageSize;
        this.totalPage = totalPage;
        this.totalCount = totalCount;
        this.recNum = recNum;
        this.key = key;
        this.ifm = ifm;
        this.ifm1 = ifm1;
    }

    public PageCommodity(int curPage, int pageSize, String key, String ifm) {
        super();
        this.curPage = curPage;
        this.pageSize = pageSize;
        this.key = key;
        this.ifm = ifm;
        this.recNum = (curPage-1)*pageSize;
    }
    
    public PageCommodity(int curPage, int pageSize, String key, String ifm,
			String ifm1) {
		super();
		this.curPage = curPage;
		this.pageSize = pageSize;
		this.key = key;
		this.ifm = ifm;
		this.ifm1 = ifm1;
		this.recNum = (curPage-1)*pageSize;
	}

	public PageCommodity(int curPage, int pageSize) {
		super();
		this.curPage = curPage;
		this.pageSize = pageSize;
		this.recNum = (curPage-1)*pageSize;
	}

	public PageCommodity() {
        super();
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getRecNum() {
        return recNum;
    }

    public void setRecNum(int recNum) {
        this.recNum = recNum;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getIfm() {
        return ifm;
    }

    public void setIfm(String ifm) {
        this.ifm = ifm;
    }

	public String getIfm1() {
		return ifm1;
	}

	public void setIfm1(String ifm1) {
		this.ifm1 = ifm1;
	}

}
