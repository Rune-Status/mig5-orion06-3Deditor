package Editor;

/**
 * Represents the position of a player or NPC.
 * 
 * @author blakeman8192
 */
public class Position {

	private int x;
	private int y;
	private int z;

	/**
	 * Creates a new Position with the specified coordinates. The Z coordinate
	 * is set to 0.
	 * 
	 * @param x
	 *            the X coordinate
	 * @param y
	 *            the Y coordinate
	 */
	public Position(int x, int y) {
		this(x, y, 0);
	}

	/**
	 * Creates a new Position with the specified coordinates.
	 * 
	 * @param x
	 *            the X coordinate
	 * @param y
	 *            the Y coordinate
	 * @param z
	 *            the Z coordinate
	 */
	public Position(int x, int y, int z) {
		this.setX(x);
		this.setY(y);
		this.setZ(z);
	}

	@Override
	public String toString() {
		return "Position(" + x + ", " + y + ", " + z + ")";
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof Position) {
			Position p = (Position) other;
			return x == p.x && y == p.y && z == p.z;
		}
		return false;
	}

	/**
	 * Sets this position as the other position. <b>Please use this method
	 * instead of player.setPosition(other)</b> because of reference conflicts
	 * (if the other position gets modified, so will the players).
	 * 
	 * @param other
	 *            the other position
	 */
	public void setAs(Position other) {
		this.x = other.x;
		this.y = other.y;
		this.z = other.z;
	}

	/**
	 * Moves the position.
	 * 
	 * @param amountX
	 *            the amount of X coordinates
	 * @param amountY
	 *            the amount of Y coordinates
	 */
	public void move(int amountX, int amountY) {
		setX(getX() + amountX);
		setY(getY() + amountY);
	}

	/**
	 * Sets the X coordinate.
	 * 
	 * @param x
	 *            the X coordinate
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Gets the X coordinate.
	 * 
	 * @return the X coordinate
	 */
	public int getX() {
		return x;
	}

	/**
	 * Sets the Y coordinate.
	 * 
	 * @param y
	 *            the Y coordinate
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Gets the Y coordinate.
	 * 
	 * @return the Y coordinate
	 */
	public int getY() {
		return y;
	}

	/**
	 * Sets the Z coordinate.
	 * 
	 * @param z
	 *            the Z coordinate
	 */
	public void setZ(int z) {
		this.z = z;
	}

	/**
	 * Gets the Z coordinate.
	 * 
	 * @return the Z coordinate.
	 */
	public int getZ() {
		return z;
	}

	/**
	 * Gets the X coordinate of the region containing this Position.
	 * 
	 * @return the region X coordinate
	 */
	public int getRegionX() {
		return (x >> 3) - 6;
	}

	/**
	 * Gets the Y coordinate of the region containing this Position.
	 * 
	 * @return the region Y coordinate
	 */
	public int getRegionY() {
		return (y >> 3) - 6;
	}

	/**
	 * Gets the local X coordinate relative to the base Position.
	 * 
	 * @param base
	 *            the base Position
	 * @return the local X coordinate
	 */
	public int getLocalX(Position base) {
		return x - 8 * base.getRegionX();
	}

	/**
	 * Gets the local Y coordinate relative to the base Position.
	 * 
	 * @param base
	 *            the base Position.
	 * @return the local Y coordinate.
	 */
	public int getLocalY(Position base) {
		return y - 8 * base.getRegionY();
	}

	/**
	 * Gets the local X coordinate relative to this Position.
	 * 
	 * @return the local X coordinate
	 */
	public int getLocalX() {
		return getLocalX(this);
	}

	/**
	 * Gets the local Y coordinate relative to this Position.
	 * 
	 * @return the local Y coordinate.=
	 */
	public int getLocalY() {
		return getLocalY(this);
	}

	/**
	 * Checks if this position is viewable from the other position.
	 * 
	 * @param other
	 *            the other position
	 * @return true if it is viewable, false otherwise
	 */
	/*public boolean isViewableFrom(Position other) {
		if (this.getZ() != other.getZ()) {
			return false;
		}
		Position p = Misc.delta(this, other);
		return p.getX() <= 14 && p.getX() >= -15 && p.getY() <= 14 && p.getY() >= -15;
	}*/

	/**
	 * Checks if this position is viewable from the other position.
	 * 
	 * @param other
	 *            the other position
	 * @return true if it is viewable, false otherwise
	 */
	/*public boolean withinDistance(Position other, int distance) {
		if (this.getZ() != other.getZ()) {
			return false;
		}
		return Misc.goodDistance(this, other, distance);
	}*/

	/*public boolean Area(int x, int x1, int y, int y1, int x2, int y2) {
		return x2 >= x && x2 <= x1 && y2 >= y && y2 <= y1;
	}*/
	

	/**
	 * Creates a new location based on this location.
	 * 
	 * @param diffX
	 *            X difference.
	 * @param diffY
	 *            Y difference.
	 * @param diffZ
	 *            Z difference.
	 * @return The new location.
	 */
	public Position transform(int diffX, int diffY, int diffZ) {
		return new Position(x + diffX, y + diffY, z + diffZ);
	}

	@Override
	public Position clone() {
		return new Position(x, y, z);
	}

	public Position getActualLocation(int biggestSize) {
		if (biggestSize == 1) {
			return this;
		}
		int bigSize = (int) Math.floor(biggestSize / 2);
		return transform(bigSize, bigSize, 0);
	}

	/**
	 * Determines whether or not this position is directly connectable in a
	 * straight line to another.
	 * 
	 * @param other
	 *            The other
	 * @return true if connectable, false otherwise
	 */
	public boolean isConnectable(Position other) {
		int deltaX = x - other.getX();
		int deltaY = y - other.getY();
		return Math.abs(deltaX) == Math.abs(deltaY) || deltaX == 0 || deltaY == 0;
	}

    /*public boolean isPerpendicularTo(Position other) {
        Position delta = Misc.delta(this, other);
        return delta.getX() != delta.getY() && delta.getX() == 0 || delta.getY() == 0;
    }*/
}
