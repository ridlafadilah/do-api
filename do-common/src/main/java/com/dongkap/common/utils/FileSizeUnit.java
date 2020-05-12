package com.dongkap.common.utils;

public enum FileSizeUnit {

	bytes("bytes"),
	kilobytes("kilobytes"),
	megabytes("megabytes"),
	gigabytes("gigabytes"),
	terabytes("terabytes"),
	petabytes("petabytes"),
	exabytes("exabytes"),
	zettabytes("zettabytes"),
	yottabytes("yottabytes");
	
	private String sizeUnit;
	
	FileSizeUnit(String sizeUnit) {
		this.sizeUnit = sizeUnit;
	}

	public String getSizeUnit() {
		return sizeUnit;
	}
}
