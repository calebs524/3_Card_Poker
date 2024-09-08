import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.Text;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.event.ActionEvent;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.text.FontWeight;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.InputStream;
import javafx.scene.layout.Pane;


public class ThreeCardPokerGame extends Application {
    private Dealer theDealer;
    private Player playerone;
    private Player playertwo;
    private Text title;


    private Button startGame;
    private Button freshStart;
    private Button newLook;
    private Button exitGame;
    private MenuBar gameMenuBar;
    private Menu pause;
    private HBox startButtons;
    private EventHandler<ActionEvent> exitGameHandler;
    private EventHandler<ActionEvent> startGameHander;
    private HBox menuBar;
    private MenuItem NewLook;
    private MenuItem Exit;
    private MenuItem FreshStart;

    private VBox playeroneInfo;
    private HBox playeroneCards;
    private Text playeroneInfoText;

    private VBox playertwoInfo;
    private HBox playertwoCards;
    private Text playertwoInfoText;

    private TextField playerOneAnte;
    private TextField playerOnePairPlus;
    private TextField playerOnePlayWager;
    private Button infoGrabberButton;
    private EventHandler<ActionEvent> infoGrabber;

    private TextField playerTwoAnte;
    private TextField playerTwoPairPlus;
    private TextField playerTwoPlayWager;

    private VBox p1Enter;

    private EventHandler<ActionEvent> showPlayerCards;
    private Button fold1;
    private Button fold2;
    private TextField wager1;
    private TextField wager2;
    private Button takeWager;
    private VBox foldOrNa;

    private EventHandler<ActionEvent> playerWagers;
    private Button showDealer;
    private EventHandler<ActionEvent> showHand;
    private ThreeCardLogic logic;
    private Text status;
    private Text update;
    private VBox statusUpdate;

