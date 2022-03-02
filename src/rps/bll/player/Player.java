package rps.bll.player;

//Project imports
import rps.bll.game.IGameState;
import rps.bll.game.Move;
import rps.bll.game.Result;

//Java imports
import java.util.ArrayList;
import java.util.Random;

/**
 * Example implementation of a player.
 *
 * @author smsj
 */
public class Player implements IPlayer {

    private String name;
    private PlayerType type;

    /**
     * @param name
     */
    public Player(String name, PlayerType type) {
        this.name = name;
        this.type = type;
    }


    @Override
    public String getPlayerName() {
        return name;
    }


    @Override
    public PlayerType getPlayerType() {
        return type;
    }


    /**
     * Decides the next move for the bot...
     * @param state Contains the current game state including historic moves/results
     * @return Next move
     */
    @Override
    public Move doMove(IGameState state) {
        //Historic data to analyze and decide next move...
        ArrayList<Result> results = (ArrayList<Result>) state.getHistoricResults();


        //Implement better AI here...
        int play = weightedRandomPlay(results);

        /*
            Hvis play er:
            0 = Rock,
            1 = Paper,
            2 = Scissor
         */
        return Move.values()[play];

    }


    private int weightedRandomPlay(ArrayList<Result> results)
    {
        int rock = 1;
        int paper = 1;
        int scissor = 1;

        for (Result r:results)
        {
            String playerMove;
            if (r.getWinnerPlayer().getPlayerName() != getPlayerName())
            {
                playerMove = r.getWinnerMove().name();
            }
            else
            {
                playerMove = r.getLoserMove().name();
            }
            switch (playerMove)
            {
                case "Rock":
                    rock+=1;
                    paper+=2;
                    scissor+=0;
                    break;
                case "Paper":
                    rock+=0;
                    paper+=1;
                    scissor+=2;
                    break;
                case "Scissor":
                    rock+=2;
                    paper+=0;
                    scissor+=1;
                    break;
            }
        }

        Random random = new Random();
        int i = random.nextInt(rock+paper+scissor);
        if (i < rock)
        {
            return 0;//Rock
        }
        else if (i < rock+paper)
        {
            return 1;//Paper
        }
        else
        {
            return 2;//Scissor
        }
    }
}
