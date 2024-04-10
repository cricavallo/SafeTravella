package backend.database.beans;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class UserBean extends BaseBean {
	private String email;
	private ArrayList<ReviewBean> reviews;
	
	@Override
	public byte[] getData() {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		try {
			output.write(email.getBytes());
			for (ReviewBean r : reviews) {
				output.write(r.getData());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return output.toByteArray();
	}
}
