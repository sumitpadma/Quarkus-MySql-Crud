package facultydetails.boundary;

import department.control.DepartmentRepository;
import department.entity.DepartmentEntity;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import facultydetails.control.FacultyDetailsRepository;
import facultydetails.entity.FacultyDetailsEntity;

@Path("/faculty")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FacultyDetailsResources {
    @Inject
    FacultyDetailsRepository repository;

    @Inject
    DepartmentRepository departmentRepository;

    @GET
    @Path("/getall")
    public Response getAll(){
            return Response.ok(repository.listAll()).build();

    }

    @GET
    @Path("/get/{id}")
    public Response getById(@PathParam("id") int id){
        FacultyDetailsEntity faculty = repository.findById((long) id);
        if(faculty==null)
        {
            Response.status(Response.Status.NOT_FOUND).build();
        }
        else{
            Response.ok(faculty).build();
        }

        return Response.ok(repository.listAll()).build();

    }

    @POST
    @Path("/add")
    @Transactional
    public Response addfaculty(FacultyDetailsEntity faculty) {
        repository.persist(faculty);
        return Response.status(Response.Status.CREATED).entity(faculty).build();
    }

    @PUT
    @Path("/update/{id}")
    @Transactional
    public Response updateFaculty(@PathParam("id") int id, FacultyDetailsEntity updatedFaculty) {
        FacultyDetailsEntity faculty = repository.findById((long) id);
        if (faculty == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        faculty.setName(updatedFaculty.getName());
        faculty.setEmail_id(updatedFaculty.getEmail_id());
        faculty.setPhone_no(updatedFaculty.getPhone_no());
        faculty.setDepartment_id(updatedFaculty.getDepartment_id());
        faculty.setDesignation(updatedFaculty.getDesignation());
        faculty.setJoining_date(updatedFaculty.getJoining_date());
        faculty.setDepartment(updatedFaculty.getDepartment());

        return Response.ok(faculty).build();
    }

    @DELETE
    @Path("/delete/{id}")
    @Transactional
    public Response deleteFaculty(@PathParam("id") int id) {
        boolean deleted = repository.deleteById((long) id);
        if (!deleted) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok("Succesfully Deleted").build();
    }

    @GET
    @Path("/get-with-department/{id}")
    public Response getFacultyWithDepartment(@PathParam("id") Long id) {
        FacultyDetailsEntity faculty = repository.findById(id);
        DepartmentEntity department = null;
        department = departmentRepository.findById((long) faculty.getDepartment_id());
        return Response.ok(new FacultyWithDepartmentDTO(faculty, department)).build();
    }
}

