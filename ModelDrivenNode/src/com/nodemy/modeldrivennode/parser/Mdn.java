package com.nodemy.modeldrivennode.parser;

/**
 * Holds web-specific settings for a resource.
 * @author broward
 *
 */
public class Mdn {

	private boolean isURL = false;
	
	public boolean isURL() {
		return isURL;
	}

	public void setURL(boolean isURL) {
		this.isURL = isURL;
	}
}
