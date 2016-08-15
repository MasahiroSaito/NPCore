package com.nepian.npcore.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Util {

	/**
	 * HashMapを作成する
	 * @return
	 */
	public static <T, V> Map<T, V> newMap() {
		return  new HashMap<T, V>();
	}

	/**
	 * HashSetを作成する
	 * @return
	 */
	public static <T> Set<T> newSet() {
		return new HashSet<T>();
	}

	/**
	 * ArrayListを作成する
	 * @return
	 */
	public static <T> List<T> newList() {
		return new ArrayList<T>();
	}
}
