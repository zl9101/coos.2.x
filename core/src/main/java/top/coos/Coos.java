package top.coos;

import java.io.Serializable;

public class Coos implements Serializable, Cloneable {

	private static final long serialVersionUID = 8782856064271640724L;

	@Override
	protected Object clone() {

		try {
			return super.clone();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	

}
