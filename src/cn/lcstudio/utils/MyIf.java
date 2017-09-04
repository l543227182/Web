package cn.lcstudio.utils;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class MyIf extends SimpleTagSupport {

	private long PID;
	private int value;
	
	
	public void setPID(long pID) {
		PID = pID;
	}


	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public void doTag() throws JspException, IOException {
		// TODO Auto-generated method stub
		if((PID&value)==value){
			this.getJspBody().invoke(null);
		}
	}

	
}
