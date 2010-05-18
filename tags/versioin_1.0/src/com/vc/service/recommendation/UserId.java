package com.vc.service.recommendation;

public class UserId {

	private String content;

	public UserId(String s) {
		content = s;
	}

	public String toString() {
		return content;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass()) {
			return false;
		}
		
		UserId other = (UserId) obj;
		
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content)) {
			return false;
		}
		return true;
	}

}
