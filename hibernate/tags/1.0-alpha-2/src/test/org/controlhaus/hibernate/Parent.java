package org.controlhaus.hibernate;

/*
 * LICENSE
 */

/**
 * @author <a href="mailto:mattis@inamo.no">Mathias Bjerke</a>
 * @version $Id: Parent.java,v 1.1 2004/10/04 18:24:29 dandiep Exp $
 */
public class Parent {

	private long id;
    private Child[] children;

    public Parent() {
	}

    public long getId() {
		return id;
	}

    public void setId(long id) {
		this.id = id;
	}

    public Child[] getChildren() {
		return children;
	}

	public void setChildren(Child[] children) {
		this.children = children;
	}
}
