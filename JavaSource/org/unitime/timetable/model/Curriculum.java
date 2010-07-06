package org.unitime.timetable.model;

import java.util.List;

import org.unitime.timetable.model.base.BaseCurriculum;
import org.unitime.timetable.model.dao.CurriculumDAO;



public class Curriculum extends BaseCurriculum implements Comparable<Curriculum> {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public Curriculum () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public Curriculum (java.lang.Long uniqueId) {
		super(uniqueId);
	}

/*[CONSTRUCTOR MARKER END]*/

	public static List<Curriculum> findAll(Long sessionId) {
	    return CurriculumDAO.getInstance().getSession()
	        .createQuery("select c from Curriculum c where c.department.session.uniqueId=:sessionId")
	        .setLong("sessionId", sessionId)
	        .setCacheable(true).list();
	}

    public static List<Curriculum> findByDepartment(Long deptId) {
        return CurriculumDAO.getInstance().getSession()
            .createQuery("select c from Curriculum c where c.department.uniqueId=:deptId")
            .setLong("deptId", deptId)
            .setCacheable(true).list();
    }
    
    public boolean canUserEdit(org.unitime.commons.User user) {
    	// Not authenticated -> false
    	if (user == null) return false;
    	
    	// Admin -> always true
    	if (Roles.ADMIN_ROLE.equals(user.getRole())) return true;
    	
    	// Not schedule deputy or curriculum manager -> false
    	if (!Roles.DEPT_SCHED_MGR_ROLE.equals(user.getRole()) &&
    			!Roles.CURRICULUM_MGR_ROLE.equals(user.getRole())) return false;
    	
		//TODO: Do we want to check Session status as well?
		//  E.g., getDepartment().effectiveStatusType().canOwnerEdit()

    	// Check department
    	TimetableManager tm = TimetableManager.getManager(user);
		return tm != null && tm.getDepartments().contains(getDepartment());
    }
    
    public int compareTo(Curriculum c) {
    	int cmp = getAbbv().compareToIgnoreCase(c.getAbbv());
    	if (cmp != 0) return cmp;
    	return getUniqueId().compareTo(c.getUniqueId());
    }
}