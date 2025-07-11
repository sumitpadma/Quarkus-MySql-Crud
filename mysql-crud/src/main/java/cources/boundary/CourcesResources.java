package cources.boundary;


import cources.control.CourcesRepository;
import cources.entity.CourcesEntity;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jdk.javadoc.doclet.Reporter;
import net.bytebuddy.asm.Advice;

import java.util.List;

@Path("/cources")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CourcesResources {

    @Inject
    CourcesRepository courcesRepository;

    @GET
    @Path("/getall")
    public Response getAll(){
        return Response.ok(courcesRepository.listAll()).build();
    }

    @GET
    @Path("/get/{id}")
    public Response getAll(@PathParam("id")int id){
        CourcesEntity cource = courcesRepository.findById((long) id);
        if (cource==null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }else{
            return Response.ok(cource).build();
        }
    }

    @POST
    @Path("/add")
    @Transactional
    public Response add(CourcesEntity newCource){
        courcesRepository.persistAndFlush(newCource);
        return Response.status(Response.Status.CREATED).entity(newCource).build();
    }

    @PUT
    @Path("/update/{id}")
    @Transactional
    public Response update(@PathParam("id")int id,CourcesEntity newCource){
        CourcesEntity cources =courcesRepository.findById((long) id);

        cources.setName(newCource.getName());
        cources.setCode(newCource.getCode());
        cources.setSemester(newCource.getSemester());
        cources.setDepartment_id(newCource.getDepartment_id());

        return Response.ok("Succesfully updated").entity(cources).build();
    }

    @DELETE
    @Transactional
    @Path("/delete/{id}")
    public Response delete (@PathParam("id")int id){
        CourcesEntity cources =courcesRepository.findById((long) id);
        boolean delete =courcesRepository.deleteById((long)id);
        if (delete==true){
            return Response.ok("Succesfully Deleted ").build();
        }
        else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
