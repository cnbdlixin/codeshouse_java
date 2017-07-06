package com.lx.codeshouse.util.data;

import java.util.ArrayList;
import java.util.List;

public class ListUtil {

	/**
	 * ��list����
	 * @param list
	 * @param sublistsize
	 * @return
	 */
	public List<List> partList(List list, int sublistsize) {
		List<List> sublistgroup = new ArrayList<List>();
		int listsize = list.size();
		if (listsize < sublistsize) {
			sublistgroup.add(list);
		} else {
			for (int i = 0; i < listsize; i += sublistsize) {
				int j = i + sublistsize;
				if (j < listsize) {
					sublistgroup.add(list.subList(i, listsize));
					break;
				} else {
					sublistgroup.add(list.subList(i, j));
				}
			}
		}
		return sublistgroup;
	}

}
