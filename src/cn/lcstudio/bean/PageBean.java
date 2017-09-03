package cn.lcstudio.bean;

public class PageBean {
	/*-------------------��ҳ����------------*/
	//���峣�� ÿҳ��
		public final static int DEFAULT_SIZE = 10;
		//ÿҳ��
		protected int pageSize = DEFAULT_SIZE;
		//��ʼ��
		protected int startRow = 0;//��ʼ��
		//ҳ��
		protected int pageNo = 1;
		//Sql��ѯ�ֶ�
		protected String fields;
		//�ܼ�¼��
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
