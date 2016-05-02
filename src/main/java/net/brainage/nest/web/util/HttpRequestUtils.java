/*
 * (#) net.brainage.nest.web.util.HttpRequestUtils.java
 * Created on 2016-05-03
 *
 * Copyright 2015 brainage.net
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.brainage.nest.web.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ms29.seo on 2016-05-03.
 */
@Slf4j
public class HttpRequestUtils {

    private HttpRequestUtils() {
    }

    /**
     * 사용자의 Language를 얻는다. HTTP Header의 "Accept-Language"의 값을 기준으로 얻는다.
     */
    public static String getLanguage(HttpServletRequest httpRequest) {
        String acceptLanguage = httpRequest.getHeader(HttpHeaders.ACCEPT_LANGUAGE);
        log.debug("accept language: {}", acceptLanguage);
        int commaIndex = acceptLanguage.indexOf(",");
        String lang = acceptLanguage;
        if (commaIndex > -1) {
            lang = acceptLanguage.substring(0, commaIndex);
        }
        log.debug("  --> language: {}", lang);
        return lang;
    }

}
