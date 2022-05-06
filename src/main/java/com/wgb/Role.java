package com.wgb;

/**
 * 角色类
 */
public class Role {
	private short x;
	private short y;
	public final static short MAP_CODE = 4;

	public Role() {
	}

	public Role(short x, short y) {
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
		return "Role{" +
				"x=" + x +
				", y=" + y +
				'}';
	}
}
