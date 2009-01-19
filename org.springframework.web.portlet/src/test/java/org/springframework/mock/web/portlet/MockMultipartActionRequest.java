/*
 * Copyright 2002-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.mock.web.portlet;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.portlet.multipart.MultipartActionRequest;

/**
 * Mock implementation of the
 * {@link org.springframework.web.portlet.multipart.MultipartActionRequest} interface.
 *
 * <p>Useful for testing application controllers that access multipart uploads.
 * The {@link org.springframework.mock.web.MockMultipartFile} can be used to
 * populate these mock requests with files.
 *
 * @author Juergen Hoeller
 * @since 2.0
 * @see org.springframework.mock.web.MockMultipartFile
 */
public class MockMultipartActionRequest extends MockActionRequest implements MultipartActionRequest {

	private final Map<String, MultipartFile> multipartFiles = new LinkedHashMap<String, MultipartFile>();


	/**
	 * Add a file to this request. The parameter name from the multipart
	 * form is taken from the {@link org.springframework.web.multipart.MultipartFile#getName()}.
	 * @param file multipart file to be added
	 */
	public void addFile(MultipartFile file) {
		Assert.notNull(file, "MultipartFile must not be null");
		this.multipartFiles.put(file.getName(), file);
	}

	public Iterator<String> getFileNames() {
		return getFileMap().keySet().iterator();
	}

	public MultipartFile getFile(String name) {
		return this.multipartFiles.get(name);
	}

	public Map<String, MultipartFile> getFileMap() {
		return Collections.unmodifiableMap(this.multipartFiles);
	}

}
