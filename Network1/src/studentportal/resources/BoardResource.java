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

import studentportal.datamodel.Board;
import studentportal.exception.RecordNotFoundException;
import studentportal.services.BoardService;
import studentportal.services.ProfessorsService;

@Path("board")
public class BoardResource {
	BoardService boardService;

	public BoardResource() {
		boardService = new BoardService();
	}
	BoardService bs = new BoardService();

	// add new board
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Board addBoard(Board b) {
		System.out.println("Posted object " + b.toString());
		return bs.addBoard(b);
	}

	// get
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Board> getAllBoards() {
		
		return bs.getAllBoards();
	}

	// get by boardId
	@GET
	@Path("/{boardId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Board getOneBoard(@PathParam("boardId") String boardId) throws RecordNotFoundException {
		if (boardService.getAllBoards().size() == 0) {
			throw new RecordNotFoundException("Board not found");
		}
		return bs.getOneBoard(boardId);
	}
	// update
		@PUT
		@Path("/{boardId}")
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public Board updateBoard(@PathParam("boardId") String boardId, Board b) throws RecordNotFoundException {
			if (boardService.getAllBoards().size() == 0) {
				throw new RecordNotFoundException("Board to be updated not found");
			}
			return bs.updateBoard(boardId, b);
		}

	// delete
	@DELETE
	@Path("/{boardId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Board deleteBoard(@PathParam("boardId") String boardId) throws RecordNotFoundException {
		if (boardService.getAllBoards().size() == 0) {
			throw new RecordNotFoundException("Board to be deleted not found");
		}
		return bs.deleteBoard(boardId);
	}

	
}