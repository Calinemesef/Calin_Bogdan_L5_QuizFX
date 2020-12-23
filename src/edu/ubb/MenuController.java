package ubb;

import javafx.fxml.FXML;

/**
 * Controller pentru Meniu
 */
public class MenuController {

	private Controller mainApp;

	public void setMainApp(Controller mainApp) {
		this.mainApp = mainApp;
	}

	/**
	 * Optiunile din bara de meniu
	 * @throws Exception
	 */
	@FXML private void loadNewGame() throws Exception{ mainApp.loadQuiz(); }
	@FXML private void quit() { System.exit(0); }
}
