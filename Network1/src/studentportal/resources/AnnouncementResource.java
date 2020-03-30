package studentportal.resources;


import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import studentportal.datamodel.Announcements;
import studentportal.exception.RecordNotFoundException;
import studentportal.services.AnnouncementService;
import studentportal.services.BoardService;



@Path("announcement")
public class AnnouncementResource {
	AnnouncementService a_service;

	public AnnouncementResource() {
		a_service = new AnnouncementService();
	}
	AnnouncementService bs = new AnnouncementService();
	
	//POST
		@POST
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public Announcements addAnnouncement(Announcements announce) {
			System.out.println("Posted object " + announce.toString());
			return a_service.addAnnouncement(announce);
		}
	
	//GET
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Announcements> getAllAnnoucements(){
		System.out.println("In get announcement");
		return a_service.getAllAnnouncements();
	}
	
	//get an announcement by annoucementid and boardid
	@GET
	@Path("/{boardId}_{announcementId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Announcements getOneAnnoucement(@PathParam("announcementId")String announcementId,@PathParam("boardId")String boardId) throws RecordNotFoundException {
		if (a_service.getAllAnnouncements().size() == 0) {
			throw new RecordNotFoundException("Announcement not found");
		}
		return a_service.getOneAnnouncement(announcementId, boardId);
	}
	
	//get announcements by boardId
	@GET
	@Path("/board/{boardId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Announcements> getAnnouncementsByBoard(@PathParam("boardId")String boardId) throws RecordNotFoundException{
		if (a_service.getAllAnnouncements().size() == 0) {
			throw new RecordNotFoundException("Announcement not found");
		}
		return a_service.getAnnoucementsByBoard(boardId);
	}
	//update
		@PUT
		@Path("/{boardId}_{annoucementId}")
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public Announcements updateAnnouncement(@PathParam("announcementId")String announcementId,@PathParam("boardId")String boardId,Announcements a) throws RecordNotFoundException {
			if (a_service.getAllAnnouncements().size() == 0) {
				throw new RecordNotFoundException("Announcement not found");
			}
			return a_service.updateAnnouncement(announcementId, boardId, a);
		}
	//delete
	@DELETE
	@Path("/{boardId}_{announcementId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Announcements deleteAnnoucement(@PathParam("announcementId")String announcementId,@PathParam("boardId")String boardId) throws RecordNotFoundException {
		if (a_service.getAllAnnouncements().size() == 0) {
			throw new RecordNotFoundException("Announcement not found");
		}
		return a_service.deleteAnnouncement(announcementId, boardId);
	}
	
	
	
	
}
