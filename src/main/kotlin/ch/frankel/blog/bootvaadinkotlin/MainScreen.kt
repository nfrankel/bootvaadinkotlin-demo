package ch.frankel.blog.bootvaadinkotlin

import com.vaadin.ui.*
import org.vaadin.spring.events.EventBus
import org.vaadin.spring.mvp.Presenter

class MainScreen() : CustomComponent() {

    fun setComponents(table: Table, button: Button) {
        table.setSizeFull()
        val layout = VerticalLayout(button, table).apply {
            setSpacing(true)
            setMargin(true)
            setSizeFull()
        }
        setCompositionRoot(layout)
    }
}

class MainScreenPresenter(tablePresenter: TablePresenter,
                          buttonPresenter: NotificationButtonPresenter,
                          view: MainScreen, eventBus: EventBus) : Presenter<MainScreen>(view, eventBus) {

    init {
        view.setComponents(tablePresenter.view, buttonPresenter.view)
    }
}