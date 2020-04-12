package main_package;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MainController implements Initializable{
	@FXML
	TextField mainTextField;
	@FXML
	Label lbl;
	@FXML
	Label ticketLbl;

	public void initialize(URL location, ResourceBundle resources) {
		mainTextField.requestFocus();
		mainTextField.textProperty().addListener(new ChangeListener<String>() {
			public void changed(final ObservableValue<? extends String> observable, final String oldValue,
					final String newValue) {
				String ticket = mainTextField.getText();
				String[] parts = ticket.split(";");
				
				if (parts[1].length() == 6) {
					ticketLbl.setText("Ticket: " + parts[1]);
					TicketDao ticketDao = new TicketDao();
					if(ticketDao.ticketIsExist(ticket)) {
						if(ticketDao.ticketIsVerified(ticket)) {
							lbl.setStyle("-fx-text-fill: red;");
							lbl.setText("Ticket sudah digunakan");
							Platform.runLater(() -> {
								mainTextField.clear();
							});
						}else {
							TicketDao ticketDaoVerify = new TicketDao();
							ticketDaoVerify.verifyTicket(ticket);
							lbl.setStyle("-fx-text-fill: green; ");
							lbl.setText("Ticket sukses terverifikasi");
							Platform.runLater(() -> {
								mainTextField.clear();
							});
						}
					}else {
						lbl.setStyle("-fx-text-fill: red;");
						lbl.setText("Ticket gagal terverifikasi");
						Platform.runLater(() -> {
							mainTextField.clear();
						});
					}
				}

			}
		});
	}

}
