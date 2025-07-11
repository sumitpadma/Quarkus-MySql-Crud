    package department.boundary;
    
    
    import department.control.DepartmentRepository;
    import department.entity.DepartmentEntity;
    import jakarta.inject.Inject;
    import jakarta.transaction.Transactional;
    import jakarta.ws.rs.*;
    import jakarta.ws.rs.core.MediaType;
    import jakarta.ws.rs.core.Response;
    
    import java.util.List;
    
    @Path("/department")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public class DepartmentResources {
    
        @Inject
        DepartmentRepository repository;
    
        @GET
        @Path("/getall")
        public Response getAll() {
            List<DepartmentEntity> result =repository.listAll();
            if (result.isEmpty()) {
                return Response.ok("No Data Available").build();
            } else {
                return Response.ok(repository.listAll()).build();
    
            }
        }
    
        @GET
        @Path("/get/{id}")
        public Response getByID(@PathParam("id") int id ) {
            DepartmentEntity department =repository.findById((long) id);
            if (department==null) {
                return Response.ok("Please Enter a valid ID ").build();
            } else {
                return Response.ok(department).build();
    
            }
        }
    
        @POST
        @Path("/add")
        @Transactional
        public Response add(DepartmentEntity department ) {
            repository.persistAndFlush(department);
            return Response.status(Response.Status.CREATED).entity(department).build();
        }
    
        @PUT
        @Path("/update/{id}")
        @Transactional
        public Response update(@PathParam("id") int id ,DepartmentEntity updateEntity) {
            DepartmentEntity department =repository.findById((long) id);
    
            department.setName(updateEntity.getName());
            department.setDescription(updateEntity.getDescription());
            return Response.ok(department).build();
        }
    
        @DELETE
        @Path("/delete/{id}")
        @Transactional
        public Response deleteById(@PathParam("id") int id){
            boolean result=repository.deleteById((long) id);
            if(result==true){
                return Response.ok("Deleted Succesfully").build();
            }else{
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        }
    }
