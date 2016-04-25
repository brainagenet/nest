/*
 * (#) net.brainage.nest.data.model.AuditableEntity.java
 * Created on 2016-03-10
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
package net.brainage.nest.data.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Auditable;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * @author <a href="mailto:ms29.seo@gmail.com">ms29.seo</a>
 */
@Data
@MappedSuperclass
public abstract class AuditableEntity  {

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_on", nullable = false)
    private Date createdOn;

    @Column(name = "created_by", nullable = false)
    private Long createdBy;

    @Column(name = "created_by_username", nullable = false)
    private String createdByUsername;

    @Column(name = "created_by_name", nullable = false)
    private String createdByName;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_modified_on")
    private Date lastModifiedOn;

    @Column(name = "last_modified_by")
    private Long lastModifiedBy;

    @Column(name = "last_modified_by_username")
    private String lastModifiedByUsername;

    @Column(name = "last_modified_by_name")
    private String lastModifiedByName;

}
