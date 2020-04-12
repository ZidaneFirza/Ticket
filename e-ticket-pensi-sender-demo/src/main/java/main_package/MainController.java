package main_package;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.effects.JFXDepthManager;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

public class MainController implements Initializable {

	JFXDepthManager depthManager;

	@FXML
	TextField firstNameField;
	@FXML
	TextField lastNameField;
	@FXML
	TextField emailAddressField;
	@FXML
	TextField phoneNumberField;
	@FXML
	Button sendButton;
	@FXML
	TextArea textArea;
	@FXML
	Pane pane;
	@FXML
	Pane pane1;
	@FXML
	ProgressIndicator pi;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		depthManager = new JFXDepthManager();

		depthManager.setDepth(pane1, 2);
		addTextAreaListener();

		textArea.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				sendAction();
			}
		});
	}

	@FXML
	public void sendAction() {
		if (fieldIsValid()) {

			pane.setVisible(true);

			Service<Void> backgroundThread = new Service<Void>() {
				@Override
				protected Task<Void> createTask() {
					return new Task<Void>() {

						@Override
						protected Void call() throws Exception {
							Customer customer = new Customer(firstNameField.getText(), lastNameField.getText(),
									emailAddressField.getText(), phoneNumberField.getText());
							TicketSender ticketSender = new TicketSender(customer);

							ticketSender.sendTicket();

							return null;
						}
					};
				}
			};

			backgroundThread.setOnSucceeded(new EventHandler<WorkerStateEvent>() {

				@Override
				public void handle(WorkerStateEvent event) {
					Platform.runLater(new Runnable() {

						@Override
						public void run() {
							firstNameField.clear();
							lastNameField.clear();
							emailAddressField.clear();
							phoneNumberField.clear();
							textArea.clear();
							pane.setVisible(false);
						}
					});
				}
			});

			backgroundThread.restart();

		}
	}

	private void addTextAreaListener() {
		textArea.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(final ObservableValue<? extends String> observable, final String oldValue,
					final String newValue) {
				String string = textArea.getText();
				if (!(string.trim().isEmpty())) {
					String[] parts = string.split("'");
					String[] parts2 = parts[3].split(" ", 2);
					firstNameField.setText(parts2[0]);
					lastNameField.setText(parts2[1]);
					emailAddressField.setText(parts[7]);
					phoneNumberField.setText(parts[11]);
				}
			}
		});
	}

	private boolean fieldIsValid() {
		if (firstNameField.getText().trim().isEmpty() || lastNameField.getText().trim().isEmpty()
				|| emailAddressField.getText().trim().isEmpty() || phoneNumberField.getText().trim().isEmpty()) {
			return false;
		}
		return true;
	}

}