    private EventHandler<ActionEvent> newLookHandler;
    private Button nextRound;
    private String currBackground;
    private EventHandler<ActionEvent> freshStartHandler;

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        launch(args);

    }

    public String playerInfo(int player, Player play) {
        String info = "Player " + player + " Winnings: $" + play.getT() + "\nPlayer " + player + " Info:\nAnte: $" +
                play.getA() + "\nPair Plus: $" + play.getPPB() + "\nPlay Wager: $" + play.getPB();
        return info;
    }


    public void start(Stage primaryStage) throws FileNotFoundException {
        // TODO Auto-generated method stub
        primaryStage.setTitle("Welcome to Project #2");
        theDealer = new Dealer();
        playerone = new Player();
        playertwo = new Player();
        //title for 3 card poker game, todo: set text alignment
        title = new Text("Welcome to Three Card Poker");
        title.setTextAlignment(TextAlignment.CENTER);
        //title.setFocusTraversable(false);
        Font font = Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 35);
        title.setFont(font);

        currBackground = "-fx-background-color: #35654D;";

        title.setStyle("-fx-background-color: #00ff00;");
        title.setFill(Color.WHITE);
        // 3 buttons: start game, new look, exit
        startGame = new Button("Start Game");
        startGame.setFont(font);
        startGame.setMinHeight(200);
        startGame.setMinWidth(150);
        startGame.setStyle("-fx-background-color: #35654D; ");

        exitGame = new Button("Exit Game");
        exitGame.setFont(font);
        exitGame.setMinSize(150, 200);
        exitGame.setStyle("-fx-background-color: #35654D; ");

        exitGameHandler = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                menuBar = new HBox();

                System.exit(0);
            }
        };
        exitGame.setOnAction(exitGameHandler);

        nextRound = new Button("Next Round");


        startGameHander = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                theDealer.startGame();
                playerone.setH(theDealer);
                playertwo.setH(theDealer);
                theDealer.setH();


                //Start of menu Bar stuff:
                BorderPane root = new BorderPane();
                gameMenuBar = new MenuBar();
                pause = new Menu("Options");
                gameMenuBar.getMenus().add(pause);
                Exit = new MenuItem("Exit");
                FreshStart = new MenuItem("Fresh Start");
                NewLook = new MenuItem("New Look");

                pause.getItems().add(NewLook);
                pause.getItems().add(FreshStart);
                pause.getItems().add(Exit);

                Exit.setOnAction(exitGameHandler -> {
                    System.exit(0);
                });


                FreshStart.setOnAction(freshStartHandler -> {
                    playerone.freshStart();
                    playertwo.freshStart();
                    theDealer.startGame();
                    playeroneInfoText.setText(playerInfo(1, playerone));
                    playertwoInfoText.setText(playerInfo(2, playertwo));
                });


                NewLook.setOnAction(newLookHandler -> {
                    String styler = root.getStyle();
                    if(styler.substring(22).equals("#35654D;")){
                        root.setStyle("-fx-background-color: #b312a5;");
                        currBackground = "-fx-background-color: #b312a5;";

                    }
                    else{
                        root.setStyle("-fx-background-color: #35654D;");
                        currBackground = "-fx-background-color: #35654D;";
                    }
                });


                //end of menu Bar
                status=new Text("Status");
                update=new Text("Game Begins");
                statusUpdate=new VBox(status,update);
                status.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));

                update.setFont(Font.font("Verdana",  FontPosture.REGULAR, 18));


                statusUpdate.setAlignment(Pos.TOP_LEFT);
                VBox allDealer = new VBox(gameMenuBar,statusUpdate);


                Image blankImage = new Image("images/backCard.png", 100, 150, false, false);
                Text dealerTitle = new Text("Dealer's Hand");
                dealerTitle.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
                allDealer.getChildren().add(dealerTitle);
                HBox dealerCards = new HBox();
                for (int i = 0; i < 3; i++) {
                    dealerCards.getChildren().add(new ImageView(blankImage));
                }
                dealerCards.setAlignment(Pos.CENTER);
                allDealer.getChildren().add(dealerCards);
                allDealer.setAlignment(Pos.CENTER);
                root.setTop(allDealer);


                String p1info = playerInfo(1, playerone);
                playeroneInfoText = new Text(p1info);
                playeroneInfoText.setFont(Font.font("Verdana",  FontPosture.REGULAR, 18));



                playeroneInfo = new VBox(playeroneInfoText);
                playeroneInfo.setAlignment(Pos.CENTER);



                HBox imageHolder1 = new HBox();

                for (int i = 0; i < 3; i++) {
                    imageHolder1.getChildren().add(new ImageView(blankImage));
                }
                Text p1Card = new Text("Player One's Cards");
                p1Card.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
                playeroneInfo.getChildren().add(p1Card);
                playeroneInfo.getChildren().add(imageHolder1);

                root.setLeft(playeroneInfo);


                String p2info = playerInfo(2, playertwo);


                playertwoInfoText = new Text(p2info);
                playertwoInfoText.setFont(Font.font("Verdana",  FontPosture.REGULAR, 18));

                playertwoInfo = new VBox(playertwoInfoText);
                playertwoInfo.setAlignment(Pos.CENTER);


                HBox imageHolder2 = new HBox();

                for (int i = 0; i < 3; i++) {
                    imageHolder2.getChildren().add(new ImageView(blankImage));
                }
                Text p2Card = new Text("Player Two's Cards");
                p2Card.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
                playertwoInfo.getChildren().add(p2Card);

                playertwoInfo.getChildren().add(imageHolder2);

                root.setRight(playertwoInfo);


                playerOneAnte = new TextField();
                if(playerone.getA() == 0){
                playerOneAnte.setPromptText("Player One: Enter Ante ($5-$25)");
                playerOneAnte.setFocusTraversable(false);
                playerOneAnte.setMaxWidth(400);
                }
                else{
                    String p1A = "";
                    p1A += playerone.getA();
                    playerOneAnte.setText(p1A );
                    playerOneAnte.setEditable(false);
                    playerOneAnte.setMaxWidth(400);
                }

                playerOnePairPlus = new TextField();
                playerOnePairPlus.setPromptText("Player One: Enter Pair Plus(0 for none, or $5-$25)");
                playerOnePairPlus.setFocusTraversable(false);
                playerOnePairPlus.setMaxWidth(400);


                playerTwoAnte = new TextField();
                if(playertwo.getA() == 0){
                    playerTwoAnte.setPromptText("Player Two: Enter Ante ($5-$25)");
                    playerTwoAnte.setFocusTraversable(false);
                    playerTwoAnte.setMaxWidth(400);
                }
                else{
                    String p2A = "";
                    p2A += playertwo.getA();
                    playerTwoAnte.setText(p2A);
                    playerTwoAnte.setEditable(false);
                    playerTwoAnte.setMaxWidth(400);
                }


                playerTwoPairPlus = new TextField();
                playerTwoPairPlus.setPromptText("Player Two: Enter Pair Plus(0 for none, or $5-$25)");
                playerTwoPairPlus.setFocusTraversable(false);
                playerTwoPairPlus.setMaxWidth(400);


                infoGrabberButton = new Button("Click When Ready");
                infoGrabberButton.setMinSize(50, 50);

                Button toCards = new Button("Show Player cards");

                infoGrabber = new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent e) {
                        String playerOneAnteText = playerOneAnte.getText();
                        int p1Ante = Integer.valueOf(playerOneAnteText);

                        String playerOnePairPlusText = playerOnePairPlus.getText();
                        int p1PP = Integer.valueOf(playerOnePairPlusText);

                        while (p1Ante < 5 || p1Ante > 25) {
                            playerOneAnte.clear();
                            playerOneAnte.setPromptText("Player One: Enter Ante ($5-$25)");
                            playerOneAnteText = playerOneAnte.getText();
                            p1Ante = Integer.valueOf(playerOneAnteText);
                        }
                        playerOneAnte.setEditable(false);

                        while (p1PP < 5 || p1PP > 25) {
                            if (p1PP == 0) {
                                break;
                            }
                            playerOnePairPlus.clear();
                            playerOnePairPlus.setPromptText("Player One: Enter Pair Plus(0 for none, or $5-$25)");
                            playerOnePairPlusText = playerOnePairPlus.getText();
                            p1PP = Integer.valueOf(playerOnePairPlusText);
                        }
                        playerOnePairPlus.setEditable(false);


                        String playerTwoAnteText = playerTwoAnte.getText();
                        int p2Ante = Integer.valueOf(playerTwoAnteText);

                        String playerTwoPairPlusText = playerTwoPairPlus.getText();
                        int p2PP = Integer.valueOf(playerTwoPairPlusText);

                        while (p2Ante < 5 || p2Ante > 25) {
                            playerTwoAnte.clear();
                            playerTwoAnte.setPromptText("Player Two: Enter Ante ($5-$25)");
                            playerTwoAnteText = playerTwoAnte.getText();
                            p2Ante = Integer.valueOf(playerTwoAnteText);
                        }
                        playerTwoAnte.setEditable(false);

                        while (p2PP < 5 || p2PP > 25) {
                            if (p2PP == 0) {
                                break;
                            }
                            playerTwoPairPlus.clear();
                            playerTwoPairPlus.setPromptText("Enter Pair Plus(0 for none, or $5-$25)");
                            playerTwoPairPlusText = playerTwoPairPlus.getText();
                            p2PP = Integer.valueOf(playerTwoPairPlusText);
                        }
                        playerTwoPairPlus.setEditable(false);

                        playerone.setA(p1Ante);
                        playerone.setPPB(p1PP);

                        playertwo.setA(p2Ante);
                        playertwo.setPPB(p2PP);

                        playeroneInfoText.setText(playerInfo(1, playerone));
                        playertwoInfoText.setText(playerInfo(2, playertwo));
                        root.setCenter(null);
                        root.setCenter(toCards);
                    }
                };
                infoGrabberButton.setOnAction(infoGrabber);

                p1Enter = new VBox(playerOneAnte, playerOnePairPlus, playerTwoAnte, playerTwoPairPlus, infoGrabberButton);
                p1Enter.setAlignment(Pos.CENTER);
                root.setCenter(p1Enter);

                root.setStyle(currBackground);
                Scene scene = new Scene(root, 1200, 700);
                primaryStage.setScene(scene);
                primaryStage.show();



                wager1 = new TextField();
                wager1.setFocusTraversable(false);
                wager1.setPromptText("Player One: Enter \"Y\" to fold or \"N\" to continue ");
                wager1.setMaxWidth(450);


                wager2 = new TextField();
                wager2.setFocusTraversable(false);
                wager2.setPromptText("Player Two: Enter \"Y\" to fold or \"N\" to continue");
                wager2.setMaxWidth(450);


                takeWager = new Button("Once bets are made, press this to continue");

                foldOrNa = new VBox(wager1, wager2, takeWager);


                showPlayerCards = new EventHandler<ActionEvent>() {


                    public void handle(ActionEvent e) {
                        imageHolder1.getChildren().clear();
                        for (int i = 0; i < 3; i++) {
                            String ourimage1 = "images/";
                            ourimage1 += playerone.getH().get(i).getV();
                            ourimage1 += playerone.getH().get(i).getC();
                            ourimage1 += ".png";
                            Image p1images = new Image(ourimage1, 100, 150, false, false);
                            imageHolder1.getChildren().add(new ImageView(p1images));
                        }
                        root.setLeft(playeroneInfo);
                        imageHolder2.getChildren().clear();
                        for (int i = 0; i < 3; i++) {
                            String ourimage1 = "images/";
                            ourimage1 += playertwo.getH().get(i).getV();
                            ourimage1 += playertwo.getH().get(i).getC();
                            ourimage1 += ".png";
                            Image p1images = new Image(ourimage1, 100, 150, false, false);
                            imageHolder2.getChildren().add(new ImageView(p1images));
                        }
                        root.setRight(playertwoInfo);
                        root.setCenter(foldOrNa);

                    }
                };
                toCards.setOnAction(showPlayerCards);
                showDealer = new Button("Show Dealer's Hand");


                playerWagers = new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent e) {
                        String p1fold = wager1.getText();

                        if (p1fold.equals("Y")) {
                            playerone.fold();
                            update.setText(update.getText()+"\n Player 1 folds");

                        } else if(p1fold.equals("N")) {
                            playerone.setPB(playerone.getA());
                        }

                        String p2fold = wager2.getText();

                        if (p2fold.equals("Y")) {
                            playertwo.fold();
                            update.setText(update.getText()+"\n Player 2 folds");
                        } else if (p2fold.equals("N")) {

                            playertwo.setPB(playertwo.getA());
                        }


                        playeroneInfoText.setText(playerInfo(1, playerone));
                        playertwoInfoText.setText(playerInfo(2, playertwo));
                        root.setCenter(showDealer);

                    }
                };

                takeWager.setOnAction(playerWagers);

                showHand = new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent e) {
                        dealerCards.getChildren().clear();
                        for (int i = 0; i < 3; i++) {
                            String ourimage1 = "images/";
                            ourimage1 += theDealer.getH().get(i).getV();
                            ourimage1 += theDealer.getH().get(i).getC();
                            ourimage1 += ".png";
                            Image p1images = new Image(ourimage1, 100, 150, false, false);
                            dealerCards.getChildren().add(new ImageView(p1images));
                        }
                        root.setTop(allDealer);
                        logic = new ThreeCardLogic();
                        if (playerone.getH().size() != 0) {
                            //set pairplus bet winnings
                            int ppWin1 = logic.evalPPWinnings(playerone.getH(), playerone.getPPB());
                            playerone.setT(ppWin1);
                            // 1: check if they had a pairplus bet
                            //2 check if they won that
                            if(playerone.getPPB() != 0){
                                if(ppWin1 ==0) {
                                    update.setText(update.getText()+"\n Player one loses Pair Plus");
                                }
                                else {
                                    update.setText(update.getText()+"\n Player one wins Pair Plus");
                                }
                            }
                            int dealervplay1 = logic.compareHands(theDealer.getH(), playerone.getH());

                            if (dealervplay1 == 0) {
                                //if its a draw, then we set everything to zero
                                playerone.setPPB(0);
                                playerone.setT(playerone.getPB());
                                playerone.setPB(0);
                                update.setText(update.getText()+"\n Dealer does not have at least Queen high; ante wager is pushed");
                            }
                            else if (dealervplay1 == 1) {
                                int loss = -1 * (playerone.getA() + playerone.getPB());
                                playerone.setT(loss);
                                playerone.setPPB(0);
                                playerone.setPB(0);
                                playerone.setA(0);
                                update.setText(update.getText()+"\n Player one loses to dealer");
                            }
                            else {
                                int win = 2* (playerone.getA() + playerone.getPB());
                                playerone.setT(win);
                                playerone.setPPB(0);
                                playerone.setPB(0);
                                playerone.setA(0);
                                update.setText(update.getText()+"\n Player one wins to dealer");
                            }
                        }

                        if (playertwo.getH().size() != 0) {
                            int ppWin2 = logic.evalPPWinnings(playertwo.getH(), playertwo.getPPB());
                            playertwo.setT(ppWin2);

                            if(playertwo.getPPB() != 0){
                                if(ppWin2 ==0) {
                                    update.setText(update.getText()+"\n Player two loses Pair Plus");
                                }
                                else {
                                    update.setText(update.getText()+"\n Player two wins Pair Plus");
                                }
                            }


                            int dealervplay2 = logic.compareHands(theDealer.getH(), playertwo.getH());
                            if (dealervplay2 == 0) {
                                //if its a draw, then we set everything to zero
                                playertwo.setPPB(0);
                                playertwo.setT(playertwo.getPB());
                                playertwo.setPB(0);
                                update.setText(update.getText()+"\n Dealer does not have at least Queen high; ante wager is pushed");
                            }
                            else if (dealervplay2 == 1) {
                                int loss = -1 * (playertwo.getA() + playertwo.getPB());
                                playertwo.setT(loss);
                                playertwo.setPPB(0);
                                playertwo.setPB(0);
                                playertwo.setA(0);
                                update.setText(update.getText()+"\n Player two loses to dealer");
                            }
                            else {
                                int win = 2* (playertwo.getA() + playertwo.getPB());
                                playertwo.setT(win);
                                playertwo.setPPB(0);
                                playertwo.setPB(0);
                                playertwo.setA(0);
                                update.setText(update.getText()+"\n Player two wins to dealer");
                            }
                        }

                        playeroneInfoText.setText( playerInfo(1, playerone));
                        playertwoInfoText.setText(playerInfo(2, playertwo));
                        showDealer.setDisable(true);

                        root.setCenter(null);
                        root.setCenter(nextRound);
                    }

                };
                showDealer.setOnAction(showHand);
            }
        };




        startGame.setOnAction(startGameHander);

        nextRound.setOnAction(startGameHander);


        newLook = new Button("New Look");
        newLook.setFont(font);
        newLook.setMinSize(150, 200);
        newLook.setStyle("-fx-background-color: #35654D; ");

        startButtons = new HBox(startGame, newLook, exitGame);
        startButtons.setAlignment(Pos.BOTTOM_CENTER);


        StackPane root = new StackPane();
        root.getChildren().add(title);
        root.getChildren().add(startButtons);
        root.setStyle("-fx-background-color: #35654D;");


        newLookHandler = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                String styler = root.getStyle();
                if(styler.substring(22).equals("#35654D;")){
                    root.setStyle("-fx-background-color: #b312a5;");
                    newLook.setStyle("-fx-background-color: #b312a5; ");
                    startGame.setStyle("-fx-background-color: #b312a5; ");
                    exitGame.setStyle("-fx-background-color: #b312a5; ");
                    currBackground = "-fx-background-color: #b312a5;";
                }
                else{
                    root.setStyle("-fx-background-color: #35654D;");
                    newLook.setStyle("-fx-background-color: #35654D; ");
                    startGame.setStyle("-fx-background-color: #35654D; ");
                    exitGame.setStyle("-fx-background-color: #35654D; ");
                    currBackground = "-fx-background-color: #35654D;";
                }
            }};
        newLook.setOnAction(newLookHandler);


        Scene scene = new Scene(root, 1000, 700);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}

