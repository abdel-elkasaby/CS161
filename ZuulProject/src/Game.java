import java.util.ArrayList;

/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0 (February 2002)
 */

//edited by Abdelrahman Elkasaby on 10/21/2020
/*
 * players start outside a university and can move between rooms using the command "go" 
 * they are able to pick up and drop items
 * the player will win once they are holding all 9 items at the same time
 */

class Game 
{
    private Parser parser;
    private Room currentRoom;
    Room outside, theatre, pub, lab, office, oneTwenty, bathroom, cafeteria, secretRoom, courtyard, storage, electrical, parkingLot, janitorsCloset, staffLounge;
    ArrayList<Item> inventory = new ArrayList<Item>();
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    public static void main(String[] args) {
    	Game myGame = new Game();
    	myGame.play();
    }
    
    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {		
    	/*
    	 *[parkingLot]------[oneTwenty]-------------[cafeteria]---------[courtyard]
    	 * |						|											|	
    	 *[Pub]--------------[Outside]--------------[Theatre]-----------[storage]
    	 *	|					|											|
    	 *[bathroom]----------[Lab]------[Office]------[secretRoom]		[electrical]
    	 *	|								|
    	 * [janitorsCloset]				[staffLounge]
    	 */
        // create the rooms
        outside = new Room("outside the main entrance of the university");
        theatre = new Room("in a lecture theatre");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");
        oneTwenty = new Room("in the coolest place in the world.");
        bathroom = new Room("in the bathroom");
        cafeteria = new Room("in the cafeteria");
        secretRoom = new Room("in the secret room");
        courtyard = new Room("in the courtyard");
        storage = new Room("in the storage room");
        electrical = new Room("in the electrical room");
        parkingLot = new Room("in the parking lot");
        janitorsCloset = new Room("in the janitors closet");
        staffLounge = new Room("in the staff lounge");
        
        // initialise room exits
        outside.setExit("east", theatre);
        outside.setExit("south", lab);
        outside.setExit("west", pub);
        outside.setExit("north", oneTwenty);
        
        theatre.setExit("west", outside);
        theatre.setExit("east", storage);

        pub.setExit("east", outside);
        pub.setExit("south", bathroom);
        pub.setExit("north", parkingLot);

        lab.setExit("north", outside);
        lab.setExit("east", office);
        lab.setExit("west", bathroom);

        office.setExit("west", lab);
        office.setExit("east", secretRoom);
        office.setExit("south", staffLounge);
        
        oneTwenty.setExit("south", outside);
        oneTwenty.setExit("east", cafeteria);
        oneTwenty.setExit("west", parkingLot);
        
        bathroom.setExit("north", pub);
        bathroom.setExit("east", lab);
        bathroom.setExit("south", janitorsCloset);
        
        cafeteria.setExit("west", oneTwenty);
        cafeteria.setExit("east", courtyard);
        
        secretRoom.setExit("west", office);
        
        courtyard.setExit("west", cafeteria);
        courtyard.setExit("south", storage);
        
        storage.setExit("west", theatre);
        storage.setExit("north", courtyard);
        storage.setExit("south", electrical);
        
        electrical.setExit("north", storage);
        
        parkingLot.setExit("east", oneTwenty);
        parkingLot.setExit("south", pub);
        
        janitorsCloset.setExit("north", bathroom);
        
        staffLounge.setExit("north", office);

        currentRoom = outside;  // start game outside
        
        //items
        lab.addItem(new Item("Computer"));
        oneTwenty.addItem(new Item("Robot"));
        pub.addItem(new Item("Soda"));
        cafeteria.addItem(new Item("Sandwich"));
        secretRoom.addItem(new Item("Calculator"));
        bathroom.addItem(new Item("Soap"));
        storage.addItem(new Item("Chocolate"));
        janitorsCloset.addItem(new Item("Mop"));
        staffLounge.addItem(new Item("Coffee"));

    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to Adventure!");
        System.out.println("Adventure is a new, incredibly boring adventure game.");
        System.out.println("Win the game by picking up all items at the same time");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * If this command ends the game, true is returned, otherwise false is
     * returned.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        else if (commandWord.equals("inventory")) {
        	printInventory();
        }
        else if (commandWord.equals("get")) {
        	getItem(command);
    	}
	    else if (commandWord.equals("drop")) {
	    	dropItem(command);
		}
        return wantToQuit;
    }
    
    private void dropItem(Command command) 
    {//dropping items
        if(!command.hasSecondWord()) {
            // if there is no second word, we dont know what to drop
            System.out.println("Drop what?");
            return;
        }

        String item = command.getSecondWord();

        Item newItem = null;
        int index = 0;
        for (int i = 0; i < inventory.size(); i++) {
			if (inventory.get(i).getDescription().equals(item)) {
				newItem = inventory.get(i);
				index = i;
			}
		}

        if (newItem == null) {// if item is not here
            System.out.println("That item is not in your inventory!");
        }
        else { //drops item
            inventory.remove(index);
            currentRoom.addItem(new Item(item));
            System.out.println("Dropped: " + item);
        }
    }
    
    private void getItem(Command command) 
    {//picking up items
        if(!command.hasSecondWord()) {
            // if there is no second word, we dont know what to pick up
            System.out.println("Get what?");
            return;
        }
        
        String item = command.getSecondWord();

        Item newItem = currentRoom.getItem(item);

        if (newItem == null) // if item is not here
            System.out.println("That item is not here!");
        else { //picks up item
            inventory.add(newItem);
            currentRoom.removeItem(item);
            System.out.println("Picked up: " + item);
        }
        if (inventory.size() == 9) {//if you pick up all 9 items you win
        	System.out.println("You Win!");
        	System.exit(0); 
        }
    }
    
    private void printInventory() { // prints whats in inventory
    	String output = "";
    	for (int i = 0; i < inventory.size(); i++) {
			output += inventory.get(i).getDescription() + ", ";
		}
    	System.out.println("You are carrying: " + output);
    }
    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    { // prints info to help user understand game 
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to go to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    { //allows user to move to different rooms
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null)
            System.out.println("There is no door!");
        else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game. Return true, if this command
     * quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    { //quits game
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else
            return true;  // signal that we want to quit
    }
}
