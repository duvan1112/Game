package presenters;

import views.JFMainWindow;

public class Presenter {

    private JFMainWindow window;

    public Presenter() {
        window = new JFMainWindow();
        window.setVisible(true);
    }

}
