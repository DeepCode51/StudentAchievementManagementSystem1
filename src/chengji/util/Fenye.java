package chengji.util;

import java.util.ArrayList;
import java.util.List;

public class Fenye {

	private int page = 1; // 当前页

	 public int totalPages = 0; // 总页数

	 private int pageRecorders;// 每页5条数据

	 private int totalRows = 0; // 总数据数

	 private int pageStartRow = 0;// 每页的起始数

	 private int pageEndRow = 0; // 每页显示数据的终止数

	 private boolean hasNextPage = false; // 是否有下一页

	 private boolean hasPreviousPage = false; // 是否有前一页

	 @SuppressWarnings("unchecked")
	private List list;
	 
	 @SuppressWarnings("unchecked")
	public Fenye(List list, int pageRecorders) {
		 init(list, pageRecorders);// 通过对象集，记录总数划分
	}
	 
	 /** *//**
	  * 初始化list，并告之该list每页的记录数
	  * @param list
	  * @param pageRecorders
	  */
	  @SuppressWarnings("unchecked")
	public void init(List list, int pageRecorders) {
	  this.pageRecorders = pageRecorders;
	  this.list = list;
	  totalRows = list.size();
	  // it = list.iterator();
	  hasPreviousPage = false;
	  if ((totalRows % pageRecorders) == 0) {
	  totalPages = totalRows / pageRecorders;
	  } else {
	  totalPages = totalRows / pageRecorders + 1;
	  }

	  if (page >= totalPages) {
	  hasNextPage = false;
	  } else {
	  hasNextPage = true;
	  }

	  if (totalRows < pageRecorders) {
	  this.pageStartRow = 0;
	  this.pageEndRow = totalRows;
	  } else {
	  this.pageStartRow = 0;
	  this.pageEndRow = pageRecorders;
	  }
	  }
	  
	// 判断要不要分页
	  public boolean isNext() {
	  return list.size() > 5;
	  }

	  public void setHasPreviousPage(boolean hasPreviousPage) {
	  this.hasPreviousPage = hasPreviousPage;
	  }

	  public String toString(int temp) {
	  String str = Integer.toString(temp);
	  return str;
	  }

	  public void description() {

	  String description = "共有数据数:" + this.getTotalRows() +

	  "共有页数: " + this.getTotalPages() + 

	  "当前页数为:" + this.getPage() +

	  " 是否有前一页: " + this.isHasPreviousPage() +

	  " 是否有下一页:" + this.isHasNextPage() +

	  " 开始行数:" + this.getPageStartRow() +

	  " 终止行数:" + this.getPageEndRow();

	  System.out.println(description);
	  }

	  @SuppressWarnings("unchecked")
	public List getNextPage() {
	  page = page + 1;

	  disposePage();

	  System.out.println("用户凋用的是第" + page + "页");
	  this.description();
	  return getObjects(page);
	  }

	  /** *//**
	  * 处理分页
	  */
	  private void disposePage() {

	  if (page == 0) {
	  page = 1;
	  }

	  if ((page - 1) > 0) {
	  hasPreviousPage = true;
	  } else {
	  hasPreviousPage = false;
	  }

	  if (page >= totalPages) {
	  hasNextPage = false;
	  } else {
	  hasNextPage = true;
	  }
	  }

	  @SuppressWarnings("unchecked")
	public List getPreviousPage() {

	  page = page - 1;

	  if ((page - 1) > 0) {
	  hasPreviousPage = true;
	  } else {
	  hasPreviousPage = false;
	  }
	  if (page >= totalPages) {
	  hasNextPage = false;
	  } else {
	  hasNextPage = true;
	  }
	  this.description();
	  return getObjects(page);
	  }

	  /** *//**
	  * 获取第几页的内容
	  * 
	  * @param page
	  * @return
	  */
	  @SuppressWarnings("unchecked")
	public List getObjects(int page) {
	  if (page == 0)
	  this.setPage(1);
	  else
	  this.setPage(page);
	  this.disposePage();
	  if (page * pageRecorders < totalRows) {// 判断是否为最后一页
	  pageEndRow = page * pageRecorders;
	  pageStartRow = pageEndRow - pageRecorders;
	  } else {
	  pageEndRow = totalRows;
	  pageStartRow = pageRecorders * (totalPages - 1);
	  }

	  List objects = null;
	  if (!list.isEmpty()) {
	  objects = list.subList(pageStartRow, pageEndRow);
	  }
	  //this.description();
	  return objects;
	  }

	  @SuppressWarnings("unchecked")
	public List getFistPage() {
	  if (this.isNext()) {
	  return list.subList(0, pageRecorders);
	  } else {
	  return list;
	  }
	  }

	  public boolean isHasNextPage() {
	  return hasNextPage;
	  }


	  public void setHasNextPage(boolean hasNextPage) {
	  this.hasNextPage = hasNextPage;
	  }


	  @SuppressWarnings("unchecked")
	public List getList() {
	  return list;
	  }


	  @SuppressWarnings("unchecked")
	public void setList(List list) {
	  this.list = list;
	  }


	  public int getPage() {
	  return page;
	  }


	  public void setPage(int page) {
	  this.page = page;
	  }


	  public int getPageEndRow() {
	  return pageEndRow;
	  }


	  public void setPageEndRow(int pageEndRow) {
	  this.pageEndRow = pageEndRow;
	  }


	  public int getPageRecorders() {
	  return pageRecorders;
	  }


	  public void setPageRecorders(int pageRecorders) {
	  this.pageRecorders = pageRecorders;
	  }


	  public int getPageStartRow() {
	  return pageStartRow;
	  }


	  public void setPageStartRow(int pageStartRow) {
	  this.pageStartRow = pageStartRow;
	  }


	  public int getTotalPages() {
	  return totalPages;
	  }


	  public void setTotalPages(int totalPages) {
	  this.totalPages = totalPages;
	  }


	  public int getTotalRows() {
	  return totalRows;
	  }


	  public void setTotalRows(int totalRows) {
	  this.totalRows = totalRows;
	  }


	  public boolean isHasPreviousPage() {
	  return hasPreviousPage;
	  }
	  
	  
	  @SuppressWarnings("unchecked")
	public static void main(String[] args) {
		  List<String> list = new ArrayList<String>();
		  list.add("a");
		  list.add("b");
		  list.add("c");
		  list.add("d");
		  list.add("e");
		  list.add("f");
		  list.add("g");
		  list.add("h");
		  list.add("h");
		  list.add("i");
		  list.add("j");
		  list.add("k");
		  list.add("l");
		  list.add("m");
		  Fenye pm = new Fenye(list, 10);
		  
		  List sublist = pm.getObjects(1);
		  for(int i = 0; i < sublist.size(); i++) {
		  System.out.println(sublist.get(i));
		  }
	
		  }
}


