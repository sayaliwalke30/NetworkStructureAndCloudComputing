package studentportal.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import studentportal.DynamoDbConnector;
import studentportal.datamodel.Board;

public class BoardService {
	static DynamoDbConnector dbConnector;
	static DynamoDBMapper board_Map;

	public BoardService() {
		dbConnector = new DynamoDbConnector();
		dbConnector.init();
		board_Map = new DynamoDBMapper(dbConnector.getClient());
	}

	// add board
	public Board addBoard(Board b) {
		board_Map.save(b);
		return board_Map.load(Board.class, b.getId());
	}

	public void addBoard(String boardId, String courseId) {
		Board newBoard = new Board(boardId, courseId);
		board_Map.save(newBoard);
	}

	// get all
	public List<Board> getAllBoards() {
		return board_Map.scan(Board.class, new DynamoDBScanExpression());
	}

	// get one
	public Board getOneBoard(String boardId) {
		Map<String, AttributeValue> eav = new HashMap<>();
		eav.put(":boardId", new AttributeValue().withS(boardId));
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression().withFilterExpression("boardId=:boardId")
				.withExpressionAttributeValues(eav);
		List<Board> result = board_Map.scan(Board.class, scanExpression);
		if (result.size() != 0) {
			return result.get(0);
		}
		return null;

	}

	// update Board
	public Board updateBoard(String boardId, Board b) {
		Map<String, AttributeValue> eav = new HashMap<>();
		eav.put(":boardId", new AttributeValue().withS(boardId));
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression().withFilterExpression("boardId=:boardId")
				.withExpressionAttributeValues(eav);
		List<Board> result = board_Map.scan(Board.class, scanExpression);
		if (result.size() != 0) {
			String Id = result.get(0).getId();
			b.setId(Id);
			board_Map.save(b);
			return board_Map.load(Board.class, Id);
		}
		return null;
	}

	// delete Board
	public Board deleteBoard(String boardId) {
		Map<String, AttributeValue> eav = new HashMap<>();
		eav.put(":boardId", new AttributeValue().withS(boardId));
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression().withFilterExpression("boardId=:boardId")
				.withExpressionAttributeValues(eav);
		List<Board> result = board_Map.scan(Board.class, scanExpression);
		if (result.size() != 0) {
			board_Map.delete(result.get(0));
			return result.get(0);
		}
		return null;
	}

}
