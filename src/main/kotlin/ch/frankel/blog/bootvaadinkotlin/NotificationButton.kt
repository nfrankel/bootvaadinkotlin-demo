package ch.frankel.blog.bootvaadinkotlin

import com.vaadin.ui.*
import org.vaadin.spring.events.EventBus
import org.vaadin.spring.events.EventScope.APPLICATION
import org.vaadin.spring.events.annotation.EventBusListenerMethod
import org.vaadin.spring.mvp.Presenter

class NotificationButton: Button("Click me")

class NotificationButtonPresenter(view: NotificationButton, eventBus: EventBus): Presenter<NotificationButton>(view, eventBus) {

    init {
        view.addClickListener{ eventBus.publish(APPLICATION, "Hello from Spring Boot Vaadin in Kotlin") }
    }

    @EventBusListenerMethod
    fun showNotification(message: String) {
        Notification.show(message)
    }
}
