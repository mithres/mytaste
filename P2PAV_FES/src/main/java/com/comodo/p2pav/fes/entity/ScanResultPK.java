package com.comodo.p2pav.fes.entity;

import java.io.Serializable;

import javax.persistence.Column;

public class ScanResultPK implements Serializable {

	private static final long serialVersionUID = -1614127496091413370L;

	@Column(name = "avId")
	private String avId = null;

	@Column(name = "avLibVersion")
	private int avLibVersion;

	@Column(name = "filehash")
	private String fileHash = null;

	public ScanResultPK(String avId, int avLibVersion, String fileHash) {
		this.avId = avId;
		this.avLibVersion = avLibVersion;
		this.fileHash = fileHash;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((avId == null) ? 0 : avId.hashCode());
		result = prime * result + ((fileHash == null) ? 0 : fileHash.hashCode());
		result = prime * result + (avLibVersion);
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}

		ScanResultPK other = (ScanResultPK) obj;

		if (avId == null) {
			if (other.avId != null) {
				return false;
			}
		} else if (!avId.equals(other.avId)) {
			return false;
		}

		if (fileHash == null) {
			if (other.fileHash != null) {
				return false;
			}
		} else if (!fileHash.equals(other.fileHash)) {
			return false;
		}

		if (avLibVersion != other.avLibVersion) {
			return false;
		}

		return true;
	}

	public String getAvId() {
		return avId;
	}

	public void setAvId(String avId) {
		this.avId = avId;
	}

	public int getAvLibVersion() {
		return avLibVersion;
	}

	public void setAvLibVersion(int avLibVersion) {
		this.avLibVersion = avLibVersion;
	}

	public String getFileHash() {
		return fileHash;
	}

	public void setFileHash(String fileHash) {
		this.fileHash = fileHash;
	}


}
