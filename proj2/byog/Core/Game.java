package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Map;
import java.util.Set;

public class Game {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;

    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    public void playWithKeyboard() {
    }

    /**
     * Method used for autograding and testing the game code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The game should
     * behave exactly as if the user typed these characters into the game after playing
     * playWithKeyboard. If the string ends in ":q", the same world should be returned as if the
     * string did not end with q. For example "n123sss" and "n123sss:q" should return the same
     * world. However, the behavior is slightly different. After playing with "n123sss:q", the game
     * should save, and thus if we then called playWithInputString with the string "l", we'd expect
     * to get the exact same world back again, since this corresponds to loading the saved game.
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] playWithInputString(String input) {
        // TODO: Fill out this method to run the game using the input passed in,
        // and return a 2D tile representation of the world that would have been
        // drawn if the same inputs had been given to playWithKeyboard().
        java.util.Map<String,Double> inputMessage = InputStringAnalysis(input);
        TETile[][] finalWorldFrame = null;
        if(inputMessage.get("model") > 0 ){
            finalWorldFrame = GenerateWorld(inputMessage.get("seed"));
        }
        else {
            finalWorldFrame = LoadWorld();
        }

        if(inputMessage.get("save") > 0){
            SaveWorld(finalWorldFrame);
        }

        return finalWorldFrame;

    }

    /**
    * Analysis input message to get " seed , new game or load " and " :q or s "
     * 2022.07.30
    * @param inputString the message that user input.
     * @return A Map that include model (1 new game or 0 load) , seed and whether save(1 ,0)
    * */
    private Map<String,Double> InputStringAnalysis(String inputString){
        java.util.Map<String,Double> message = new java.util.HashMap<String,Double>(3);


        if(inputString.charAt(0) == 'N'|| inputString.charAt(0)=='n'){
            message.put("model",1.0);

            int i = 1;
            String seed_str = "0";

            while ((inputString.charAt(i) <= 57)&&(inputString.charAt(i) >= 48)){
                // use ascII
                String c =  String.valueOf(inputString.charAt(i));
                seed_str = seed_str + c;
                i++;
            }

            Double seed = Double.parseDouble(seed_str);
            message.put("seed",seed);
            String inputString_last = inputString.substring(i);
            if(inputString_last.contains(":Q")||inputString_last.contains(":q")){
                message.put("save",1.0);
            }
            else{
                message.put("save",0.0);
            }
            return message;


        }
        else if(inputString.charAt(0) == 'L'|| inputString.charAt(0)=='l'){

            message.put("model",0.0);
            message.put("seed",null);

            if(inputString.contains(":Q")||inputString.contains(":q")){
                message.put("save",1.0);
            }
            else {
                message.put("save",0.0);
            }

            return message;
        }

        throw new RuntimeException("Please input truly param.");
    }


    /**
     * Initialize the array of game world by letting every position is nothing.
     * 2022.07.30
     * @param world An array to represent the world.
     * @return
     * */
    private static void TETile_Ini(TETile[][] world){
        for (int x = 0; x < world.length; x += 1) {
            for (int y = 0; y < world[0].length; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
    }

    /**
     * Generate a World be made with array TETile by the seed.
     * 2022.07.30
     * @param seed the random seed of world.
     * @return A array that contain every position's message of world.
     * */
    private TETile[][] GenerateWorld(double seed){
        TETile[][] Tiles = new TETile[WIDTH][HEIGHT];
        TETile_Ini(Tiles);
        // Todo：根据种子随机在图中撒下n个点；
        // Todo:在保证各个点不碰撞的情况下以该点为左下角随机生成矩形；
        // Todo:将各个矩形相连接。在每个矩形上生成门;

        // Todo: 房间类：位置，长，宽；
        return null;
    }

    /**
     * Load a World from a txt file that saved last time.
     * 2022.07.30
     * @param
     * @return  A array that contain every position's message of world.
     * */
    private TETile[][] LoadWorld(){
        return null;
    }

    private void SaveWorld(TETile[][] world){

    }


}
