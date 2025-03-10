package com.yl.learn.common.location;

import java.io.Serializable;

public interface Locatable extends Serializable{

	public Location getLocation();

	public void setLocation(Location location);

}
