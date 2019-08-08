package utils;

public class ExecResult {
	
	private boolean success;
	private String msg;
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
	public String toJson(){
		String json = "{\"success\" : "+success+" , \"msg\" : \""+msg+"\"}";
		return json;
	}
	

}
