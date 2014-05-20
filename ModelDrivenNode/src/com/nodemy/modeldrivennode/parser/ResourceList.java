package com.nodemy.modeldrivennode.parser;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Resource list singleton
 * 
 * @author broward
 * 
 */
public enum ResourceList {
	INSTANCE;

	private LinkedList<Resource> resources = new LinkedList<Resource>();
	private LinkedList<Resource> sortList = new LinkedList<Resource>();

	/**
	 * get our resources
	 */
	public List<Resource> getResources() {
		return resources;
	}

	/**
	 * Add a new resource. 
	 */
	public void add(Resource resource) {

		// don't allow reserved words
		if (Utils.isReservedWord(resource.getName())) {
			return;
		} else {
			resources.add(resource);
		}
	}

	/**
	 * Was this resource already processed?
	 */
	public boolean contains(String resourceName) {
		Resource target = get(resourceName);
		return target != null;
	}

	/**
	 * Get a specific resource by name.
	 */
	private Resource get(String resourceName) {
		if (resourceName == null) {
			return null;
		}

		Iterator<Resource> i = resources.listIterator();
		while (i.hasNext()) {
			Resource tempResource = i.next();
			if (resourceName.equals(tempResource.getName())) {
				return tempResource;
			}
		}
		return null;
	}

	/**
	 * Re-sort in dependency order
	 */
	public void sortByDependency() {
		sortList = new LinkedList<Resource>();

		// Sort through all resources
		for (Resource resource : resources) {
			// remove myself from other list if it exists
			Resource temp = null;
			for (Resource sortResource : sortList) {
				if (resource.getName().equals(sortResource.getName())) {
					temp = sortResource;
				}
			}
			sortList.remove(temp);
			sortList.addFirst(resource);

			// recurse through our children
			recursiveSort(resource, resource.getName());
		}

		// finished sorting, now write out object graph stats
		ObjectGrapher.INSTANCE.writeObjectGraph();

		// make the sorted list our master copy.
		resources = sortList;
		
		// write our mdninfo here for now
		// if objectgraph is deeper than two, assume it's a REST url for now
		for (Resource resource : resources) {
			resource.getMdn().setURL(true);
		}
	}

	/**
	 * Find children resources in current list, move them to sort list in proper
	 * dependency order.
	 * 
	 * @param resource
	 */
	private void recursiveSort(Resource resource, String graph) {

		for (ResourceProperty property : resource.getProperties()) {
			Resource target = this.get(property.getType());
			if (target != null) {

				// remove it from other list if it exists
				Resource temp = null;
				for (Resource sortResource : sortList) {
					if (target.getName().equals(sortResource.getName())) {
						temp = sortResource;
					}
				}
				sortList.remove(temp);

				// re-add it to first position
				sortList.addFirst(target);
				
				// write object graph for current resource.
				ObjectGrapher.INSTANCE.addGraph(graph + " | " + target.getName());
				
				// recurse through our children and add to object graph
				recursiveSort(target, graph + " | " + target.getName());
			}
		}
	}
}
