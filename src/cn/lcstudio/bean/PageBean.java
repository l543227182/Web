package cn.lcstudio.bean;

public class PageBean {
	/*-------------------分页数据------------*/
	//定义常量 每页数
		public final static int DEFAULT_SIZE = 10;
		//每页数
		protected int pageSize = DEFAULT_SIZE;
		//起始行
		protected int startRow = 0;//起始行
		//页码
		protected int pageNo = 1;
		//Sql查询字段
		protected String fields;
		//总记录数
		protected int records;
		
		
		public int getRecords() {
			return records;
		}
		public void setRecords(int records) {
			this.records = records;
			if(this.startRow>records){
				this.startRow=records-pageSize;
			}
		}
		public int getPageNo() {
			return pageNo;
		}
		public void setPageNo(int pageNo) {
			this.pageNo = pageNo;
			this.startRow = (pageNo-1)*this.pageSize;
			
		}
		public int getPageSize() {
			return pageSize;
		}
		public void setPageSize(int pageSize) {
			this.pageSize = pageSize;
			this.startRow = (pageNo-1)*this.pageSize;		
		}
		public int getStartRow() {
			return startRow;
		}
		public void setStartRow(int startRow) {
			this.startRow = startRow;
		}	
}
