package com.wgb;

import java.util.Objects;

public class PublicData {
	private short x;
	private short y;

	public final  static short MAP_CODE_BG = 0;
	public final static short MAP_CODE_WALL = 1;
	public final static short MAP_CODE_BOX = 2;
	public final static short MAP_CODE_DESTINATION = 3;
	public final static short MAP_CODE_ROLE = 4;

	public PublicData(short x, short y) {
		this.x = x;
		this.y = y;
	}

	public short getX() {
		return x;
	}

	public void setX(short x) {
		this.x = x;
	}

	public short getY() {
		return y;
	}

	public void setY(short y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "PublicData{" +
				"x=" + x +
				", y=" + y +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		PublicData that = (PublicData) o;
		return x == that.x && y == that.y;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}
}
