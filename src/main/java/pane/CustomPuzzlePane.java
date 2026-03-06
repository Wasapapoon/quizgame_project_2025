package pane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.scene.Cursor;
import mode.GameMode;
import utils.Goto;
import item.usage.hasHint;

import java.io.File;
import java.util.List;
import java.util.Objects;

public class CustomPuzzlePane extends VBox {
    private static Boolean backButtonClicked = false;

    public CustomPuzzlePane() {
        setSpacing(18);
        setAlignment(Pos.TOP_CENTER);
        setPadding(new Insets(28));

        showListView();
    }

    private void showListView() {
        getChildren().clear();

        Text title = new Text("Custom Puzzles");
        title.setFont(Font.font("Noto Sans Thai", FontWeight.BOLD, 45));
        title.setFill(Color.WHITE);

        ObservableList<Goto.CustomPuzzleEntry> customPuzzles = FXCollections.observableArrayList(Goto.getCustomPuzzles());
        ListView<Goto.CustomPuzzleEntry> customList = new ListView<>(customPuzzles);
        customList.setStyle("-fx-background-radius: 10; -fx-border-radius: 10; -fx-border-color: transparent;");
        customList.setPrefWidth(720);
        customList.setPrefHeight(400);
        customList.setMaxWidth(800);

        customList.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Goto.CustomPuzzleEntry item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    VBox root = new VBox(10);
                    root.setPadding(new Insets(10));
                    root.setStyle("-fx-border-color: #ccc; -fx-border-width: 0 0 1 0;");
                    root.setStyle("-fx-border-color: #ccc; -fx-border-width: 0 0 1 0;");

                    Text header = new Text(item.difficulty + " | Answer: " + item.puzzle.getAnswer());
                    header.setFont(Font.font("Noto Sans Thai", FontWeight.BOLD, 16));

                    FlowPane imagesPane = new FlowPane(10, 10);
                    List<String> paths = item.puzzle.getPictureNames();
                    List<String> hints = (item.puzzle instanceof hasHint h) ? h.getHint() : List.of();

                    for (int i = 0; i < paths.size(); i++) {
                        String path = paths.get(i);
                        String hint = (i < hints.size()) ? hints.get(i) : "";

                        VBox imgBox = new VBox(5);
                        imgBox.setAlignment(Pos.CENTER);
                        try {
                            Image img;
                            if (path.startsWith("/") || path.contains(":/") || path.contains(":\\")) {
                                // Assume it's an absolute path if it looks like one
                                img = new Image(new File(path).toURI().toString(), 80, 80, true, true);
                            } else {
                                // Try resource
                                img = new Image(Objects.requireNonNull(Goto.class.getResourceAsStream("/" + path)), 80, 80, true, true);
                            }
                            ImageView iv = new ImageView(img);
                            imgBox.getChildren().add(iv);
                        } catch (Exception e) {
                            imgBox.getChildren().add(new Text("No Image"));
                        }
                        if (!hint.isEmpty()) {
                            Text hintText = new Text("Hint: " + hint);
                            hintText.setFont(Font.font("Noto Sans Thai", 12));
                            imgBox.getChildren().add(hintText);
                        }
                        imagesPane.getChildren().add(imgBox);
                    }

                    root.getChildren().addAll(header, imagesPane);
                    setGraphic(root);
                }
            }
        });

        Button addButton = new Button("Add New Puzzle");
        styleButton(addButton);
        addButton.setOnAction(e -> showAddView());

        Button removePuzzleButton = new Button("Remove Selected Puzzle");
        styleButton(removePuzzleButton);
        removePuzzleButton.setOnAction(e -> {
            Goto.CustomPuzzleEntry selected = customList.getSelectionModel().getSelectedItem();
            if (selected != null) {
                Goto.removeCustomPuzzle(selected.id);
                customPuzzles.setAll(Goto.getCustomPuzzles());
            }
        });

        HBox listButtons = new HBox(20, addButton, removePuzzleButton);
        listButtons.setAlignment(Pos.CENTER);

        getChildren().addAll(title, customList, listButtons, createBackButton());
    }

    private void showAddView() {
        getChildren().clear();

        Text title = new Text("Add New Puzzle");
        title.setFont(Font.font("Noto Sans Thai", FontWeight.BOLD, 45));
        title.setFill(Color.WHITE);

        ComboBox<GameMode> difficultyBox = new ComboBox<>(
                FXCollections.observableArrayList(GameMode.EASY, GameMode.MEDIUM, GameMode.HARD)
        );
        difficultyBox.setValue(GameMode.EASY);
        difficultyBox.setPrefWidth(520);

        TextField answerField = new TextField();
        answerField.setPromptText("Answer (required)");
        answerField.setPrefWidth(520);


        FlowPane imageGallery = new FlowPane(10, 10);
        imageGallery.setAlignment(Pos.CENTER_LEFT);
        imageGallery.setPadding(new Insets(10));
        imageGallery.setStyle("-fx-background-color: rgba(255, 255, 255, 0.1); -fx-background-radius: 10; -fx-border-radius: 10; -fx-border-color: transparent;");
        imageGallery.setPrefWrapLength(720);
        imageGallery.setMinHeight(150);

        ObservableList<ImageHintBox> selectedImages = FXCollections.observableArrayList();

        Label statusLabel = new Label();
        statusLabel.setTextFill(Color.WHITE);
        statusLabel.setFont(Font.font("Noto Sans Thai", FontWeight.BOLD, 16));

        Button chooseImagesButton = new Button("Choose Images…");
        styleButton(chooseImagesButton);

        chooseImagesButton.setOnAction(e -> {
            FileChooser chooser = new FileChooser();
            chooser.setTitle("Choose Puzzle Image(s)");
            chooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg", "*.gif")
            );

            List<File> files = chooser.showOpenMultipleDialog(getScene().getWindow());
            if (files != null) {
                GameMode currentDifficulty = difficultyBox.getValue();
                int required = switch (currentDifficulty) {
                    case EASY -> 2;
                    case MEDIUM -> 3;
                    case HARD -> 4;
                    default -> 0;
                };

                for (File f : files) {
                    if (selectedImages.size() >= required) {
                        statusLabel.setText("You can only add " + required + " images for " + currentDifficulty + " difficulty.");
                        break;
                    }
                    String path = f.getAbsolutePath();
                    ImageHintBox box = new ImageHintBox(path);
                    selectedImages.add(box);
                    addImageToGallery(imageGallery, selectedImages, box);
                }
            }
        });

        Button saveButton = new Button("Save Puzzle");
        styleButton(saveButton);
        saveButton.setOnAction(e -> {
            GameMode difficulty = difficultyBox.getValue();
            String answer = safeTrim(answerField.getText());
            if (answer.isEmpty()) {
                statusLabel.setText("Please enter an answer.");
                return;
            }
            int requiredImages = switch (difficulty) {
                case EASY -> 2;
                case MEDIUM -> 3;
                case HARD -> 4;
                default -> 0;
            };

            if (selectedImages.size() != requiredImages) {
                statusLabel.setText("Please choose exactly " + requiredImages + " images for " + difficulty + " difficulty.");
                return;
            }

            List<String> paths = selectedImages.stream().map(b -> b.path).toList();
            List<String> hints = selectedImages.stream().map(b -> b.hintField.getText().trim()).toList();

            boolean needsHints = difficulty == GameMode.MEDIUM || difficulty == GameMode.HARD;
            if (needsHints) {
                for (String h : hints) {
                    if (h.isEmpty()) {
                        statusLabel.setText("All images must have hints for Medium/Hard puzzles.");
                        return;
                    }
                }
            }

            try {
                Goto.addCustomPuzzle(difficulty, answer, paths, hints);
                showListView();
            } catch (Exception ex) {
                statusLabel.setText("Error: " + ex.getMessage());
            }
        });

        Button cancelButton = new Button("Cancel");
        styleButton(cancelButton);
        cancelButton.setOnAction(e -> showListView());

        HBox actionButtons = new HBox(20, saveButton, cancelButton);
        actionButtons.setAlignment(Pos.CENTER);

        difficultyBox.setOnAction(e -> {
            selectedImages.clear();
            imageGallery.getChildren().clear();
            statusLabel.setText("");
        });

        VBox form = new VBox(15);
        form.setAlignment(Pos.CENTER_LEFT);
        form.setMaxWidth(760);
        form.getChildren().addAll(
                labeled("Difficulty", difficultyBox),
                labeled("Answer", answerField),
                new Label("Images & Hints (click image to remove):") {{
                    setTextFill(Color.WHITE);
                    setFont(Font.font("Noto Sans Thai", FontWeight.BOLD, 16));
                }},
                imageGallery,
                chooseImagesButton,
                statusLabel,
                actionButtons
        );

        getChildren().addAll(title, form);
    }

    private void addImageToGallery(FlowPane gallery, ObservableList<ImageHintBox> items, ImageHintBox box) {
        try {
            Image img = new Image(new File(box.path).toURI().toString(), 100, 100, true, true);
            ImageView iv = new ImageView(img);
            iv.setCursor(Cursor.HAND);
            
            Tooltip.install(iv, new Tooltip("Click to remove image"));

            VBox container = new VBox(5);
            iv.setOnMouseClicked(e -> {
                items.remove(box);
                gallery.getChildren().remove(container);
            });

            container.setAlignment(Pos.CENTER);
            container.getChildren().addAll(iv, box.hintField);
            container.setUserData(box.path);
            container.setStyle("-fx-border-color: white; -fx-border-width: 1; -fx-border-radius: 5; -fx-padding: 5;");
            
            gallery.getChildren().add(container);
        } catch (Exception e) {
            System.err.println("Could not load image preview: " + e.getMessage());
        }
    }

    private static class ImageHintBox {
        String path;
        TextField hintField;

        ImageHintBox(String path) {
            this.path = path;
            this.hintField = new TextField();
            this.hintField.setPromptText("Hint");
            this.hintField.setPrefWidth(100);
        }
    }

    private static VBox labeled(String labelText, Control control) {
        Label label = new Label(labelText);
        label.setTextFill(Color.WHITE);
        label.setFont(Font.font("Noto Sans Thai", FontWeight.BOLD, 16));
        VBox box = new VBox(6, label, control);
        box.setAlignment(Pos.CENTER_LEFT);
        box.setMaxWidth(760);
        return box;
    }

    private static String safeTrim(String s) {
        return s == null ? "" : s.trim();
    }

    private void styleButton(Button button) {
        button.setBackground(new Background(new BackgroundImage(new Image(Objects.requireNonNull(Goto.class.getResourceAsStream("/choiceback.png"))),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(100, 100, true, true, true, false))));

        button.setOnMouseExited(mouseEvent -> {
            button.setBackground(new Background(new BackgroundImage(new Image(Objects.requireNonNull(Goto.class.getResourceAsStream("/choiceback.png"))),
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                    new BackgroundSize(100, 100, true, true, true, false))));
            button.setTextFill(Color.BLACK);
        });

        button.setOnMouseEntered(mouseEvent -> {
            button.setBackground(new Background(new BackgroundImage(new Image(Objects.requireNonNull(Goto.class.getResourceAsStream("/choiceback2.png"))),
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                    new BackgroundSize(100, 100, true, true, true, false))));
            button.setTextFill(Color.WHITE);
        });

        button.setFont(Font.font("Noto Sans Thai", FontWeight.BOLD, 20));
        button.setPrefWidth(300);
        button.setPrefHeight(80);
    }

    private Button createBackButton() {
        Button back = new Button("Back");
        styleButton(back);
        VBox.setMargin(back, new Insets(40, 0, 0, 0));

        back.setOnMouseClicked(mouseEvent -> {
            setBackButtonClicked(true);
            Goto.titleScreenPage();
        });

        return back;
    }

    public static Boolean getBackButtonClicked() {
        return backButtonClicked;
    }

    public static void setBackButtonClicked(Boolean backButtonClicked) {
        CustomPuzzlePane.backButtonClicked = backButtonClicked;
    }
}
