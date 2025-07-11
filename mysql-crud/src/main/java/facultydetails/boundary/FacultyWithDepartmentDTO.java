package facultydetails.boundary;

import department.entity.DepartmentEntity;
import facultydetails.entity.FacultyDetailsEntity;

public class FacultyWithDepartmentDTO {
    public FacultyDetailsEntity faculty;
    public DepartmentEntity department;

    public FacultyWithDepartmentDTO(FacultyDetailsEntity faculty, DepartmentEntity department) {
        this.faculty = faculty;
        this.department = department;
    }
}