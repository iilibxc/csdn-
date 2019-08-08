package entity;

import java.util.List;

public class PageBean  {
    //主要是对分页的几个字段属性进行封装，这些字段都可以根据jsp页面来进行推断出来

    //这些可以页码获取
    private int currentPage;//当前页码
    private int pageSize;//每页显示多少条记录

    //这些可以数据库获取
    private List recordList;//本页的数据列表
    private int recordCount;//总记录数

    //这些可以根据上面的计算得到
    private int pageCount;//总共多少页
    private int beginPageIndex;//页码的开始索引
    private int endPageIndex;//页码的结束索引


    public PageBean(int currentPage, int pageSize, List recordList, int recordCount) {
        super();
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.recordList = recordList;
        this.recordCount = recordCount;


        //计算剩余的三个值,根据公式进行计算
        pageCount=(recordCount+pageSize-1)/pageSize;
        /*计算开始页索引和结束页索引：这里我们定义总共显示10个页码。分几种情况
         * 第一：当总共页数pageCount不足10页的时候，则显示全部页码
         * 第二：当总共页数超过10页时，则显示当前页的前4页和当前页的后5页。
         *      但是这里又有两种情况：如果总共有11页，当前页是3，则3-4=-1，这是不可以的，
         *           即当前面的页面少于4个时，这个时候显示前十页。
         *          同理：当后面的页面不足5个时，显示后10页。
         */

        if (pageCount<=10) {
            beginPageIndex=1;
            endPageIndex=pageCount;
        }else{
            beginPageIndex=currentPage-4;
            endPageIndex=currentPage+5;
            if (beginPageIndex-4<1) {
                beginPageIndex=1;
                endPageIndex=10;
            }
            if (endPageIndex>pageCount) {
                beginPageIndex=currentPage-10+1;
                endPageIndex=pageCount;
            }

        }

    }
    public int getCurrentPage() {
        return currentPage;
    }
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    public List getRecordList() {
        return recordList;
    }
    public void setRecordList(List recordList) {
        this.recordList = recordList;
    }
    public int getRecordCount() {
        return recordCount;
    }
    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }
    public int getPageCount() {
        return pageCount;
    }
    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
    public int getBeginPageIndex() {
        return beginPageIndex;
    }
    public void setBeginPageIndex(int beginPageIndex) {
        this.beginPageIndex = beginPageIndex;
    }
    public int getEndPageIndex() {
        return endPageIndex;
    }
    public void setEndPageIndex(int endPageIndex) {
        this.endPageIndex = endPageIndex;
    }
}

