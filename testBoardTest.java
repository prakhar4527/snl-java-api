import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qainfotech.tap.training.snl.api.Board;
import com.qainfotech.tap.training.snl.api.GameInProgressException;
import com.qainfotech.tap.training.snl.api.InvalidTurnException;
import com.qainfotech.tap.training.snl.api.MaxPlayersReachedExeption;
import com.qainfotech.tap.training.snl.api.NoUserWithSuchUUIDException;
import com.qainfotech.tap.training.snl.api.PlayerExistsException;

public class testBoardTest 
{
	Board b;
	//BoardTest bt=new BoardTest();
	
	/*@BeforeTest
	public void checkBoardConstructor() throws FileNotFoundException, UnsupportedEncodingException, IOException
	{
		bt=new BoardTest(); 
		bt.BoardConstructor();
	}*/
	@BeforeTest
	public void startGame() throws FileNotFoundException, UnsupportedEncodingException, IOException
	{
		b=new Board();
	}
	@Test(expectedExceptions= PlayerExistsException.class)
	public void checkPlayerExistsException() throws FileNotFoundException, UnsupportedEncodingException, PlayerExistsException, GameInProgressException, MaxPlayersReachedExeption, IOException
	{
		b=new Board();
		b.registerPlayer("Prakhar");
		b.registerPlayer("Prakhar");

	}
	@Test(expectedExceptions= MaxPlayersReachedExeption.class)
	public void checkMaxPlayersReachedExeption() throws FileNotFoundException, UnsupportedEncodingException, PlayerExistsException, GameInProgressException, MaxPlayersReachedExeption, IOException
	{
		b=new Board();
		b.registerPlayer("Praksshar");
		b.registerPlayer("Prggakhar");
		b.registerPlayer("Prakhaaar");
		b.registerPlayer("Praxxkhar");
		b.registerPlayer("Prakhjjar");
	}
	@Test(expectedExceptions= GameInProgressException .class)
	public void checkGameInProgressException () throws FileNotFoundException, UnsupportedEncodingException, PlayerExistsException, GameInProgressException, MaxPlayersReachedExeption, IOException, InvalidTurnException
	{
		b=new Board();
		JSONArray players=b.registerPlayer("Prakhar");
		JSONObject obj=players.getJSONObject(0);
		UUID uuid=UUID.fromString(obj.getString("uuid"));
		    b.rollDice(uuid);
		    b.registerPlayer("Prakhjjar");	    
	}
//	@Test(expectedExceptions= NoUserWithSuchUUIDException.class)
	public void checkNoUserWithSuchUUIDException () throws FileNotFoundException, UnsupportedEncodingException, PlayerExistsException, GameInProgressException, MaxPlayersReachedExeption, IOException, InvalidTurnException, NoUserWithSuchUUIDException
	{
		/*b=new Board();
		JSONArray players=b.registerPlayer("bhfgd");
		JSONObject obj=players.getJSONObject(0);
		UUID uuid=UUID.fromString(obj.getString("uuid"));
		b.deletePlayer(uuid);
		   */
		UUID uuid=UUID.randomUUID();
		b.deletePlayer(uuid);
	}
	@Test(expectedExceptions= InvalidTurnException.class)
	public void checkInvalidTurnException() throws FileNotFoundException, UnsupportedEncodingException, PlayerExistsException, GameInProgressException, MaxPlayersReachedExeption, IOException, InvalidTurnException, NoUserWithSuchUUIDException
	{
		UUID uuid=UUID.randomUUID();
		b.rollDice(uuid);
	}
}
