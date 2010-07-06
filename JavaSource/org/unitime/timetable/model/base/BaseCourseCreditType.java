/*
 * UniTime 3.2 (University Timetabling Application)
 * Copyright (C) 2010, UniTime LLC, and individual contributors
 * as indicated by the @authors tag.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
*/
package org.unitime.timetable.model.base;

import java.io.Serializable;

import org.unitime.timetable.model.CourseCreditType;
import org.unitime.timetable.model.RefTableEntry;

public abstract class BaseCourseCreditType extends RefTableEntry implements Serializable {
	private static final long serialVersionUID = 1L;

	private String iLegacyCourseMasterCode;
	private String iAbbreviation;


	public static String PROP_LEGACY_CRSE_MASTER_CODE = "legacyCourseMasterCode";
	public static String PROP_ABBREVIATION = "abbreviation";

	public BaseCourseCreditType() {
		initialize();
	}

	public BaseCourseCreditType(Long uniqueId) {
		setUniqueId(uniqueId);
		initialize();
	}

	protected void initialize() {}

	public String getLegacyCourseMasterCode() { return iLegacyCourseMasterCode; }
	public void setLegacyCourseMasterCode(String legacyCourseMasterCode) { iLegacyCourseMasterCode = legacyCourseMasterCode; }

	public String getAbbreviation() { return iAbbreviation; }
	public void setAbbreviation(String abbreviation) { iAbbreviation = abbreviation; }

	public boolean equals(Object o) {
		if (o == null || !(o instanceof CourseCreditType)) return false;
		if (getUniqueId() == null || ((CourseCreditType)o).getUniqueId() == null) return false;
		return getUniqueId().equals(((CourseCreditType)o).getUniqueId());
	}

	public int hashCode() {
		if (getUniqueId() == null) return super.hashCode();
		return getUniqueId().hashCode();
	}

	public String toString() {
		return "CourseCreditType["+getUniqueId()+" "+getLabel()+"]";
	}

	public String toDebugString() {
		return "CourseCreditType[" +
			"\n	Abbreviation: " + getAbbreviation() +
			"\n	Label: " + getLabel() +
			"\n	LegacyCourseMasterCode: " + getLegacyCourseMasterCode() +
			"\n	Reference: " + getReference() +
			"\n	UniqueId: " + getUniqueId() +
			"]";
	}
}
