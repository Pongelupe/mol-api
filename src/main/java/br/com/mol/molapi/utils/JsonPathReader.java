package br.com.mol.molapi.utils;

import java.util.ArrayList;
import java.util.List;

import com.jayway.jsonpath.PathNotFoundException;
import com.jayway.jsonpath.ReadContext;

import lombok.Getter;

public class JsonPathReader {

	private static final String DEFAULT_PATH = "$.path";

	@Getter
	private final ReadContext context;
	private final List<String> pathesRead;

	public JsonPathReader(ReadContext context) {
		this.context = context;
		this.pathesRead = new ArrayList<>();
	}

	public void clearPathesRead() {
		pathesRead.clear();
	}

	public String readFromContext(String path) {
		Object readFromContext = readFromContext(path, true, null);

		return readFromContext != null ? readFromContext.toString() : null;
	}

	public <T> T readFromContext(String path, boolean isDefault, Class<T> clazz) {
		path = isDefault ? DEFAULT_PATH.replace("path", path) : path;
		pathesRead.add(path);
		try {
			if (clazz != null) {
				return context.read(path, clazz);
			} else {
				return context.read(path);
			}
		} catch (PathNotFoundException e) {
			return null;
		}
	}

}
